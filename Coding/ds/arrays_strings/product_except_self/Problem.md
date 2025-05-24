## Problem Statement:

Given an integer array `nums`, return an array output such that `output[i]` is equal to the product of all the elements of nums except `nums[i]`.

**⚠️ Solve without using division and in O(n) time.**

Constraints:

- `2 <= nums.length <= 10^5`
- `-30 <= nums[i] <= 30`

The product of any prefix or suffix will fit in a 32-bit integer

**Do not use division**

Achieve `O(n)` time complexity

Use only `constant space` (`excluding the output array`)

Example 1:

```makefile
Input:  nums = [1,2,3,4]
Output: [24,12,8,6]
```

Example 2:

```makefile
Input:  nums = [-1,1,0,-3,3]
Output: [0,0,9,0,0]
```
