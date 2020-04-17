```
Given an array A of integers, return the length of the longest arithmetic subsequence in A.
Recall that a subsequence of A is a list A[i_1], A[i_2], ..., A[i_k] with 0 <= i_1 < i_2 < ... < i_k <= A.length - 1, and that a sequence B is arithmetic if B[i+1] - B[i] are all the same value (for 0 <= i < B.length - 1).

Example 1:

Input: [3,6,9,12]
Output: 4
Explanation: 
The whole array is an arithmetic sequence with steps of length = 3.

Example 2:

Input: [9,4,7,2,10]
Output: 3
Explanation: 
The longest arithmetic subsequence is [4,7,10].

Example 3:

Input: [20,1,15,3,10,5,8]
Output: 4
Explanation: 
The longest arithmetic subsequence is [20,15,10,5].

```

```
class Solution {
    public int longestArithSeqLength(int[] A) {
        Map<Integer, Map<Integer, Integer>> dp = new HashMap<>();
        int res = 2;
        for(int i = 0 ; i < A.length; i ++){
            for(int j = i+1; j < A.length; j++){
                Map<Integer, Integer> m = dp.computeIfAbsent(A[j]-A[i], d->new HashMap<>());
                m.put(j, m.getOrDefault(i, 1) + 1);
                res = Math.max(res, m.get(j));
            }
        }
        return res;
    }
}

```

```
Given a binary tree, determine if it is a complete binary tree.

Definition of a complete binary tree from Wikipedia:
In a complete binary tree every level, except possibly the last, is completely filled, and all nodes in the last level are as far left as possible. It can have between 1 and 2h nodes inclusive at the last level h.

 

Example 1:

Input: [1,2,3,4,5,6]
Output: true
Explanation: Every level before the last is full (ie. levels with node-values {1} and {2, 3}), and all nodes in the last level ({4, 5, 6}) are as far left as possible.

Example 2:

Input: [1,2,3,4,5,null,7]
Output: false
Explanation: The node with value 7 isn't as far left as possible.

```

```
class Solution {
    public boolean isCompleteTree(TreeNode root) {
        if(root == null)
            return true;
        Queue<TreeNode> q = new LinkedList<TreeNode>();
        q.offer(root);
        
        boolean missing = false;
        while(!q.isEmpty()){
                TreeNode node = q.poll();
                if(node != null){
                    if(missing) return false;
                    q.offer(node.left);
                    q.offer(node.right);
                }else{
                    missing = true;
                }
        }
        return true;
    }
}
```


```
Given two arrays, write a function to compute their intersection.

Example 1:

Input: nums1 = [1,2,2,1], nums2 = [2,2]
Output: [2,2]

Example 2:

Input: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
Output: [4,9]

Note:

    Each element in the result should appear as many times as it shows in both arrays.
    The result can be in any order.

Follow up:

    What if the given array is already sorted? How would you optimize your algorithm?
    What if nums1's size is small compared to nums2's size? Which algorithm is better?
    What if elements of nums2 are stored on disk, and the memory is limited such that you cannot load all elements into the memory at once?


```


```
class Solution {
    public int[] intersect(int[] nums1, int[] nums2) {
        if(nums1 == null && nums2 == null)
            return nums1;
        
        Map<Integer, Integer> map = new HashMap<>();
        if(nums1.length > nums2.length){
            for(int i : nums1){
                map.put(i, map.getOrDefault(i, 0) + 1);
            }
            return answer(nums2, map);
        }else{
            for(int i : nums2){
                map.put(i, map.getOrDefault(i, 0) + 1);
            }
            return answer(nums1, map);
        }
    }
    
    public int [] answer(int [] nums, Map<Integer, Integer> map){
        List<Integer> result = new ArrayList<>();
        for(int i : nums){
            if(map.containsKey(i) && map.get(i) > 0){
                result.add(i);
                map.put(i, map.get(i)-1);
            }
        }
        int [] ans = new int[result.size()];
        for(int i = 0 ; i < result.size() ; i++){
            ans[i] = result.get(i);
        }
        return ans;
    }
}
```
