package com.asianaidt.dutyfree.domain.member.service;

import com.asianaidt.dutyfree.domain.member.domain.Flight;
import com.asianaidt.dutyfree.domain.member.domain.Member;
import com.asianaidt.dutyfree.domain.member.dto.MemberRequestDto;
import com.asianaidt.dutyfree.domain.member.repository.FlightRepository;
import com.asianaidt.dutyfree.domain.member.rerpository.MemberRepository;
import com.asianaidt.dutyfree.global.error.StandardException;
import com.asianaidt.dutyfree.global.util.PasswordEncoder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.XML;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberService {
    private final MemberRepository memberRepository;
    private final FlightRepository flightRepository;

    public Member login(String id, String password){
        Member member = memberRepository.findById(id).orElseThrow(() -> new RuntimeException("아이디가 일치하지 않습니다."));
        if(!Objects.equals(member.getPassword(), PasswordEncoder.encode(password))) throw new RuntimeException("비밀번호가 일치하지 않습니다.");
        return member;
    }

    public boolean checkId(String id){
        Member member = memberRepository.findById(id).orElse(null);
        //아이디가 중복되지 않으면
        return member == null;
    }

    public boolean signUp(MemberRequestDto memberRequestDto){
        try{
            Member member = MemberRequestDto.toEntity(memberRequestDto);
            memberRepository.save(member);
            return true;
        }catch (Exception e){
            log.error(e.getMessage());
            return false;
        }
    }

    public void addFlightInfo() {
        for (int no = 1; no <= 10; no++) {
            try {
                StringBuilder urlBuilder = new StringBuilder("http://openapi.airport.co.kr/service/rest/FlightStatusList/getFlightStatusList"); /*URL*/
                String serviceKey = "SGv0JWXsrgZ3gYFyfLFe%2FfWKwsz0UU9Yr3MWrW7YZASyFZkaWRpGZElEHPwaCWobrghM4a1JmCyer60D5am%2BgQ%3D%3D";

                urlBuilder.append("?").append(URLEncoder.encode("serviceKey", StandardCharsets.UTF_8)).append("=").append(serviceKey)
                        .append("&").append(URLEncoder.encode("pageNo", StandardCharsets.UTF_8)).append("=").append(no); /*Service Key*/
                URL url = new URL(urlBuilder.toString());
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();

                conn.setRequestMethod("GET");
                conn.setRequestProperty("Content-type", "application/json");
                System.out.println("Response code: " + conn.getResponseCode());

                BufferedReader rd;
                if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
                    rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                } else {
                    rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
                }

                StringBuilder sb = new StringBuilder();
                String line;
                while ((line = rd.readLine()) != null) {
                    sb.append(line);
                }

                rd.close();
                conn.disconnect();
                String output = "";

                try {
                    JSONObject jObject = XML.toJSONObject(sb.toString());
                    JSONObject itemObject = (JSONObject) ((JSONObject) jObject.get("response")).get("body");
                    JSONArray jsonArray = (JSONArray) ((JSONObject) itemObject.get("items")).get("item");

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject object = (JSONObject) jsonArray.get(i);
                        String flightDate = (String) object.get("flightDate");
                        String airFln = (String) object.get("airFln");
                        String boardingEng = (String) object.get("boardingEng");
                        String rmkEng = (String) object.get("rmkEng");
                        String boardingKor = (String) object.get("boardingKor");
                        String airlineEng = (String) object.get("airlineEnglish");
                        String airlineKor = (String) object.get("airlineKorean");
                        String city = (String) object.get("city");

                        Flight flight = Flight.builder()
                                .flightDate(flightDate)
                                .boardingEng(boardingEng)
                                .boardingKor(boardingKor)
                                .flightCode(airFln)
                                .airlineEng(airlineEng)
                                .airlineKor(airlineKor)
                                .flightStatus(rmkEng)
                                .city(city)
                                .build();

                        flightRepository.save(flight);
                    }

                } catch (Exception e) {
                    log.info(e.getMessage());
                }


            } catch (IOException e) {
                throw new StandardException("API에 연결할 수 없습니다.");
            }
        }

    }

}
