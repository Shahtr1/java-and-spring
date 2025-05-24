Problem Statement:

You are given two integer arrays `nums1` and `nums2`, sorted in non-decreasing order, and two integers `m` and `n`, representing the number of elements in `nums1` and `nums2` respectively.

Merge `nums2` into `nums1` as one sorted array in-place.
You may assume that `nums1` has enough space (size that is equal to `m + n`) to hold additional elements from `nums2`.

Constraints:

- `nums1.length == m + n`
- `nums2.length == n`
- `0 <= m, n <= 200`
- `-10^9 <= nums1[i], nums2[i] <= 10^9`
- All elements in `nums1` and `nums2` are sorted in non-decreasing order.

Example 1:

```makefile
Input:
nums1 = [1,2,3,0,0,0], m = 3
nums2 = [2,5,6], n = 3

Output:
[1,2,2,3,5,6]
```

Example 2:

```makefile
Input:
nums1 = [1], m = 1
nums2 = [], n = 0

Output:
[1]
```
