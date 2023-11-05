package edu.hw5;

import java.util.regex.Pattern;

public class Task8 {
    private Task8() {
    }

    public static final Pattern ODD_LEN =
            Pattern.compile("^[0,1]([01]{2})*$");
    public static final Pattern EVERY_ODD_NUMBER_IS_ONE =
            Pattern.compile("^([01]1)*[01]?$");
    public static final Pattern START_ZERO_EVEN_LEN_OR_ONE_ODD_LEN =
            Pattern.compile("^((1[01])|0)([01]{2})*$");
    public static final Pattern NO_CONSECUTIVE_ONES =
            Pattern.compile("^1?(0[01]?)*$");

    public static final Pattern NEXT =
            Pattern.compile("^1?(0[01]?)*$");
}
