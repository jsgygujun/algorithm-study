package com.jsgygujun.algorighm.code;


import org.junit.Assert;
import org.junit.Test;

/**
 * @author gujun@qiyi.com
 * @since 2020/7/15 3:15 下午
 */
public class BinarySearch {

    // 二分查找，左闭右开
    int binarySearch(int[] nums, int target) {
        int lo = 0, hi = nums.length; // 搜索空间[lo, hi)
        while (lo < hi) { // 搜索条件： 搜索空间不为空
            int mid = lo + (hi-lo)/2;
            if (nums[mid] == target) return mid;
            // 此时 mid 将搜索空间分成了[lo,mid) (mid,hi) 也即： [lo,mid) [mid+1,hi)
            else if (nums[mid] < target) lo = mid + 1; // 继续搜索右半区间
            else hi = mid; // 继续搜索左半区间
        }
        return -1;
    }

    // 二分查找，左闭右闭
    int binarySearch2(int[] nums, int target) {
        int lo = 0, hi = nums.length; // 搜索空间[lo, hi]
        while (lo <= hi) { // 搜索条件： 搜索空间不为空
            int mid = lo + (hi-lo)/2;
            if (nums[mid] == target) return mid;
            // 此时 mid 将搜索空间分成了[lo,mid) (mid,hi] 也即： [lo,mid-1] [mid+1,hi]
            else if (nums[mid] < target) lo = mid + 1;
            else hi = mid - 1 ;
        }
        return -1;
    }

    // 搜索左侧边界，左闭右开
    int binarySearchLeftBound(int[] nums, int target) {
        int lo = 0, hi = nums.length;
        while (lo < hi) {
            int mid = lo + (hi-lo)/2;
            if (nums[mid] == target) hi = mid; // 搜索左边界，找到target不要立即返回，而是缩小「搜索区间」的上界hi
            else if (nums[mid] < target) lo = mid + 1;
            else hi = mid;
        }
        return nums[lo] == target ? lo : -1;
    }

    // 搜索右侧边界，左闭右开
    int binarySearchRightBound(int[] nums, int target) {
        int lo = 0, hi = nums.length;
        while (lo < hi) {
            int mid = lo + (hi-lo)/2;
            if (nums[mid] == target) lo = mid + 1; // 搜索右边界，找到target不要立即返回，而是缩小「搜索区间」的下界lo
            else if (nums[mid] < target) lo = mid + 1;
            else hi = mid;
        }
        return nums[lo-1] == target ? (lo-1) : -1; // 当搜索条件终止时，nums[lo]一定不等于target，而nums[left-1]可能是target
    }

    @Test
    public void test() {
        int[] nums = new int[]{1,2,3,4,5,6,7,8};
        BinarySearch binarySearch = new BinarySearch();
        Assert.assertEquals(4, binarySearch.binarySearch(nums, 5));
        Assert.assertEquals(0, binarySearch.binarySearch(nums, 1));
        Assert.assertEquals(7, binarySearch.binarySearch(nums, 8));
        Assert.assertEquals(-1, binarySearch.binarySearch(nums, 0));
    }

    @Test
    public void test2() {
        int[] nums = new int[]{1,2,3,4,5,6,7,8};
        BinarySearch binarySearch = new BinarySearch();
        Assert.assertEquals(4, binarySearch.binarySearch2(nums, 5));
        Assert.assertEquals(0, binarySearch.binarySearch2(nums, 1));
        Assert.assertEquals(7, binarySearch.binarySearch2(nums, 8));
        Assert.assertEquals(-1, binarySearch.binarySearch2(nums, 0));
    }

    @Test
    public void test3() {
        int[] nums = new int[]{1,2,3,4,4,4,5,6};
        BinarySearch binarySearch = new BinarySearch();
        Assert.assertEquals(6, binarySearch.binarySearchLeftBound(nums, 5));
        Assert.assertEquals(6, binarySearch.binarySearchRightBound(nums, 5));
        Assert.assertEquals(3, binarySearch.binarySearchLeftBound(nums, 4));
        Assert.assertEquals(5, binarySearch.binarySearchRightBound(nums, 4));
        Assert.assertEquals(-1, binarySearch.binarySearchLeftBound(nums, 0));
        Assert.assertEquals(-1, binarySearch.binarySearchRightBound(nums, 7));
    }

}
