package com.asianaidt.dutyfree.domain.member.repository;

import com.asianaidt.dutyfree.domain.member.domain.Passport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PassportRepository extends JpaRepository<Passport, Long> {
}
