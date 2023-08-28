package com.asianaidt.dutyfree.domain.member.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "flightId")
    private Long id;
    private String flightDate;
    private String airlineEng;
    private String airlineKor;
    private String boardingEng;
    private String boardingKor;
    private String city;
    private String flightCode;
    private String flightStatus;
    private LocalDateTime regDate;
}
