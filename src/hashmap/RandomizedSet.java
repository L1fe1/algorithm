package hashmap;

import java.util.HashMap;
import java.util.Map;

/**
 * 实现 RandomizedSet 类：
 * RandomizedSet() 初始化 RandomizedSet 对象
 * bool insert(int val) 当元素 val 不存在时，向集合中插入该项，并返回 true ；否则，返回 false 。
 * bool remove(int val) 当元素 val 存在时，从集合中移除该项，并返回 true ；否则，返回 false 。
 * int getRandom() 随机返回现有集合中的一项（测试用例保证调用此方法时集合中至少存在一个元素）。每个元素应该有 相同的概率 被返回。
 * 你必须实现类的所有函数，并满足每个函数的 平均 时间复杂度为 O(1) 。
 */
public class RandomizedSet {
    // val -> index
    public Map<Integer, Integer> valIndexMap;
    // index -> val
    public Map<Integer, Integer> indexValMap;
    // 元素个数
    public int size;

    public RandomizedSet() {
        valIndexMap = new HashMap<>();
        indexValMap = new HashMap<>();
        size = 0;
    }
    
    public boolean insert(int val) {
        if (!valIndexMap.containsKey(val)) {
            // 将当前 size 作为 val 的 index，size 加 1
            int index = size++;
            valIndexMap.put(val, index);
            indexValMap.put(index, val);
            return true;
        }
        return false;
    }
    
    public boolean remove(int val) {
        if (valIndexMap.containsKey(val)) {
            // 获取 val 的 index
            int index = valIndexMap.get(val);
            // 获取最后一个元素的 val，size 减一
            int lastVal = indexValMap.get(--size);
            // 将 val 的 index 对应的值更新成最后一个元素的 val
            indexValMap.put(index, lastVal);
            // 更新最后一个元素的 val 对应的 index
            valIndexMap.put(lastVal, index);
            // 移除原来最后一个元素的 val 的 index 对应的值
            indexValMap.remove(size);
            // 移除 val 对应的 index
            valIndexMap.remove(val);
            return true;
        }
        return false;
    }
    
    public int getRandom() {
        if (size == 0) {
            return -1;
        }
        return indexValMap.get((int) (Math.random() * size));
    }
}