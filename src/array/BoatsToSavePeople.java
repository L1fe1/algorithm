package array;

import java.util.Arrays;

/**
 * 给定数组 people 。people[i]表示第 i 个人的体重，船的数量不限，每艘船可以承载的最大重量为 limit。
 * 每艘船最多可同时载两人，但条件是这些人的重量之和最多为 limit。
 * 返回 承载所有人所需的最小船数。
 */
public class BoatsToSavePeople {
    public int numRescueBoats(int[] people, int limit) {
        Arrays.sort(people);
        int l = 0;
        int r = people.length - 1;
        int ans = 0;
        while (l <= r) {
            // 如果 l = r，那么说明只剩一个人了，只计算一个人的重量
            int sum = l == r ? people[l] : people[l] + people[r];
            if (sum <= limit) {
                l ++;
                r --;
            } else {
                r --;
            }
            ans ++;
        }
        return ans;
    }

    // 除了上述要求，额外要求重量之和必须为偶数
    public static int numEvenRescueBoats(int[] people, int limit) {
        Arrays.sort(people);
        int len = people.length;
        int oddL = 0, evenL = 0;
        int oddR = len - 1, evenR = len - 1;
        int ans = 0;
        while (oddL <= oddR) {
            if (people[oddL] % 2 == 0) {
                oddL ++;
            } else if (people[oddR] % 2 == 0) {
                oddR --;
            } else {
                int sum = oddL == oddR ? people[oddL] : people[oddL] + people[oddR];
                if (sum <= limit) {
                    oddL ++;
                    oddR --;
                } else {
                    oddR --;
                }
                ans ++;
            }
        }
        while (evenL <= evenR) {
            if (people[evenL] % 2 != 0) {
                evenL ++;
            } else if (people[evenR] % 2 != 0) {
                evenR --;
            } else {
                int sum = evenL == evenR ? people[evenL] : people[evenL] + people[evenR];
                if (sum <= limit) {
                    evenL ++;
                    evenR --;
                } else {
                    evenR --;
                }
                ans ++;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] people = {3, 5, 3, 4, 1, 2, 4};
        System.out.println(numEvenRescueBoats(people, 5));
    }
}
