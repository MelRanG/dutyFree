package com.asianaidt.dutyfree.domain.member.controller;

import com.asianaidt.dutyfree.domain.member.dto.MemberRequestDto;
import com.asianaidt.dutyfree.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/member")
@Controller
public class MemberController {
    private final MemberService memberService;

    //    로그인 페이지 이동
    @GetMapping("/login")
    public String login(){
        return "login";
    }

    //    로그인 로직
    @PostMapping("/login")
    public String login(@RequestParam String id, @RequestParam String password, Model model){
        boolean success = memberService.login(id, password);
        System.out.println(success);
        model.addAttribute("userId", id);
        return "test";
    }

    // 회원가입 페이지 이동
    @GetMapping("/signup")
    public String signUp(){
        return "signup";
    }

    @GetMapping("/check/id/{id}")
    public String checkId(@PathVariable String id, Model model){
        boolean success = memberService.checkId(id);
        model.addAttribute("checkId", success);
        System.out.println(success);
        return "test";
    }

    //회원가입 로직
    @PostMapping("/signup")
    public String signUp(MemberRequestDto memberRequestDto){
        System.out.println(memberRequestDto);
        boolean test = memberService.signUp(memberRequestDto);
        return "test";
    }
}
