package com.ictedu.springapp.service;

import com.ictedu.springapp.mapper.MemberMapper;
import com.ictedu.springapp.model.MemberDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

    @Autowired
    private MemberMapper memberMapper;

    public void registerMember(MemberDTO memberDTO) {
        memberMapper.insertMember(memberDTO);
    }

    public MemberDTO login(String id, String password) {
        MemberDTO member = memberMapper.getUserById(id);
        if (member != null && member.getPassword().equals(password)) {
            return member;
        }
        return null;
    }
}
