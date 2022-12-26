package ru.valaubr.tasktracker.configs;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringHandler {
    public static String getToken(String bigString) {
        Matcher matcher = Pattern.compile("\\w+@\\w+\\.\\w+").matcher(bigString);
        matcher.find();
        return matcher.group();
    }
}
