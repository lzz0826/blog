package com.sai.blog.web;


import com.sai.blog.Service.BlogsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller

public class ArchivesShowController {




    @Autowired
    public BlogsService blogsService;

@GetMapping("/archives")
   public String archivesShow (Model model){
    model.addAttribute("archiveMap",blogsService.archivesBlog());
    model.addAttribute("blogCount",blogsService.countBlog());


     return "archives";
   }


}
