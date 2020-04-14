```
 Given a string s and a string t, check if s is subsequence of t.

You may assume that there is only lower case English letters in both s and t. t is potentially a very long (length ~= 500,000) string, and s is a short string (<=100).

A subsequence of a string is a new string which is formed from the original string by deleting some (can be none) of the characters without disturbing the relative positions of the remaining characters. (ie, "ace" is a subsequence of "abcde" while "aec" is not).

Example 1:
s = "abc", t = "ahbgdc"

Return true.

Example 2:
s = "axc", t = "ahbgdc"

Return false.

Follow up:
If there are lots of incoming S, say S1, S2, ... , Sk where k >= 1B, and you want to check one by one to see if T has its subsequence. In this scenario, how would you change your code?
```

```
class Solution {
    public boolean isSubsequence(String s, String t) {
        if(s.length() == 0){
            return true;
        }
        int i = 0, j = 0;
        while(i < s.length() && j < t.length()){
            if(s.charAt(i) == t.charAt(j)){
                i++;
            }
            j++;
            if(i == s.length()){
                return true;
            }
        }
        return false;
    }
}
```

```
/**
Given a 2D binary matrix filled with 0's and 1's, find the largest square containing only 1's and return its area.

Example:

Input: 

1 0 1 0 0
1 0 1 1 1
1 1 1 1 1
1 0 0 1 0

Output: 4

*/
class Solution {
    public int maximalSquare(char[][] matrix) {
    if(matrix==null||matrix.length==0){
        return 0;
    }
 
    int result=0;
    int[][] dp = new int[matrix.length][matrix[0].length];
 
    for(int i=0; i<matrix.length; i++){
        dp[i][0]=matrix[i][0]-'0';
        result=Math.max(result, dp[i][0]);
    }
 
    for(int j=0; j<matrix[0].length; j++){
        dp[0][j]=matrix[0][j]-'0';
        result=Math.max(result, dp[0][j]);
    }
 
    for(int i=1; i<matrix.length; i++){
        for(int j=1; j<matrix[0].length; j++){
            if(matrix[i][j]=='1'){
                int min = Math.min(dp[i-1][j], dp[i][j-1]);
                min = Math.min(min, dp[i-1][j-1]);
                dp[i][j]=min+1;
 
                result = Math.max(result, min+1);
            }else{
                dp[i][j]=0;
            }    
        }
    }
 
    return result*result;
    }
}
```

```
/***
Given two strings str1 and str2 of the same length, determine whether you can transform str1 into str2 by doing zero or more conversions.

In one conversion you can convert all occurrences of one character in str1 to any other lowercase English character.

Return true if and only if you can transform str1 into str2.

 

Example 1:

Input: str1 = "aabcc", str2 = "ccdee"
Output: true
Explanation: Convert 'c' to 'e' then 'b' to 'd' then 'a' to 'c'. Note that the order of conversions matter.

Example 2:

Input: str1 = "leetcode", str2 = "codeleet"
Output: false
Explanation: There is no way to transform str1 to str2.

 
*/
class Solution {
    public boolean canConvert(String str1, String str2) {
        if(str1.equals(str2)){
            return true;
        }
        Set<Character> s = new HashSet<>();
        for(Character c : str2.toCharArray()){
            s.add(c);
        }
        if(s.size() == 26) return false;
        
        Map<Character, Character> mp = new HashMap<>();
        for(int i = 0; i < str1.length(); i ++){
            if(mp.get(str1.charAt(i)) == null){
                mp.put(str1.charAt(i), str2.charAt(i));
            }else if(mp.get(str1.charAt(i)) != str2.charAt(i)){
                return false;
            }
        }
        return true;
    }
}
```

```
Given two strings s and t, determine if they are isomorphic.

Two strings are isomorphic if the characters in s can be replaced to get t.

All occurrences of a character must be replaced with another character while preserving the order of characters. No two characters may map to the same character but a character may map to itself.

Example 1:

Input: s = "egg", t = "add"
Output: true

Example 2:

Input: s = "foo", t = "bar"
Output: false

Example 3:

Input: s = "paper", t = "title"
Output: true

Note:
You may assume both s and t have the same length.
```
```
class Solution {
    public boolean isIsomorphic(String s, String t) {
        if(s.equals(t)){
            return true;
        }
        
        Set<Character> set = new HashSet<>();
        for(char c : t.toCharArray()){
            set.add(c);
        }
        
        Map<Character, Character> mp = new HashMap<>();
        for(int i = 0; i < s.length() ; i++){
            if(mp.get(s.charAt(i)) == null){
                mp.put(s.charAt(i), t.charAt(i));
            }else if(mp.get(s.charAt(i)) != t.charAt(i)){
                return false;
            }
        }
        if(mp.values().size() == set.size()) return true;
        return false;
    }
}
```

```
In an exam room, there are N seats in a single row, numbered 0, 1, 2, ..., N-1.

When a student enters the room, they must sit in the seat that maximizes the distance to the closest person.  If there are multiple such seats, they sit in the seat with the lowest number.  (Also, if no one is in the room, then the student sits at seat number 0.)

Return a class ExamRoom(int N) that exposes two functions: ExamRoom.seat() returning an int representing what seat the student sat in, and ExamRoom.leave(int p) representing that the student in seat number p now leaves the room.  It is guaranteed that any calls to ExamRoom.leave(p) have a student sitting in seat p.

 

Example 1:

Input: ["ExamRoom","seat","seat","seat","seat","leave","seat"], [[10],[],[],[],[],[4],[]]
Output: [null,0,9,4,2,null,5]
Explanation:
ExamRoom(10) -> null
seat() -> 0, no one is in the room, then the student sits at seat number 0.
seat() -> 9, the student sits at the last seat number 9.
seat() -> 4, the student sits at the last seat number 4.
seat() -> 2, the student sits at the last seat number 2.
leave(4) -> null
seat() -> 5, the student sits at the last seat number 5.
```
```
class ExamRoom {
    int N;
    TreeSet <Integer> students;
    public ExamRoom(int N) {
        this.N = N;
        students = new TreeSet<Integer>();
    }
    
    public int seat() {
        int student = 0;
        if (students.size() > 0){
            int dist = students.first();
            Integer prev = null;
            for(Integer s: students){
                if(prev != null){
                    int d = (s - prev)/2;
                    if(d > dist){
                        dist = d;
                        student = prev + d;
                    }
                }
                prev = s;
            }
            if(N-1-students.last() > dist){
                student = N-1;
            }
        }
        students.add(student);
        return student;
    }
    
    public void leave(int p) {
        students.remove(p);
    }
}

/**
 * Your ExamRoom object will be instantiated and called as such:
 * ExamRoom obj = new ExamRoom(N);
 * int param_1 = obj.seat();
 * obj.leave(p);
 */
 ```
 
 ```
 On an alphabet board, we start at position (0, 0), corresponding to character board[0][0].

Here, board = ["abcde", "fghij", "klmno", "pqrst", "uvwxy", "z"], as shown in the diagram below.

We may make the following moves:

    'U' moves our position up one row, if the position exists on the board;
    'D' moves our position down one row, if the position exists on the board;
    'L' moves our position left one column, if the position exists on the board;
    'R' moves our position right one column, if the position exists on the board;
    '!' adds the character board[r][c] at our current position (r, c) to the answer.

(Here, the only positions that exist on the board are positions with letters on them.)

Return a sequence of moves that makes our answer equal to target in the minimum number of moves.  You may return any path that does so.
```

```
class Solution {
    public String alphabetBoardPath(String target) {
        StringBuilder sb = new StringBuilder();
        int preRow = 0, preCol = 0;
        for(int i = 0 ; i < target.length() ; i ++){
            int currRow = (target.charAt(i) - 'a')/5;
            int currCol = (target.charAt(i) - 'a')%5;
            addPath(currRow, preRow, currCol, preCol, sb);
            sb.append('!');
            preRow = currRow;
            preCol = currCol;
        }
        return sb.toString();
    }
    
    public void addPath(int currRow, int preRow, int currCol, int preCol, StringBuilder sb){
        while(currRow < preRow){
            sb.append('U');
            preRow--;
        }
        
        while(currCol < preCol){
            sb.append('L');
            preCol--;
        }
        
        while(currCol > preCol){
            sb.append('R');
            preCol++;
        }
        
        while(currRow > preRow){
            sb.append('D');
            preRow++;
        }
    }
}
```
n a 2x3 board, there are 5 tiles represented by the integers 1 through 5, and an empty square represented by 0.

A move consists of choosing 0 and a 4-directionally adjacent number and swapping it.

The state of the board is solved if and only if the board is [[1,2,3],[4,5,0]].

Given a puzzle board, return the least number of moves required so that the state of the board is solved. If it is impossible for the state of the board to be solved, return -1.

Examples:

Input: board = [[1,2,3],[4,0,5]]
Output: 1
Explanation: Swap the 0 and the 5 in one move.

Input: board = [[1,2,3],[5,4,0]]
Output: -1
Explanation: No number of moves will make the board solved.

Input: board = [[4,1,2],[5,0,3]]
Output: 5
Explanation: 5 is the smallest number of moves that solves the board.
An example path:
After move 0: [[4,1,2],[5,0,3]]
After move 1: [[4,1,2],[0,5,3]]
After move 2: [[0,1,2],[4,5,3]]
After move 3: [[1,0,2],[4,5,3]]
After move 4: [[1,2,0],[4,5,3]]
After move 5: [[1,2,3],[4,5,0]]

Input: board = [[3,2,4],[1,5,0]]
Output: 14

```
class Solution {
    public int slidingPuzzle(int[][] board) {
        int col = board.length;
        int row = board[0].length;
        StringBuilder sb = new StringBuilder();
        for (int[] i : board) {
            for (int j : i) {
                sb.append(j);
            }
        }
        String start = sb.toString();
        String end = "123450";
        
        if (board == null || board.length == 0) return -1;
        if (start.equals(end)) return 0;
        
        Set<String> visited = new HashSet<>();
        Queue<String> queue = new LinkedList<>();
        int ans = 0;
        int[][] dirs = new int[][] {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        
        
        queue.offer(start);
        visited.add(start);
        while (!queue.isEmpty()) {
            ans++;
            int n = queue.size();
            for (int i = 0; i < n; i++) {
                String cur = queue.poll();
                int zero = cur.indexOf('0');
                int zeroCol = zero / row;
                int zeroRow = zero % row;
                
                for (int[] dir : dirs) {
                    int nextRow = zeroRow + dir[0];
                    int nextCol = zeroCol + dir[1];
                    if (nextCol < 0 || nextCol >= col || nextRow < 0 || nextRow >= row) continue;
                    String newString = swap(cur, zero, nextCol * row + nextRow);
                    if (newString.equals(end)) return ans;
                    if (visited.contains(newString)) continue;
                    
                    visited.add(newString);
                    queue.offer(newString);
                }
            }
        }
        return -1;
    }
    private String swap(String s, int i, int j) {
        StringBuilder sb = new StringBuilder(s);
        sb.setCharAt(i, s.charAt(j));
        sb.setCharAt(j, s.charAt(i));
        return sb.toString();
    }
}
```

```
You are given an array colors, in which there are three colors: 1, 2 and 3.

You are also given some queries. Each query consists of two integers i and c, return the shortest distance between the given index i and the target color c. If there is no solution return -1.

 

Example 1:

Input: colors = [1,1,2,1,3,2,2,3,3], queries = [[1,3],[2,2],[6,1]]
Output: [3,0,3]
Explanation: 
The nearest 3 from index 1 is at index 4 (3 steps away).
The nearest 2 from index 2 is at index 2 itself (0 steps away).
The nearest 1 from index 6 is at index 3 (3 steps away).

Example 2:

Input: colors = [1,2], queries = [[0,3]]
Output: [-1]
Explanation: There is no 3 in the array.

```
```
class Solution {
    public List<Integer> shortestDistanceColor(int[] colors, int[][] queries) {
        List<Integer> result = new ArrayList<>();
        for(int [] query : queries){
            int i = query[0];
            int c = query[1];
            int l = i, r = i;
            if(colors[i] == c){
                result.add(0);
                continue;
            }
            int min = Integer.MAX_VALUE;
            while (l >= 0){
                if(l >= 0 && colors[l] == c){
                    min = Math.min(min, (i-l));
                    break;
                }
                l--;
            }
            while (r < colors.length){
                if(r < colors.length && colors[r] == c){
                    min = Math.min(min, (r-i));
                    break;
                }
                r++;
            }
            if(min != Integer.MAX_VALUE){
              result.add(min);  
            }else{
                result.add(-1);
            } 
            
        }
        return result;
    }
}
```

```
Every email consists of a local name and a domain name, separated by the @ sign.

For example, in alice@leetcode.com, alice is the local name, and leetcode.com is the domain name.

Besides lowercase letters, these emails may contain '.'s or '+'s.

If you add periods ('.') between some characters in the local name part of an email address, mail sent there will be forwarded to the same address without dots in the local name.  For example, "alice.z@leetcode.com" and "alicez@leetcode.com" forward to the same email address.  (Note that this rule does not apply for domain names.)

If you add a plus ('+') in the local name, everything after the first plus sign will be ignored. This allows certain emails to be filtered, for example m.y+name@email.com will be forwarded to my@email.com.  (Again, this rule does not apply for domain names.)

It is possible to use both of these rules at the same time.

Given a list of emails, we send one email to each address in the list.  How many different addresses actually receive mails? 

 

Example 1:

Input: ["test.email+alex@leetcode.com","test.e.mail+bob.cathy@leetcode.com","testemail+david@lee.tcode.com"]
Output: 2
Explanation: "testemail@leetcode.com" and "testemail@lee.tcode.com" actually receive mails

```

```
class Solution {
    public int numUniqueEmails(String[] emails) {
        Set<String> s = new HashSet<>();
        for(String email : emails){
            // get the '@'
            int indexOfPlus = email.indexOf('+');
            int indexOfAt = email.indexOf('@');
            if(indexOfPlus < 0){
                indexOfPlus = indexOfAt;
            }
            String unique = email.substring(0, indexOfPlus).replace(".", "") + email.substring(indexOfAt);
            System.out.println(unique);
            s.add(unique);
        }
        return s.size();
    }
}
```

```
Odd Even Jump

You are given an integer array A.  From some starting index, you can make a series of jumps.  The (1st, 3rd, 5th, ...) jumps in the series are called odd numbered jumps, and the (2nd, 4th, 6th, ...) jumps in the series are called even numbered jumps.

You may from index i jump forward to index j (with i < j) in the following way:

    During odd numbered jumps (ie. jumps 1, 3, 5, ...), you jump to the index j such that A[i] <= A[j] and A[j] is the smallest possible value.  If there are multiple such indexes j, you can only jump to the smallest such index j.
    During even numbered jumps (ie. jumps 2, 4, 6, ...), you jump to the index j such that A[i] >= A[j] and A[j] is the largest possible value.  If there are multiple such indexes j, you can only jump to the smallest such index j.
    (It may be the case that for some index i, there are no legal jumps.)

A starting index is good if, starting from that index, you can reach the end of the array (index A.length - 1) by jumping some number of times (possibly 0 or more than once.)

Return the number of good starting indexes.

 

Example 1:

Input: [10,13,12,14,15]
Output: 2
Explanation: 
From starting index i = 0, we can jump to i = 2 (since A[2] is the smallest among A[1], A[2], A[3], A[4] that is greater or equal to A[0]), then we can't jump any more.
From starting index i = 1 and i = 2, we can jump to i = 3, then we can't jump any more.
From starting index i = 3, we can jump to i = 4, so we've reached the end.
From starting index i = 4, we've reached the end already.
In total, there are 2 different starting indexes (i = 3, i = 4) where we can reach the end with some number of jumps.
```

```
class Solution {
    public int oddEvenJumps(int[] A) {
        int N = A.length;
        if(N <= 1) return N;
        boolean [] odd = new boolean[N];
        boolean [] even =  new boolean[N];
        
        odd[N-1] =  even[N-1] = true;
        TreeMap<Integer, Integer> vals = new TreeMap();
        vals.put(A[N-1], N-1);
        for(int i = N-2 ; i >= 0; --i){
            int v = A[i];
            if(vals.containsKey(v)){
                odd[i] = even[vals.get(v)];
                even[i] = odd[vals.get(v)];
            }else{
                Integer lower = vals.lowerKey(v);
                Integer higher = vals.higherKey(v);
                if(lower != null){
                    even[i] = odd[vals.get(lower)];
                }
                if(higher != null){
                    odd[i] = even[vals.get(higher)];
                }
            }
            vals.put(v, i);
        }
        int ans = 0;
        for(boolean b : odd){
            if(b) ans++;
        }
        return ans;
        
    }
}
```

```
You are given a license key represented as a string S which consists only alphanumeric character and dashes. The string is separated into N+1 groups by N dashes.

Given a number K, we would want to reformat the strings such that each group contains exactly K characters, except for the first group which could be shorter than K, but still must contain at least one character. Furthermore, there must be a dash inserted between two groups and all lowercase letters should be converted to uppercase.

Given a non-empty string S and a number K, format the string according to the rules described above.

Example 1:

Input: S = "5F3Z-2e-9-w", K = 4

Output: "5F3Z-2E9W"

Explanation: The string S has been split into two parts, each part has 4 characters.
Note that the two extra dashes are not needed and can be removed.

Example 2:

Input: S = "2-5g-3-J", K = 2

Output: "2-5G-3J"

Explanation: The string S has been split into three parts, each part has 2 characters except the first part as it could be shorter as mentioned above.

Note:

    The length of string S will not exceed 12,000, and K is a positive integer.
    String S consists only of alphanumerical characters (a-z and/or A-Z and/or 0-9) and dashes(-).
    String S is non-empty.
```

```
class Solution {
    public String licenseKeyFormatting(String S, int K) {
        S = S.replaceAll("-", "").toUpperCase();
        StringBuilder sb = new StringBuilder(S);
        
        int i = sb.length() - K;
        while(i > 0){
            sb.insert(i, '-');
            i -= K;
        }
        return sb.toString();
    }
}
```

```
  Fruit Into Baskets

In a row of trees, the i-th tree produces fruit with type tree[i].

You start at any tree of your choice, then repeatedly perform the following steps:

    Add one piece of fruit from this tree to your baskets.  If you cannot, stop.
    Move to the next tree to the right of the current tree.  If there is no tree to the right, stop.

Note that you do not have any choice after the initial choice of starting tree: you must perform step 1, then step 2, then back to step 1, then step 2, and so on until you stop.

You have two baskets, and each basket can carry any quantity of fruit, but you want each basket to only carry one type of fruit each.

What is the total amount of fruit you can collect with this procedure?

 

Example 1:

Input: [1,2,1]
Output: 3
Explanation: We can collect [1,2,1].

Example 2:

Input: [0,1,2,2]
Output: 3
Explanation: We can collect [1,2,2].
If we started at the first tree, we would only collect [0, 1].

Example 3:

Input: [1,2,3,2,2]
Output: 4
Explanation: We can collect [2,3,2,2].
If we started at the first tree, we would only collect [1, 2].

Example 4:

Input: [3,3,3,1,2,1,1,2,3,3,4]
Output: 5
Explanation: We can collect [1,2,1,1,2].
If we started at the first tree or the eighth tree, we would only collect 4 fruits.
```

```
class Solution {
    public int totalFruit(int[] tree) {
        Map<Integer, Integer> count = new HashMap<Integer, Integer>();
        int result = 0,  second = 0;
        for(int first = 0 ; first < tree.length; ++first){
            count.put(tree[first], count.getOrDefault(tree[first], 0) + 1);
            while(count.size() > 2){
                count.put(tree[second], count.get(tree[second]) - 1);
                if(count.get(tree[second]) == 0) count.remove(tree[second]);
                second++;
            }
            result = Math.max(result, first-second+1);
        }
        return result;
    }
}
```

```
Longest Substring Without Repeating Characters

Given a string, find the length of the longest substring without repeating characters.

Example 1:

Input: "abcabcbb"
Output: 3 
Explanation: The answer is "abc", with the length of 3. 

Example 2:

Input: "bbbbb"
Output: 1
Explanation: The answer is "b", with the length of 1.

Example 3:

Input: "pwwkew"
Output: 3
Explanation: The answer is "wke", with the length of 3. 
             Note that the answer must be a substring, "pwke" is a subsequence and not a substring.

```

```
class Solution {
    public int lengthOfLongestSubstring(String s) {
        int n = s.length(), ans = 0;
        Map<Character, Integer> map = new HashMap<>();
        for(int j = 0, i = 0; j< n ; j++){
            if(map.containsKey(s.charAt(j))){
                i = Math.max(map.get(s.charAt(j)), i);
            }
            ans = Math.max(ans, j-i+1);
            map.put(s.charAt(j), j+1);
        }
        return ans;
    }
}
```

```
Container With Most Water

Given n non-negative integers a1, a2, ..., an , where each represents a point at coordinate (i, ai). n vertical lines are drawn such that the two endpoints of line i is at (i, ai) and (i, 0). Find two lines, which together with x-axis forms a container, such that the container contains the most water.

Note: You may not slant the container and n is at least 2.
```

```
class Solution {
    public int maxArea(int[] height) {
        int max = Integer.MIN_VALUE;
        int i = 0;
        int j = height.length - 1;
        while(i<j){
            int min = Math.min(height[i], height[j]);
            max = Math.max(max, min * (j-i));
            if(height[i] < height[j]){
                i++;
            }else{
                j--;
            }
        }
        return max;
    }
}
```
```
Given words first and second, consider occurrences in some text of the form "first second third", where second comes immediately after first, and third comes immediately after second.

For each such occurrence, add "third" to the answer, and return the answer.

 

Example 1:

Input: text = "alice is a good girl she is a good student", first = "a", second = "good"
Output: ["girl","student"]

Example 2:

Input: text = "we will we will rock you", first = "we", second = "will"
Output: ["we","rock"]
```

```
class Solution {
    public String[] findOcurrences(String text, String first, String second) {
        if(null == text || null == first || null == second || text.length() == 0 || first.length() == 0 || second.length() == 0){
            return new String[0];
        }
        
        final ArrayList<String> res = new ArrayList<>();
        final String [] words = text.split(" ");
        for(int i = 2; i < words.length ; i++){
            if(first.equals(words[i-2]) && second.equals(words[i-1])){
                res.add(words[i]);
            }
        }
        return res.toArray(new String[0]);
    }
}
```

```
iven a binary string S (a string consisting only of '0' and '1's) and a positive integer N, return true if and only if for every integer X from 1 to N, the binary representation of X is a substring of S.

 

Example 1:

Input: S = "0110", N = 3
Output: true

Example 2:

Input: S = "0110", N = 4
Output: false
```

```
class Solution {
    public boolean queryString(String S, int N) {
        if(S == null || S.length() == 0)
            return false;
        
        if( N < 1)
            return false;
        
        for(int i = 1 ; i <= N ; i ++){
            // binary number
            String binary = Integer.toBinaryString(i);
            if(S.indexOf(binary) == -1){
                return false;
            }
        }
        return true;
    }
}
```

```
We have a set of items: the i-th item has value values[i] and label labels[i].

Then, we choose a subset S of these items, such that:

    |S| <= num_wanted
    For every label L, the number of items in S with label L is <= use_limit.

Return the largest possible sum of the subset S.

 

Example 1:

Input: values = [5,4,3,2,1], labels = [1,1,2,2,3], num_wanted = 3, use_limit = 1
Output: 9
Explanation: The subset chosen is the first, third, and fifth item.

Example 2:

Input: values = [5,4,3,2,1], labels = [1,3,3,3,2], num_wanted = 3, use_limit = 2
Output: 12
Explanation: The subset chosen is the first, second, and third item.

Example 3:

Input: values = [9,8,8,7,6], labels = [0,0,0,1,1], num_wanted = 3, use_limit = 1
Output: 16
Explanation: The subset chosen is the first and fourth item.

Example 4:

Input: values = [9,8,8,7,6], labels = [0,0,0,1,1], num_wanted = 3, use_limit = 2
Output: 24
Explanation: The subset chosen is the first, second, and fourth item.

```

```
class Solution {
    public int largestValsFromLabels(int[] values, int[] labels, int num_wanted, int use_limit) {
        int maxSum = 0;
            PriorityQueue<Item> pq = new PriorityQueue<>((o1, o2) -> o2.v - o1.v);
            for(int i = 0 ; i < values.length; i ++){
                pq.add(new Item(values[i], i));
            }
        
        HashMap<Integer, Integer> counter = new HashMap<>();
        for(int i = 0 ; i < labels.length; i ++){
            counter.put(labels[i], 0);
        }
        
        int limit = 0 ;
        while(!pq.isEmpty() && limit < num_wanted){
            Item it = pq.poll();
            if(counter.get(labels[it.i]) < use_limit){
                //counter.computeIfAbsent(labels[it.i], (k, v) -> v+1);
                counter.put(labels[it.i], counter.getOrDefault(labels[it.i], 0) + 1);
                maxSum +=  it.v;
                limit++;
            }
        }
        return maxSum;
    }
    
    class Item{
        int v, i;
        Item(int v, int i){
            this.v = v;
            this.i = i;
        }
        
        public int getVal(){
            return this.v;
        }
        
        public void setVal(int v){
            this.v = v;
        }
    }
}
```

```
Filling Bookcase Shelves

We have a sequence of books: the i-th book has thickness books[i][0] and height books[i][1].

We want to place these books in order onto bookcase shelves that have total width shelf_width.

We choose some of the books to place on this shelf (such that the sum of their thickness is <= shelf_width), then build another level of shelf of the bookcase so that the total height of the bookcase has increased by the maximum height of the books we just put down.  We repeat this process until there are no more books to place.

Note again that at each step of the above process, the order of the books we place is the same order as the given sequence of books.  For example, if we have an ordered list of 5 books, we might place the first and second book onto the first shelf, the third book on the second shelf, and the fourth and fifth book on the last shelf.

Return the minimum possible height that the total bookshelf can be after placing shelves in this manner.
```
```
class Solution {
    public int minHeightShelves(int[][] books, int shelf_width) {
        if(books == null || books.length == 0 || books[0].length != 2) return 0;
        
        final int n = books.length;
        final int [] dp = new int[n+1];
        
        for(int i = 0 ; i < n ; i ++){
            int width = books[i][0];
            int height = books[i][1];
            
            dp[i+1] =  dp[i] + height;
            for(int j = i-1; j>=0 && width + books[j][0] <= shelf_width; j--){
                width += books[j][0];
                height = Math.max(height, books[j][1]);
                dp[i+1] = Math.min(dp[i+1], dp[j] + height);
            }
        }
        return dp[n];
    }
}
```
```
Decode String
Medium

Given an encoded string, return its decoded string.

The encoding rule is: k[encoded_string], where the encoded_string inside the square brackets is being repeated exactly k times. Note that k is guaranteed to be a positive integer.

You may assume that the input string is always valid; No extra white spaces, square brackets are well-formed, etc.

Furthermore, you may assume that the original data does not contain any digits and that digits are only for those repeat numbers, k. For example, there won't be input like 3a or 2[4].

Examples:

s = "3[a]2[bc]", return "aaabcbc".
s = "3[a2[c]]", return "accaccacc".
s = "2[abc]3[cd]ef", return "abcabccdcdcdef".
```

```
class Solution {
    public String decodeString(String s) {
        if(s == null || s.isEmpty()) return s;
        Stack<String> cStack = new Stack<>();
        Stack<Integer> iStack = new Stack<>();
        
        String result = "";
        int idx = 0 ;
        while(idx < s.length()){
            char ch = s.charAt(idx);
            // whenever we encounter a number, push into integer stack
            if(Character.isDigit(ch)){
                int count = 0;
                while(Character.isDigit(ch)){
                    count = count * 10 + (ch-'0');
                    idx++;
                    ch = s.charAt(idx);
                }
                iStack.push(count);
            }else if(ch == '['){
                cStack.push(result);
                result = "";
                idx++;
            }else if(ch == ']'){
                StringBuilder sb = new StringBuilder(cStack.pop());
                int times = iStack.pop();
                for(int i = 0 ; i < times; i ++){
                    sb.append(result);
                }
                result = sb.toString();
                idx++;
            }else{
                result += ch;
                idx++;
            }
        }
        return result;
    }
}
```

```
3Sum

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
        if (null == nums || nums.length == 0){
            return new ArrayList<>();
        }
        ArrayList<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
        for(int i = 0; i < nums.length ; i++){
            int l = i+1;
            int r = nums.length - 1;
            if(i > 0 && nums[i] == nums[i-1]){
                continue; 
            }
            
            while(l < r){
                if(r < nums.length-1 && nums[r] == nums[r+1]){
                    r--;
                    continue; 
                }
                
                if(nums[i] + nums[l] + nums[r] > 0){
                    r--;
                }else if (nums[i] + nums[l] + nums[r] < 0 ){
                    l++;
                }else{
                    ArrayList<Integer> ans = new ArrayList<>();
                    ans.add(nums[i]);
                    ans.add(nums[l]);
                    ans.add(nums[r]);
                    result.add(ans);
                    l++;
                    r--;
                }
            }
        }
        return result;
    }
}
```

```
Longest Palindromic Substring

Given a string s, find the longest palindromic substring in s. You may assume that the maximum length of s is 1000.

Example 1:

Input: "babad"
Output: "bab"
Note: "aba" is also a valid answer.

Example 2:

Input: "cbbd"
Output: "bb"
```

```
class Solution {
    public String longestPalindrome(String s) {
        if(null == s || s.length() <= 1)
            return s;
        String longest = s.substring(0, 1);
        for(int i = 0 ; i < s.length(); i++){
            String tmp = helper(s, i, i);
            if(tmp.length() > longest.length()){
                longest = tmp;
            }
            
            tmp = helper(s, i, i+1 );
            if(tmp.length() > longest.length()){
                longest = tmp;
            }
        }
        return longest;
    }
    public String helper(String s, int begin, int end){
        while(begin >= 0 && end <= s.length()-1 && s.charAt(begin) == s.charAt(end)){
            begin--;
            end++;
        }
        return s.substring(begin+1, end);
    }
}
```

```
Given an integer array nums, find the contiguous subarray (containing at least one number) which has the largest sum and return its sum.

Example:

Input: [-2,1,-3,4,-1,2,1,-5,4],
Output: 6
Explanation: [4,-1,2,1] has the largest sum = 6.

```

```
class Solution {
    public int maxSubArray(int[] nums) {
        int maxSoFar = Integer.MIN_VALUE;
        int maxEndingHere = 0;
        int start = 0, end = 0;
        int s = 0;
    
        for(int i = 0 ; i < nums.length; i++){
            maxEndingHere += nums[i];
            if(maxSoFar < maxEndingHere){
                maxSoFar = maxEndingHere;
                start = s;
                end = i;
            }
            if(maxEndingHere < 0){
                maxEndingHere = 0;
                s = i + 1;  
            }
        }
        return  maxSoFar;
    }
}
```
```
Best Time to Buy and Sell Stock

Say you have an array for which the ith element is the price of a given stock on day i.

If you were only permitted to complete at most one transaction (i.e., buy one and sell one share of the stock), design an algorithm to find the maximum profit.

Note that you cannot sell a stock before you buy one.

Example 1:

Input: [7,1,5,3,6,4]
Output: 5
Explanation: Buy on day 2 (price = 1) and sell on day 5 (price = 6), profit = 6-1 = 5.
             Not 7-1 = 6, as selling price needs to be larger than buying price.

Example 2:

Input: [7,6,4,3,1]
Output: 0
Explanation: In this case, no transaction is done, i.e. max profit = 0.

```

```
class Solution {
    public int maxProfit(int[] prices) {
        if(prices == null || prices.length == 0){
            return 0;
        }
        
        int min = prices[0];
        int result = 0;
        for(int i = 1; i < prices.length ; i++){
            result = Math.max(result, prices[i] - min);
            min = Math.min(min, prices[i]);
        }
        return result;
    }
}
```
```
Given an integer array nums, find the contiguous subarray within an array (containing at least one number) which has the largest product.

Example 1:

Input: [2,3,-2,4]
Output: 6
Explanation: [2,3] has the largest product 6.

Example 2:

Input: [-2,0,-1]
Output: 0
Explanation: The result cannot be 2, because [-2,-1] is not a subarray.

```

```
class Solution {
    public int maxProduct(int[] nums) {
        if(nums ==  null || nums.length == 0)
            return 0;
        int [] max  = new int [nums.length];
        int [] min = new int [nums.length];
        max[0] = min[0] = nums[0];
        int result = nums[0];
        for(int i = 1; i < nums.length; i++){
            if(nums[i] > 0){
                max[i] = Math.max(nums[i], max[i-1] * nums[i]);
                min[i] = Math.min(nums[i], min[i-1] * nums[i]);
            }else{
                max[i] = Math.max(nums[i], min[i-1] * nums[i]);
                min[i] = Math.min(nums[i], max[i-1] * nums[i]);
            }
            result = Math.max(result, max[i]);
        }
        return result;
    }
}
```

```
You are given coins of different denominations and a total amount of money amount. Write a function to compute the fewest number of coins that you need to make up that amount. If that amount of money cannot be made up by any combination of the coins, return -1.

Example 1:

Input: coins = [1, 2, 5], amount = 11
Output: 3 
Explanation: 11 = 5 + 5 + 1

Example 2:

Input: coins = [2], amount = 3
Output: -1
```
```
class Solution {
    public int coinChange(int[] coins, int amount) {
        if(amount == 0)
            return 0;
        int [] dp = new int[amount+1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for(int i = 0 ; i <= amount ; i++){
            if(dp[i] == Integer.MAX_VALUE)
                continue;
            
            for(int coin : coins){
                if(i <= amount-coin){
                    dp [i+coin] = Math.min(dp[i] + 1, dp[i+coin]);
                }
            }
        }
        if(dp[amount] == Integer.MAX_VALUE){
            return -1;
        }
        return dp[amount];
    }
}
```
```
There is a special keyboard with all keys in a single row.

Given a string keyboard of length 26 indicating the layout of the keyboard (indexed from 0 to 25), initially your finger is at index 0. To type a character, you have to move your finger to the index of the desired character. The time taken to move your finger from index i to index j is |i - j|.

You want to type a string word. Write a function to calculate how much time it takes to type it with one finger.

 

Example 1:

Input: keyboard = "abcdefghijklmnopqrstuvwxyz", word = "cba"
Output: 4
Explanation: The index moves from 0 to 2 to write 'c' then to 1 to write 'b' then to 0 again to write 'a'.
Total time = 2 + 1 + 1 = 4. 

Example 2:

Input: keyboard = "pqrstuvwxyzabcdefghijklmno", word = "leetcode"
Output: 73
```

```
class Solution {
    public int calculateTime(String keyboard, String word) {
        Map<Character, Integer> map = new HashMap<>();
        for(int i = 0 ; i < keyboard.length(); i ++){
            map.put(keyboard.charAt(i), i);
        }
        int r = map.get(word.charAt(0));
        for(int i = 1 ; i < word.length(); i++){
            r += Math.abs(map.get(word.charAt(i)) - map.get(word.charAt(i-1)));
        }
        return r;
    }
}
```

```
Given the root of a binary tree, the level of its root is 1, the level of its children is 2, and so on.

Return the smallest level X such that the sum of all the values of nodes at level X is maximal.

 

Example 1:

Input: [1,7,0,7,-8,null,null]
Output: 2
Explanation: 
Level 1 sum = 1.
Level 2 sum = 7 + 0 = 7.
Level 3 sum = 7 + -8 = -1.
So we return the level with the maximum sum which is level 2.

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
    public int maxLevelSum(TreeNode root) {
        if(root == null) return 0;
        int maxLevel = 1, maxSum = root.val, level = 1;
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        while(!q.isEmpty()){
            int levelSum = 0, size = q.size();
            for(int i = 0; i < size; i++){
                TreeNode node = q.poll();
                if(node.left != null) q.offer(node.left);
                if(node.right != null) q.offer(node.right);
                levelSum += node.val;
            }
            if(levelSum > maxSum){
                maxSum = levelSum;
                maxLevel = level;
            }
            level++;
        }
        return maxLevel;
    }
}
```
```
You are given an integer array A.  From some starting index, you can make a series of jumps.  The (1st, 3rd, 5th, ...) jumps in the series are called odd numbered jumps, and the (2nd, 4th, 6th, ...) jumps in the series are called even numbered jumps.

You may from index i jump forward to index j (with i < j) in the following way:

    During odd numbered jumps (ie. jumps 1, 3, 5, ...), you jump to the index j such that A[i] <= A[j] and A[j] is the smallest possible value.  If there are multiple such indexes j, you can only jump to the smallest such index j.
    During even numbered jumps (ie. jumps 2, 4, 6, ...), you jump to the index j such that A[i] >= A[j] and A[j] is the largest possible value.  If there are multiple such indexes j, you can only jump to the smallest such index j.
    (It may be the case that for some index i, there are no legal jumps.)

A starting index is good if, starting from that index, you can reach the end of the array (index A.length - 1) by jumping some number of times (possibly 0 or more than once.)

Return the number of good starting indexes.

 

Example 1:

Input: [10,13,12,14,15]
Output: 2
Explanation: 
From starting index i = 0, we can jump to i = 2 (since A[2] is the smallest among A[1], A[2], A[3], A[4] that is greater or equal to A[0]), then we can't jump any more.
From starting index i = 1 and i = 2, we can jump to i = 3, then we can't jump any more.
From starting index i = 3, we can jump to i = 4, so we've reached the end.
From starting index i = 4, we've reached the end already.
In total, there are 2 different starting indexes (i = 3, i = 4) where we can reach the end with some number of jumps.

Example 2:

Input: [2,3,1,1,4]
Output: 3
Explanation: 
From starting index i = 0, we make jumps to i = 1, i = 2, i = 3:

During our 1st jump (odd numbered), we first jump to i = 1 because A[1] is the smallest value in (A[1], A[2], A[3], A[4]) that is greater than or equal to A[0].

During our 2nd jump (even numbered), we jump from i = 1 to i = 2 because A[2] is the largest value in (A[2], A[3], A[4]) that is less than or equal to A[1].  A[3] is also the largest value, but 2 is a smaller index, so we can only jump to i = 2 and not i = 3.

During our 3rd jump (odd numbered), we jump from i = 2 to i = 3 because A[3] is the smallest value in (A[3], A[4]) that is greater than or equal to A[2].

We can't jump from i = 3 to i = 4, so the starting index i = 0 is not good.

In a similar manner, we can deduce that:
From starting index i = 1, we jump to i = 4, so we reach the end.
From starting index i = 2, we jump to i = 3, and then we can't jump anymore.
From starting index i = 3, we jump to i = 4, so we reach the end.
From starting index i = 4, we are already at the end.
In total, there are 3 different starting indexes (i = 1, i = 3, i = 4) where we can reach the end with some number of jumps.

Example 3:

Input: [5,1,3,4,2]
Output: 3
Explanation: 
We can reach the end from starting indexes 1, 2, and 4.

```

```
class Solution {
    public int oddEvenJumps(int[] A) {
        int n = A.length;
        if(n <= 1) return n;
        boolean [] odd = new boolean [n];
        boolean [] even = new boolean[n];
        // last index
        odd[n-1] = even[n-1] = true;
        
        /***
        * Maintain order list of jumps 
        We can use a TreeMap, which is an excellent structure for maintaining sorted data. Our map vals will map values v = A[i] to indices i.

Iterating from i = N-2 to i = 0, we have some value v = A[i] and we want to know what the next largest or next smallest value is. The TreeMap.lowerKey and TreeMap.higherKey functions do this for us.

With this in mind, the rest of the solution is straightforward: we use dynamic programming to maintain odd[i] and even[i]: whether the state of being at index i on an odd or even numbered jump is possible to reach.
        */
        TreeMap<Integer, Integer> vals = new TreeMap<>();
        vals.put(A[n-1], n-1);
        for(int i = n-2 ; i>=0 ; --i){
            int v = A[i];
            if(vals.containsKey(v)){
                odd[i] = even[vals.get(v)];
                even[i] = odd[vals.get(v)];
            }else{
                Integer lower = vals.lowerKey(v);
                Integer higher = vals.higherKey(v);
                if(lower != null){
                    even[i] = odd[vals.get(lower)];
                }
                if(higher != null){
                    odd[i] = even[vals.get(higher)];
                }
            }
            vals.put(v, i);
        }
        int ans = 0;
        for(boolean b : odd){
            if(b) ans++;
        }
        return ans;
    }
}
```

```
A matrix is Toeplitz if every diagonal from top-left to bottom-right has the same element.

Now given an M x N matrix, return True if and only if the matrix is Toeplitz.
 

Example 1:

Input:
matrix = [
  [1,2,3,4],
  [5,1,2,3],
  [9,5,1,2]
]
Output: True
Explanation:
In the above grid, the diagonals are:
"[9]", "[5, 5]", "[1, 1, 1]", "[2, 2, 2]", "[3, 3]", "[4]".
In each diagonal all elements are the same, so the answer is True.

Example 2:

Input:
matrix = [
  [1,2],
  [2,2]
]
Output: False
Explanation:
The diagonal "[1, 2]" has different elements.

```
```
class Solution {
    public boolean isToeplitzMatrix(int[][] matrix) {
        int N = matrix.length;
        int M = matrix[0].length;
        for(int i = 0 ; i < M ; i++ ){
            if(!verifyDiagonal(matrix, 0, i)){
                return false;
            }
        }
        
        for(int i = 0 ; i < N ; i++ ){
            if(!verifyDiagonal(matrix,i, 0)){
                return false;
            }
        }
        
        return true;
    }
    
    public boolean verifyDiagonal(int m[][], int i , int j){
        int res = m[i][j];
        int N = m.length;
        int M = m[0].length;
        while(++i < N && ++j < M){
            if (m[i][j] != res){
                return false;
            }
        }
        return true;
    }
}
```

```
You are playing the following Bulls and Cows game with your friend: You write down a number and ask your friend to guess what the number is. Each time your friend makes a guess, you provide a hint that indicates how many digits in said guess match your secret number exactly in both digit and position (called "bulls") and how many digits match the secret number but locate in the wrong position (called "cows"). Your friend will use successive guesses and hints to eventually derive the secret number.

Write a function to return a hint according to the secret number and friend's guess, use A to indicate the bulls and B to indicate the cows. 

Please note that both secret number and friend's guess may contain duplicate digits.

Example 1:

Input: secret = "1807", guess = "7810"

Output: "1A3B"

Explanation: 1 bull and 3 cows. The bull is 8, the cows are 0, 1 and 7.

Example 2:

Input: secret = "1123", guess = "0111"

Output: "1A1B"

Explanation: The 1st 1 in friend's guess is a bull, the 2nd or 3rd 1 is a cow.
```

```
class Solution {
    public String getHint(String secret, String guess) {
        int countBull = 0; 
        int countCow = 0;
        HashMap<Character, Integer> map = new HashMap<>();
        
        // first check buill
        for(int i = 0 ; i < secret.length() ; i ++){
            char c1 = secret.charAt(i);
            char c2 = guess.charAt(i);
            if(c1 == c2){
                countBull++;
            }else{
                if(map.containsKey(c1)){
                    int freq = map.get(c1);
                    freq++;
                    map.put(c1, freq);
                }else{
                    map.put(c1, 1);
                }
            }
        }
        for(int i = 0; i < secret.length() ; i ++){
            char c1 = secret.charAt(i);
            char c2 = guess.charAt(i);
            if(c1 != c2){
                if(map.containsKey(c2)){
                    countCow++;
                    if(map.get(c2) == 1){
                        map.remove(c2);
                    }else{
                        int freq = map.get(c2);
                        freq--;
                        map.put(c2, freq);
                    }
                }
            }
        }
        return countBull + "A" + countCow + "B";
    }
}
```

```
Implement next permutation, which rearranges numbers into the lexicographically next greater permutation of numbers.

If such arrangement is not possible, it must rearrange it as the lowest possible order (ie, sorted in ascending order).

The replacement must be in-place and use only constant extra memory.

Here are some examples. Inputs are in the left-hand column and its corresponding outputs are in the right-hand column.

1,2,3 → 1,3,2
3,2,1 → 1,2,3
1,1,5 → 1,5,1
```

```
public class Solution {
    public void nextPermutation(int[] nums) {
        int i = nums.length - 2;
        while (i >= 0 && nums[i + 1] <= nums[i]) {
            i--;
        }
        if (i >= 0) {
            int j = nums.length - 1;
            while (j >= 0 && nums[j] <= nums[i]) {
                j--;
            }
            swap(nums, i, j);
        }
        reverse(nums, i + 1);
    }

    private void reverse(int[] nums, int start) {
        int i = start, j = nums.length - 1;
        while (i < j) {
            swap(nums, i, j);
            i++;
            j--;
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
```

```
Given two sentences words1, words2 (each represented as an array of strings), and a list of similar word pairs pairs, determine if two sentences are similar.

For example, "great acting skills" and "fine drama talent" are similar, if the similar word pairs are pairs = [["great", "fine"], ["acting","drama"], ["skills","talent"]].

Note that the similarity relation is not transitive. For example, if "great" and "fine" are similar, and "fine" and "good" are similar, "great" and "good" are not necessarily similar.

However, similarity is symmetric. For example, "great" and "fine" being similar is the same as "fine" and "great" being similar.

Also, a word is always similar with itself. For example, the sentences words1 = ["great"], words2 = ["great"], pairs = [] are similar, even though there are no specified similar word pairs.

Finally, sentences can only be similar if they have the same number of words. So a sentence like words1 = ["great"] can never be similar to words2 = ["doubleplus","good"].

Note:

    The length of words1 and words2 will not exceed 1000.
    The length of pairs will not exceed 2000.
    The length of each pairs[i] will be 2.
    The length of each words[i] and pairs[i][j] will be in the range [1, 20].

```

```
    public boolean areSentencesSimilar(String[] words1, String[] words2, List<List<String>> pairs) {

        if(words1 == null && words2 == null)
            return true;

        if(words1.length == 0 && words2.length == 0)
            return true;

        if(words1.length != words2.length)
            return false;

        if(pairs.size() == 0){
            return true;
        }

        Set<String> list = new HashSet<>();
        for(List<String> pair : pairs){
            list.add(pair.get(0) +""+pair.get(1));
            list.add(pair.get(1) +""+pair.get(0));
        }

        int totalMatch = 0;
        for(int i = 0 ; i < words1.length; i++){
            if(words1[i].equals(words2[i]) 
               || list.contains(words1[i]+""+words2[i]) 
               || list.contains(words2[i]+""+words1[i])){
                totalMatch ++;
            }
        }
        System.out.println("total match "+ totalMatch);
        return totalMatch >= words1.length ? true : false;
    }
  ```
