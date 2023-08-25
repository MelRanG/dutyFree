package com.asianaidt.dutyfree.domain.member.controller;

import com.asianaidt.dutyfree.domain.member.domain.Member;
import com.asianaidt.dutyfree.domain.member.dto.MemberRequestDto;
import com.asianaidt.dutyfree.domain.member.service.MemberService;
import com.asianaidt.dutyfree.domain.purchase.domain.Purchase;
import com.asianaidt.dutyfree.domain.purchase.domain.PurchaseDetail;
import com.asianaidt.dutyfree.domain.purchase.service.PurchaseService;
import com.asianaidt.dutyfree.global.error.StandardException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/member")
@Controller
public class MemberController {
    private final MemberService memberService;
    private final PurchaseService purchaseService;
    //    로그인 페이지 이동
    @GetMapping("/login")
    public String login(){
        return "ProductDetail";
    }

    //    로그인 로직
    @PostMapping("/login")
    public String login(HttpSession session, @RequestParam String id, @RequestParam String password, Model model){
        boolean success = memberService.login(id, password);
        System.out.println(success);
        if(success) {
            // 임시 로그인 로직
            session.setAttribute("member",new Member(id, password));
            model.addAttribute("userId", id);
        }
        return "test";
    }

    // 회원가입 페이지 이동
    @GetMapping("/signup")
    public String signUp(){
        return "signup";
    }

    @GetMapping("/check/id/{id}")
    public String checkId(@PathVariable String id, Model model){
        try{
            boolean success = memberService.checkId(id);
            model.addAttribute("checkId", success);
            System.out.println(success);
        }catch (StandardException e){
            model.addAttribute("error", e.getMessage());
        }
        return "test";
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
