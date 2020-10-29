package com.example.cinema.blImpl.user;

import com.example.cinema.bl.user.AccountService;
import com.example.cinema.testpack.CinemaTest;
import com.example.cinema.vo.UserForm;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class AccountServiceImplTest extends CinemaTest {

    @Autowired
    private AccountService accountService;

    @Override
    public void after() {
        System.out.println("OK!!!!");
    }

    @Test
    public void test1() {
        UserForm form = new UserForm();
        form.setUsername("test");
        form.setPassword("123456");
        form.setPrivilegeLevel(1);
        Assert.assertEquals("有错误！", true, accountService.checkPassword(form).getContent());
    }
}