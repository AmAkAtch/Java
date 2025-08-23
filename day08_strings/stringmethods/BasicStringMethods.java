package day08_strings.stringmethods;

public class BasicStringMethods {
    public static void main(String[] args) {

        // essential string methods Len, substring, charAt
        String userEmail = "rushiraj@gmail.com";
        int stringLength = userEmail.length();
        char stringCharacter = userEmail.charAt(2);
        String subString = userEmail.substring(2, 4); // charcter on begin index is included while the endIndex is not
                                                      // included

        System.out.println("sub String :" + subString);
        System.out.println("Character :" + stringCharacter);
        System.out.println("String length :" + stringLength);
    }
}
