package com.asianaidt.dutyfree.domain.member.controller;

import com.asianaidt.dutyfree.domain.member.dto.MemberRequestDto;
import com.asianaidt.dutyfree.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@RequestMapping("/member")
@Controller
public class MemberController {
    private final MemberService memberService;

    @PostMapping()
    public String login(){
        memberService.login();
        return "test";
    }

    @GetMapping("/signup")
    public String signUp(){
        return "signup";
    }

    @PostMapping("/signup")
    public String signUp(MemberRequestDto memberRequestDto){
        System.out.println(memberRequestDto);
        boolean test = memberService.signUp(memberRequestDto);
        return "test";
    }
}
