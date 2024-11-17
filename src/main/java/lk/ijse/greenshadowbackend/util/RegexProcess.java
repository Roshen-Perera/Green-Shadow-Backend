package lk.ijse.greenshadowbackend.util;
import java.util.regex.Pattern;

public class RegexProcess {
    public static boolean fieldIdMatcher(String fieldId) {
        String regexForFieldID = "^F\\d{3}$";
        Pattern regexPattern = Pattern.compile(regexForFieldID);
        return regexPattern.matcher(fieldId).matches();
    }

    public static boolean cropIdMatcher(String cropId) {
        String regexForCropID = "^C\\d{3}$";
        Pattern regexPattern = Pattern.compile(regexForCropID);
        return regexPattern.matcher(cropId).matches();
    }

}
