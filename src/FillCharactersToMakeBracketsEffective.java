/**
 * 括号有效配对是指：
 * 1）任何一个左括号都能找到和其正确配对的右括号
 * 2）任何一个右括号都能找到和其正确配对的左括号
 * 有效的：    (())  ()()   (()())  等
 * 无效的：     (()   )(     等
 * 如果一个括号字符串无效，返回至少填几个字符能让其整体有效？
 */
public class FillCharactersToMakeBracketsEffective {
    public static int minCharactersNeedFill(String s) {
        int count = 0;
        // 记录需要添加的括号数
        int need = 0;
        for (int i = 0; i < s.length(); i ++) {
            if (s.charAt(i) == '(') {
                count ++;
            } else {
                count --;
                if (count == -1) {
                    /**
                     * count 为 -1 说明有一个未能匹配的右括号
                     * 此时给 need 加一，相当于添加一个左括号去匹配这个右括号
                     * 此时相当于所有括号都匹配上了，将 count 重置
                     */
                    need ++;
                    count ++;
                }
            }
        }
        // 最后再加上所有未匹配的左括号数量即可
        return need + count;
    }

    public static void main(String[] args) {
        minCharactersNeedFill(")))(((");
    }
}
