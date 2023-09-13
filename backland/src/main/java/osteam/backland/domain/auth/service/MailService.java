package osteam.backland.domain.auth.service;

import jakarta.mail.internet.MimeMessage;

public interface MailService {

    MimeMessage createMail(String to);

    String sendMail(String to);
}
