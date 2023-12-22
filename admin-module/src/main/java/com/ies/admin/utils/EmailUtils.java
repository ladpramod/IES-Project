package com.ies.admin.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.internet.MimeMessage;

@Component
public class EmailUtils {

    Logger logger = LoggerFactory.getLogger(EmailUtils.class);
    @Autowired
    private JavaMailSender mailSender;

    public boolean emailSender(String to, String subject, String body) {

        boolean isSend = false;

        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(body);
            mailSender.send(message);
            isSend = true;
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return isSend;
    }
}
