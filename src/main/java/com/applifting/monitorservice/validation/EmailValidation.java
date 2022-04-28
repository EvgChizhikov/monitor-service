package com.applifting.monitorservice.validation;

import lombok.extern.slf4j.Slf4j;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
public class EmailValidation implements Validation {

    @Override
    public Boolean validate(String object) {
        String regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(object);
        if (!matcher.matches()) {
            log.error("Email {} is not valid", object);
            throw new IllegalStateException("Email is not valid");
        } else
            return true;
    }
}
