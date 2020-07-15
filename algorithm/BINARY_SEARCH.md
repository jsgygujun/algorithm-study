# 二分查找

三个二分查找场景：
* 搜索特定值
* 搜索特定值左侧边界
* 搜索特定值右侧边界

## 一、二分查找框架

## 2.1 搜索特定值

* 左闭右开

  ```java
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
  ```

* 左闭右闭

  ```java
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
  ```

### 2.2 搜索特定值左右界

定值的左右界：假如给定一个有序数组 `nums = {1,2,3,4,4,4,5,6}`，搜索特定值 `target=4`，则索引3和索引5即为数值4的左右界。

* 搜索左侧边界

  ```java
  // 搜索左侧边界，左闭右开
  int binarySearchLeftBound(int[] nums, int target) {
    int lo = 0, hi = nums.length;
    while (lo < hi) {
      int mid = lo + (hi-lo)/2;
      if (nums[mid] == target) hi = mid; // 搜索左边界，找到target不要立即返回，而是缩小「搜索区间」的上界hi
      else if (nums[mid] < target) lo = mid + 1;
      else hi = mid;
    }
    return lo < nums.length && nums[lo] == target ? lo : -1;
  }
  ```

* 搜索右侧边界

  ```java
  // 搜索右侧边界，左闭右开
  int binarySearchRightBound(int[] nums, int target) {
    int lo = 0, hi = nums.length;
    while (lo < hi) {
      int mid = lo + (hi-lo)/2;
      if (nums[mid] == target) lo = mid + 1; // 搜索右边界，找到target不要立即返回，而是缩小「搜索区间」的下界lo
      else if (nums[mid] < target) lo = mid + 1;
      else hi = mid;
    }
    return lo > 0 && nums[lo-1] == target ? (lo-1) : -1; // 当搜索条件终止时，nums[lo]一定不等于target，而nums[left-1]可能是target
  }
  ```

  ## 经典习题
  
  * [LeetCode-34在排序数组中查找元素的第一个和最后一个位置](https://github.com/jsgygujun/leetcode-report/blob/master/src/main/java/com/jsgygujun/code/problem/_00001_00100/_00034/Solution.java)