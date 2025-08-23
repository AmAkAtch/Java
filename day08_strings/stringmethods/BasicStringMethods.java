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

        // essential string methods indexof and last index of

        String name = "Rushiraj";

        System.out.println(name.indexOf("r")); // index when the character first appears //normal search from index=0
        System.out.println(name.lastIndexOf("r")); // index when the charcter last appears //reverse side search

        /*
         * Best Practises
         * indexOf(String str): First position or -1 if not.
         * lastIndexOf(String str): Last position or -1.
         * Purpose: Search—can take char too.
         * Quirk Tip: Case-sensitive; overload for start index.
         * Best Practice: Check != -1 before use.
         */

        // Essential String methods toLower() and toUpper()

        System.out.println(name.toLowerCase());
        System.out.println(name.toUpperCase());
        System.out.println(name);

        /*
         * Normalize before compare—example: email.toLowerCase() for case-insens.
         * Tip: Chain: mixed.toLowerCase().substring(0,4).
         * Misunderstanding: Numbers change? No, "1" stays.
         */

        // Essential String methods trim(), replace(), replaceAll()
        String dirtyString = "hello ";

        System.out.println(dirtyString + "hi");
        System.out.println(dirtyString.trim() + "hi"); // trim the whitespaces from the end

        String userName = "Rushiraj.Gadhavi";

        System.out.println(userName.replace(".", "_")); // This literally convertes whatever is written before comman
        System.out.println(userName.replaceAll("\\.", "_")); // this converts the regex

    }
}
