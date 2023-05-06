package binary_search;

public class BinarySearch {
    public static boolean exist(int[] sortedArr, int num) {
        if (sortedArr == null || sortedArr.length == 0) {
            return false;
        }
        int l = 0, r = sortedArr.length - 1;
        int mid;
        while (l <= r) {
            mid = l + ((r - l) >> 1);
            if (sortedArr[mid] > num) {
                r = mid - 1;
            } else if (sortedArr[mid] < num) {
                l = mid + 1;
            } else {
                return true;
            }
        }
        return false;
    }

    // 在 arr 上，找满足 >=num 的最左位置
    public static int leftmost(int[] sortedArr, int num) {
        int ans = -1;
        int l = 0, r = sortedArr.length - 1;
        int mid;
        while (l <= r) {
            mid = l + ((r - l) >> 1);
            if (sortedArr[mid] >= num) {
                ans = mid;
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return ans;
    }

    // 在 arr 上，找满足 <=num 的最右位置
    public static int rightmost(int[] sortedArr, int num) {
        int ans = -1;
        int l = 0, r = sortedArr.length - 1;
        int mid;
        while (l <= r) {
            mid = l + ((r - l) >> 1);
            if (sortedArr[mid] <= num) {
                ans = mid;
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] sortedArr = {1, 3, 4, 5, 8, 10, 15, 20};
        System.out.println(exist(sortedArr, 5));
        System.out.println(leftmost(sortedArr, 9));
        System.out.println(rightmost(sortedArr, 10));
    }
}
