package osteam.backland.domain.person.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import osteam.backland.domain.person.controller.request.PhoneRequest;

public class PersonControllerTest {

    private MockMvc mock;
    private ObjectMapper objectMapper;

    /*
    @Test
    void longNamePersonTest() throws JsonProcessingException {
        String longNamePerson = objectMapper
                .writeValueAsString(new PhoneRequest(
                        "teamaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa",
                        "01012341234"
                ));
    }

    @Test
    void longPhonePersonTest() throws JsonProcessingException {
        String longPhonePerson = objectMapper
                .writeValueAsString(new PhoneRequest(
                        "team",
                        "010123412341111111111111111111111"
                ));
    }

    @Test
    void shortPhonePersonTest() throws JsonProcessingException {
        String shortPhonePerson = objectMapper
                .writeValueAsString(new PhoneRequest(
                        "team",
                        "010"
                ));
    }

    @Test
    void nullPersonTest() throws JsonProcessingException {
        String nullPerson = objectMapper
                .writeValueAsString(new PhoneRequest(
                        null,
                        null
                ));
    }

    @Test
    void blankPersonTest() throws JsonProcessingException {
        String blankPerson = objectMapper
                .writeValueAsString(new PhoneRequest(
                        "",
                        ""
                ));
    }

    @Test
    void nullInputTest() throws JsonProcessingException {
        String longPhonePerson = objectMapper
                .writeValueAsString(null);
    }


    @Test
    void changeNamePersonTest() throws JsonProcessingException {
        String originName = "sos";
        String phone = "01012341234";
        String changeName = "team";

        String successPerson = objectMapper
                .writeValueAsString(new PhoneRequest(
                        originName,
                        phone
                ));

        String changePerson = objectMapper
                .writeValueAsString(new PhoneRequest(
                        changeName,
                        phone
                ));
    }
    */
}
