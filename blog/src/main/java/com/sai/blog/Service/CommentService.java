package com.sai.blog.Service;


import com.sai.blog.po.Comment;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CommentService {



    List<Comment> listCommentByBlogId (Long blogId);

    List<Comment> deleteComment (Long blogId);

    Comment saveComment (Comment comment);
}
