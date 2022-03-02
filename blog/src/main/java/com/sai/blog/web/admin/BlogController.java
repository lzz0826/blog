package com.sai.blog.web.admin;


import com.sai.blog.Service.BlogsService;
import com.sai.blog.Service.CommentService;
import com.sai.blog.Service.TagsService;
import com.sai.blog.Service.TypeService;
import com.sai.blog.po.Blog;
import com.sai.blog.po.User;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class BlogController {

    private static final String INPUT ="admin/blogs-input";
    private static final String LIST ="admin/blogs";
    private static final String REDIRECT_LIST ="redirect:/admin/blogs";


    @Autowired
    private CommentService commentService;

    @Autowired
    private BlogsService blogsService;

    @Autowired
    private TypeService typeService;

    @Autowired
    private TagsService tagsService;


    @GetMapping("/blogs")
    public String blogs(@PageableDefault(size = 5 ,sort = {"updateTime"} ,direction = Sort.Direction.DESC)
                                    Pageable pageable, BlogQuery blog, Model model){
        model.addAttribute("types",typeService.listType());
        model.addAttribute("page",blogsService.listBlog(pageable,blog));
        return LIST;

    }

    @PostMapping("/blogs/search")
    public String search(@PageableDefault(size = 5 ,sort = {"updateTime"} ,direction = Sort.Direction.DESC) Pageable pageable,
                         BlogQuery blog, Model model){
        model.addAttribute("page",blogsService.listBlog(pageable,blog));
        return "admin/blogs :: blogList";

    }


    @GetMapping("/blogs/input")
    public String input(Model model){
        setTypeAndTag(model);
        model.addAttribute("blog",new Blog());

      return INPUT;
    };

    private void setTypeAndTag(Model model){
        model.addAttribute("types",typeService.listType());
        model.addAttribute("tags",tagsService.listTag());
    }


    @GetMapping("/blogs/{id}/input")
    public String editInput(@PathVariable Long id, Model model){
        setTypeAndTag(model);
        Blog blog = blogsService.getBlog(id);
        blog.init();
        model.addAttribute("blog",blog);

        return INPUT;
    };

    @PostMapping("/blogs")
    public String post(Blog blog , RedirectAttributes attributes, HttpSession session ){
        blog.setUser((User) session.getAttribute("user"));
        blog.setType(typeService.getType(blog.getType().getId()));
        blog.setTags(tagsService.listTag(blog.getTagIds()));
        Blog b ;
        if (blog.getId() == null){
            b = blogsService.saveBlog(blog);
        }else {
            b = blogsService.updateBlog(blog.getId(),blog);
        }

        if (b == null){
            attributes.addFlashAttribute("message","新增失敗");
        }else {
            attributes.addFlashAttribute("message","新增成功");
        }
        return REDIRECT_LIST;
    };

    @GetMapping("blogs/{id}/delete")
    private String delete(@PathVariable Long id , RedirectAttributes attributes ){
        attributes.addFlashAttribute("message","刪除成功");
        commentService.deleteComment(id);
        blogsService.deleteBlog(id);
        return REDIRECT_LIST;
    }

}
