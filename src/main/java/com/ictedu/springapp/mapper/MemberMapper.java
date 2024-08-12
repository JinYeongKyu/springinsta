package com.ictedu.springapp.mapper;

import com.ictedu.springapp.model.MemberDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {
    void insertMember(MemberDTO memberDTO);

    MemberDTO getUserById(String id);
}
