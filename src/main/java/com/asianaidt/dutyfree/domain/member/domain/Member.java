package com.asianaidt.dutyfree.domain.member.domain;

import com.asianaidt.dutyfree.domain.purchase.domain.Purchase;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Entity
public class Member {
    @Id
    @Column(name = "memberId")
    private String id;
    private String name;
    private String password;
    private String phoneNumber;
    private LocalDate birth;
    @OneToMany(mappedBy = "member")
    private List<Purchase> purchases;
    @OneToOne(mappedBy = "member")
    @JoinColumn(name = "passportId")
    private Passport passport;
    @OneToOne(mappedBy = "member")
    @JoinColumn(name = "departureId")
    private Departure departure;

    public Member(String id, String password) {
        this.id = id;
        this.password = password;
    }
}
