package com.ies.module.co.api.utils;

import com.ies.module.co.api.exception.ApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.internet.MimeMessage;
import java.io.File;


@Component
public class EmailUtils {

    @Autowired
    private JavaMailSender mailSender;

    public boolean emailSend(String to, String subject, String body, File file){

        boolean isSend = false;

        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message,true);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(body);
            helper.addAttachment(file.getName(), file);
            mailSender.send(message);
            isSend=true;

        }catch(Exception e){
            throw new ApiException("Email not sent.."+e.getMessage());
        }
        return isSend;
    }
}
