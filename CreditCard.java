import static java.util.stream.Collectors.*;

public class CreditCard {
    private static final String MASKING_CHAR = "#";
    private static final String NUMBERS = "0123456789";
    private static final int MINIMAL_LENGTH = 6;

    private static final int LENGTH_OF_UNMASKED_PREFIX = 1;
    private static final int LENGTH_OF_UNMASKED_POSTFIX = 4;

    public static String maskify(String creditCardNumber) {
        if(isTooShort(creditCardNumber)) {
            return creditCardNumber;
        }

        String prefix = getPrefixOf(creditCardNumber);
        String postfix = getPostfixOf(creditCardNumber);
        String maskifiedPart = getMaskifiedPartOf(creditCardNumber);

        System.out.println(prefix + maskifiedPart + postfix);

        return prefix + maskifiedPart + postfix;
    }

    private static boolean isTooShort(String creditCardNumber) {
        return creditCardNumber.length() < MINIMAL_LENGTH;
    }

    private static String getPrefixOf(String creditCardNumber) {
        return creditCardNumber.substring(0, LENGTH_OF_UNMASKED_PREFIX);
    }

    private static String getPostfixOf(String creditCardNumber) {
        return creditCardNumber.substring(creditCardNumber.length() - LENGTH_OF_UNMASKED_POSTFIX);
    }

    private static String getMaskifiedPartOf(String creditCardNumber) {
        String partToMaskify = getPartToMaskifyOf(creditCardNumber);

        return partToMaskify.chars().mapToObj(CreditCard::mask).collect(joining(""));
    }

    private static String getPartToMaskifyOf(String creditCardNumber) {
        return creditCardNumber.substring(LENGTH_OF_UNMASKED_PREFIX, creditCardNumber.length() - LENGTH_OF_UNMASKED_POSTFIX);
    }

    // The input is the ASCII code of the character, that's why we have to convert it first to a String
    private static String mask(int item) {
        String character = String.valueOf((char) item);

        if(NUMBERS.contains(character)) {
            character = MASKING_CHAR;
        }

        return character;
    }
}
