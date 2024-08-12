package com.ictedu.springapp.controller;

import com.ictedu.springapp.model.MemberDTO;
import com.ictedu.springapp.service.MemberService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @PostMapping("/signup")
    public void signUp(@RequestBody MemberDTO memberDTO) {
        memberService.registerMember(memberDTO);
    }

    @PostMapping("/login")
    public String login(@RequestParam String id, @RequestParam String password, HttpSession session) {
        MemberDTO member = memberService.login(id, password);

        if (member != null) {
            session.setAttribute("member", member);
            return member.getId() + "님 환영합니다!";
        }
        else {
            return "아이디 또는 비밀번호가 올바르지 않습니다.";
        }
    }

    @PostMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "로그아웃 되었습니다.";
    }

    @GetMapping("/current-user")
    public MemberDTO getCurrentUser(HttpSession session) {
        MemberDTO member = (MemberDTO) session.getAttribute("member");
        if (member != null) {
            return member;
        }
        else {
            return null;
        }
    }

}
