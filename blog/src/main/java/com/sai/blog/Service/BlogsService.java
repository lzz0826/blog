package com.sai.blog.Service;

import com.sai.blog.po.Blog;
import com.sai.blog.vo.BlogQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;


public interface BlogsService {




    Blog getBlog(Long id);

    Blog getAndConvert(Long id);


    Page<Blog> listBlog(Pageable pageable, BlogQuery blog);

    Page<Blog> listBlog(Pageable pageable);

    Page<Blog> listBlog(Long tagId , Pageable pageable);

    Page<Blog> listBlog(String query , Pageable pageable);

    List<Blog> listBlogTop(Integer size);

    Map<String,List<Blog>> archivesBlog();

    Long countBlog();

    Blog saveBlog(Blog blog);

    Blog updateBlog(Long id,Blog blog);

    boolean isTypeIdContainsBlogs(Long id);

    boolean isTagIdContainBlogs(Long id);

    void deleteBlog(Long id);
}
