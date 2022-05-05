package dp;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 给定一个数组 arr代表每一个咖啡机冲一杯咖啡的时间，每个咖啡机只能串行的制造咖啡。
 * 现在有n个人需要喝咖啡，只能用咖啡机来制造咖啡。
 * 认为每个人喝咖啡的时间非常短，冲好的时间即是喝完的时间。
 * 每个人喝完之后咖啡杯可以选择洗或者自然挥发干净，只有一台洗咖啡杯的机器，只能串行的洗咖啡杯。
 * 洗杯子的机器洗完一个杯子时间为a，任何一个杯子自然挥发干净的时间为b。
 * 四个参数：arr, n, a, b
 * 假设时间点从0开始，返回所有人喝完咖啡并洗完咖啡杯的全部过程结束后，至少来到什么时间点。
 */
public class Coffee {
    public static int minTime1(int[] arr, int n, int a, int b) {
        int[] times = new int[arr.length];
        int[] drink = new int[n];
        return forceMake(arr, times, 0, drink, n, a, b);
    }

    public static int forceMake(int[] arr, int[] times, int kth, int[] drink, int n, int a, int b) {
        if (kth == n) {
            int[] drinkSorted = Arrays.copyOf(drink, kth);
            Arrays.sort(drinkSorted);
            return forceWash(drinkSorted, a, b, 0, 0, 0);
        }
        int time = Integer.MAX_VALUE;
        for (int i = 0; i < arr.length; i++) {
            int work = arr[i];
            int pre = times[i];
            drink[kth] = pre + work;
            times[i] = pre + work;
            time = Math.min(time, forceMake(arr, times, kth + 1, drink, n, a, b));
            drink[kth] = 0;
            times[i] = pre;
        }
        return time;
    }

    public static int forceWash(int[] drinks, int a, int b, int index, int washLine, int time) {
        if (index == drinks.length) {
            return time;
        }
        // 选择一：当前index号咖啡杯，选择用洗咖啡机刷干净
        int wash = Math.max(drinks[index], washLine) + a;
        int ans1 = forceWash(drinks, a, b, index + 1, wash, Math.max(wash, time));

        // 选择二：当前index号咖啡杯，选择自然挥发
        int dry = drinks[index] + b;
        int ans2 = forceWash(drinks, a, b, index + 1, washLine, Math.max(dry, time));
        return Math.min(ans1, ans2);
    }

    public static class Machine {
        public int timePoint;
        public int workTime;

        public Machine(int t, int w) {
            timePoint = t;
            workTime = w;
        }
    }

    public static class MachineComparator implements Comparator<Machine> {

        @Override
        public int compare(Machine o1, Machine o2) {
            return (o1.timePoint + o1.workTime) - (o2.timePoint + o2.workTime);
        }

    }

    public static int minTime2(int[] arr, int n, int a, int b) {
        int[] drinks = getSortedDrinks(arr, n);
        return process(drinks, a, b, 0, 0);
    }

    // drinks 0~index-1 的咖啡杯已经干净了，index 号咖啡杯要变干净
    // 洗咖啡杯的机器 washLine 时间点可用
    public static int process(int[] drinks, int a, int b, int index, int washLine) {
        // base case
        // 剩最后一杯了
        if (index == drinks.length - 1) {
            // 如果选择挥发，那么时间点会来到 drinks[index] + b（喝完就可以挥发）
            // 如果选择洗，那么时间点会来到 Math.max(drinks[index], washLine) + a
            // 因为只有等到机器可用而且喝完了才能洗
            // 返回挥发或洗结束时间早的时间点
            return Math.min(Math.max(drinks[index], washLine) + a, drinks[index] + b);
        }
        // 选择洗 index 这杯咖啡
        int wash = Math.max(drinks[index], washLine) + a;
        // 去 index + 1 位置做选择
        int p1 = process(drinks, a, b, index + 1, wash);
        // 选择 wash 时间点和剩余咖啡杯干净时间点的较大值
        int res1 = Math.max(wash, p1);
        // 选择让 index 这杯咖啡自然挥发
        int dry = drinks[index] + b;
        // 去 index + 1 位置做选择
        int p2 = process(drinks, a, b, index + 1, washLine);
        // 选择 dry 时间点和剩余咖啡杯干净时间点的较大值
        int res2 = Math.max(dry, p2);
        // 返回两种决策中用时少的
        return Math.min(res1, res2);
    }

    public static int minTime3(int[] arr, int n, int a, int b) {
        int[] drinks = getSortedDrinks(arr, n);
        if (a >= b) {
            return drinks[n - 1] + b;
        }
        int[][] dp = new int[n][drinks[n - 1] + n * a];
        for (int i = 0; i < dp[0].length; i++) {
            dp[n - 1][i] = Math.min(Math.max(i, drinks[n - 1]) + a, drinks[n - 1] + b);
        }
        for (int row = n - 2; row >= 0; row--) { // row 咖啡杯的编号
            int washLine = drinks[row] + (row + 1) * a;
            for (int col = 0; col < washLine; col++) {
                int wash = Math.max(col, drinks[row]) + a;
                dp[row][col] = Math.min(Math.max(wash, dp[row + 1][wash]), Math.max(drinks[row] + b, dp[row + 1][col]));
            }
        }
        return dp[0][0];
    }

    public static int minTime4(int[] arr, int n, int a, int b) {
        int[] drinks = getSortedDrinks(arr, n);
        // 如果洗的时间大于等于挥发的时间，那么最早时间等于最后一杯咖啡挥发的时间
        if (a >= b) {
            return drinks[n - 1] + b;
        }
        int limit = 0;
        // 所有咖啡杯都选择洗，洗完的时间
        for (int i = 0; i < n; i++) {
            limit = Math.max(limit, drinks[i]) + a;
        }
        // dp[i][j]：第 i~n-1 个咖啡杯需要变干净，洗咖啡杯的机器 j 时间点可用，最早干净时间
        int[][] dp = new int[n][limit + 1];
        // 最后一个咖啡杯最早干净时间
        for (int washLine = 0; washLine <= limit; washLine++) {
            dp[n - 1][washLine] = Math.min(Math.max(drinks[n - 1], washLine) + a,
                            drinks[n - 1] + b);
        }
        for (int index = n - 2; index >= 0; index--) {
            for (int washLine = 0; washLine <= limit; washLine++) {
                // index 号咖啡杯选择洗
                int wash = Math.max(drinks[index], washLine) + a;
                int res1 = Integer.MAX_VALUE;
                // 只有 wash <= limit 的情况才记录结果
                if (wash <= limit) {
                    res1 = Math.max(wash, dp[index + 1][wash]);
                }
                // index 号咖啡杯选择自然挥发
                int dry = drinks[index] + b;
                int res2 =  Math.max(dry, dp[index + 1][washLine]);
                dp[index][washLine] = Math.min(res1, res2);
            }
        }
        return dp[0][0];
    }

    private static int[] getSortedDrinks(int[] arr, int n) {
        PriorityQueue<Machine> heap = new PriorityQueue<>(new MachineComparator());
        for (int i = 0; i < arr.length; i++) {
            heap.add(new Machine(0, arr[i]));
        }
        int[] drinks = new int[n];
        for (int i = 0; i < n; i++) {
            Machine cur = heap.poll();
            cur.timePoint += cur.workTime;
            drinks[i] = cur.timePoint;
            heap.add(cur);
        }
        return drinks;
    }

    // for test
    public static int[] randomArray(int len, int max) {
        int[] arr = new int[len];
        for (int i = 0; i < len; i++) {
            arr[i] = (int) (Math.random() * max) + 1;
        }
        return arr;
    }

    // for test
    public static void printArray(int[] arr) {
        System.out.print("arr : ");
        for (int j = 0; j < arr.length; j++) {
            System.out.print(arr[j] + ", ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int len = 5;
        int max = 9;
        int testTime = 50000;
        for (int i = 0; i < testTime; i++) {
            int[] arr = randomArray(len, max);
            int n = (int) (Math.random() * 5) + 1;
            int a = (int) (Math.random() * 5) + 1;
            int b = (int) (Math.random() * 10) + 1;
            int ans1 = minTime1(arr, n, a, b);
            int ans2 = minTime2(arr, n, a, b);
            int ans3 = minTime3(arr, n, a, b);
            int ans4 = minTime4(arr, n, a, b);
            if (ans1 != ans2 || ans2 != ans3 || ans3 != ans4) {
                printArray(arr);
                System.out.println("n : " + n);
                System.out.println("a : " + a);
                System.out.println("b : " + b);
                System.out.println(ans1 + " , " + ans2 + " , " + ans3);
                System.out.println("===============");
                break;
            }
        }
    }
}
