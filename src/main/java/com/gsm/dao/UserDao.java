package com.gsm.dao;

import com.gsm.model.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao {
    User selectUser(Integer id);
}
