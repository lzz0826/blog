package com.sai.blog.Service;


import com.sai.blog.NotFoundException;
import com.sai.blog.dao.UserRepository;
import com.sai.blog.po.User;
import com.sai.blog.util.MD5Utils;
import org.aspectj.lang.annotation.AfterReturning;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    public UserRepository userRepository;


    @Override
    public User checkUser(String username, String password) {

        User user= userRepository.findByUsernameAndPassword(username, MD5Utils.code(password));

        return user;
    }

    @Override
    public User saveUser(User user ) {

        user.setPassword(MD5Utils.code(user.getPassword()));
        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());
        return userRepository.save(user);
    }



    @Override
    public User getUser(Long id) {
        return userRepository.findOne(id);
    }

    @Override
    public User getUserName(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User getPassword(String password) {
        return userRepository.findByPassword(password);
    }


}
