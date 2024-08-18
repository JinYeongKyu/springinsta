package com.ictedu.springapp.service;

import com.ictedu.springapp.mapper.PostMapper;
import com.ictedu.springapp.model.PostDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    @Autowired
    private PostMapper postMapper;

    public void createPost(PostDTO postDTO) {
        postMapper.createPost(postDTO);
    }

    public List<PostDTO> getAllPosts() {
        return postMapper.getAllPost();
    }
}
