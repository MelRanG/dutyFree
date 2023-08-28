package com.asianaidt.dutyfree.domain.member.repository;


import com.asianaidt.dutyfree.domain.member.domain.Departure;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartureRepository extends JpaRepository<Departure, Long> {
}
