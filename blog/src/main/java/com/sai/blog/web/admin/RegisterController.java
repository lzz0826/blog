package com.sai.blog.web.admin;


import com.sai.blog.Service.UserService;
import com.sai.blog.po.User;
import com.sai.blog.util.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("admin")
public class RegisterController {
    @Autowired
    private UserService userService;

    @GetMapping("/register")
    private  String input(Model model) {
        model.addAttribute("users" ,new User());
        return "admin/register";
    }

    @PostMapping("register")
    private String post(@Valid User user , BindingResult result , RedirectAttributes attributes ){
        User user1=userService.getUserName(user.getUsername());
        if (user1 != null){
            attributes.addFlashAttribute("errormessage","輸入帳號已有人使用");
            return "redirect:/admin/register";
        }

        if (result.hasErrors()){

        }

        User u =userService.saveUser(user);



        if (u == null){
            attributes.addFlashAttribute("message","新增失敗");
        }else {
            attributes.addFlashAttribute("message","新增成功");
        }
        return "redirect:/admin/register";





    }



}
