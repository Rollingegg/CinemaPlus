package com.example.cinema.blImpl.user;

import com.example.cinema.bl.user.AccountService;
import com.example.cinema.blImpl.promotion.VIPServiceForBl;
import com.example.cinema.blImpl.sales.ConsumptionServiceForBL;
import com.example.cinema.data.user.AccountMapper;
import com.example.cinema.po.User;
import com.example.cinema.po.VIPCard;
import com.example.cinema.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author huwen
 * @date 2019/3/23
 */
@Service
public class AccountServiceImpl implements AccountService {
    private final static String BUILD_FAILURE = "出错";
    private final static String ACCOUNT_EXIST = "账号已存在";
    private static final String ILLEGAL_DELETION = "root账户不能被删除";
    private static final String DELETE_FAILURE = "删除失败";
    private static final String CHECK_FAILURE = "密码错误";

    private static final int USER_LEVEL = 1;
    @Autowired
    private AccountMapper accountMapper;
    @Autowired
    private ConsumptionServiceForBL consumptionService;
    @Autowired
    private VIPServiceForBl vipService;

    @Override
    public ResponseVO registerAccount(UserForm userForm) {
        try {
            accountMapper.createNewAccount(userForm.getUsername(), userForm.getPassword(), 1);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure(ACCOUNT_EXIST);
        }
        return ResponseVO.buildSuccess();
    }

    @Override
    public UserVO login(UserForm userForm) {
        User user = accountMapper.getAccountByName(userForm.getUsername());
        if (null == user || !user.getPassword().equals(userForm.getPassword())) {
            return null;
        }
        return new UserVO(user);
    }

    @Override
    public ResponseVO createAccount(UserForm userForm) {
        try {
            accountMapper.createNewAccount(userForm.getUsername(), userForm.getPassword(), userForm.getPrivilegeLevel());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure(ACCOUNT_EXIST);
        }
        return ResponseVO.buildSuccess();
    }


    @Override
    public ResponseVO getUserDetailByUserId(Integer userId) {
        UserDetailVO user;
        try {
            user = getUserDetailVO(userId);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure(e.getMessage());
        }
        return ResponseVO.buildSuccess(user);
    }

    private UserDetailVO getUserDetailVO(int userId) throws Exception {
        UserDetailVO user = new UserDetailVO();
        User userTemp = accountMapper.getAccountByUserId(userId);
        user.setUserId(userId);
        user.setUsername(userTemp.getUsername());
        user.setPrivilegeLevel(userTemp.getPrivilgeLevel());
        if (user.getPrivilegeLevel() == 1) {
            user.setTotalConsumption(consumptionService.totalConsumption(userId));
            VIPCard card = vipService.getVIPCardByUserId(userId);
            if (card == null) {
                user.setValidVIP(false);
                user.setVipBalance(0.0);
            } else {
                user.setValidVIP(true);
                user.setVipBalance(card.getBalance());
            }
        }
        return user;
    }

    private UserDetailVO getUserDetailVO(User user) throws Exception {
        return getUserDetailVO(user.getId());
    }

    @Override
    public ResponseVO deleteUser(Integer userId) {
        try {
            if (accountMapper.getAccountByUserId(userId).getUsername().equals("root")) {
                return ResponseVO.buildFailure(ILLEGAL_DELETION);
            }
            accountMapper.deleteAccountByUserId(userId);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure(DELETE_FAILURE);
        }
        return ResponseVO.buildSuccess();
    }

    @Override
    public ResponseVO checkPassword(UserForm userForm) {
        try {
            User user = accountMapper.getAccountByName(userForm.getUsername());
            if (userForm.getPassword().equals(user.getPassword())) {
                return ResponseVO.buildSuccess(true);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure(e.getMessage());
        }
        return ResponseVO.buildFailure(CHECK_FAILURE);
    }

    @Override
    public ResponseVO getUserList() {
        List<UserListVO> userList = new ArrayList<>();
        try {
            for (int i = 0; i < 3; ++i) {
                UserListVO vo = new UserListVO();
                vo.setPrivilegeLevel(i);
                List<UserDetailVO> voList = new ArrayList<>();
                List<User> userTempList = accountMapper.getUserListByPrivilegeLevel(i);
                for (User u : userTempList)
                    voList.add(getUserDetailVO(u.getId()));
                vo.setUserList(voList);
                userList.add(vo);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure(e.getMessage());
        }
        return ResponseVO.buildSuccess(userList);
    }

    @Override
    public ResponseVO getUserListByTargetAmount(double amount) {
        List<UserDetailVO> userList = new ArrayList<>();
        try {
            List<User> userTemp = accountMapper.getUserListByPrivilegeLevel(USER_LEVEL);
            userTemp.removeIf(user -> consumptionService.totalConsumption(user.getId()) < amount);
            for (User user : userTemp) {
                userList.add(getUserDetailVO(user));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure(BUILD_FAILURE);
        }
        return ResponseVO.buildSuccess(userList);
    }


}
