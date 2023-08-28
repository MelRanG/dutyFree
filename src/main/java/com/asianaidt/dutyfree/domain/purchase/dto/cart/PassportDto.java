package com.asianaidt.dutyfree.domain.purchase.dto.cart;

import com.asianaidt.dutyfree.domain.member.domain.Member;
import com.asianaidt.dutyfree.domain.member.domain.Passport;
import lombok.Getter;

@Getter
public class PassportDto {
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

    public Passport toEntity(Member member) {
        Passport passport = Passport.builder()
                .member(member)
                .korName(korName)
                .birth(birth)
                .gender(gender)
                .country(country)
                .lastName(lastName)
                .firstName(firstName)
                .passportNo(passportNo)
                .expirationDate(expirationDate)
                .email(email)
                .phone(phone)
                .build();

        return passport;
    }
}
