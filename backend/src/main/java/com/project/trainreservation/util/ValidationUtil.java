package com.project.trainreservation.util;

import java.util.regex.Pattern;

public class ValidationUtil {

    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);

    
    public static boolean isValidEmail(String email) {
        return email != null && EMAIL_PATTERN.matcher(email).matches();
    }

   
    public static boolean isValidPassword(String password) {
        // Şifre en az 8 karakter, 1 büyük harf, 1 küçük harf ve 1 rakam içermelidir
        return password != null && password.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,}$");
    }

    
    public static boolean isNotEmpty(String input) {
        return input != null && !input.trim().isEmpty();
    }
}
