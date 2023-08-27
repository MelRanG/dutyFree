package com.asianaidt.dutyfree.domain.member.controller;

import com.asianaidt.dutyfree.domain.member.domain.Member;
import com.asianaidt.dutyfree.domain.member.dto.MemberRequestDto;
import com.asianaidt.dutyfree.domain.member.dto.MemberResponseDto;
import com.asianaidt.dutyfree.domain.member.service.MemberService;
import com.asianaidt.dutyfree.domain.purchase.domain.Purchase;
import com.asianaidt.dutyfree.domain.purchase.domain.PurchaseDetail;
import com.asianaidt.dutyfree.domain.purchase.service.PurchaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
@RequestMapping("/member")
@Controller
public class MemberController {
    private final MemberService memberService;
    private final PurchaseService purchaseService;
    //    로그인 페이지 이동
    @GetMapping("/login")
    public String login(){
        return "Login";
    }

    //    로그인 로직
    @PostMapping("/login")
    public String login(HttpSession session, @RequestParam String id, @RequestParam String password, Model model){
        Optional<Member> member = memberService.login(id, password);

        if(member.isPresent()) {
            MemberResponseDto responseDto = MemberResponseDto.builder()
                    .id(member.get().getId())
                    .name(member.get().getName())
                    .build();
            session.setAttribute("user", responseDto);
        }

        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

    // 회원가입 페이지 이동
    @GetMapping("/signup")
    public String signUp(){
        return "SignUp";
    }

    @GetMapping("/check/id/{id}")
    @ResponseBody
    public Map<String, Boolean> checkId(@PathVariable String id){
        boolean result = memberService.checkId(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("result", result);
        System.out.println(result);
        return response;
    }

    //회원가입 로직
    @PostMapping("/signup")
    public String signUp(MemberRequestDto memberRequestDto){
        System.out.println(memberRequestDto);
        boolean test = memberService.signUp(memberRequestDto);
        return "test";
    }

    @GetMapping("/purchase")
    public String getUserPurchase(HttpSession session, Model model) {
        Member member = (Member) session.getAttribute("member");
        List<Purchase> purchaseList = purchaseService.getPurchaseList(member.getId());
        model.addAttribute("purchaseList", purchaseList);

        return "";
    }

    @GetMapping("/purchase/{purchaseId}")
    public String getPurchaseDetail(HttpSession session, @PathVariable long purchaseId, Model model) {
        List<PurchaseDetail> details = purchaseService.getPurchase(purchaseId);
        model.addAttribute("details", details);
        return "test";
    }

}
