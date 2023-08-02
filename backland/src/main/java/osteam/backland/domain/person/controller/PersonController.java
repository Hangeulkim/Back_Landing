package osteam.backland.domain.person.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import osteam.backland.domain.person.controller.request.PersonCreateRequest;
import osteam.backland.domain.person.controller.response.PersonResponse;
import osteam.backland.domain.person.service.PersonCreateService;
import osteam.backland.domain.person.service.PersonSearchService;
import osteam.backland.domain.person.service.PersonUpdateService;

import java.util.List;

/**
 * PersonController
 * 등록 수정 검색 구현
 * <p>
 * 등록 - 입력된 이름과 전화번호를 person에 저장
 * 수정 - 이미 등록된 전화번호가 입력될 경우 해당 전화번호의 소유주 이름을 변경
 * 검색 - 전체 출력, 이름으로 검색, 전화번호로 검색 구현, 검색은 전부 OneToMany 테이블로 진행.
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/person")
public class PersonController {

    private PersonCreateService personCreateService;
    private PersonUpdateService personUpdateService;
    private PersonSearchService personSearchService;

    /**
     * 등록 기능
     * personRequest를 service에 그대로 넘겨주지 말 것.
     *
     * @param personCreateRequest
     * @return 성공 시 이름 반환
     */
    @PostMapping
    public String person(PersonCreateRequest personCreateRequest) {
        return personCreateRequest.getName();
    }

    /**
     * 전체 검색 기능
     */
    @GetMapping
    public ResponseEntity<List<PersonResponse>> getPeople() {
        return null;
    }

    /**
     * 이름으로 검색
     *
     * @param name
     */
    @GetMapping("/name")
    public ResponseEntity<List<PersonResponse>> getPeopleByName(String name) {
        return null;
    }

    /**
     * 번호로 검색
     *
     * @param phone
     */
    @GetMapping("/phone")
    public ResponseEntity<List<PersonResponse>> getPeopleByPhone(String phone) {
        return null;
    }
}
