package ru.project.quiz.mailsender;

public interface MailSenderService {
    void send(String subject, String text, String toEmail);
}
