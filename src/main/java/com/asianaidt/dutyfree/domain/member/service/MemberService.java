package com.asianaidt.dutyfree.domain.member.service;

import com.asianaidt.dutyfree.domain.member.domain.Member;
import com.asianaidt.dutyfree.domain.member.rerpository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public void login(){
        Member member = Member.builder()
                .phoneNumber("123")
                .address("서울")
                .password("123")
                .name("곽두팔")
                .build();
        memberRepository.save(member);

        System.out.println(memberRepository.findById(member.getId()));
    }
}
