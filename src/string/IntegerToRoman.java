package string;

/**
 * <a href="https://leetcode.cn/problems/integer-to-roman/">...</a>
 */
public class IntegerToRoman {
    public static String intToRoman(int num) {
        String[][] roman = new String[][]{
                {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"},
                {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"},
                {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"},
                {"", "M", "MM", "MMM"}
        };
        return roman[3][num / 1000] +
                roman[2][num / 100 % 10] +
                roman[1][num / 10 % 10] +
                roman[0][num % 10];
    }

    public static void main(String[] args) {
        System.out.println(intToRoman(1994));
    }
}
