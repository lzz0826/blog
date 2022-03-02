package com.sai.blog.dao;

import com.sai.blog.Service.UserService;
import com.sai.blog.po.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {


    User findByUsernameAndPassword(String username, String password );

    User findByUsername(String username);

    User findByPassword(String password);



}
