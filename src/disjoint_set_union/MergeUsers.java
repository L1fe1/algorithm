package disjoint_set_union;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 如果两个 user，a 字段一样、或者 b 字段一样、或者 c 字段一样，就认为是一个人
 * 请合并 users。返回合并之后的用户数量
 */
public class MergeUsers {

    public static class User {
        private String a;
        private String b;
        private String c;

        public User(String a, String b, String c) {
            this.a = a;
            this.b = b;
            this.c = c;
        }
    }

    public static int mergeUsers(List<User> users) {
        UnionFind.UnionSet<User> unionSet = new UnionFind.UnionSet<>(users);
        // 字段 a 的值 -> user map
        Map<String, User> mapA = new HashMap<>();
        // 字段 b 的值 -> user map
        Map<String, User> mapB = new HashMap<>();
        // 字段 b 的值 -> user map
        Map<String, User> mapC = new HashMap<>();
        users.forEach(user -> {
            // 如果 user 的字段 a 或字段 b 或字段 c 存在（相同），合并；否则将它加入 mapA 或 mapB 或 mapC
            if (mapA.containsKey(user.a)) {
                unionSet.union(mapA.get(user.a), user);
            } else {
                mapA.put(user.a, user);
            }
            if (mapB.containsKey(user.b)) {
                unionSet.union(mapB.get(user.b), user);
            } else {
                mapB.put(user.b, user);
            }
            if (mapC.containsKey(user.c)) {
                unionSet.union(mapC.get(user.c), user);
            } else {
                mapC.put(user.c, user);
            }
        });
        // 并查集的集合数量就是合并之后的用户数量
        return unionSet.sizeMap.size();
    }

}
