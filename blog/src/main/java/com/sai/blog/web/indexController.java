package com.sai.blog.web;

import com.sai.blog.NotFoundException;
import com.sai.blog.Service.BlogsService;
import com.sai.blog.Service.TagsService;
import com.sai.blog.Service.TypeService;
import com.sai.blog.po.Blog;
import com.sai.blog.vo.BlogQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller


public class indexController {

    @Autowired
    private BlogsService blogsService;

    @Autowired
    private TagsService tagsService;

    @Autowired
    TypeService typeService;


    @GetMapping("/")
    public String index (@PageableDefault(size = 5 ,sort = {"updateTime"} ,direction = Sort.Direction.DESC)
                                     Pageable pageable,  Model model){
        model.addAttribute("page",blogsService.listBlog(pageable));
        model.addAttribute("types",typeService.listTypeTop(6));
        model.addAttribute("tags",tagsService.listTagTop(10));
        model.addAttribute("recommendBlogs",blogsService.listBlogTop(8));

        return "index";
    }

    @PostMapping("/search")
    public String search (@PageableDefault(size = 5 ,sort = {"updateTime"} ,direction = Sort.Direction.DESC)
                                  @RequestParam String query, Pageable pageable, Model model){
        model.addAttribute("page",blogsService.listBlog("%"+query+"%",pageable));
        model.addAttribute("query",query);
        return "search";
    }




    @GetMapping("/blog/{id}")
    public String blog (@PathVariable Long id , Model model){
        model.addAttribute("blog",blogsService.getAndConvert(id));
        return "blog";
    }


    @GetMapping("/footer/newblog")
    public String newBlogList(Model model) {
        model.addAttribute("newblogs",blogsService.listBlogTop(3));
        return "_Fragments :: newblogList";
    }


}
