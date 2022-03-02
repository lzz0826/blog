package com.sai.blog.web;


import com.sai.blog.Service.BlogsService;
import com.sai.blog.Service.BlogsServicelmpl;
import com.sai.blog.Service.CommentService;
import com.sai.blog.Service.CommentServicelmpl;
import com.sai.blog.po.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private BlogsService blogsService;


    @Value("${comment.avatar}")
    private String avatar;


    @GetMapping("/comments/{blogId}")
    public String comments(@PathVariable Long blogId , Model model){
        model.addAttribute("comments",commentService.listCommentByBlogId(blogId));
        return "blog :: commentList";
    }



    @PostMapping("/comments")
    public String post(Comment comment){
        Long blogId =comment.getBlog().getId();
        comment.setBlog(blogsService.getBlog(blogId));
        comment.setAvatar(avatar);
        commentService.saveComment(comment);
        return "redirect:/comments/" + blogId;
    }


}
