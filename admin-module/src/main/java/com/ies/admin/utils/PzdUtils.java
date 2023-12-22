package com.ies.admin.utils;

import com.ies.admin.constants.AppConstant;

public class PzdUtils {

    private PzdUtils() {
    }

    public static String passwordGenerator(int n) {

        String characters = AppConstant.STR_CAP_ALPHA + AppConstant.STR_SMA_ALPHA + AppConstant.STR_DIGIT;
        StringBuilder pwd = new StringBuilder(n);

        for (int i = 0; i < n; i++) {
            int index = (int) (characters.length() * Math.random());
            pwd.append(characters.charAt(index));
        }

        return pwd.toString();
    }

}

