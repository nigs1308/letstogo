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

```
Given a string, find the length of the longest substring T that contains at most k distinct characters.

Example 1:

Input: s = "eceba", k = 2
Output: 3
Explanation: T is "ece" which its length is 3.

Example 2:

Input: s = "aa", k = 1
Output: 2
Explanation: T is "aa" which its length is 2.

```

```
class Solution {
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        if (s == null || s.length() == 0 || k <= 0) {
            return 0;
        }
         
        Map<Character, Integer> map = new HashMap<>();
        int i = 0;
        int j = 0;
        int maxLen = 0;
         
        for (i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (map.containsKey(c)) {
                int freq = map.get(c);
                map.put(c, freq + 1);
            } else {
                map.put(c, 1);
            }
             
            if (map.size() > k) {
                maxLen = Math.max(maxLen, i - j);
             
                // Shrink the window size
                while (map.size() > k) {
                    char endC = s.charAt(j);
                    int freq = map.get(endC);
                    if (freq == 1) {
                        map.remove(endC);
                    } else {
                        map.put(endC, freq - 1);
                    }
                    j++;
                }
            }
        }
         
        if (j < s.length()) {
            maxLen = Math.max(maxLen, i - j);
        }
         
        return maxLen;
    }
}
```

```
Island Perimeter

You are given a map in form of a two-dimensional integer grid where 1 represents land and 0 represents water.

Grid cells are connected horizontally/vertically (not diagonally). The grid is completely surrounded by water, and there is exactly one island (i.e., one or more connected land cells).

The island doesn't have "lakes" (water inside that isn't connected to the water around the island). One cell is a square with side length 1. The grid is rectangular, width and height don't exceed 100. Determine the perimeter of the island.

 

Example:

Input:
[[0,1,0,0],
 [1,1,1,0],
 [0,1,0,0],
 [1,1,0,0]]

Output: 16

Explanation: The perimeter is the 16 yellow stripes in the image below:

```

```
class Solution {
    public int islandPerimeter(int[][] grid) {
        int land = 0;
        int edge = 0;
        for(int i = 0; i < grid.length; i++){
            for (int j = 0 ; j < grid[i].length; j++){
                if(grid[i][j] == 1){
                    land++;
                    if(i > 0 && grid[i-1][j] == 1) {edge++;}
                    if(j > 0 && grid[i][j-1] == 1) {edge++;}
                }
            }
        }
        return land * 4 - edge * 2;
    }
}
```

```
Reorder List

Given a singly linked list L: L0→L1→…→Ln-1→Ln,
reorder it to: L0→Ln→L1→Ln-1→L2→Ln-2→…

You may not modify the values in the list's nodes, only nodes itself may be changed.

Example 1:

Given 1->2->3->4, reorder it to 1->4->2->3.

Example 2:

Given 1->2->3->4->5, reorder it to 1->5->2->4->3.

```

```
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public void reorderList(ListNode head) {
        if(head != null && head.next != null){
            ListNode slow = head;
            ListNode fast = head;
            while(fast != null && fast.next != null && fast.next.next != null){
                slow = slow.next;
                fast = fast.next.next;
            }
            ListNode second = slow.next;
            slow.next = null;
            second = reverseList(second);
            
            ListNode p1 = head;
            ListNode p2 = second;
            while(p2 != null){
                ListNode temp1 = p1.next;
                ListNode temp2 = p2.next;
                
                p1.next = p2;
                p2.next = temp1;
                
                p1 = temp1;
                p2 = temp2;
            }
        }
    }
    
    public ListNode reverseList(ListNode head){
        if(head == null || head.next == null)
            return head;
        ListNode prev = head;
        ListNode curr = head.next;
        while(curr != null){
            ListNode temp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = temp;
        }
        head.next = null;
        return prev;
    }
}
```

```
Clone Graph

Given a reference of a node in a connected undirected graph.

Return a deep copy (clone) of the graph.

Each node in the graph contains a val (int) and a list (List[Node]) of its neighbors.

class Node {
    public int val;
    public List<Node> neighbors;
}

 

Test case format:

For simplicity sake, each node's value is the same as the node's index (1-indexed). For example, the first node with val = 1, the second node with val = 2, and so on. The graph is represented in the test case using an adjacency list.

Adjacency list is a collection of unordered lists used to represent a finite graph. Each list describes the set of neighbors of a node in the graph.

The given node will always be the first node with val = 1. You must return the copy of the given node as a reference to the cloned graph.

 

Example 1:

Input: adjList = [[2,4],[1,3],[2,4],[1,3]]
Output: [[2,4],[1,3],[2,4],[1,3]]
Explanation: There are 4 nodes in the graph.
1st node (val = 1)'s neighbors are 2nd node (val = 2) and 4th node (val = 4).
2nd node (val = 2)'s neighbors are 1st node (val = 1) and 3rd node (val = 3).
3rd node (val = 3)'s neighbors are 2nd node (val = 2) and 4th node (val = 4).
4th node (val = 4)'s neighbors are 1st node (val = 1) and 3rd node (val = 3).

Example 2:

Input: adjList = [[]]
Output: [[]]
Explanation: Note that the input contains one empty list. The graph consists of only one node with val = 1 and it does not have any neighbors.

Example 3:

Input: adjList = []
Output: []
Explanation: This an empty graph, it does not have any nodes.

Example 4:

Input: adjList = [[2],[1]]
Output: [[2],[1]]

 

Constraints:

    1 <= Node.val <= 100
    Node.val is unique for each node.
    Number of Nodes will not exceed 100.
    There is no repeated edges and no self-loops in the graph.
    The Graph is connected and all nodes can be visited starting from the given node.

```

```
/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> neighbors;
    
    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }
    
    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }
    
    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}
*/

class Solution {
    public Node cloneGraph(Node node) {
        if(node == null ) return node;
        Map<Node, Node> map = new HashMap<>();
        Queue<Node> queue = new ArrayDeque<>();

        queue.offer(node);
        map.put(node, new Node(node.val, new ArrayList<>()));
        while (!queue.isEmpty()) {
            Node h = queue.poll();

            for (Node neighbor : h.neighbors) {
                if (!map.containsKey(neighbor)) {
                    map.put(neighbor, new Node(neighbor.val, new ArrayList<>()));
                    queue.offer(neighbor);
                }
                map.get(h).neighbors.add(map.get(neighbor));
            }
        }

        return map.get(node);
    }
}
```

```
Valid Palindrome

Given a string, determine if it is a palindrome, considering only alphanumeric characters and ignoring cases.

Note: For the purpose of this problem, we define empty string as valid palindrome.

Example 1:

Input: "A man, a plan, a canal: Panama"
Output: true

Example 2:

Input: "race a car"
Output: false

```

```
class Solution {
    public boolean isPalindrome(String s) {
        if(s == null || s.length() == 0)
            return true;
        
        s = s.toLowerCase();
        
        int i = 0;
        int j = s.length() - 1;
        while(i < j){
            while(i < j && (!(s.charAt(i) >= 'a' && s.charAt(i) <= 'z') && !(s.charAt(i) >= '0' && s.charAt(i) <= '9'))){
                i++;
            }
            
            while(i < j && (!(s.charAt(j) >= 'a' && s.charAt(j) <= 'z') && !(s.charAt(j) >= '0' && s.charAt(j) <= '9'))){
                j--;
            }
            System.out.println(s.charAt(i) + "-" + s.charAt(j));
            if(s.charAt(i) !=  s.charAt(j)){
                return false;
            }i++; j--;
            
        }
        return true;
        
    }
}
```

```
Valid Palindrome II

Given a non-empty string s, you may delete at most one character. Judge whether you can make it a palindrome.

Example 1:

Input: "aba"
Output: True

Example 2:

Input: "abca"
Output: True
Explanation: You could delete the character 'c'.

Note:

    The string will only contain lowercase characters a-z. The maximum length of the string is 50000.

```

```
class Solution {
    public boolean validPalindrome(String s) {
        if (s == null || s.length() == 0)
            return true;
        
        int i = 0, j = s.length()-1;
        while(i < j){
            if(s.charAt(i) != s.charAt(j)){
                return isPalindrome(s, i+1, j) || isPalindrome(s, i, j-1);
            } i++;j--;
        }
        return true;
    }
    
    public boolean isPalindrome(String s, int i , int j){
        while(i < j){
            if(s.charAt(i++) != s.charAt(j--)){
                return false;
            }
        }
        return true;
    }
}
```

```
Expression Add Operators

Given a string that contains only digits 0-9 and a target value, return all possibilities to add binary operators (not unary) +, -, or * between the digits so they evaluate to the target value.

Example 1:

Input: num = "123", target = 6
Output: ["1+2+3", "1*2*3"] 

Example 2:

Input: num = "232", target = 8
Output: ["2*3+2", "2+3*2"]

Example 3:

Input: num = "105", target = 5
Output: ["1*0+5","10-5"]

Example 4:

Input: num = "00", target = 0
Output: ["0+0", "0-0", "0*0"]

Example 5:

Input: num = "3456237490", target = 9191
Output: []

```

```
class Solution {
    public List<String> addOperators(String num, int target) {
        List<String> list = new ArrayList<>();
        addOperators(num, target, 0, 0, "", list);
        return list;
    }
    
    private void addOperators(String num, long target, int start, long prevItem, String exp, List<String> list) {
        if (target == 0 && start == num.length()) {
            list.add(exp);
            return;
        }
        
        for (int i = start; i < num.length(); i++) {
            if (i > start && num.charAt(start) == '0') {
                return;
            }
            
            long n = Long.parseLong(num.substring(start, i + 1));
            if (start == 0) {
                addOperators(num, target - n, i + 1, n, exp + n, list);
            } else {
                addOperators(num, target - n, i + 1, n, exp + "+" + n, list);
                addOperators(num, target + n, i + 1, -n, exp + "-" + n, list);
                addOperators(num, target + prevItem - prevItem * n, i + 1, prevItem * n, exp + "*" + n, list);
            }
        }
    }
}

```

```
Intersection of Two Arrays II

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

```
Longest Substring with At Most K Distinct Characters

Given a string, find the length of the longest substring T that contains at most k distinct characters.

Example 1:

Input: s = "eceba", k = 2
Output: 3
Explanation: T is "ece" which its length is 3.

Example 2:

Input: s = "aa", k = 1
Output: 2
Explanation: T is "aa" which its length is 2.

```

```
class Solution {
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        if (s == null || s.length() == 0 || k <= 0) {
            return 0;
        }
         
        Map<Character, Integer> map = new HashMap<>();
        int i = 0;
        int j = 0;
        int maxLen = 0;
         
        for (i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (map.containsKey(c)) {
                int freq = map.get(c);
                map.put(c, freq + 1);
            } else {
                map.put(c, 1);
            }
             
            if (map.size() > k) {
                maxLen = Math.max(maxLen, i - j);
             
                // Shrink the window size
                while (map.size() > k) {
                    char endC = s.charAt(j);
                    int freq = map.get(endC);
                    if (freq == 1) {
                        map.remove(endC);
                    } else {
                        map.put(endC, freq - 1);
                    }
                    j++;
                }
            }
        }
         
        if (j < s.length()) {
            maxLen = Math.max(maxLen, i - j);
        }
         
        return maxLen;
    }
}
```

```
Longest Arithmetic Sequence

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

 

Note:

    2 <= A.length <= 2000
    0 <= A[i] <= 10000

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
Check Completeness of a Binary Tree

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

 

Note:

    The tree will have between 1 and 100 nodes.


```

```
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
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
Bulb Switcher

There are n bulbs that are initially off. You first turn on all the bulbs. Then, you turn off every second bulb. On the third round, you toggle every third bulb (turning on if it's off or turning off if it's on). For the i-th round, you toggle every i bulb. For the n-th round, you only toggle the last bulb. Find how many bulbs are on after n rounds.

Example:

Input: 3
Output: 1 
Explanation: 
At first, the three bulbs are [off, off, off].
After first round, the three bulbs are [on, on, on].
After second round, the three bulbs are [on, off, on].
After third round, the three bulbs are [on, off, off]. 

So you should return 1, b
```

```
class Solution {
    public int bulbSwitch(int n) {
         return (int)Math.sqrt(n);
    }
}
```


```
Range Sum Query 2D - Immutable

Given a 2D matrix matrix, find the sum of the elements inside the rectangle defined by its upper left corner (row1, col1) and lower right corner (row2, col2).

Range Sum Query 2D
The above rectangle (with the red border) is defined by (row1, col1) = (2, 1) and (row2, col2) = (4, 3), which contains sum = 8.

Example:

Given matrix = [
  [3, 0, 1, 4, 2],
  [5, 6, 3, 2, 1],
  [1, 2, 0, 1, 5],
  [4, 1, 0, 1, 7],
  [1, 0, 3, 0, 5]
]

sumRegion(2, 1, 4, 3) -> 8
sumRegion(1, 1, 2, 2) -> 11
sumRegion(1, 2, 2, 4) -> 12

Note:

    You may assume that the matrix does not change.
    There are many calls to sumRegion function.
    You may assume that row1 ≤ row2 and col1 ≤ col2.

```

```
class NumMatrix {
    private int [][] dp;
    public NumMatrix(int[][] matrix) {
        if(matrix.length == 0 || matrix[0].length == 0) return;
        dp = new int [matrix.length][matrix[0].length+1];
        for(int r = 0; r < matrix.length; r++){
            for(int c = 0 ; c < matrix[0].length; c++){
                dp[r][c+1] = dp[r][c] + matrix[r][c];
            }
        }
    }
    
    public int sumRegion(int row1, int col1, int row2, int col2) {
        int sum = 0;
        for(int row = row1; row <= row2; row++){
            sum += dp[row][col2+1] - dp[row][col1];
        }
        return sum;
    }
}

/**
 * Your NumMatrix object will be instantiated and called as such:
 * NumMatrix obj = new NumMatrix(matrix);
 * int
 */
 
 ```

```
Given two arrays, write a function to compute their intersection.

Example 1:

Input: nums1 = [1,2,2,1], nums2 = [2,2]
Output: [2]

Example 2:

Input: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
Output: [9,4]

Note:

    Each element in the result must be unique.
    The result can be in any order.
```

```
class Solution {
    public int[] intersection(int[] nums1, int[] nums2) {
        HashSet<Integer> set1 = new HashSet<Integer>();
        for(int i: nums1){
            set1.add(i);
        }

        HashSet<Integer> set2 = new HashSet<Integer>();
        for(int i: nums2){
            if(set1.contains(i)){
                set2.add(i);
            }
        }

        int[] result = new int[set2.size()];
        int i=0;
        for(int n: set2){
            result[i++] = n;
        }

        return result;
    }
}
```


```
Smallest Subtree with all the Deepest Nodes

Given a binary tree rooted at root, the depth of each node is the shortest distance to the root.

A node is deepest if it has the largest depth possible among any node in the entire tree.

The subtree of a node is that node, plus the set of all descendants of that node.

Return the node with the largest depth such that it contains all the deepest nodes in its subtree.

 

Example 1:

Input: [3,5,1,6,2,0,8,null,null,7,4]
Output: [2,7,4]
Explanation:



We return the node with value 2, colored in yellow in the diagram.
The nodes colored in blue are the deepest nodes of the tree.
The input "[3, 5, 1, 6, 2, 0, 8, null, null, 7, 4]" is a serialization of the given tree.
The output "[2, 7, 4]" is a serialization of the subtree rooted at the node with value 2.
Both the input and output have TreeNode type.

 

Note:

    The number of nodes in the tree will be between 1 and 500.
    The values of each node are unique.

```


```
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public TreeNode subtreeWithAllDeepest(TreeNode root) {
        return dfs(root).node;
    }
    
    public Result dfs(TreeNode node){
        if(node == null) return new Result(null, 0);
        Result l = dfs(node.left), r = dfs(node.right);
        if(l.dist > r.dist){
            return new Result(l.node, l.dist+1);   
        }
        if(l.dist < r.dist){
            return new Result(r.node, r.dist+1);
        }
        return new Result(node, l.dist+1);
        
    }
    
    class Result{
        TreeNode node;
        int dist;
        Result(TreeNode n, int d){
            node = n;
            dist = d;
        }
    }
}

```

```
Verifying an Alien Dictionary
Easy

In an alien language, surprisingly they also use english lowercase letters, but possibly in a different order. The order of the alphabet is some permutation of lowercase letters.

Given a sequence of words written in the alien language, and the order of the alphabet, return true if and only if the given words are sorted lexicographicaly in this alien language.

 

Example 1:

Input: words = ["hello","leetcode"], order = "hlabcdefgijkmnopqrstuvwxyz"
Output: true
Explanation: As 'h' comes before 'l' in this language, then the sequence is sorted.

Example 2:

Input: words = ["word","world","row"], order = "worldabcefghijkmnpqstuvxyz"
Output: false
Explanation: As 'd' comes after 'l' in this language, then words[0] > words[1], hence the sequence is unsorted.

Example 3:

Input: words = ["apple","app"], order = "abcdefghijklmnopqrstuvwxyz"
Output: false
Explanation: The first three characters "app" match, and the second string is shorter (in size.) According to lexicographical rules "apple" > "app", because 'l' > '∅', where '∅' is defined as the blank character which is less than any other character (More info).

 

Constraints:

    1 <= words.length <= 100
    1 <= words[i].length <= 20
    order.length == 26
    All characters in words[i] and order are English lowercase letters.

```

```
class Solution {
    int [] table;
    public boolean isAlienSorted(String[] words, String order) {
        table = new int [26];
        for(int i = 0 ; i < order.length(); i++){
            table[order.charAt(i) - 'a'] = i;
        }
        
        for(int i = 0 ; i < words.length-1 ; i++){
            if(isBigger(words[i], words[i+1])){
                return false;
            }
        }
        return true;
    }
    
    private boolean isBigger(String s1, String s2){
        int length = Math.min(s1.length(), s2.length());
        for(int i = 0 ; i < length ; i++){
            char c1 = s1.charAt(i);
            char c2 = s2.charAt(i);
            if(c1 != c2) return table[c1-'a'] > table[c2 - 'a'];
        }
        return s1.length() > s2.length();
    }
}
```


```
 Binary Tree Right Side View
Medium

Given a binary tree, imagine yourself standing on the right side of it, return the values of the nodes you can see ordered from top to bottom.

Example:

Input: [1,2,3,null,5,null,4]
Output: [1, 3, 4]
Explanation:

   1            <---
 /   \
2     3         <---
 \     \
  5     4       <---

```

```
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public List<Integer> rightSideView(TreeNode root) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        if(root == null) return result;
        
        LinkedList<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(root);
        
        while(queue.size() > 0){
            int size = queue.size();
            for(int i = 0 ; i < size; i ++){
                TreeNode top = queue.remove();
                if( i == 0){
                    result.add(top.val);
                }
                
                // right first
                if(top.right != null){
                    queue.add(top.right);
                }
                
                if(top.left != null){
                    queue.add(top.left);
                }
            }
        }
        return result;
    }
}

```

```
Permutation in String
Medium

Given two strings s1 and s2, write a function to return true if s2 contains the permutation of s1. In other words, one of the first string's permutations is the substring of the second string.

 

Example 1:

Input: s1 = "ab" s2 = "eidbaooo"
Output: True
Explanation: s2 contains one permutation of s1 ("ba").

Example 2:

Input:s1= "ab" s2 = "eidboaoo"
Output: False

 

Note:

    The input strings only contain lower case letters.
    The length of both given strings is in range [1, 10,000].


```

```
class Solution {
    public boolean checkInclusion(String s1, String s2) {
        if(s1.length() > s2.length())
            return false;
        int [] s1map = new int[26];
        for(int i = 0; i < s1.length() ; i++){
            s1map[s1.charAt(i) - 'a']++;
        }
        for(int i = 0; i <= s2.length()-s1.length() ; i ++){
            int [] s2map = new int[26];
            for(int j = 0; j < s1.length() ; j ++){
                s2map[s2.charAt(i+j)-'a']++;
            }
            if(matches(s1map, s2map))
                return true;
        }
        return false;
    }
    
    public boolean matches(int [] s1map, int [] s2map){
        for(int i = 0 ; i < 26; i ++){
            if(s1map[i] != s2map[i])
                return false;
        }
        return true;
    }
}
```


```
Given a binary tree, find its minimum depth.

The minimum depth is the number of nodes along the shortest path from the root node down to the nearest leaf node.

Note: A leaf is a node with no children.

Example:

Given binary tree [3,9,20,null,null,15,7],

    3
   / \
  9  20
    /  \
   15   7

return its minimum depth = 2.

```

```
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public int minDepth(TreeNode root) {
        if(root == null)
            return 0;
        
        LinkedList<Result> nodes = new LinkedList<>();
        nodes.add(new Result(root, 1));
        while(!nodes.isEmpty()){
            Result curr = nodes.remove();
            TreeNode node = curr.node;
            int count = curr.count;
            if(node.left == null && node.right == null){
                return count;
            }
            if(node.left != null){
                nodes.add(new Result(node.left, count+1));
            }
            
            if(node.right != null){
                nodes.add(new Result(node.right, count+1));
            }
        }
        return 0;

    }
    
    class Result {
        int count;
        TreeNode node;
        public Result(TreeNode node, int count){
            this.count = count;
            this.node = node;
        }
    }
}

```

```
Given an array of integers with possible duplicates, randomly output the index of a given target number. You can assume that the given target number must exist in the array.

Note:
The array size can be very large. Solution that uses too much extra space will not pass the judge.

Example:

int[] nums = new int[] {1,2,3,3,3};
Solution solution = new Solution(nums);

// pick(3) should return either index 2, 3, or 4 randomly. Each index should have equal probability of returning.
solution.pick(3);

// pick(1) should return 0. Since in the array only nums[0] is equal to 1.
solution.pick(1);

```

```
class Solution {
    private Random rand;
    private int[] nums;
    private int k = 1;

    public Solution(int[] nums) {
        rand = new Random();
        this.nums = nums;
    }
    
    public int pick(int target) {
        int result = -1;
        int count = 0;
        for(int i = 0 ; i < nums.length; i ++){
            if(nums[i] == target){
                count++;
                if(rand.nextInt(count) < k){
                    result = i ;
                }
            }
        }
        return result;
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(nums);
 * int param_1 = obj.pick(target);
 */
 ```

```
A sentence S is given, composed of words separated by spaces. Each word consists of lowercase and uppercase letters only.

We would like to convert the sentence to "Goat Latin" (a made-up language similar to Pig Latin.)

The rules of Goat Latin are as follows:

    If a word begins with a vowel (a, e, i, o, or u), append "ma" to the end of the word.
    For example, the word 'apple' becomes 'applema'.
     
    If a word begins with a consonant (i.e. not a vowel), remove the first letter and append it to the end, then add "ma".
    For example, the word "goat" becomes "oatgma".
     
    Add one letter 'a' to the end of each word per its word index in the sentence, starting with 1.
    For example, the first word gets "a" added to the end, the second word gets "aa" added to the end and so on.

Return the final sentence representing the conversion from S to Goat Latin. 

 

Example 1:

Input: "I speak Goat Latin"
Output: "Imaa peaksmaaa oatGmaaaa atinLmaaaaa"

Example 2:

Input: "The quick brown fox jumped over the lazy dog"
Output: "heTmaa uickqmaaa rownbmaaaa oxfmaaaaa umpedjmaaaaaa overmaaaaaaa hetmaaaaaaaa azylmaaaaaaaaa ogdmaaaaaaaaaa"

 

Notes:

    S contains only uppercase, lowercase and spaces. Exactly one space between each word.
    1 <= S.length <= 150.


```

```
class Solution {
    public String toGoatLatin(String S) {
        Set<Character> vowel = new HashSet();
        for (char c: new char[]{'a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U'})
            vowel.add(c);
        
        int t = 1;
        StringBuilder ans = new StringBuilder();
        for(String word : S.split(" ")){
            char first = word.charAt(0);
            if(vowel.contains(first)){
                ans.append(word);
            }else{
                ans.append(word.substring(1));
                ans.append(word.subSequence(0, 1));
            }
            ans.append("ma");
            for(int i = 0 ; i < t; i ++){
                ans.append("a");
            }
            t++;
            ans.append(" ");
        }
        ans.deleteCharAt(ans.length()-1);
        return ans.toString();
    }
}
```

```
Given two integers representing the numerator and denominator of a fraction, return the fraction in string format.

If the fractional part is repeating, enclose the repeating part in parentheses.

Example 1:

Input: numerator = 1, denominator = 2
Output: "0.5"

Example 2:

Input: numerator = 2, denominator = 1
Output: "2"

Example 3:

Input: numerator = 2, denominator = 3
Output: "0.(6)"

```

```
class Solution {
    public String fractionToDecimal(int numerator, int denominator) {
        if(numerator == 0)
            return "0";
        
        if(denominator == 0)
            return "";
        
        String result = "";
        if((numerator < 0) ^ (denominator < 0)){
            result += "-";
        }
        
        // convert int to long
        long num = numerator, den = denominator;
        num = Math.abs(num);
        den = Math.abs(den);
        
        // quotient
        long res = num/den;
        result +=  String.valueOf(res);
        
        long remainder = (num%den) * 10;
        if(remainder == 0)
            return result;
        HashMap<Long, Integer> map = new HashMap<>();
        result += ".";
        
        while(remainder != 0){
            if(map.containsKey(remainder)){
                int beg = map.get(remainder);
                String part1 = result.substring(0, beg);
                String part2 = result.substring(beg, result.length());
                result = part1 + "(" + part2 + ")";
                return result;
            }
            map.put(remainder, result.length());
            res = remainder/den;
            result += String.valueOf(res);
            remainder = (remainder % den) * 10;
        }
        return result;
    }
}
```

```
Move Zeroes

Given an array nums, write a function to move all 0's to the end of it while maintaining the relative order of the non-zero elements.

Example:

Input: [0,1,0,3,12]
Output: [1,3,12,0,0]

Note:

    You must do this in-place without making a copy of the array.
    Minimize the total number of operations.

```

```
class Solution {
    public void moveZeroes(int[] nums) {
        int c = 0;
        for(int i : nums){
            if(i != 0){
                nums[c++] = i;
            }
        }
        
        while(c < nums.length){
            nums[c++] = 0;
        }
    }
}
```


```
Subarray Sum Equals K

Given an array of integers and an integer k, you need to find the total number of continuous subarrays whose sum equals to k.

Example 1:

Input:nums = [1,1,1], k = 2
Output: 2

Note:

    The length of the array is in range [1, 20,000].
    The range of numbers in the array is [-1000, 1000] and the range of the integer k is [-1e7, 1e7].

```

```
class Solution {
    public int subarraySum(int[] nums, int k) {
        int count = 0 , sum = 0;
        HashMap<Integer, Integer> map = new HashMap<> ();
        map.put(0, 1);
        for(int i = 0; i < nums.length; i ++){
            sum += nums[i];
            if(map.containsKey(sum-k))
                count += map.get(sum-k);
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        return count;
    }
}
```

```
Given a binary tree, return the vertical order traversal of its nodes' values. (ie, from top to bottom, column by column).

If two nodes are in the same row and column, the order should be from left to right.

Examples 1:

Input: [3,9,20,null,null,15,7]

   3
  /\
 /  \
 9  20
    /\
   /  \
  15   7 

Output:

[
  [9],
  [3,15],
  [20],
  [7]
]

Examples 2:

Input: [3,9,8,4,0,1,7]

     3
    /\
   /  \
   9   8
  /\  /\
 /  \/  \
 4  01   7 

Output:

[
  [4],
  [9],
  [3,0,1],
  [8],
  [7]
]

Examples 3:

Input: [3,9,8,4,0,1,7,null,null,null,2,5] (0's right child is 2 and 1's left child is 5)

     3
    /\
   /  \
   9   8
  /\  /\
 /  \/  \
 4  01   7
    /\
   /  \
   5   2

Output:

[
  [4],
  [9,5],
  [3,0,1],
  [8,2],
  [7]
]

```

```
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public List<List<Integer>> verticalOrder(TreeNode root) {
        TreeMap<Integer, ArrayList<Integer>> map = new TreeMap<>();
        helper(root, map);
        List<List<Integer>> result = new ArrayList<>();
        result.addAll(map.values());
        return result;
    }
    private void helper(TreeNode t, TreeMap<Integer, ArrayList<Integer>> map){
        if(t  == null)
            return;
        LinkedList<TreeNode> q1 = new LinkedList<>();
        LinkedList<Integer> q2 = new LinkedList<>();
        q1.offer(t);
        q2.offer(0);
        while(!q1.isEmpty()){
            TreeNode node = q1.poll();
            int order =q2.poll();
            ArrayList<Integer> list = map.get(order);
            if(list == null){
                list = new ArrayList<>();
                map.put(order, list);
            }
            list.add(node.val);
            if(node.left != null){
                q1.offer(node.left);
                q2.offer(order-1);
            }
            
            if(node.right != null){
                q1.offer(node.right);
                q2.offer(order+1);
            }
        }
     }
}

```

```
Given an array nums of n integers, are there elements a, b, c in nums such that a + b + c = 0? Find all unique triplets in the array which gives the sum of zero.

Note:

The solution set must not contain duplicate triplets.

Example:

Given array nums = [-1, 0, 1, 2, -1, -4],

A solution set is:
[
  [-1, 0, 1],
  [-1, -1, 2]
]

```

```
class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        ArrayList<List<Integer>> result = new ArrayList<>();
        for(int i = 0; i < nums.length; i++){
            int j = i+1;
            int k = nums.length -1 ;
            if(i > 0 && nums[i] == nums[i-1]){
                continue;
            }
            
            while(j < k){
                if(k < nums.length-1 && nums[k] == nums[k+1]){
                    k--;
                    continue;
                }
                if(nums[i]+nums[j]+nums[k] > 0){
                    k--;
                }else if(nums[i]+nums[j]+nums[k] < 0){
                    j++;
                }else{
                    ArrayList<Integer> l = new ArrayList<>();
                    l.add(nums[i]);
                    l.add(nums[j]);
                    l.add(nums[k]);
                    result.add(l);
                    j++;
                    k--;
                }
            }
        }
        return result;
    }
}

```

```
Next Permutation

Implement next permutation, which rearranges numbers into the lexicographically next greater permutation of numbers.

If such arrangement is not possible, it must rearrange it as the lowest possible order (ie, sorted in ascending order).

The replacement must be in-place and use only constant extra memory.

Here are some examples. Inputs are in the left-hand column and its corresponding outputs are in the right-hand column.

1,2,3 → 1,3,2
3,2,1 → 1,2,3
1,1,5 → 1,5,1

```

```
class Solution {
    public void nextPermutation(int[] nums) {
        int i = nums.length - 2;
        // get the first largest
        while(i >= 0 && nums[i+1] <= nums[i]){
            i--;
        }
        if(i >= 0){
            int j = nums.length - 1;
            // swap with the next largest
            while( j >= 0 && nums[j] <= nums[i]){
                j--;
            }
            swap(nums, i, j);
        }
        reverse(nums, i+1);
    }
    
    
    private void reverse(int [] nums, int start){
        int i = start, j = nums.length - 1;
        while(i < j){
            swap(nums, i , j); i++; j--;
        }
    }
    private void swap(int [] nums, int i , int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
```


```
Letter Combinations of a Phone Number

Given a string containing digits from 2-9 inclusive, return all possible letter combinations that the number could represent.

A mapping of digit to letters (just like on the telephone buttons) is given below. Note that 1 does not map to any letters.

Example:

Input: "23"
Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].

Note:

Although the above answer is in lexicographical order, your answer could be in any order you want.

```

```
class Solution {
    public List<String> letterCombinations(String digits) {
        Map<Character, char[]> dict = new HashMap<Character, char[]>();
        dict.put('2',new char[]{'a','b','c'});
        dict.put('3',new char[]{'d','e','f'});
        dict.put('4',new char[]{'g','h','i'});
        dict.put('5',new char[]{'j','k','l'});
        dict.put('6',new char[]{'m','n','o'});
        dict.put('7',new char[]{'p','q','r','s'});
        dict.put('8',new char[]{'t','u','v'});
        dict.put('9',new char[]{'w','x','y','z'});

        List<String> result = new ArrayList<String>();
        if(digits==null||digits.length()==0){
            return result;
        }

        char[] arr = new char[digits.length()];
        helper(digits, 0, dict, result, arr);

        return result;
    }
    
    private void helper(String digits, int index, Map<Character, char[]> dict, List<String> result, char[] arr){
        if(index == digits.length()){
            result.add(new String(arr));
            return;
        }
        
        char number = digits.charAt(index);
        char[] candidates = dict.get(number);
        for(int i  = 0 ; i < candidates.length; i ++){
            arr[index] = candidates[i];
            helper(digits, index+1, dict, result, arr);
        }
    }
}

```

```
Given a string s and a non-empty string p, find all the start indices of p's anagrams in s.

Strings consists of lowercase English letters only and the length of both strings s and p will not be larger than 20,100.

The order of output does not matter.

Example 1:

Input:
s: "cbaebabacd" p: "abc"

Output:
[0, 6]

Explanation:
The substring with start index = 0 is "cba", which is an anagram of "abc".
The substring with start index = 6 is "bac", which is an anagram of "abc".

Example 2:

Input:
s: "abab" p: "ab"

Output:
[0, 1, 2]

Explanation:
The substring with start index = 0 is "ab", which is an anagram of "ab".
The substring with start index = 1 is "ba", which is an anagram of "ab".
The substring with start index = 2 is "ab", which is an anagram of "ab".

```

```
class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> ans = new ArrayList<>();
        if(s == null || s.length() == 0 || s.length() < p.length())
            return ans;
        
        Map<Character, Integer> map = new HashMap<>();
        for(char c : p.toCharArray()){
            map.put(c, map.getOrDefault(c, 0) +1);
        }
        
        int counter = map.size();
        for(int left =0, right = 0; right < s.length() ; right ++){
            char cRight = s.charAt(right);
            if(map.containsKey(cRight)){
                map.put(cRight, map.get(cRight) -1 );
                if(map.get(cRight) == 0)
                    counter -= 1;
            }
            while(counter <= 0){
                char cLeft = s.charAt(left);
                if (map.containsKey(cLeft)) {
                    map.put(cLeft, map.get(cLeft) + 1);
                    if (map.get(cLeft) > 0) {
                        counter += 1;
                    }
                }
                if (right - left + 1 == p.length()) {
                    ans.add(left);
                }
                left++;
            }
        }
        return ans;
    }
}

```

```
Find All Anagrams in a String

Given a string s and a non-empty string p, find all the start indices of p's anagrams in s.

Strings consists of lowercase English letters only and the length of both strings s and p will not be larger than 20,100.

The order of output does not matter.

Example 1:

Input:
s: "cbaebabacd" p: "abc"

Output:
[0, 6]

Explanation:
The substring with start index = 0 is "cba", which is an anagram of "abc".
The substring with start index = 6 is "bac", which is an anagram of "abc".

Example 2:

Input:
s: "abab" p: "ab"

Output:
[0, 1, 2]

Explanation:
The substring with start index = 0 is "ab", which is an anagram of "ab".
The substring with start index = 1 is "ba", which is an anagram of "ab".
The substring with start index = 2 is "ab", which is an anagram of "ab".

```

```
class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> ans = new ArrayList<>();
        if(s == null || s.length() == 0 || s.length() < p.length())
            return ans;
        
        Map<Character, Integer> map = new HashMap<>();
        for(char c : p.toCharArray()){
            map.put(c, map.getOrDefault(c, 0) +1);
        }
        
        int counter = map.size();
        for(int left =0, right = 0; right < s.length() ; right ++){
            char cRight = s.charAt(right);
            if(map.containsKey(cRight)){
                map.put(cRight, map.get(cRight) -1 );
                if(map.get(cRight) == 0)
                    counter -= 1;
            }
            while(counter <= 0){
                char cLeft = s.charAt(left);
                if (map.containsKey(cLeft)) {
                    map.put(cLeft, map.get(cLeft) + 1);
                    if (map.get(cLeft) > 0) {
                        counter += 1;
                    }
                }
                if (right - left + 1 == p.length()) {
                    ans.add(left);
                }
                left++;
            }
        }
        return ans;
    }
}

```

```
Lowest Common Ancestor of Deepest Leaves

Given a rooted binary tree, return the lowest common ancestor of its deepest leaves.

Recall that:

    The node of a binary tree is a leaf if and only if it has no children
    The depth of the root of the tree is 0, and if the depth of a node is d, the depth of each of its children is d+1.
    The lowest common ancestor of a set S of nodes is the node A with the largest depth such that every node in S is in the subtree with root A.

 

Example 1:

Input: root = [1,2,3]
Output: [1,2,3]
Explanation: 
The deepest leaves are the nodes with values 2 and 3.
The lowest common ancestor of these leaves is the node with value 1.
The answer returned is a TreeNode object (not an array) with serialization "[1,2,3]".

Example 2:

Input: root = [1,2,3,4]
Output: [4]

Example 3:

Input: root = [1,2,3,4,5]
Output: [2,4,5]

 

Constraints:

    The given tree will have between 1 and 1000 nodes.
    Each node of the tree will have a distinct value between 1 and 1000.

```

```
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    int deepest  = 0;
    TreeNode lca;
    public TreeNode lcaDeepestLeaves(TreeNode root) {
        lcaDeepestLeaves(root, 0);
        return lca;
    }
    
    public int lcaDeepestLeaves(TreeNode node, int depth){
        deepest = Math.max(deepest, depth);
        if(node == null)
            return depth;
        int left = lcaDeepestLeaves(node.left, depth+1);
        int right = lcaDeepestLeaves(node.right, depth+1);
        if(left ==  deepest && right == deepest){
            lca  = node;
        }
        return Math.max(left, right);
    }
}

```


```
Partition Equal Subset Sum

Given a non-empty array containing only positive integers, find if the array can be partitioned into two subsets such that the sum of elements in both subsets is equal.

Note:

    Each of the array element will not exceed 100.
    The array size will not exceed 200.

 

Example 1:

Input: [1, 5, 11, 5]

Output: true

Explanation: The array can be partitioned as [1, 5, 5] and [11].

 

Example 2:

Input: [1, 2, 3, 5]

Output: false

Explanation: The array cannot be partitioned into equal sum subsets.

```

```
class Solution {
    public boolean canPartition(int[] nums) {
        int total = 0;
        for(int i : nums)
            total += i;
        if(total % 2 != 0)
            return false;
        
        return canPartition(nums, 0, 0, total, new HashMap<String, Boolean>());
    }
    
    public boolean canPartition(int [] nums, int index, int sum, int total, HashMap<String, Boolean> state){
        String current = index + "" + sum;
        if(state.containsKey(current)){
            return state.get(current);
        }
        
        if(sum * 2 == total)
            return true;
        
        if(sum > total/2 || index >= nums.length){
            return false;
        }
        
        boolean foundPartition =  canPartition(nums, index+1, sum, total, state) ||
            canPartition(nums, index+1, sum+nums[index], total, state);
        state.put(current, foundPartition);
        return foundPartition;
    }
  }
 ```
