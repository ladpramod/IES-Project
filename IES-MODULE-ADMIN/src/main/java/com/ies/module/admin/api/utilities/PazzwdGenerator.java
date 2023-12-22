package com.ies.module.admin.api.utilities;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class PazzwdGenerator {

    public String pazzedGenerator(){
        String upperCase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowerCase = "abcdefghijklmnopqrstuvwxyz";
        String number = "0123456789";
        String alphanumeric = upperCase+lowerCase+number;

        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        int length = 6;
        for (int i=0; i<length;i++){
            int index = random.nextInt(alphanumeric.length());
            char randomChar = alphanumeric.charAt(index);
            sb.append(randomChar);
        }
        return sb.toString(); //
    }
}
