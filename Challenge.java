public class Challenge {
    private static final String SUFFIX_FOR_1 = "st";
    private static final String SUFFIX_FOR_2 = "nd";
    private static final String SUFFIX_FOR_3 = "rd";
    private static final String GENERAL_SUFFIX = "th";

    private static final String TEEN_NUMBER = "1";


    public static String numberToOrdinal( Integer number ) {
        if(number == 0) {
            return "0";
        }

        String suffix = getSuffixOf(number.toString());

        if(isTeen(number.toString())) {
            suffix = GENERAL_SUFFIX;
        }

        return number + suffix;
    }

    private static String getSuffixOf(String number) {
        switch (getOrdinal(number)) {
            case "1":
                return SUFFIX_FOR_1;
            case "2":
                return SUFFIX_FOR_2;
            case "3":
                return SUFFIX_FOR_3;
            default:
                return GENERAL_SUFFIX;
        }
    }

    private static boolean isTeen(String number) {
        if(number.length() == 1) {
            return false;
        }

        String secondToLastNumber = number.substring(number.length() - 2, number.length() - 1);

        return TEEN_NUMBER.equals(secondToLastNumber);
    }

    private static String getOrdinal(String number) {
        return number.substring(number.length() - 1);
    }
}
