package bitwise_operation;

public class EvenTimesOddTimes {
    /**
     * 在 arr 中，只有一种数出现奇数次，其他数都出现偶数次
     * @param arr 数组
     * @return 出现奇数次的数
     */
    public static int findOneOddTimesNumber(int[] arr) {
        /*
          0 异或任何数字都等于该数字本身：0 ^ a = a
          任何数字与自身异或都为 0：a ^ a = 0
          异或的结果与顺序无关（异或可以看作无进位相加）：a ^ b ^ c = c ^ a ^ b
         */
        int eor = 0;
        for (int num : arr) {
            eor ^= num;
        }
        return eor;
    }

    /**
     * 在 arr 中，只有两种数出现奇数次，其他数都出现偶数次
     * @param arr 数组
     */
    public static void printTwoOddTimesNumber(int[] arr) {
        int eor = findOneOddTimesNumber(arr);
        /*
          把 num 最右侧的 1 提取出来：num & (-num) 或者 num & (~num + 1)
          1101     num
          0011     -num
          0001     num & (-num)
         */
        int rightOne = eor & (-eor);
        int left = 0;
        for (int num : arr) {
            /*
                arr 中的数字只有两类，一类在 rightOne 的位置为 0，另一类在 rightOne 的位置为 0
                left 只异或其中的一类，另一类忽略
             */
            if ((num & rightOne) == 0) {
                left ^= num;
            }
        }
        System.out.println("第一个数是：" + left + "，第二个数是：" + (left ^ eor));
    }

    public static void main(String[] args) {
        int[] arr1 = {1, 2, 1, 3, 3, 4, 4, 4, 4};
        System.out.println(findOneOddTimesNumber(arr1));
        int[] arr2 = {100, 20, 100, 3, 3, 5, 5, 3, 4, 4};
        printTwoOddTimesNumber(arr2);
    }
}
