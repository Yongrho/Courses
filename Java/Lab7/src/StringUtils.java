/**
 * A collection of constant values and utility methods for manipulating  a string
 */
public class StringUtils {
    /**
     * An accumulator variable for keeping track of how many characters have been handled with.
     */
    private static int accumulatedCharacters = 0;

    /**
     * Takes a string argument, inserts a blank space between each letter then returns the new string
     *
     * @param s        the string to be changed
     * @return         the string with blank spaces
     */
    public static String spaceString(String s) {
        char[] out = new char[100];
        int i = 0;

        accumulatedCharacters += s.length();

        for (int j = 0; j < s.length(); j++) {
            out[i++] = s.charAt(j);
            out[i++] = ' ';
        }
        return String.valueOf(out);
    }

    /**
     * Takes a string argument, transforms every odd character to uppercase then returns the new string
     *
     * @param s        the string to be changed
     * @return         the string with uppercase characters
     */
    public static String switchCase(String s) {
        char[] out = new char[100];
        int i = 0;

        accumulatedCharacters += s.length();

        for (int j = 0; j < s.length(); j++) {
            if ((j + 1) % 2 == 0) {
                out[i++] = s.charAt(j);
            } else {
                out[i++] = Character.toUpperCase(s.charAt(j));
            }
        }
        return String.valueOf(out);
    }

    /**
     * Takes a string argument, returns a new string which is the exact reverse of the argument string
     *
     * @param s        the string to be changed
     * @return         the string with the reverse
     */
    public static String reverse(String s) {
        char[] out = new char[100];
        int i = 0;

        accumulatedCharacters += s.length();

        for (int j = s.length() - 1; j >= 0; j--) {
            out[i++] = s.charAt(j);
        }
        return String.valueOf(out);
    }

    /**
     * returns a value which is accumulated whenever a method in StringUtils is called
     *
     * @return         the count of characters is accumulated
     */
    public static int getAccumulatedCharacters() {
        return accumulatedCharacters;
    }
}