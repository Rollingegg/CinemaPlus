package com.example.cinema.controller.user;

import com.example.cinema.blImpl.user.AccountServiceImpl;
import com.example.cinema.config.InterceptorConfiguration;
import com.example.cinema.po.User;
import com.example.cinema.vo.UserForm;
import com.example.cinema.vo.ResponseVO;
import com.example.cinema.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * @author huwen
 * @date 2019/3/23
 *
 * Updated by zhihao li on 2019/6/8
 */
@RestController()
public class AccountController {
    private final static String ACCOUNT_INFO_ERROR="用户名或密码错误";
    @Autowired
    private AccountServiceImpl accountService;
    @PostMapping("/login")
    public ResponseVO login(@RequestBody UserForm userForm, HttpSession session){
        UserVO user = accountService.login(userForm);
        if(user==null){
           return ResponseVO.buildFailure(ACCOUNT_INFO_ERROR);
        }
        //注册session
        int level = user.getPrivilegeLevel();
        userForm.setPrivilegeLevel(level);
        session.setAttribute(InterceptorConfiguration.SESSION_KEY,userForm);
        return ResponseVO.buildSuccess(user);
    }
    @PostMapping("/register")
    public ResponseVO registerAccount(@RequestBody UserForm userForm){
        return accountService.registerAccount(userForm);
    }

    @PostMapping("/logout")
    public String logOut(HttpSession session){
        session.removeAttribute(InterceptorConfiguration.SESSION_KEY);
        return "index";
    }

    @PostMapping("/account/create")
    public ResponseVO createAccount(@RequestBody UserForm userForm) {
        return accountService.createAccount(userForm);
    }

    @GetMapping("/account/detail")
    public ResponseVO getUserDetail(@RequestParam Integer userId) {
        return accountService.getUserDetailByUserId(userId);
    }

    @GetMapping("/account/delete")
    public ResponseVO deleteUser(@RequestParam Integer userId) {
        return accountService.deleteUser(userId);
    }

    @PostMapping("/account/check/password")
    public ResponseVO checkPassword(@RequestBody UserForm userForm) {
        return accountService.checkPassword(userForm);
    }

    @GetMapping("/account/list")
    public ResponseVO getUserList() {
        return accountService.getUserList();
    }
}
