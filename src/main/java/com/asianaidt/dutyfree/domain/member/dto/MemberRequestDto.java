package com.asianaidt.dutyfree.domain.member.dto;

import com.asianaidt.dutyfree.domain.member.domain.Member;
import com.asianaidt.dutyfree.global.util.PasswordEncoder;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@ToString
public class MemberRequestDto {
    private String id;
    private String name;
    private String password;
    private String phoneNumber;
    private LocalDate birth;

    public static Member toEntity(MemberRequestDto dto){
        return Member.builder()
                .id(dto.id)
                .birth(dto.birth)
                .phoneNumber(dto.phoneNumber)
                .name(dto.name)
                .password(PasswordEncoder.encode(dto.password))
                .build();
    }
}
