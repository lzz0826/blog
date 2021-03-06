package com.sai.blog.Service;

import com.sai.blog.NotFoundException;
import com.sai.blog.dao.TagRepository;
import com.sai.blog.po.Tag;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;


@Service
public class TagsServiceImpl implements TagsService {

    @Autowired
    private TagRepository tagRepository;

    @Override
    public Tag getTagByName(String name) {
        return tagRepository.findByName(name);
    }

    @Transactional
    @Override
    public Tag saveTag(Tag tag) {
        return tagRepository.save(tag);
    }

    @Transactional
    @Override
    public Tag getTag(Long id) {
        return tagRepository.findOne(id);
    }

    @Transactional
    @Override
    public Page<Tag> listTag(Pageable pageable) {
        return tagRepository.findAll(pageable);
    }

    @Override
    public List<Tag> listTag() {
        return tagRepository.findAll();
    }

    @Override
    public List<Tag> listTagTop(Integer size) {
        Sort sort = new Sort(Sort.Direction.DESC,"blogs.size");
        Pageable pageable = new PageRequest(0,size,sort);
        return tagRepository.findTop(pageable);
    }


    @Override
    public List<Tag> listTag(String ids) {

        return tagRepository.findAll(convertToList(ids));
    }

    private List<Long> convertToList(String ids){
        List<Long> list = new ArrayList<>();
        if (!"".equals(ids) && ids !=null) {
        String[] idarray = ids.split(",");
        for (int i=0 ; i<idarray.length;i++) {
            list.add(new Long(idarray[i]));
        }

      }return list;

    }


    @Transactional
    @Override
    public Tag updateTag(Long id, Tag tag) {
        Tag t =tagRepository.findOne(id);
        if(t == null){
            throw new NotFoundException("???????????????");
        }
        BeanUtils.copyProperties(tag,t);
        return tagRepository.save(t);
    }

    @Transactional
    @Override
    public void deleteTag(Long id) {
        tagRepository.delete(id);

    }
}
