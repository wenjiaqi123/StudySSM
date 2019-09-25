package com.gsm.service.impl;

import com.gsm.dao.UserDao;
import com.gsm.model.User;
import com.gsm.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserDao userDao;

    @Override
    public User selectUser(Integer id) {
        User user = userDao.selectUser(id);
        return user==null?new User():user;
    }
}
