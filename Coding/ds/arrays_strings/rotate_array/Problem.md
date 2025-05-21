## Given an integer array nums, rotate the array to the right by k steps, where k is non-negative.

Constraints:

- `1 <= nums.length <= 10^5`
- `-2^31 <= nums[i] <= 2^31 - 1`
- `0 <= k <= 10^5`

Try to do it in-place with `O(1)` extra space

Example 1:

```makefile
Input: nums = [1,2,3,4,5,6,7], k = 3
Output: [5,6,7,1,2,3,4]
```

Example 2:

```makefile
Input: nums = [-1,-100,3,99], k = 2
Output: [3,99,-1,-100]
```
