package osteam.backland.global.security.service;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Objects;

@Slf4j
public class IpSearchService {

    public static String getClientIP() {
        // 현재 HTTP 요청에 대한 정보를 포함하고 있으며, 이를 통해 요청 헤더, 파라미터, 세션 등을 다룬다.
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String IP = request.getHeader("X-Real-IP");


        if (IP == null || IP.isBlank()) {
            return "0.0.0.0";
        }

        return IP;
    }
}
