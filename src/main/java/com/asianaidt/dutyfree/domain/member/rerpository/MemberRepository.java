package com.asianaidt.dutyfree.domain.member.rerpository;

import com.asianaidt.dutyfree.domain.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, String> {
}
