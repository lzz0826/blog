package com.sai.blog.Service;


import com.sai.blog.po.User;

public interface UserService {


    User checkUser(String username,String password);

    User saveUser(User user );

    User getUser(Long id);

    User getUserName(String username );

    User getPassword(String password);


}
