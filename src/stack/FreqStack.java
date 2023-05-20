package stack;

import java.util.*;

/**
 * 设计一个类似堆栈的数据结构，将元素推入堆栈，并从堆栈中弹出出现频率最高的元素。
 * 实现 FreqStack 类:
 * FreqStack() 构造一个空的堆栈。
 * void push(int val) 将一个整数 val 压入栈顶。
 * int pop() 删除并返回堆栈中出现频率最高的元素。
 * 如果出现频率最高的元素不只一个，则移除并返回最接近栈顶的元素。
 */
public class FreqStack {
    // 频率值 -> 栈
    public Map<Integer, Stack<Integer>> stackMap;
    // 最大频率值
    public int maxFreq = 0;
    // val -> val 出现的频率
    public Map<Integer, Integer> freqMap;

    public FreqStack() {
        stackMap = new HashMap<>();
        freqMap = new HashMap<>();
    }

    public void push(int val) {
        // 更新 val 频率
        freqMap.put(val, freqMap.getOrDefault(val, 0) + 1);
        int freq = freqMap.get(val);
        // 更新最大频率值
        maxFreq = Math.max(maxFreq, freq);
        // 将 val 加入对应频率值的 stack 中
        if (!stackMap.containsKey(freq)) {
            stackMap.put(freq, new Stack<>());
        }
        Stack<Integer> stack = stackMap.get(freq);
        stack.push(val);
    }

    public int pop() {
        // 获取最大频率值对应的栈
        Stack<Integer> maxFreqStack = stackMap.get(maxFreq);
        // 将栈顶的 val 弹出，该 val 对应的频率值减一
        int val = maxFreqStack.pop();
        freqMap.put(val, freqMap.get(val) - 1);
        // 弹出之后栈为空，stackMap 移除最大频率值对应的键值对，最大频率值减一
        if (maxFreqStack.empty()) {
            stackMap.remove(maxFreq--);
        }
        // 如果 val pop 之后频率值减为 0，在 freqMap 中将对应的 val 移除
        if (freqMap.get(val) == 0) {
            freqMap.remove(val);
        }
        return val;
    }
}
