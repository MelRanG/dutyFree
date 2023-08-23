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


    public boolean login(String id, String password){
//        Member member = Member.builder()
//                .id("hihi")
//                .phoneNumber("123")
//                .birth("서울")
//                .password("123")
//                .name("곽두팔")
//                .build();
//        memberRepository.save(member);


        boolean a = false;
        Member member = memberRepository.findById(id).orElse(null);
        System.out.println(member);
        //사용자 자체가 없을 때 || 비밀번호가 다를 때
        if(member==null || !member.getPassword().equals(password)){
            return false;
        }
        return true;
    }

    public boolean checkId(String id){
        Member member = memberRepository.findById(id).orElse(null);
        //아이디가 중복되지 않으면
        if(member==null){
            return true;
        }
        return false;
    }

    public boolean signUp(MemberRequestDto memberRequestDto){
        Member member = MemberRequestDto.toEntity(memberRequestDto);
        Optional<Member> member2 = memberRepository.findById(member.getId());
        if(member2.isPresent()) return false;
        memberRepository.save(member);
        return true;
    }
}
