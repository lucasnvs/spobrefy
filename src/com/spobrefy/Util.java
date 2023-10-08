package com.spobrefy;

import java.text.ParseException;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Util {
    
    public static int findAge(String dataNasc, String pattern) throws ParseException {
        DateFormat sdf = new SimpleDateFormat(pattern);
        Date dataNascInput = null;
        try {
            dataNascInput = sdf.parse(dataNasc);
        } catch (Exception e) {
            throw e;
        }

        Calendar dateOfBirth = new GregorianCalendar();
        dateOfBirth.setTime(dataNascInput);

        // data atual
        Calendar today = Calendar.getInstance();
        int age = today.get(Calendar.YEAR) - dateOfBirth.get(Calendar.YEAR);   // calculo
        dateOfBirth.add(Calendar.YEAR, age);

        if (today.before(dateOfBirth)) { // verifica ano
            age--;
        }
        return age;
    }
}