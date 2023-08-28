package com.asianaidt.dutyfree.domain.purchase.dto.cart;

import com.asianaidt.dutyfree.domain.member.domain.Departure;
import com.asianaidt.dutyfree.domain.member.domain.Member;
import lombok.Getter;

@Getter
public class DepartureDto {
    private long flightId;
    private String flightDate;
    private String boarding;
    private String airline;

    public Departure toEntity(Member member) {
        Departure departure = Departure.builder()
                .member(member)
                .flightDate(flightDate)
                .boarding(boarding)
                .airline(airline)
                .build();

        return departure;
    }
}
