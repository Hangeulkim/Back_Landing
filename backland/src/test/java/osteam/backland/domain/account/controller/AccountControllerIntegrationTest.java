package osteam.backland.domain.account.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import osteam.backland.domain.account.controller.request.LoginRequest;
import osteam.backland.global.BaseIntegrationTest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class AccountControllerIntegrationTest extends BaseIntegrationTest {
    @Nested
    class LoginTest {

        @Test
        @DisplayName("로그인 성공 - 200")
        public void login_success() throws Exception {
            //given
            LoginRequest loginRequest = new LoginRequest("testid", "testtestPWD1!");

            //when
            ResultActions actions = mock.perform(post("/user/login")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(loginRequest))
                    .accept(MediaType.APPLICATION_JSON)
                    .characterEncoding("utf-8"));

            //then
            actions
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("accessToken").value("this-is-test-token"))
                    .andExpect(jsonPath("refreshToken").value("this-is-test-token"));
        }
    }
}
