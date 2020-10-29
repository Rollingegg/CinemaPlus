package com.example.cinema.data.user;

import com.example.cinema.po.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author huwen
 * @date 2019/3/23
 */
@Mapper
public interface AccountMapper {

    /**
     * 创建一个新的账号
     * @param username
     * @param password
     * @param privilegeLevel
     * @return
     */
    int createNewAccount(@Param("username") String username, @Param("password") String password, @Param("privilegeLevel") Integer privilegeLevel);

    /**
     * 根据用户名查找账号
     * @param username
     * @return
     */
    User getAccountByName(@Param("username") String username);

    int deleteAccountByName(@Param("username") String username);

    int deleteAccountByUserId(@Param("userId") Integer userId);

    User getAccountByUserId(@Param("userId") Integer userId);

    List<User> getUserListByPrivilegeLevel(@Param("privilegeLevel") Integer privilegeLevel);


}
