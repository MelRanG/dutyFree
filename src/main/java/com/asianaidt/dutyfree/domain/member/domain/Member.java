package com.asianaidt.dutyfree.domain.member.domain;

import com.asianaidt.dutyfree.domain.purchase.domain.Purchase;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Entity
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "memberId")
    private Long id;
    private String name;
    private String password;
    private String phoneNumber;
    private String address;
    @OneToMany(mappedBy = "member")
    private List<Purchase> purchases;

}
