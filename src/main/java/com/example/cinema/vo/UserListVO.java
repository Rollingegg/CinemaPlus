package com.example.cinema.vo;

import java.util.List;

public class UserListVO {
    private int privilegeLevel;

    private List<UserDetailVO> userList;

    public int getPrivilegeLevel() {
        return privilegeLevel;
    }

    public void setPrivilegeLevel(int privilegeLevel) {
        this.privilegeLevel = privilegeLevel;
    }

    public List<UserDetailVO> getUserList() {
        return userList;
    }

    public void setUserList(List<UserDetailVO> userList) {
        this.userList = userList;
    }
}
