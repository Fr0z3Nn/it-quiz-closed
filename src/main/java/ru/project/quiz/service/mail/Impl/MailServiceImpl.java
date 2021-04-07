package ru.project.quiz.service.mail.Impl;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;
import ru.project.quiz.mailsender.MailSenderService;
import ru.project.quiz.service.mail.MailService;

@Service
public class MailServiceImpl implements MailService {

    private final MailSenderService mailSenderService;

    public MailServiceImpl(MailSenderService mailSenderService) {
        this.mailSenderService = mailSenderService;
    }

    private final static String registrationSuccessfulMessageSubject = "Успешная регистрация";
    private final static String registrationSuccessfulMessageText = "Спасибо, что выбрали наш сервис!";

    @Override
    public void registrationSuccessfulMessage(String toEmail) {
        mailSenderService.send(registrationSuccessfulMessageSubject,
                registrationSuccessfulMessageText,
                toEmail);
    }
}
