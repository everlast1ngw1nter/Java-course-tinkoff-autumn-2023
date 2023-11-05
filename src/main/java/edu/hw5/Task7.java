package edu.hw5;

import java.util.regex.Pattern;

public class Task7 {

    private Task7() {
    }

    public static final Pattern THREE_PLUS_SYMBOLS_THIRD_IS_ZERO =
            Pattern.compile("^[01]{2}0[01]*$");
    public static final Pattern START_AND_END_EQUAL =
            Pattern.compile("^([01])(.*\\1)?$");
    public static final Pattern LEN_BETWEEN_ONE_AND_THREE =
            Pattern.compile("^([01]{1,3})$");
}
