package searchInRotatedSortedArray;

public class Solution {
    public static void main(String[] args) {
        int[] nums = {1, 3, 3};
        System.out.println(new Solution().findMinDup(nums));
    }

    // what if there's dup?
    public int findMinDup(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            // right half sorted, min is either [mid] or in left half
            if (nums[mid] < nums[right]) {
                if (mid == 0 || nums[mid] < nums[mid - 1]) {
                    return nums[mid];
                } else {
                    right = mid - 1;
                }
            }
            // left part sorted min is either [left] or in right half
            else if (nums[mid] > nums[left]) {
                // left to right is sorted
                if (nums[left] < nums[right]) {
                    return nums[left];
                } else {
                    left = mid + 1;
                }
            }
            // we can't skip left and right, might be 1,1,1,2,3,4,5,1,1,1
            else {
                left++;
            }
        }
        return nums[left];
    }

    // find min in the array
    public int findMin(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            // right half sorted, min is either [mid] or in left half
            if (nums[mid] <= nums[right]) {
                if (mid == 0 || nums[mid] < nums[mid - 1]) {
                    return nums[mid];
                } else {
                    right = mid - 1;
                }
            }
            // left part sorted min is either [left] or in right half
            else {
                // left to right is sorted
                if (nums[left] < nums[right]) {
                    return nums[left];
                } else {
                    left = mid + 1;
                }
            }
        }
        return nums[left];
    }


    // 1) bSerarch end is the actual last index
    // 2) while loop needs check equal i.e (start<=end) - think about a single element array
    public int search2(int[] A, int target) {
        int start = 0, end = A.length - 1;
        while (start <= end) {
            int mid = (start + end) / 2;
            if (A[mid] == target)
                return mid;
            // left sorted
            if (A[start] <= A[mid]) {
                // go left
                if (A[start] <= target && target < A[mid]) {
                    end = mid - 1;
                } else {
                    start = mid + 1;
                }
            }
            // right sorted
            else {
                // go right
                if (A[mid] < target && target <= A[end]) {
                    start = mid + 1;
                } else {
                    end = mid - 1;
                }
            }
        }
        return -1;
    }

    public int search(int[] A, int target) {
        // don't recurse when you can use while!
        int l = 0, r = A.length - 1;

        while (l <= r) {
            int m = (l + r) / 2;
            if (A[m] == target)
                return m;
            // left half sorted
            // when A[l] == A[m], we have reach left bound, need to go right
            if (A[l] <= A[m]) {
                // we can definitely go left, go left
                if (A[l] <= target && target < A[m]) {
                    r = m - 1;
                } else {
                    l = m + 1;
                }
            }
            // right half sorted
            else {
                // we can definitely go right, go right
                if (A[m] < target && target <= A[r]) {
                    l = m + 1;
                } else {
                    r = m - 1;
                }
            }
        }
        return -1;
    }

    public int searchRec(int[] A, int target) {
        return doSearch(A, 0, A.length - 1, target);
    }

    // compare [target, mid[, and [target, left] or [target, right]
    // to decide go left or right

    // note this works for regular sorted array as well
    int doSearch(int[] A, int start, int end, int target) {
        if (start > end)
            return -1;
        int mid = (start + end) / 2;
        if (A[mid] == target)
            return mid;
        if (target > A[mid]) {
            // left half must be smaller than target, need to go right
            if (A[mid] >= A[start]) {
                return doSearch(A, mid + 1, end, target);
            }
            // break point is at left half, need to consider two scenarios
            else {
                // A[mid] < A[start] <= target, go left
                if (target >= A[start]) {
                    return doSearch(A, start, mid - 1, target);
                }
                // A[mid] < target < A[start], go right
                else {
                    return doSearch(A, mid + 1, end, target);
                }
            }
        } else {
            // right half must be bigger than target, go left
            if (A[mid] <= A[end]) {
                return doSearch(A, start, mid - 1, target);
            } else {
                // target <= A[end] < A[mid], go right
                if (target <= A[end]) {
                    return doSearch(A, mid + 1, end, target);
                }
                // A[end] < target < A[mid], go left
                else {
                    return doSearch(A, start, mid - 1, target);
                }
            }
        }
    }

}
