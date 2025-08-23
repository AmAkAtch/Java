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

        // String Comparision
        String s1 = "hello";
        String s2 = "hello";
        String s3 = new String("hello");

        if (s1 == s2)
            System.out.println("True");
        if (s1 == s3)
            System.out.println("True 2"); // This is false because both string look same but pool Differnt
        if (s1.equals(s3))
            System.out.println("True 3");
        // s1.equals(s3): Compares length (5=5), then char by char ('h'='h', etc.), all
        // match, true.

        // String comparision
        String a = "apple";
        String b = "canana";
        System.out.println(a.compareTo(b));
        /*
         * a.compareTo(b) compares strings character by character, not just their
         * lengths.
         * Even if a is shorter, it checks each character—like 'a' (97) vs 'b' (98), so
         * it returns -1.
         * It stops at the first difference and returns the difference in character
         * values
         * Negative if less, 0 equal, positive greater.
         */

        // append is better than + to join string and to use in array
        // + creates a new string everytime joining
        // append increases the size of internal array and joins the string

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 5; i++) {
            sb.append("part").append(i);
        }
        String result = sb.toString();
        System.out.println(result);

        // This String buffer is goof for threaded process
        // but buffer is slower

        StringBuffer sb1 = new StringBuffer();
        sb1.append("safe");
        String result1 = sb1.toString();
        System.out.println(result1); // "safe"

        // Essential string methods to match, split and join
        String email = "a@b.com";
        boolean valid = email.matches(".+@.+\\..+"); // Simple email regex
        String[] parts = email.split("@"); // {"a","b.com"}
        String joined = String.join("@", parts); // "a@b.com"
        System.out.println(valid + " " + joined);

    }
}
