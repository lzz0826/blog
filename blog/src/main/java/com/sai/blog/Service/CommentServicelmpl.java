package com.sai.blog.Service;

import com.sai.blog.dao.CommentRepository;
import com.sai.blog.po.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;


@Service
public class CommentServicelmpl implements CommentService{


    @Autowired
    CommentRepository commentRepository;


    @Override
    public List<Comment> listCommentByBlogId(Long blogId) {
        Sort sort = new Sort(Sort.Direction.DESC,"createTime");
        return commentRepository.findByBlogId(blogId,sort);
    }

    @Override
    public List<Comment> deleteComment(Long blogId) {
        List c1 =commentRepository.findByBlogId(blogId);
        commentRepository.delete(c1);
        return c1;
    }

    @Transactional
    @Override
    public Comment saveComment(Comment comment) {
        Long parentCommentId = comment.getParentcomment().getId();
        if (parentCommentId != -1 ){
            comment.setParentcomment(commentRepository.findOne(parentCommentId));
        }else {
            comment.setParentcomment(null);
        }
        comment.setCreateTime(new Date());
        return commentRepository.save(comment);
    }
}
