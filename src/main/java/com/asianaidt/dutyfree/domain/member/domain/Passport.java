package com.asianaidt.dutyfree.domain.member.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Passport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "passportId")
    private Long id;
    private String korName;
    private String birth;
    private String gender;
    private String country;
    private String lastName;
    private String firstName;
    private String passportNo;
    private String expirationDate;
    private String email;
    private String phone;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "memberId")
    private Member member;
    private LocalDateTime regDate;

}
