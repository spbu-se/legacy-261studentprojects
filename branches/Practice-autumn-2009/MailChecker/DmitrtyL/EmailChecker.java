//Lebedev Dmitry g261 2009 (c)
package emailchecker;

import java.util.regex.*;

public class EmailChecker {

    private static String login = "(([a-zA-Z_])|([a-zA-Z_]+(\\.?[a-zA-Z0-9_])+))";
    private static String topLevelDomain = "(((travel)|(museum)|([a-z]{2,4})))";
    private static String lowLevelDomain = "(([a-zA-Z0-9]\\.?)+[.])+";
    private static String atSign = "@";
    private static String mailTemplate =
        String.format("%s%s%s%s", login, atSign, lowLevelDomain, topLevelDomain);

    public static boolean checkEmail(String email) {
        if (email == null) {
            throw new IllegalArgumentException("email");
        }
        Pattern pattern = Pattern.compile(mailTemplate);

        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}

