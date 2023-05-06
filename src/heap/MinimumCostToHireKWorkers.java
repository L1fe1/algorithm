package heap;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 有 n 名工人。给定两个数组 quality 和 wage，其中，quality[i] 表示第 i 名工人的工作质量，其最低期望工资为 wage[i]。
 * 现在我们想雇佣 k 名工人组成一个工资组。在雇佣一组 k 名工人时，我们必须按照下述规则向他们支付工资：
 * 对工资组中的每名工人，应当按其工作质量与同组其他工人的工作质量的比例来支付工资。
 * 工资组中的每名工人至少应当得到他们的最低期望工资。
 * 给定整数 k，返回组成满足上述条件的付费群体所需的最小金额。在实际答案的 10^-5 以内的答案将被接受。
 */
public class MinimumCostToHireKWorkers {
    public static class Employee {
        public double rubbishDegree;
        public int quality;

        public Employee(int w, int q) {
            // 垃圾指数
            rubbishDegree = (double) w / (double) q;
            quality = q;
        }
    }

    public static double mincostToHireWorkers(int[] quality, int[] wage, int k) {
        int len = quality.length;
        Employee[] employees = new Employee[len];
        for (int i = 0; i < len; i ++) {
            employees[i] = new Employee(wage[i], quality[i]);
        }
        Arrays.sort(employees, Comparator.comparingDouble(a -> a.rubbishDegree));
        // 大根堆
        PriorityQueue<Integer> heap = new PriorityQueue<>((a, b) -> b - a);
        double ans = Double.MAX_VALUE;
        for (int i = 0, qualitySum = 0; i < len; i ++) {
            int empQuality = employees[i].quality;
            double rubbishDegree = employees[i].rubbishDegree;
            // 没满 k 个人
            if (heap.size() < k) {
                qualitySum += empQuality;
                heap.add(empQuality);
                // 添加完之后满 k 个人了
                if (heap.size() == k) {
                    // i 之前选的 k 个人需要支付的金额 = i 的 rubbishDegree * qualitySum
                    ans = Math.min(ans, qualitySum * rubbishDegree);
                }
            } else {
                int curMaxQuality = heap.peek();
                /*
                    如果当前工人 quality 小于 heap 中最大的 quality（也就是堆顶），
                    将当前工人的 quality 加入 heap 并移除掉原来的堆顶，
                    这样一直维持 heap 中的 k 个工人的能力和是最小的
                 */
                if (curMaxQuality > empQuality) {
                    qualitySum += empQuality - curMaxQuality;
                    heap.poll();
                    heap.add(empQuality);
                    ans = Math.min(ans, qualitySum * rubbishDegree);
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] quality = {4, 4, 4, 5};
        int[] wage = {13, 12, 13, 12};
        int k = 2;
        System.out.println(mincostToHireWorkers(quality, wage, k));
    }
}
