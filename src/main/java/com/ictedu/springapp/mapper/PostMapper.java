package com.ictedu.springapp.mapper;

import com.ictedu.springapp.model.PostDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PostMapper {

    void createPost(PostDTO postDTO);

    List<PostDTO> getAllPost();
}
