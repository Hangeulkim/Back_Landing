package osteam.backland.domain.person.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.web.bind.annotation.RestController;
import osteam.backland.domain.person.controller.request.DeleteRequest;
import osteam.backland.domain.person.controller.request.PhoneRequest;
import osteam.backland.domain.person.controller.request.SearchRequest;
import osteam.backland.domain.person.controller.response.PhoneResponse;
import osteam.backland.domain.person.entity.dto.PersonDTO;
import osteam.backland.domain.person.service.PersonCreateService;
import osteam.backland.domain.person.service.PersonDeleteService;
import osteam.backland.domain.person.service.PersonSearchService;
import osteam.backland.domain.person.service.PersonUpdateService;

import java.util.Set;

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
public class PersonController {

    private final HttpServletRequest httpServletRequest;
    private final PersonCreateService personCreateService;
    private final PersonUpdateService personUpdateService;
    private final PersonSearchService personSearchService;

    private final PersonDeleteService personDeleteService;

    /**
     * 등록 기능
     * 중복되는 전화번호가 있을 시 이름을 변경
     *
     * @param request
     * @return 성공 시 이름 반환
     */
    @MutationMapping
    public PhoneResponse createOrUpdatePerson(@Argument @Valid PhoneRequest request) {
        String auth = httpServletRequest.getHeader("Authorization");
        String accessToken = auth.substring(7);
        PersonDTO personDTO = personSearchService.searchPerson(accessToken, request.getPhone());

        if (personDTO == null) {
            personDTO = personCreateService.createPerson(accessToken, request.getName(), request.getPhone());
        } else {
            personDTO = personUpdateService.modifyPersonName(accessToken, request.getName(), request.getPhone());
        }

        return new PhoneResponse(personDTO.getName(), personDTO.getPhone());
    }

    /**
     * 검색 기능
     */
    @QueryMapping
    public Set<PhoneResponse> getPeople(@Argument @Valid SearchRequest request) {
        String auth = httpServletRequest.getHeader("Authorization");
        String accessToken = auth.substring(7);

        Set<PhoneResponse> peopleResponse;
        if (request == null) {
            peopleResponse = personSearchService.searchPeople(accessToken, null, null);
        } else {
            peopleResponse = personSearchService.searchPeople(accessToken, request.getName(), request.getPhone());
        }
        return peopleResponse;
    }

    /**
     * 삭제기능
     */
    @MutationMapping
    public Long deletePerson(@Argument @Valid DeleteRequest request) {
        String auth = httpServletRequest.getHeader("Authorization");
        String accessToken = auth.substring(7);
        long personId = personDeleteService.deletePerson(accessToken, request.getPhone());

        return personId;
    }
}
