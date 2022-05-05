package dp;

/**
 * 假设村里有很多人，每个人既可以寄信也可以收信，
 * 而且每个人只能寄出一封信以及收一封信，
 * 问：一共有多少种寄信方式？
 */
public class EulerEnvelope {
    public static int eulerEnvelope(int num) {
        // 如果只有 1 个人，既无法寄信，也无法收信
        if (num == 1) {
            return 0;
        }
        // 如果有两个人，只有一种寄信方式：A 寄给 B，B寄给 A
        if (num == 2) {
            return 1;
        }
        // 如果有 3 个人 A、B、C
        // 那么有两种寄信方式
        // 1. A 寄给 B，那么 B 只能寄给 C，然后 C 寄给 A
        // 2. A 寄给 C，那么 C 只能寄给 B，然后 B 寄给 A
        if (num == 3) {
            return 2;
        }
        // 如果有 5 个人 A、B、C、D、E
        // 假设 A 把信寄给了 B，那么有两种情况：
        // 1. B 也把信寄给了 A，那么剩下的 3 个人只能相互寄信，而不能给 A、B 寄了，相当于 eulerEnvelope(3) 的情况
        // 2. B 没有把信寄给 A，那么此时 A 和 B 可以看作一个人（因为 A 等着别寄信给他，B 等着寄信给别人），
        // 此时就相当于 4 个人相互寄信的情况，也就是 eulerEnvelope(4)
        // A 可以把信寄给 B，那么他也可以把信寄给 C、D、E，因此一共有 4 种情况
        // 所以 eulerEnvelope(5) = 4 * (eulerEnvelope(3) + eulerEnvelope(4))
        // 同理，6 个人、7 个人...是一样的道理，
        // 也即 eulerEnvelope(num) = (num - 1) * (eulerEnvelope(num - 1) + eulerEnvelope(num - 2))
        return (num - 1) * (eulerEnvelope(num - 1) + eulerEnvelope(num - 2));
    }
}
