package com.asianaidt.dutyfree.domain.member.service;

import com.asianaidt.dutyfree.domain.member.domain.Member;
import com.asianaidt.dutyfree.domain.member.dto.MemberRequestDto;
import com.asianaidt.dutyfree.domain.member.rerpository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;


    public void login(){
        Member member = Member.builder()
                .id("hihi")
                .phoneNumber("123")
                .birth("서울")
                .password("123")
                .name("곽두팔")
                .build();
        memberRepository.save(member);



        System.out.println(memberRepository.findById(member.getId()));
    }

    public boolean signUp(MemberRequestDto memberRequestDto){
        Member member = MemberRequestDto.toEntity(memberRequestDto);
        Optional<Member> member2 = memberRepository.findById(member.getId());
        if(member2.isPresent()) return false;
        memberRepository.save(member);
        return true;
    }
}
