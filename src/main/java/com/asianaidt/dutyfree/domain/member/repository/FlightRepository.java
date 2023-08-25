package com.asianaidt.dutyfree.domain.member.repository;

import com.asianaidt.dutyfree.domain.member.domain.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {
}
