package com.sai.blog.web.admin;


import com.sai.blog.Service.UserServiceImpl;
import com.sai.blog.po.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")

public class LoginController {



    @Autowired
    private UserServiceImpl userServicelmpl;


    @GetMapping
    public String logPage(){

     return "admin/login";

    }
    @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        HttpSession session,
                        RedirectAttributes attributes){

        User user =userServicelmpl.checkUser(username,password);
        if (user !=null){
            user.setPassword(null);
            session.setAttribute("user",user);
            return "admin/blogs-index";

        }else {
            attributes.addFlashAttribute("message","用戶名.密碼錯誤");
            return "redirect:/admin";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session){

        session.removeAttribute("user");
        return "redirect:/admin";

    }



}
