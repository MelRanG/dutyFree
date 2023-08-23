package com.asianaidt.dutyfree.domain.purchase.domain;

import com.asianaidt.dutyfree.domain.member.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Entity
public class Purchase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "purchaseId")
    private Long id;
    private String address;
    private int totalAmount;
    @CreationTimestamp
    @Column(name = "regDate", nullable = false)
    private LocalDateTime regDate;
    @ManyToOne
    @JoinColumn(name = "memberId")
    private Member member;
    @OneToMany(mappedBy = "purchase", cascade = CascadeType.ALL)
    private List<PurchaseDetail> purchaseDetails;
}
