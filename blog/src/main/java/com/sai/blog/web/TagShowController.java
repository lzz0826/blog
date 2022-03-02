package com.sai.blog.web;


import com.sai.blog.Service.BlogsService;
import com.sai.blog.Service.TagsService;
import com.sai.blog.po.Tag;
import com.sai.blog.vo.BlogQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;


@Controller
public class TagShowController {


    @Autowired
    public TagsService tagsService;


    @Autowired
    public BlogsService blogsService;



@GetMapping("/tags/{id}")
public String tagShow(@PageableDefault(size = 8 , sort ={"updateTime"} , direction = Sort.Direction.DESC)
        Model model , @PathVariable Long id , Pageable pageable ){
    List<Tag> tags =tagsService.listTagTop(10000);
    if ( id==-1){
        id = tags.get(0).getId();
    }

    model.addAttribute("tags",tags);
    model.addAttribute("page",blogsService.listBlog(id,pageable));
    model.addAttribute("activeTagId",id);




    return "tags";

}

}
