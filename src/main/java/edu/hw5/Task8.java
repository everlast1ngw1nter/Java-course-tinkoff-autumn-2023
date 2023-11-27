package edu.hw5;

import java.util.regex.Pattern;

public class Task8 {
    private Task8() {
    }

    public static final Pattern ODD_LEN =
            Pattern.compile("^[0,1]([01]{2})*$");
    public static final Pattern START_ZERO_EVEN_LEN_OR_ONE_ODD_LEN =
            Pattern.compile("^(1|(0[01]))([01]{2})*$");
    public static final Pattern NUMBER_ZEROS_IS_MULTIPLE_THREE =
            Pattern.compile("^1*((01*){3})*$");
    public static final Pattern NOT_11_OR_111 =
            Pattern.compile("^(?!11$|111$)[01]*$");
    public static final Pattern EVERY_ODD_NUMBER_IS_ONE =
            Pattern.compile("^([01]1)*[01]?$");
    public static final Pattern MORE_ONE_ZERO_LESS_TWO_ONES =
            Pattern.compile("^(?=(1*0){2})(?!(0*1){2})[01]*$");
    public static final Pattern NO_CONSECUTIVE_ONES =
            Pattern.compile("^1?(0[01]?)*$");
}
