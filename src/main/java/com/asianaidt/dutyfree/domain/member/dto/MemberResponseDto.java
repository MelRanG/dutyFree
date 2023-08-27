package com.asianaidt.dutyfree.domain.member.dto;

import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberResponseDto {
    private String id;
    private String name;
}
