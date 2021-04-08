package ru.project.quiz.mailsender.Impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.project.quiz.mailsender.MailSenderService;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Service
public class MailSenderServiceImpl implements MailSenderService {

    @Value("${mail-sender.email}")
    private String username;
    @Value("${mail-sender.password}")
    private String password;
    @Value("${mail-sender.active}")
    private String active;
    private final Properties props;

    Logger log = LoggerFactory.getLogger(MailSenderServiceImpl.class);

    private final static String welcomeMessage = "<h1 style=\"text-align: center;\">Добро пожаловать в <span style=\"color: #ff9900;\">QUIZ</span>.</h1>\n" +
            "<p><span style=\"text-decoration: underline;\">У нас есть множество тестов для решения, а также:</span></p>\n" +
            "<ul>\n" +
            "<li>Вы можете купить подписку за ♂♂<span style=\"color: #ff0000;\"><strong>THREE HUNDRED BUCKS</strong></span>♂♂</li>\n" +
            "<li>Ты никогда не почувствуешь себя ♂♂<span style=\"color: #00ff00;\"><strong>FUCKING SLAVES♂</strong></span>&nbsp;</li>\n" +
            "<li>Пройди все тесты и стань настоящим ♂♂<span style=\"color: #0000ff;\"><strong>DUNGEON MASTER</strong></span>♂♂</li>\n" +
            "<li>♂♂<span style=\"color: #00ffff;\"><strong>FISTING</strong></span>♂♂ с нашими тестами тебе обеспечен<img src=\"https://html-online.com/editor/tiny4_9_11/plugins/emoticons/img/smiley-cool.gif\" alt=\"cool\" /></li>\n" +
            "</ul>\n" +
            "<h1 style=\"text-align: center;\"><span style=\"text-decoration: underline;\"><span style=\"color: #3366ff; text-decoration: underline;\">WELCOME TO THE CLUB BUDDY</span></span></h1>\n" +
            "<p><span style=\"text-decoration: underline;\"><span style=\"color: #3366ff; text-decoration: underline;\"><img src=\"https://i.ibb.co/5MjPghB/lk0iec-X2p-RU.jpg\" alt=\"\" width=\"687\" height=\"386\" /></span></span></p>";

    public void send(String subject, String text, String toEmail) {
        if (active.equals("disable")) {
            log.warn("Отправка на почту отключена, будьте внимательны!");
            return;
        }
        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            message.setSubject(subject);
            message.setContent(welcomeMessage, "text/html; charset=utf-8");
            Transport.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    public MailSenderServiceImpl(Properties props) {
        this.props = props;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public Properties getProps() {
        return props;
    }

}
