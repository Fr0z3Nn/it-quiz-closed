package ru.project.quiz.service.Impl;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.project.quiz.mailsender.MailSenderService;
import ru.project.quiz.service.MailService;

@Service
@Data
@AllArgsConstructor
public class MailServiceImpl implements MailService {

    private final MailSenderService mailSenderService;

    private final static String registrationSuccessfulMessageSubject = "Успешная регистрация";
    private final static String registrationSuccessfulMessageText = "Спасибо, что выбрали наш сервис!";

    @Override
    public void registrationSuccessfulMessage(String toEmail) {
        mailSenderService.send(registrationSuccessfulMessageSubject,
                registrationSuccessfulMessageText,
                toEmail);
    }
}
