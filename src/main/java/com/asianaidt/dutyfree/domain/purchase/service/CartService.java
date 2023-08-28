package com.asianaidt.dutyfree.domain.purchase.service;

import com.asianaidt.dutyfree.domain.member.domain.Departure;
import com.asianaidt.dutyfree.domain.member.domain.Flight;
import com.asianaidt.dutyfree.domain.member.domain.Member;
import com.asianaidt.dutyfree.domain.member.domain.Passport;
import com.asianaidt.dutyfree.domain.member.dto.MemberResponseDto;
import com.asianaidt.dutyfree.domain.member.repository.DepartureRepository;
import com.asianaidt.dutyfree.domain.member.repository.FlightRepository;
import com.asianaidt.dutyfree.domain.member.repository.MemberRepository;
import com.asianaidt.dutyfree.domain.member.repository.PassportRepository;
import com.asianaidt.dutyfree.domain.purchase.dto.cart.DepartureDto;
import com.asianaidt.dutyfree.domain.purchase.dto.cart.PassportDto;
import com.asianaidt.dutyfree.global.error.StandardException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartService {
    private final PassportRepository passportRepository;
    private final DepartureRepository departureRepository;
    private final MemberRepository memberRepository;
    private final FlightRepository flightRepository;
    public void addPassportInfo(MemberResponseDto memberDto, PassportDto passportDto) {
        Optional<Member> member = memberRepository.findById(memberDto.getId());
        try {
            if(member.isPresent()) {
                Passport passport = passportDto.toEntity(member.get());
                passportRepository.save(passport);
            }
        } catch (Exception e) {
            throw new StandardException("여권정보를 추가하지 못했습니다.");
        }
    }

    public void addDepartureInfo(MemberResponseDto memberDto, DepartureDto departureDto) {
        Optional<Member> member = memberRepository.findById(memberDto.getId());

        try {
            if(member.isPresent()) {
                Departure departure = departureDto.toEntity(member.get());
                departureRepository.save(departure);
            }
        }
        catch (Exception e) {
            throw new StandardException("출국정보를 추가하지 못했습니다.");
        }
        
    }

    public List<Flight> getFlightList(String flightCode, String flightDate, String boarding) {
        return flightRepository.findByFlightCodeContainingAndFlightDateAndBoardingEngContaining(flightCode, flightDate, boarding);
    }
}
