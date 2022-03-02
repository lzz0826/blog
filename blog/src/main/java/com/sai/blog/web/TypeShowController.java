package com.sai.blog.web;


import com.sai.blog.Service.BlogsService;
import com.sai.blog.Service.TypeService;
import com.sai.blog.po.Type;
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
public class TypeShowController {



    @Autowired
    public TypeService typeService;

    @Autowired
    public BlogsService blogsService;




    @GetMapping("/types/{id}")
    public String typeShow (@PageableDefault(size = 8 ,sort ={"updateTime"} ,direction = Sort.Direction.DESC)
                            @PathVariable Long id, Model model , Pageable pageable){
        List<Type> types = typeService.listTypeTop(10000);
        if ( id == -1 ){
            id = types.get(0).getId();
        }
        BlogQuery blogQuery = new BlogQuery();
        blogQuery.setTypeId(id);
        model.addAttribute("types", types);
        model.addAttribute("page",blogsService.listBlog(pageable,blogQuery));
        model.addAttribute("activeTypeId",id);


        return "types";
    }

}
