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

```
Given an Android 3x3 key lock screen and two integers m and n, where 1 ≤ m ≤ n ≤ 9, count the total number of unlock patterns of the Android lock screen, which consist of minimum of m keys and maximum n keys.

 

Rules for a valid pattern:

    Each pattern must connect at least m keys and at most n keys.
    All the keys must be distinct.
    If the line connecting two consecutive keys in the pattern passes through any other keys, the other keys must have previously selected in the pattern. No jumps through non selected key is allowed.
    The order of keys used matters.

```

```
class Solution {
        private int[][] jumps;
        private boolean[] visited;

        public int numberOfPatterns(int m, int n) {
            jumps = new int[10][10];
            jumps[1][3] = jumps[3][1] = 2;
            jumps[4][6] = jumps[6][4] = 5;
            jumps[7][9] = jumps[9][7] = 8;
            jumps[1][7] = jumps[7][1] = 4;
            jumps[2][8] = jumps[8][2] = jumps[1][9] = jumps[9][1] = 5;
            jumps[9][3] = jumps[3][9] = 6;
            jumps[3][7] = jumps[7][3] = 5;
            visited = new boolean[10];
            int count = 0;
            count += dfs(1, 1, 0, m, n)
                * 4;//1,3,7,9 are symmetric, so we only need to use 1 to do it once and then multiply the result by 4
            count += dfs(2, 1, 0, m, n)
                * 4;//2,4,6,8 are symmetric, so we only need to use 1 to do it once and then multiply the result by 4
            count += dfs(5, 1, 0, m, n);
            return count;
        }

        private int dfs(int num, int len, int count, int m, int n) {
            if (len >= m) {
                count++;
            }
            len++;
            if (len > n) {
                return count;
            }
            visited[num] = true;
            for (int next = 1; next <= 9; next++) {
                int jump = jumps[num][next];
                if (!visited[next] && (jump == 0 || visited[jump])) {
                    count = dfs(next, len, count, m, n);
                }
            }
            visited[num] = false;//backtracking
            return count;
        }
}

```

```
Design a search autocomplete system for a search engine. Users may input a sentence (at least one word and end with a special character '#'). For each character they type except '#', you need to return the top 3 historical hot sentences that have prefix the same as the part of sentence already typed. Here are the specific rules:

    The hot degree for a sentence is defined as the number of times a user typed the exactly same sentence before.
    The returned top 3 hot sentences should be sorted by hot degree (The first is the hottest one). If several sentences have the same degree of hot, you need to use ASCII-code order (smaller one appears first).
    If less than 3 hot sentences exist, then just return as many as you can.
    When the input is a special character, it means the sentence ends, and in this case, you need to return an empty list.

Your job is to implement the following functions:

The constructor function:

AutocompleteSystem(String[] sentences, int[] times): This is the constructor. The input is historical data. Sentences is a string array consists of previously typed sentences. Times is the corresponding times a sentence has been typed. Your system should record these historical data.

Now, the user wants to input a new sentence. The following function will provide the next character the user types:

List<String> input(char c): The input c is the next character typed by the user. The character will only be lower-case letters ('a' to 'z'), blank space (' ') or a special character ('#'). Also, the previously typed sentence should be recorded in your system. The output will be the top 3 historical hot sentences that have prefix the same as the part of sentence already typed.
 

Example:
Operation: AutocompleteSystem(["i love you", "island","ironman", "i love leetcode"], [5,3,2,2])
The system have already tracked down the following sentences and their corresponding times:
"i love you" : 5 times
"island" : 3 times
"ironman" : 2 times
"i love leetcode" : 2 times
Now, the user begins another search:

Operation: input('i')
Output: ["i love you", "island","i love leetcode"]
Explanation:
There are four sentences that have prefix "i". Among them, "ironman" and "i love leetcode" have same hot degree. Since ' ' has ASCII code 32 and 'r' has ASCII code 114, "i love leetcode" should be in front of "ironman". Also we only need to output top 3 hot sentences, so "ironman" will be ignored.

Operation: input(' ')
Output: ["i love you","i love leetcode"]
Explanation:
There are only two sentences that have prefix "i ".

Operation: input('a')
Output: []
Explanation:
There are no sentences that have prefix "i a".

Operation: input('#')
Output: []
Explanation:
The user finished the input, the sentence "i a" should be saved as a historical sentence in system. And the following input will be counted as a new search.

```

```
class AutocompleteSystem {

    class TrieNode {
        public boolean isLeaf;
        public List<String> cands;
        HashMap<Character, TrieNode> children;
        public TrieNode() {
            isLeaf = false;
            children = new HashMap<Character, TrieNode>();
            cands = new LinkedList<String>();
        }
    }
    class Trie {
        private TrieNode root;
        public Trie() {
            root = new TrieNode();
        }
        // Inserts a word into the trie.
        public void insert(String word) {
            TrieNode node = root;
            for (int i = 0; i < word.length(); i ++) {
                HashMap<Character, TrieNode> children = node.children;
                char c = word.charAt(i);
                if (!children.containsKey(c)) {
                    children.put(c, new TrieNode());
                }
                children.get(c).cands.add(word);
                if (i == word.length() - 1) {
                    children.get(c).isLeaf = true;
                }
                node = node.children.get(c);
            }
        }
        private TrieNode searchNode(String pre) {
            HashMap<Character, TrieNode> children = root.children;
            TrieNode node = root;
            for (int i = 0; i < pre.length(); i ++) {
                if (!children.containsKey(pre.charAt(i))) {
                    return null;
                }
                node = children.get(pre.charAt(i));
                children = node.children;
            }
            return node;
        }
    }
    HashMap<String, Integer> count = new HashMap<String, Integer>();
    Trie trie = new Trie();
    String curr = "";
    public AutocompleteSystem(String[] sentences, int[] times) {
        for (int i = 0; i < sentences.length; i ++) {
            count.put(sentences[i], times[i]);
            trie.insert(sentences[i]);
        }
    }

    public List<String> input(char c) {
        List<String> res = new LinkedList<String>();
        if (c == '#') {
            if (!count.containsKey(curr)) {
                trie.insert(curr);
                count.put(curr, 1);
            }
            else {
                count.put(curr, count.get(curr) + 1);
            }
            curr = "";
        }
        else {
            curr += c;
            res = getSuggestions();
        }

        return res;
    }
    private List<String> getSuggestions() {
        List<String> res = new LinkedList<String>();
        TrieNode node = trie.searchNode(curr);
        if (node == null) {
            return res;
        }
        List<String> cands = node.cands;
        Collections.sort(cands, new Comparator<String>(){
            public int compare(String s1, String s2) {
                if (count.get(s1) != count.get(s2)) {
                    return count.get(s2) - count.get(s1);
                }
                return s1.compareTo(s2);
            } 
        });
        int added = 0; 
        for (String s:cands) {
            res.add(s);
            added ++;
            if (added > 2) {
                break;
            }
        }
        return res;
    }

}

/**
 * Your AutocompleteSystem object will be instantiated and called as such:
 * AutocompleteSystem obj = new AutocompleteSystem(sentences, times);
 * List<String> param_1 = obj.input(c);
 */
 ```

```
Given two sentences words1, words2 (each represented as an array of strings), and a list of similar word pairs pairs, determine if two sentences are similar.

For example, words1 = ["great", "acting", "skills"] and words2 = ["fine", "drama", "talent"] are similar, if the similar word pairs are pairs = [["great", "good"], ["fine", "good"], ["acting","drama"], ["skills","talent"]].

Note that the similarity relation is transitive. For example, if "great" and "good" are similar, and "fine" and "good" are similar, then "great" and "fine" are similar.

Similarity is also symmetric. For example, "great" and "fine" being similar is the same as "fine" and "great" being similar.

Also, a word is always similar with itself. For example, the sentences words1 = ["great"], words2 = ["great"], pairs = [] are similar, even though there are no specified similar word pairs.

Finally, sentences can only be similar if they have the same number of words. So a sentence like words1 = ["great"] can never be similar to words2 = ["doubleplus","good"].

Note:

    The length of words1 and words2 will not exceed 1000.
    The length of pairs will not exceed 2000.
    The length of each pairs[i] will be 2.
    The length of each words[i] and pairs[i][j] will be in the range [1, 20].

```

```
class Solution {
    public boolean areSentencesSimilarTwo(String[] words1, String[] words2, List<List<String>> pairs) {
        if (words1.length != words2.length) {
            return false;
        }
        // Build the graph of pairs
        HashMap<String, Set<String>> pairMap = new HashMap<>();
        for (List<String> pair : pairs) {
            // Create keys(words in [][]pairs without duplication) and empty set 
            if (!pairMap.containsKey(pair.get(0))) {
                pairMap.put(pair.get(0), new HashSet<String>());
            }
            if (!pairMap.containsKey(pair.get(1))) {
                pairMap.put(pair.get(1), new HashSet<String>());
            }
            // Add the corresponding pairs to each other
            pairMap.get(pair.get(0)).add(pair.get(1));
            pairMap.get(pair.get(1)).add(pair.get(0));
        }
     
        // Iterate throught each word in both input strings and do DFS search
        for (int i = 0; i < words1.length; i++) {
            // If same, then we don't need to do DFS search
            if (words1[i].equals(words2[i])) continue;
            // If they are not the same and no such strings in the pairs
            if (!pairMap.containsKey(words1[i]) || !pairMap.containsKey(words2[i])) return false;
            // Do DFS search, initialize the set to prevent revisiting. 
            if (!dfs(words1[i], words2[i], pairMap, new HashSet<>())) return false;
        }
        return true;
    }
        
    public boolean dfs(String source, String target, HashMap<String, Set<String>> pairMap, HashSet<String> visited) {
        if (pairMap.get(source).contains(target)) {
            return true;
        }
        // Mark as visited 
        visited.add(source);
        for (String next : pairMap.get(source)) {
            // DFS other connected node, except the mirrowed string 
            if (!visited.contains(next) && next.equals(target) ||
                !visited.contains(next) && dfs(next, target, pairMap, visited)) {
                return true;    
            }
        }
        // We've done dfs still can't find the target 
        return false;
    }
}
```

```
Given two non-negative integers num1 and num2 represented as strings, return the product of num1 and num2, also represented as a string.

Example 1:

Input: num1 = "2", num2 = "3"
Output: "6"

Example 2:

Input: num1 = "123", num2 = "456"
Output: "56088"

Note:

    The length of both num1 and num2 is < 110.
    Both num1 and num2 contain only digits 0-9.
    Both num1 and num2 do not contain any leading zero, except the number 0 itself.
    You must not use any built-in BigInteger library or convert the inputs to integer directly.

```

```
class Solution {
    public String multiply(String num1, String num2) {
        int [] products = new int [num1.length() + num2.length()];
        for(int i = num1.length()-1 ; i >= 0 ; i--){
            for(int j = num2.length()-1 ; j >= 0 ; j--){
                products[i+j+1] += (num1.charAt(i)-'0') * (num2.charAt(j)-'0');
            }
        }
        int carry = 0;
        StringBuilder sb = new StringBuilder();
        for(int i = products.length-1; i>= 0 ; i--){
            sb.insert(0, (products[i]+carry)%10);
            carry = (products[i]+carry)/10;
        }
        while(sb.length() != 0 && sb.charAt(0) == '0'){
            sb.deleteCharAt(0);
        }
        return sb.length() == 0 ? "0" : sb.toString();
    }
}
```

```
Rotate Image

You are given an n x n 2D matrix representing an image.

Rotate the image by 90 degrees (clockwise).

Note:

You have to rotate the image in-place, which means you have to modify the input 2D matrix directly. DO NOT allocate another 2D matrix and do the rotation.

Example 1:

Given input matrix = 
[
  [1,2,3],
  [4,5,6],
  [7,8,9]
],

rotate the input matrix in-place such that it becomes:
[
  [7,4,1],
  [8,5,2],
  [9,6,3]
]

Example 2:

Given input matrix =
[
  [ 5, 1, 9,11],
  [ 2, 4, 8,10],
  [13, 3, 6, 7],
  [15,14,12,16]
], 

rotate the input matrix in-place such that it becomes:
[
  [15,13, 2, 5],
  [14, 3, 4, 1],
  [12, 6, 8, 9],
  [16, 7,10,11]
]

```

```
class Solution {
    public void rotate(int[][] matrix) {
        int n = matrix.length;
        for(int i =0 ; i < n/2; i ++){
            for(int j = 0 ; j< Math.ceil(((double)n)/2.); j++){
                int temp = matrix[i][j];
                matrix[i][j] = matrix[n-1-j][i];
                matrix[n-1-j][i] = matrix[n-1-i][n-1-j];
                matrix[n-1-i][n-1-j] = matrix[j][n-1-i];
                matrix[j][n-1-i] = temp;
            }
        }
    }
}
```

```
Jump Game

Given an array of non-negative integers, you are initially positioned at the first index of the array.

Each element in the array represents your maximum jump length at that position.

Determine if you are able to reach the last index.

Example 1:

Input: [2,3,1,1,4]
Output: true
Explanation: Jump 1 step from index 0 to 1, then 3 steps to the last index.

Example 2:

Input: [3,2,1,0,4]
Output: false
Explanation: You will always arrive at index 3 no matter what. Its maximum
             jump length is 0, which makes it impossible to reach the last index.

```

```
class Solution {
    public boolean canJump(int[] nums) {
        int max = nums[0];
        if(nums.length == 1 && max >= 0){
            return true;
        }
        for(int i=0 ; i < nums.length; i ++){
            if(max <= i && nums[i] == 0){
                return false;
            }
            if(i+nums[i] > max){
                max = i + nums[i];
            }
            
            if(max >= nums.length-1)
                return true;
        }
        return false;
    }
}
```

```
Minimum Window Substring

Given a string S and a string T, find the minimum window in S which will contain all the characters in T in complexity O(n).

Example:

Input: S = "ADOBECODEBANC", T = "ABC"
Output: "BANC"

Note:

    If there is no such window in S that covers all characters in T, return the empty string "".
    If there is such window, you are guaranteed that there will always be only one unique minimum window in S.

```

```
class Solution {
    public String minWindow(String s, String t) {
        if(s.length() == 0 || t.length() == 0)
            return "";
        // get the count of the unique characters
        Map<Character, Integer> dict = new HashMap<>();
        for(int i = 0 ; i < t.length() ; i ++){
            int count = dict.getOrDefault(t.charAt(i), 0);
            dict.put(t.charAt(i), count+1);
        }
        
        int required = dict.size();
        int l = 0, r = 0;
        int formed = 0;
        
        Map<Character, Integer> windowCounts = new HashMap<>();
        int [] ans = {-1, 0 , 0}; // (length, left, right)
        while(r < s.length()){
            char c = s.charAt(r);
            int count = windowCounts.getOrDefault(c, 0);
            windowCounts.put(c, count+1);
            if(dict.containsKey(c) && windowCounts.get(c).intValue() == dict.get(c).intValue()){
                formed++;
            }
            while(l <= r && formed == required){
                c = s.charAt(l);
                if(ans[0] == -1 || r-l+1 < ans[0]){
                    ans[0] = r-l+1;
                    ans[1] = l;
                    ans[2] = r;
                }
                windowCounts.put(c, windowCounts.get(c)-1);
                if(dict.containsKey(c) && windowCounts.get(c).intValue() < dict.get(c).intValue()){
                    formed--;
                }
                l++;
            }
            r++;
        }
        return ans[0] == -1 ? "" : s.substring(ans[1], ans[2]+1);
     }
}
```

```
Binary Tree Maximum Path Sum

Given a non-empty binary tree, find the maximum path sum.

For this problem, a path is defined as any sequence of nodes from some starting node to any node in the tree along the parent-child connections. The path must contain at least one node and does not need to go through the root.

Example 1:

Input: [1,2,3]

       1
      / \
     2   3

Output: 6

Example 2:

Input: [-10,9,20,null,null,15,7]

   -10
   / \
  9  20
    /  \
   15   7

Output: 42

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
    public int maxPathSum(TreeNode root) {
        int max[] = new int [1];
        max[0] = Integer.MIN_VALUE;
        calculateSum(root, max);
        return max[0];
    }
    
    public int calculateSum(TreeNode root, int [] max){
        if(root == null)
            return 0;
        int left = calculateSum(root.left, max);
        int right = calculateSum(root.right, max);
        int current = Math.max (root.val, root.val + Math.max(left, right));
        max[0] = Math.max(max[0], Math.max(current, left+root.val+right));
        return current;
    }
}
```


```
Course Schedule II

There are a total of n courses you have to take, labeled from 0 to n-1.

Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]

Given the total number of courses and a list of prerequisite pairs, return the ordering of courses you should take to finish all courses.

There may be multiple correct orders, you just need to return one of them. If it is impossible to finish all courses, return an empty array.

Example 1:

Input: 2, [[1,0]] 
Output: [0,1]
Explanation: There are a total of 2 courses to take. To take course 1 you should have finished   
             course 0. So the correct course order is [0,1] .

Example 2:

Input: 4, [[1,0],[2,0],[3,1],[3,2]]
Output: [0,1,2,3] or [0,2,1,3]
Explanation: There are a total of 4 courses to take. To take course 3 you should have finished both     
             courses 1 and 2. Both courses 1 and 2 should be taken after you finished course 0. 
             So one correct course order is [0,1,2,3]. Another correct ordering is [0,2,1,3] .

Note:

    The input prerequisites is a graph represented by a list of edges, not adjacency matrices. Read more about how a graph is represented.
    You may assume that there are no duplicate edges in the input prerequisites.

```

```
class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        int len = prerequisites.length;
        if(numCourses == 0 && len == 0){
            return null;
        }
        int[] result = new int[numCourses];
         if(len == 0){
            result [0] = 0;
        }

        // counter for number of prerequisites
        int[] pCounter = new int[numCourses];
        for(int i=0; i<len; i++){
            pCounter[prerequisites[i][0]]++;
        }

        //store courses that have no prerequisites
        LinkedList<Integer> queue = new LinkedList<Integer>();
        for(int i=0; i<numCourses; i++){
            if(pCounter[i]==0){
                queue.add(i);
            }
        }

        // number of courses that have no prerequisites
        int numNoPre = queue.size();
        int j = 0;
        while(!queue.isEmpty()){
            int top = queue.remove();
            result[j++] = top;
            for(int i=0; i<len; i++){
                // if a course's prerequisite can be satisfied by a course in queue
                if(prerequisites[i][1]==top){
                    if(--pCounter[prerequisites[i][0]] ==0){
                        numNoPre++;
                        queue.add(prerequisites[i][0]);
                    }
                }
            }
        }
        
        return numNoPre == numCourses ? result : new int [0];

    }
}
```


```
 Number of Islands

Given a 2d grid map of '1's (land) and '0's (water), count the number of islands. An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.

Example 1:

Input:
11110
11010
11000
00000

Output: 1

Example 2:

Input:
11000
11000
00100
00011

Output: 3
```

```
class Solution {
    public int numIslands(char[][] grid) {
        if(grid == null || grid.length == 0){
            return 0;
        }
        
        int numIslands = 0;
        for(int i = 0 ; i < grid.length; i++){
            for(int j = 0; j < grid[i].length;  j++){
                if(grid[i][j] == '1'){
                    numIslands += dfs(grid, i, j);
                }
            }
        }
        return numIslands;
    }
    
    public int dfs(char [][] grid, int i, int j){
        if(i < 0 || i >= grid.length || j < 0 || j>= grid[i].length || grid[i][j] == '0' ){
            return 0;
        }
        grid[i][j] = '0';
        dfs(grid, i+1, j);
        dfs(grid, i-1, j);
        dfs(grid, i , j+1);
        dfs(grid, i, j-1);
        return 1;
    }
}
```


```
Count Complete Tree Nodes

Given a complete binary tree, count the number of nodes.

Note:

Definition of a complete binary tree from Wikipedia:
In a complete binary tree every level, except possibly the last, is completely filled, and all nodes in the last level are as far left as possible. It can have between 1 and 2h nodes inclusive at the last level h.

Example:

Input: 
    1
   / \
  2   3
 / \  /
4  5 6

Output: 6


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
    public int countNodes(TreeNode root) {
        if(root == null)
            return 0;
        
        Queue<TreeNode> q = new LinkedList<TreeNode>();
        q.offer(root);
        boolean missing = false;
        int num = 0;
        while (!q.isEmpty()){
            TreeNode node = q.poll();
            if(node != null){
                num++;
                if(missing) return 0;
                q.offer(node.left);
                q.offer(node.right);
            }else{
                missing = true;
            }
        }
        return num;
    }
}
```

```
Longest Increasing Path in a Matrix

Given an integer matrix, find the length of the longest increasing path.

From each cell, you can either move to four directions: left, right, up or down. You may NOT move diagonally or move outside of the boundary (i.e. wrap-around is not allowed).

Example 1:

Input: nums = 
[
  [9,9,4],
  [6,6,8],
  [2,1,1]
] 
Output: 4 
Explanation: The longest increasing path is [1, 2, 6, 9].

Example 2:

Input: nums = 
[
  [3,4,5],
  [3,2,6],
  [2,2,1]
] 
Output: 4 
Explanation: The longest increasing path is [3, 4, 5, 6]. Moving diagonally is not allowed.

```

```
class Solution {
    public int longestIncreasingPath(int[][] matrix) {
        if(matrix == null || matrix.length == 0)
            return 0;
        int result = 0;
        int m = matrix.length;
        int n = matrix[0].length;
        int [][] dp = new int[m][n];
        for(int i = 0; i < m ; i++){
            for(int j = 0; j < n ; j++){
                int t = helper(matrix, dp, i, j);
                result = Math.max(result, t);
            }
        }
        return result;
    }
    
    public int helper(int [][] matrix, int [][] dp, int i, int j){
        if(dp[i][j] > 0){
            return dp[i][j];
        }
        int [] dx = {-1, 0, 1, 0};
        int [] dy = {0, 1, 0, -1};
        for(int k = 0 ; k < 4 ; k++){
            int x = i + dx[k];
            int y = j + dy[k];
            if(x >= 0 && y >= 0
              && x < matrix.length && y < matrix[0].length 
              && matrix[x][y] > matrix[i][j]){
                dp[i][j] = Math.max(dp[i][j], helper(matrix, dp, x, y));
            }
        }
        return ++dp[i][j];
    }
}
```

```
Evaluate Division

Equations are given in the format A / B = k, where A and B are variables represented as strings, and k is a real number (floating point number). Given some queries, return the answers. If the answer does not exist, return -1.0.

Example:
Given a / b = 2.0, b / c = 3.0.
queries are: a / c = ?, b / a = ?, a / e = ?, a / a = ?, x / x = ? .
return [6.0, 0.5, -1.0, 1.0, -1.0 ].

The input is: vector<pair<string, string>> equations, vector<double>& values, vector<pair<string, string>> queries , where equations.size() == values.size(), and the values are positive. This represents the equations. Return vector<double>.

According to the example above:

equations = [ ["a", "b"], ["b", "c"] ],
values = [2.0, 3.0],
queries = [ ["a", "c"], ["b", "a"], ["a", "e"], ["a", "a"], ["x", "x"] ]. 

 

The input is always valid. You may assume that evaluating the queries will result in no division by zero and there is no contradiction.

```

```

class Solution {
    private Map<String, String> root;
    private Map<String, Double> rate;

    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        root = new HashMap<String, String>();
        rate = new HashMap<String, Double>();
        int n = equations.size();
        for (int i = 0; i < n; ++i) {
            String X = equations.get(i).get(0);
            String Y = equations.get(i).get(1);
            root.put(X, X);
            root.put(Y, Y);
            rate.put(X, 1.0);
            rate.put(Y, 1.0);
        }

        for (int i = 0; i < n; ++i) {
            String X = equations.get(i).get(0);
            String Y = equations.get(i).get(1);
            union(X, Y, values[i]);
        }

        double[] result = new double[queries.size()];
        for (int i = 0; i < queries.size(); ++i) {
            String X = queries.get(i).get(0);
            String Y = queries.get(i).get(1);
            if (!root.containsKey(X) || !root.containsKey(Y)) {
                result[i] = -1;
                continue;
            }

            String rootx = findRoot(X, X, 1.0);
            String rooty = findRoot(Y, Y, 1.0);
            result[i] = rootx.equals(rooty) ? rate.get(X) / rate.get(Y) : -1.0;
        }
        
        return result;
    }

    private void union(String X, String Y, double v) {
        String rootx = findRoot(X, X, 1.0);
        String rooty = findRoot(Y, Y, 1.0);
        root.put(rootx, rooty);
        double r1 = rate.get(X);
        double r2 = rate.get(Y);
        rate.put(rootx, v * r2 / r1);
    }

    private String findRoot(String originalX, String X, double r) {
        if (root.get(X).equals(X)) {
            root.put(originalX, X);
            rate.put(originalX, r * rate.get(X));
            return X;
        }

        return findRoot(originalX, root.get(X), r * rate.get(X));
    }
}
```

```
Diameter of Binary Tree

Given a binary tree, you need to compute the length of the diameter of the tree. The diameter of a binary tree is the length of the longest path between any two nodes in a tree. This path may or may not pass through the root.

Example:
Given a binary tree

          1
         / \
        2   3
       / \     
      4   5    

Return 3, which is the length of the path [4,2,1,3] or [5,2,1,3].

Note: The length of path between two nodes is represented by the number of edges between them. 
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
    public int diameterOfBinaryTree(TreeNode root) {
        if(root == null)
            return 0;
        AtomicInteger d = new AtomicInteger(0);
        getDiameter(root, d);
        return d.get()-1;
    }
    
    public int getDiameter(TreeNode root, AtomicInteger d){
        if(root == null)
            return 0;
        
        int lH = getDiameter(root.left, d);
        int rH = getDiameter(root.right, d);
        int maxD = lH + rH + 1;
        d.set(Math.max(d.get(), maxD));
        return Math.max(lH, rH) +1;
    }
}
```


```
Cracking the Safe

There is a box protected by a password. The password is a sequence of n digits where each digit can be one of the first k digits 0, 1, ..., k-1.

While entering a password, the last n digits entered will automatically be matched against the correct password.

For example, assuming the correct password is "345", if you type "012345", the box will open because the correct password matches the suffix of the entered password.

Return any password of minimum length that is guaranteed to open the box at some point of entering it.

 

Example 1:

Input: n = 1, k = 2
Output: "01"
Note: "10" will be accepted too.

Example 2:

Input: n = 2, k = 2
Output: "00110"
Note: "01100", "10011", "11001" will be accepted too.

```

```
class Solution {
    public String crackSafe(int n, int k) {
        // current string
        StringBuilder sb = new StringBuilder();
        
        // total 
        int total = (int) (Math.pow(k, n));
        // all 0 for initial
        for(int i = 0 ; i < n; i ++) sb.append('0');
        // for visited
        Set<String> visited = new HashSet<>();
        visited.add(sb.toString());
        
        dfs(sb, total, visited, n, k);
        return sb.toString();
    }
    
    public boolean dfs(StringBuilder sb, int goal, Set <String> visited, int n, int k){
        if(visited.size() ==  goal) return true;
        String prev = sb.substring(sb.length()-n+1, sb.length());
        for(int i = 0 ; i < k ; i ++){
            String next = prev+i;
            if(!visited.contains(next)){
                visited.add(next);
                sb.append(i);
                if(dfs(sb, goal, visited, n, k)) return true;
                else{
                    visited.remove(next);
                    sb.delete(sb.length()-1, sb.length());
                }
            }
        }
        return false;
    }
}
```

```
Robot Room Cleaner

Given a robot cleaner in a room modeled as a grid.

Each cell in the grid can be empty or blocked.

The robot cleaner with 4 given APIs can move forward, turn left or turn right. Each turn it made is 90 degrees.

When it tries to move into a blocked cell, its bumper sensor detects the obstacle and it stays on the current cell.

Design an algorithm to clean the entire room using only the 4 given APIs shown below.

interface Robot {
  // returns true if next cell is open and robot moves into the cell.
  // returns false if next cell is obstacle and robot stays on the current cell.
  boolean move();

  // Robot will stay on the same cell after calling turnLeft/turnRight.
  // Each turn will be 90 degrees.
  void turnLeft();
  void turnRight();

  // Clean the current cell.
  void clean();
}

Example:

Input:
room = [
  [1,1,1,1,1,0,1,1],
  [1,1,1,1,1,0,1,1],
  [1,0,1,1,1,1,1,1],
  [0,0,0,1,0,0,0,0],
  [1,1,1,1,1,1,1,1]
],
row = 1,
col = 3

Explanation:
All grids in the room are marked by either 0 or 1.
0 means the cell is blocked, while 1 means the cell is accessible.
The robot initially starts at the position of row=1, col=3.
From the top left corner, its position is one row below and three columns right.

Notes:

    The input is only given to initialize the room and the robot's position internally. You must solve this problem "blindfolded". In other words, you must control the robot using only the mentioned 4 APIs, without knowing the room layout and the initial robot's position.
    The robot's initial position will always be in an accessible cell.
    The initial direction of the robot will be facing up.
    All accessible cells are connected, which means the all cells marked as 1 will be accessible by the robot.
    Assume all four edges of the grid are all surrounded by wall.
```

```
/**
 * // This is the robot's control interface.
 * // You should not implement it, or speculate about its implementation
 * interface Robot {
 *     // Returns true if the cell in front is open and robot moves into the cell.
 *     // Returns false if the cell in front is blocked and robot stays in the current cell.
 *     public boolean move();
 *
 *     // Robot will stay in the same cell after calling turnLeft/turnRight.
 *     // Each turn will be 90 degrees.
 *     public void turnLeft();
 *     public void turnRight();
 *
 *     // Clean the current cell.
 *     public void clean();
 * }
 */

class Solution {
    int[] dx = {-1, 0, 1, 0};
    int[] dy = {0, 1, 0, -1};
    
    public void cleanRoom(Robot robot) {
        Set<String> visited = new HashSet<>();
        dfs(robot, visited, 0, 0, 0);
    }
    

    public void dfs(Robot robot, Set<String> visited, int x, int y, int curDir) {
    	String key = x + "-" + y;
    	if (visited.contains(key)) return;
        visited.add(key);
    	robot.clean();

    	for (int i = 0; i < 4; i++) { // 4 directions
    		if(robot.move()) { // can go directly. Find the (x, y) for the next cell based on current direction
    			dfs(robot, visited, x + dx[curDir], y + dy[curDir], curDir);
                backtrack(robot);
    		} 

            // turn to next direction
    		robot.turnRight();
    		curDir += 1;
    		curDir %= 4;
    	}
    }

    // go back to the starting position
    private void backtrack(Robot robot) {
        robot.turnLeft();
        robot.turnLeft();
        robot.move();
        robot.turnRight();
        robot.turnRight();
    }
}
```

```
Most Stones Removed with Same Row or Column

On a 2D plane, we place stones at some integer coordinate points.  Each coordinate point may have at most one stone.

Now, a move consists of removing a stone that shares a column or row with another stone on the grid.

What is the largest possible number of moves we can make?

 

Example 1:

Input: stones = [[0,0],[0,1],[1,0],[1,2],[2,1],[2,2]]
Output: 5

Example 2:

Input: stones = [[0,0],[0,2],[1,1],[2,0],[2,2]]
Output: 3

Example 3:

Input: stones = [[0,0]]
Output: 0

 

Note:

    1 <= stones.length <= 1000
    0 <= stones[i][j] < 10000

```

```
class Solution {
    public int removeStones(int[][] stones) {
        int islands = 0;
        Set<Integer> visited = new HashSet<>();
        for(int i = 0 ; i < stones.length ; i ++){
            if(visited.contains(i)) continue;
            islands++;
            dfs(i, stones, visited);
        }
        return stones.length - islands;
    }
    
    public void dfs(int idx, int [][] stones, Set<Integer> visited){
        visited.add(idx);
        for(int nextIdx = 0 ; nextIdx < stones.length; nextIdx ++){
            if(visited.contains(nextIdx)) continue;
            if(stones[idx][0] == stones[nextIdx][0] || stones[idx][1] == stones[nextIdx][1]){
                dfs(nextIdx, stones, visited);
            }
        }
    }
}
```

```
Flip Equivalent Binary Trees

For a binary tree T, we can define a flip operation as follows: choose any node, and swap the left and right child subtrees.

A binary tree X is flip equivalent to a binary tree Y if and only if we can make X equal to Y after some number of flip operations.

Write a function that determines whether two binary trees are flip equivalent.  The trees are given by root nodes root1 and root2.

 

Example 1:

Input: root1 = [1,2,3,4,5,6,null,null,null,7,8], root2 = [1,3,2,null,6,4,5,null,null,null,null,8,7]
Output: true
Explanation: We flipped at nodes with values 1, 3, and 5.
Flipped Trees Diagram

 

Note:

    Each tree will have at most 100 nodes.
    Each value in each tree will be a unique integer in the range [0, 99].
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
    public boolean flipEquiv(TreeNode root1, TreeNode root2) {
        if(root1 == root2)
            return true;
        if(root1 == null || root2 == null || root1.val !=  root2.val)
            return false;
        return (flipEquiv(root1.left, root2.left) && flipEquiv(root1.right, root2.right) || 
               flipEquiv(root1.left, root2.right) && flipEquiv(root1.right, root2.left));
    }
}
```

```
Given a positive integer n, find the least number of perfect square numbers (for example, 1, 4, 9, 16, ...) which sum to n.

Example 1:

Input: n = 12
Output: 3 
Explanation: 12 = 4 + 4 + 4.

Example 2:

Input: n = 13
Output: 2
Explanation: 13 = 4 + 9.
```

```
class Solution {
    public int numSquares(int n) {
        int max = (int) Math.sqrt(n);
        
        int[] dp = new int [n+1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        for(int i = 1; i <= n ; i++){
            for(int j = 1; j <= max; j++){
                if(i == j*j){
                    dp[i] = 1;
                }else if( i > j*j){
                    dp[i] = Math.min(dp[i], dp[i-j*j] + 1);
                }
            }
        }
        return dp[n];
    }
}
```

```
On a campus represented as a 2D grid, there are N workers and M bikes, with N <= M. Each worker and bike is a 2D coordinate on this grid.

We assign one unique bike to each worker so that the sum of the Manhattan distances between each worker and their assigned bike is minimized.

The Manhattan distance between two points p1 and p2 is Manhattan(p1, p2) = |p1.x - p2.x| + |p1.y - p2.y|.

Return the minimum possible sum of Manhattan distances between each worker and their assigned bike.

 

Example 1:

Input: workers = [[0,0],[2,1]], bikes = [[1,2],[3,3]]
Output: 6
Explanation: 
We assign bike 0 to worker 0, bike 1 to worker 1. The Manhattan distance of both assignments is 3, so the output is 6.

Example 2:

Input: workers = [[0,0],[1,1],[2,0]], bikes = [[1,0],[2,2],[2,1]]
Output: 4
Explanation: 
We first assign bike 0 to worker 0, then assign bike 1 to worker 1 or worker 2, bike 2 to worker 2 or worker 1. Both assignments lead to sum of the Manhattan distances as 4.

 

Note:

    0 <= workers[i][0], workers[i][1], bikes[i][0], bikes[i][1] < 1000
    All worker and bike locations are distinct.
    1 <= workers.length <= bikes.length <= 10
    
```

```
class Solution {
    public int min = Integer.MAX_VALUE;
	
    public int assignBikes(int[][] workers, int[][] bikes) {
        dfs(workers, bikes, 0, 0, new boolean[bikes.length], new int[workers.length][bikes.length]);
        return min;
    }
    public void dfs(int[][] workers, int[][] bikes, int index, int distance, boolean[] isOccupied, int[][] dis) {
        if (index == workers.length) {
            min = Math.min(min, distance);
            return; 
        }
        
        if (distance >= min) {
            return;
        }
        for (int i = 0; i < bikes.length; i++) {
            if (!isOccupied[i]) {
                isOccupied[i] = true;

                if (dis[index][i] == 0) {
                    dis[index][i] = calcDistance(workers[index], bikes[i]);
                }

                dfs(workers, bikes, index + 1,  dis[index][i] + distance, isOccupied, dis);
                isOccupied[i] = false;
            }
        }
        return;
    }
    public int calcDistance(int[] worker, int[] bike) {
        return Math.abs(worker[0] - bike[0]) + Math.abs(worker[1] - bike[1]);
    }
}
```


```
Binary Tree Coloring Game

Two players play a turn based game on a binary tree.  We are given the root of this binary tree, and the number of nodes n in the tree.  n is odd, and each node has a distinct value from 1 to n.

Initially, the first player names a value x with 1 <= x <= n, and the second player names a value y with 1 <= y <= n and y != x.  The first player colors the node with value x red, and the second player colors the node with value y blue.

Then, the players take turns starting with the first player.  In each turn, that player chooses a node of their color (red if player 1, blue if player 2) and colors an uncolored neighbor of the chosen node (either the left child, right child, or parent of the chosen node.)

If (and only if) a player cannot choose such a node in this way, they must pass their turn.  If both players pass their turn, the game ends, and the winner is the player that colored more nodes.

You are the second player.  If it is possible to choose such a y to ensure you win the game, return true.  If it is not possible, return false.

 

Example 1:

Input: root = [1,2,3,4,5,6,7,8,9,10,11], n = 11, x = 3
Output: true
Explanation: The second player can choose the node with value 2.

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
    TreeNode xNode = null;
    int leftSize, rightSize;
    public boolean btreeGameWinningMove(TreeNode root, int n, int x) {
        dfs(root, x);
        return Math.max(n-leftSize-rightSize-1, Math.max(leftSize, rightSize)) > n/2;
    }
    
    private int dfs(TreeNode root, int x){
        if(root == null)
            return 0;
        int left = dfs(root.left, x);
        int right = dfs(root.right, x);
        if(root.val == x){
            leftSize = left;
            rightSize = right;
        }
        return left + right + 1;
    }
}
```


```
Perfect Squares

Given a positive integer n, find the least number of perfect square numbers (for example, 1, 4, 9, 16, ...) which sum to n.

Example 1:

Input: n = 12
Output: 3 
Explanation: 12 = 4 + 4 + 4.

Example 2:

Input: n = 13
Output: 2
Explanation: 13 = 4 + 9.

```

```
class Solution {
    public int numSquares(int n) {
        int max = (int) Math.sqrt(n);
        
        int[] dp = new int [n+1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        for(int i = 1; i <= n ; i++){
            for(int j = 1; j <= max; j++){
                if(i == j*j){
                    dp[i] = 1;
                }else if( i > j*j){
                    dp[i] = Math.min(dp[i], dp[i-j*j] + 1);
                }
            }
        }
        return dp[n];
    }
}
```

```
Brace Expansion
Medium

A string S represents a list of words.

Each letter in the word has 1 or more options.  If there is one option, the letter is represented as is.  If there is more than one option, then curly braces delimit the options.  For example, "{a,b,c}" represents options ["a", "b", "c"].

For example, "{a,b,c}d{e,f}" represents the list ["ade", "adf", "bde", "bdf", "cde", "cdf"].

Return all words that can be formed in this manner, in lexicographical order.

 

Example 1:

Input: "{a,b}c{d,e}f"
Output: ["acdf","acef","bcdf","bcef"]

Example 2:

Input: "abcd"
Output: ["abcd"]

 

Note:

    1 <= S.length <= 50
    There are no nested curly brackets.
    All characters inside a pair of consecutive opening and ending curly brackets are different.

```

```
class Solution {
    public String[] expand(String S) {
        List<String> result = new ArrayList<>();
        dfs(S, 0, "", result);
        String [] ans = new String[result.size()];
        int i = 0;
        for(String s : result){
            ans[i++] = s;
        }
        // for lexography, we need to sort that
        Arrays.sort(ans);
        return ans;
    }
    
    public void dfs(String s, int start, String curr, List<String> res){
        if(start == s.length()){
            res.add(curr);
            return;
        }
        
        int left = s.indexOf('{', start); /// from offset, get the starting index {
        int right = s.indexOf('}', start); /// from offset, get the starting index }
        if(left == -1){
            res.add(curr + s.substring(start));
            return;
        }
        
        String sub = s.substring(left+1, right);
        String [] strs = sub.split(",");
        // do it for the rest of them, after the closing }
        for(String str : strs){
            dfs(s, right+1, curr+s.substring(start, left) + str, res);
        }
    }
}

```

```
Stream of Characters
Hard

Implement the StreamChecker class as follows:

    StreamChecker(words): Constructor, init the data structure with the given words.
    query(letter): returns true if and only if for some k >= 1, the last k characters queried (in order from oldest to newest, including this letter just queried) spell one of the words in the given list.

 

Example:

StreamChecker streamChecker = new StreamChecker(["cd","f","kl"]); // init the dictionary.
streamChecker.query('a');          // return false
streamChecker.query('b');          // return false
streamChecker.query('c');          // return false
streamChecker.query('d');          // return true, because 'cd' is in the wordlist
streamChecker.query('e');          // return false
streamChecker.query('f');          // return true, because 'f' is in the wordlist
streamChecker.query('g');          // return false
streamChecker.query('h');          // return false
streamChecker.query('i');          // return false
streamChecker.query('j');          // return false
streamChecker.query('k');          // return false
streamChecker.query('l');          // return true, because 'kl' is in the wordlist

 

Note:

    1 <= words.length <= 2000
    1 <= words[i].length <= 2000
    Words will only consist of lowercase English letters.
    Queries will only consist of lowercase English letters.
    The number of queries is at most 40000.


```

```
class StreamChecker {
    
   class TrieNode {
        boolean isWord;
        TrieNode[] next = new TrieNode[26];
    }

    TrieNode root = new TrieNode();
    StringBuilder sb = new StringBuilder();

    public StreamChecker(String[] words) {
        createTrie(words);
    }
    
    public boolean query(char letter) {
        sb.append(letter);
        TrieNode node = root;
        for(int i = sb.length()-1; i >= 0 && node != null ; i--){
            char c = sb.charAt(i);
            node = node.next[c-'a'];
            if(node != null && node.isWord){
                return true;
            }
        }
        return false;
    }
    
    private void createTrie(String [] words){
        for(String s : words){
            TrieNode node = root;
            int len = s.length();
            for(int i = len-1; i >= 0 ; i --){
                char c = s.charAt(i);
                if(node.next[c-'a'] == null){
                    node.next[c-'a'] = new TrieNode();
                }
                node = node.next[c-'a'];
            }
            node.isWord = true;
        }
    }
}

/**
 * Your StreamChecker object will be instantiated and called as such:
 * StreamChecker obj = new StreamChecker(words);
 * boolean param_1 = obj.query(letter);
 */
 
 ```
 
 ```
 There is a robot starting at position (0, 0), the origin, on a 2D plane. Given a sequence of its moves, judge if this robot ends up at (0, 0) after it completes its moves.

The move sequence is represented by a string, and the character moves[i] represents its ith move. Valid moves are R (right), L (left), U (up), and D (down). If the robot returns to the origin after it finishes all of its moves, return true. Otherwise, return false.

Note: The way that the robot is "facing" is irrelevant. "R" will always make the robot move to the right once, "L" will always make it move left, etc. Also, assume that the magnitude of the robot's movement is the same for each move.

Example 1:

Input: "UD"
Output: true 
Explanation: The robot moves up once, and then down once. All moves have the same magnitude, so it ended up at the origin where it started. Therefore, we return true.

 

Example 2:

Input: "LL"
Output: false
Explanation: The robot moves left twice. It ends up two "moves" to the left of the origin. We return false because it is not at the origin at the end of its moves.

```

```
class Solution {
    public boolean judgeCircle(String moves) {
        int x = 0, y = 0;
        for(char c : moves.toCharArray()){
            if(c == 'U'){
                y++;
            }else if (c == 'D'){
                y--;
            }else if (c == 'R'){
                x++;
            }else{
                x--;
            }
        }
        if(x == 0 && y == 0){
            return true;
        }
        return false;
    }
}

```

```
 Expressive Words
Medium

Sometimes people repeat letters to represent extra feeling, such as "hello" -> "heeellooo", "hi" -> "hiiii".  In these strings like "heeellooo", we have groups of adjacent letters that are all the same:  "h", "eee", "ll", "ooo".

For some given string S, a query word is stretchy if it can be made to be equal to S by any number of applications of the following extension operation: choose a group consisting of characters c, and add some number of characters c to the group so that the size of the group is 3 or more.

For example, starting with "hello", we could do an extension on the group "o" to get "hellooo", but we cannot get "helloo" since the group "oo" has size less than 3.  Also, we could do another extension like "ll" -> "lllll" to get "helllllooo".  If S = "helllllooo", then the query word "hello" would be stretchy because of these two extension operations: query = "hello" -> "hellooo" -> "helllllooo" = S.

Given a list of query words, return the number of words that are stretchy. 

 

Example:
Input: 
S = "heeellooo"
words = ["hello", "hi", "helo"]
Output: 1
Explanation: 
We can extend "e" and "o" in the word "hello" to get "heeellooo".
We can't extend "helo" to get "heeellooo" because the group "ll" is not size 3 or more.

 

Notes:

    0 <= len(S) <= 100.
    0 <= len(words) <= 100.
    0 <= len(words[i]) <= 100.
    S and all words in words consist only of lowercase letters

```

```
class Solution {
    public int expressiveWords(String S, String[] words) {
        int res = 0;
        for (String W : words) if (check(S, W)) res++;
        return res;
    }
    public boolean check(String S, String W) {
        int n = S.length(), m = W.length(), j = 0;
        for (int i = 0; i < n; i++)
            if (j < m && S.charAt(i) == W.charAt(j)) j++;
            else if (i > 1 && S.charAt(i) == S.charAt(i - 1) && S.charAt(i - 1) == S.charAt(i - 2));
            else if (0 < i && i < n - 1 && S.charAt(i - 1) == S.charAt(i) && S.charAt(i) == S.charAt(i + 1));
            else return false;
        return j == m;
    }
}

```

```
 Most Stones Removed with Same Row or Column
Medium

On a 2D plane, we place stones at some integer coordinate points.  Each coordinate point may have at most one stone.

Now, a move consists of removing a stone that shares a column or row with another stone on the grid.

What is the largest possible number of moves we can make?

 

Example 1:

Input: stones = [[0,0],[0,1],[1,0],[1,2],[2,1],[2,2]]
Output: 5

Example 2:

Input: stones = [[0,0],[0,2],[1,1],[2,0],[2,2]]
Output: 3

Example 3:

Input: stones = [[0,0]]
Output: 0

 

Note:

    1 <= stones.length <= 1000
    0 <= stones[i][j] < 10000

```

```
class Solution {
    public int removeStones(int[][] stones) {
        int islands = 0;
        Set<Integer> visited = new HashSet<>();
        for(int i = 0 ; i < stones.length ; i ++){
            if(visited.contains(i)) continue;
            islands++;
            dfs(i, stones, visited);
        }
        return stones.length - islands;
    }
    
    public void dfs(int idx, int [][] stones, Set<Integer> visited){
        visited.add(idx);
        for(int nextIdx = 0 ; nextIdx < stones.length; nextIdx ++){
            if(visited.contains(nextIdx)) continue;
            if(stones[idx][0] == stones[nextIdx][0] || stones[idx][1] == stones[nextIdx][1]){
                dfs(nextIdx, stones, visited);
            }
        }
    }
}
```

```
Longest Increasing Path in a Matrix
Hard

Given an integer matrix, find the length of the longest increasing path.

From each cell, you can either move to four directions: left, right, up or down. You may NOT move diagonally or move outside of the boundary (i.e. wrap-around is not allowed).

Example 1:

Input: nums = 
[
  [9,9,4],
  [6,6,8],
  [2,1,1]
] 
Output: 4 
Explanation: The longest increasing path is [1, 2, 6, 9].

Example 2:

Input: nums = 
[
  [3,4,5],
  [3,2,6],
  [2,2,1]
] 
Output: 4 
Explanation: The longest increasing path is [3, 4, 5, 6]. Moving diagonally is not allowed.

```

```
class Solution {
    public int longestIncreasingPath(int[][] matrix) {
        if(matrix == null || matrix.length == 0)
            return 0;
        int result = 0;
        int m = matrix.length;
        int n = matrix[0].length;
        int [][] dp = new int[m][n];
        for(int i = 0; i < m ; i++){
            for(int j = 0; j < n ; j++){
                int t = helper(matrix, dp, i, j);
                result = Math.max(result, t);
            }
        }
        return result;
    }
    
    public int helper(int [][] matrix, int [][] dp, int i, int j){
        if(dp[i][j] > 0){
            return dp[i][j];
        }
        int [] dx = {-1, 0, 1, 0};
        int [] dy = {0, 1, 0, -1};
        for(int k = 0 ; k < 4 ; k++){
            int x = i + dx[k];
            int y = j + dy[k];
            if(x >= 0 && y >= 0
              && x < matrix.length && y < matrix[0].length 
              && matrix[x][y] > matrix[i][j]){
                dp[i][j] = Math.max(dp[i][j], helper(matrix, dp, x, y));
            }
        }
        return ++dp[i][j];
    }
}

```

```
Let's call an array A a mountain if the following properties hold:

    A.length >= 3
    There exists some 0 < i < A.length - 1 such that A[0] < A[1] < ... A[i-1] < A[i] > A[i+1] > ... > A[A.length - 1]

Given an array that is definitely a mountain, return any i such that A[0] < A[1] < ... A[i-1] < A[i] > A[i+1] > ... > A[A.length - 1].

Example 1:

Input: [0,1,0]
Output: 1

Example 2:

Input: [0,2,1,0]
Output: 1

Note:

    3 <= A.length <= 10000
    0 <= A[i] <= 10^6
    A is a mountain, as defined above.

```


```
class Solution {
    public int peakIndexInMountainArray(int[] A) {
        int lo = 0, hi = A.length - 1;
        while(lo < hi){
            int mi = lo + (hi-lo)/2;
            if(A[mi] < A[mi+1]){
                lo = mi + 1;
            }else{
                hi = mi;
            }
        }
        return lo;
    }
}
```

```
(This problem is an interactive problem.)

You may recall that an array A is a mountain array if and only if:

    A.length >= 3
    There exists some i with 0 < i < A.length - 1 such that:
        A[0] < A[1] < ... A[i-1] < A[i]
        A[i] > A[i+1] > ... > A[A.length - 1]

Given a mountain array mountainArr, return the minimum index such that mountainArr.get(index) == target.  If such an index doesn't exist, return -1.

You can't access the mountain array directly.  You may only access the array using a MountainArray interface:

    MountainArray.get(k) returns the element of the array at index k (0-indexed).
    MountainArray.length() returns the length of the array.

Submissions making more than 100 calls to MountainArray.get will be judged Wrong Answer.  Also, any solutions that attempt to circumvent the judge will result in disqualification.

 

Example 1:

Input: array = [1,2,3,4,5,3,1], target = 3
Output: 2
Explanation: 3 exists in the array, at index=2 and index=5. Return the minimum index, which is 2.

Example 2:

Input: array = [0,1,2,4,2,1], target = 3
Output: -1
Explanation: 3 does not exist in the array, so we return -1.

```

```
/**
 * // This is MountainArray's API interface.
 * // You should not implement it, or speculate about its implementation
 * interface MountainArray {
 *     public int get(int index) {}
 *     public int length() {}
 * }
 */
 
class Solution {
    public int findInMountainArray(int target, MountainArray mountainArr) {
        int peakIndex = peakIndexInMountainArray(mountainArr);
        int left = 0, right = peakIndex;
        while (left < right) {
            int mid = left + (right - left >> 1);
            if (mountainArr.get(mid) >= target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        if (mountainArr.get(left) == target) {
            return left;
        }

        left = peakIndex;
        right = mountainArr.length();
        while (left < right) {
            int mid = left + (right - left >> 1);
            if (mountainArr.get(mid) <= target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left < mountainArr.length() && mountainArr.get(left) == target ? left : -1;
    }
    
    private int peakIndexInMountainArray(MountainArray arr) {
        int left = 0, right = arr.length() - 1;
        while (left < right) {
            int mid = left + (right - left >> 1);
            if (arr.get(mid) < arr.get(mid + 1)) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }
}

```

```
Given the root of a binary tree, each node in the tree has a distinct value.

After deleting all nodes with a value in to_delete, we are left with a forest (a disjoint union of trees).

Return the roots of the trees in the remaining forest.  You may return the result in any order.

 

Example 1:

Input: root = [1,2,3,4,5,6,7], to_delete = [3,5]
Output: [[1,2,null,4],[6],[7]]

 

Constraints:

    The number of nodes in the given tree is at most 1000.
    Each node has a distinct value between 1 and 1000.
    to_delete.length <= 1000
    to_delete contains distinct values between 1 and 1000.
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
    List<TreeNode> list = new ArrayList<>();
    Set<Integer> set = new HashSet<>();
    public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
        for(int i : to_delete){
            set.add(i);
        }
        
        root = delete(root, true);
        return list;
    }
    
    private TreeNode delete(TreeNode node, boolean isRoot){
        if(node == null) return null;
        
        boolean delete = set.contains(node.val);
        if(isRoot && !delete) list.add(node);
        node.left = delete(node.left, delete);
        node.right = delete(node.right, delete);
        return delete ? null : node;
    }
}
```

```
iven a string, we can "shift" each of its letter to its successive letter, for example: "abc" -> "bcd". We can keep "shifting" which forms the sequence:

"abc" -> "bcd" -> ... -> "xyz"

Given a list of strings which contains only lowercase alphabets, group all strings that belong to the same shifting sequence.

Example:

Input: ["abc", "bcd", "acef", "xyz", "az", "ba", "a", "z"],
Output: 
[
  ["abc","bcd","xyz"],
  ["az","ba"],
  ["acef"],
  ["a","z"]
]

```

```
class Solution {
    public List<List<String>> groupStrings(String[] strings) {
        List<List<String>> result = new ArrayList<List<String>>();
        HashMap<String, ArrayList<String>> map 
                    = new HashMap<String, ArrayList<String>>();
        
        for(String s : strings){
            char [] arr = s.toCharArray();
            if(arr.length > 0){
                int diff = arr[0]-'a';
                for(int i = 0; i < arr.length ; i ++){
                    if(arr[i] - diff < 'a'){
                        arr[i] = (char) (arr[i] - diff + 26);
                    }else{
                        arr[i] = (char) (arr[i] - diff);
                    }
                }
            }
            String ns = new String(arr);
            if(map.containsKey(ns)){
                map.get(ns).add(s);
            }else{
                ArrayList<String> al = new ArrayList<String>();
                al.add(s);
                map.put(ns, al);
            }

        }
    
        for(Map.Entry<String, ArrayList<String>> entry: map.entrySet()){
            Collections.sort(entry.getValue());
        }
 
        result.addAll(map.values());
 
        return result;
    }
}
```


```
Given a 2d grid map of '1's (land) and '0's (water), count the number of islands. An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.

Example 1:

Input:
11110
11010
11000
00000

Output: 1

Example 2:

Input:
11000
11000
00100
00011

Output: 3

```


```
class Solution {
    public int numIslands(char[][] grid) {
        if(grid == null || grid.length == 0)
            return 0;
        int numIsLands = 0;
        for(int i = 0; i < grid.length ; i++){
            for(int j = 0 ; j < grid[i].length ; j++){
                if(grid[i][j] == '1'){
                    numIsLands += dfs(grid, i, j);
                }
            }
        }
        return numIsLands;
    }
    
    public int dfs(char[][] grid, int i , int j){
        if(i < 0 || j >= grid.length || j < 0 || j >= grid[i].length || grid[i][j] == '0')
            return 0;
        
        grid[i][j] = '0';
        dfs(grid, i+1, j);
        dfs(grid, i-1, j);
        dfs(grid, i, j+1);
        dfs(grid, i, j-1);
        return 1;
    }
}
```


```
Given a 2d grid map of '1's (land) and '0's (water), count the number of islands. An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.

Example 1:

Input:
11110
11010
11000
00000

Output: 1

Example 2:

Input:
11000
11000
00100
00011

Output: 3
```

```
class Solution {
    public int numIslands(char[][] grid) {
        if(grid == null || grid.length == 0)
            return 0;
        int numIsLands = 0;
        for(int i = 0; i < grid.length ; i++){
            for(int j = 0 ; j < grid[i].length ; j++){
                if(grid[i][j] == '1'){
                    numIsLands += dfs(grid, i, j);
                }
            }
        }
        return numIsLands;
    }
    
    public int dfs(char[][] grid, int i , int j){
        if(i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] == '0')
            return 0;
        
        grid[i][j] = '0';
        dfs(grid, i+1, j);
        dfs(grid, i-1, j);
        dfs(grid, i, j+1);
        dfs(grid, i, j-1);
        return 1;
    }
}
```

```
Given a rows x cols screen and a sentence represented by a list of non-empty words, find how many times the given sentence can be fitted on the screen.

Note:

    A word cannot be split into two lines.
    The order of words in the sentence must remain unchanged.
    Two consecutive words in a line must be separated by a single space.
    Total words in the sentence won't exceed 100.
    Length of each word is greater than 0 and won't exceed 10.
    1 ≤ rows, cols ≤ 20,000.

Example 1:

Input:
rows = 2, cols = 8, sentence = ["hello", "world"]

Output: 
1

Explanation:
hello---
world---

The character '-' signifies an empty space on the screen.

Example 2:

Input:
rows = 3, cols = 6, sentence = ["a", "bcd", "e"]

Output: 
2

Explanation:
a-bcd- 
e-a---
bcd-e-

The character '-' signifies an empty space on the screen.

Example 3:

Input:
rows = 4, cols = 5, sentence = ["I", "had", "apple", "pie"]

Output: 
1

Explanation:
I-had
apple
pie-I
had--

The character '-' signifies an empty space on the screen.

```

```
class Solution {
    public int wordsTyping(String[] sentence, int rows, int cols) {
        String sen = String.join(" ", sentence) + " ";
        int len = sen.length();
        int total = 0;
        
        for(int i= 0 ; i < rows ; i++){
            total += cols;
            if(sen.charAt(total%len) == ' '){
                total++;
            }else{
                while(total > 0 && sen.charAt((total-1)%len) != ' '){
                    total--;
                }
            }
        }
        return total/len;
    }
}

```

```
Initially, the 2d grid grid is filled with water. (Assume 0 represents water and 1 represents land).

0 0 0
0 0 0
0 0 0

Operation #1: addLand(0, 0) turns the water at grid[0][0] into a land.

1 0 0
0 0 0   Number of islands = 1
0 0 0

Operation #2: addLand(0, 1) turns the water at grid[0][1] into a land.

1 1 0
0 0 0   Number of islands = 1
0 0 0

Operation #3: addLand(1, 2) turns the water at grid[1][2] into a land.

1 1 0
0 0 1   Number of islands = 2
0 0 0

Operation #4: addLand(2, 1) turns the water at grid[2][1] into a land.

1 1 0
0 0 1   Number of islands = 3
0 1 0

Follow up:

Can you do it in time complexity O(k log mn), where k is the length of the positions?
```

```
class Solution {
    public List<Integer> numIslands2(int m, int n, int[][] positions) {
    int[] parent = new int[m * n];
    Arrays.fill(parent,-1);
 
    ArrayList<Integer> result = new ArrayList<>();
 
    int[] dx = {-1, 0, 1, 0};
    int[] dy = {0, 1, 0, -1};
 
    int count = 0;
    for (int[] position : positions) {
        count++;
        int idx = n * position[0] + position[1];
 
        if (parent[idx] == -1) {
            parent[idx] = idx;
        }
 
        for (int k = 0; k < 4; k++) {
            int x = position[0] + dx[k];
            int y = position[1] + dy[k];
 
            int idxNeighbor = n * x + y;
 
            if (x >= 0 && x < m && y >= 0 && y < n && parent[idxNeighbor] != -1) {
                int p = getParent(parent, idxNeighbor);
 
                //set neighor's parent to be current idx
                if (parent[p] != idx) {
                    parent[p] = idx;
                    count--;
                }
            }
        }
 
        result.add(count);
    }
 
    return result;
}
 
private int getParent(int[] parent, int i) {
    while (parent[i] != i) {
        i = parent[i];
    }
 
    return i;
}
}

```

```
Design a logger system that receive stream of messages along with its timestamps, each message should be printed if and only if it is not printed in the last 10 seconds.

Given a message and a timestamp (in seconds granularity), return true if the message should be printed in the given timestamp, otherwise returns false.

It is possible that several messages arrive roughly at the same time.

Example:

Logger logger = new Logger();

// logging string "foo" at timestamp 1
logger.shouldPrintMessage(1, "foo"); returns true; 

// logging string "bar" at timestamp 2
logger.shouldPrintMessage(2,"bar"); returns true;

// logging string "foo" at timestamp 3
logger.shouldPrintMessage(3,"foo"); returns false;

// logging string "bar" at timestamp 8
logger.shouldPrintMessage(8,"bar"); returns false;

// logging string "foo" at timestamp 10
logger.shouldPrintMessage(10,"foo"); returns false;

// logging string "foo" at timestamp 11
logger.shouldPrintMessage(11,"foo"); returns true;

```

```
class Logger {
     private Map<String, Integer> ok = new HashMap<>();
    /** Initialize your data structure here. */
    public Logger() {
        
    }
    
    /** Returns true if the message should be printed in the given timestamp, otherwise returns false.
        If this method returns false, the message will not be printed.
        The timestamp is in seconds granularity. */
    public boolean shouldPrintMessage(int timestamp, String message) {
        if (timestamp < ok.getOrDefault(message, 0))
            return false;
        ok.put(message, timestamp + 10);
        return true;
    }
}

/**
 * Your Logger object will be instantiated and called as such:
 * Logger obj = new Logger();
 * boolean param_1 = obj.shouldPrintMessage(timestamp,message);
 */
 ```
 
 
 ```
 On a campus represented as a 2D grid, there are N workers and M bikes, with N <= M. Each worker and bike is a 2D coordinate on this grid.

Our goal is to assign a bike to each worker. Among the available bikes and workers, we choose the (worker, bike) pair with the shortest Manhattan distance between each other, and assign the bike to that worker. (If there are multiple (worker, bike) pairs with the same shortest Manhattan distance, we choose the pair with the smallest worker index; if there are multiple ways to do that, we choose the pair with the smallest bike index). We repeat this process until there are no available workers.

The Manhattan distance between two points p1 and p2 is Manhattan(p1, p2) = |p1.x - p2.x| + |p1.y - p2.y|.

Return a vector ans of length N, where ans[i] is the index (0-indexed) of the bike that the i-th worker is assigned to.

 

Example 1:

Input: workers = [[0,0],[2,1]], bikes = [[1,2],[3,3]]
Output: [1,0]
Explanation: 
Worker 1 grabs Bike 0 as they are closest (without ties), and Worker 0 is assigned Bike 1. So the output is [1, 0].

Example 2:

Input: workers = [[0,0],[1,1],[2,0]], bikes = [[1,0],[2,2],[2,1]]
Output: [0,2,1]
Explanation: 
Worker 0 grabs Bike 0 at first. Worker 1 and Worker 2 share the same distance to Bike 2, thus Worker 1 is assigned to Bike 2, and Worker 2 will take Bike 1. So the output is [0,2,1].

 

Note:

    0 <= workers[i][j], bikes[i][j] < 1000
    All worker and bike locations are distinct.
    1 <= workers.length <= bikes.length <= 1000

```

```
class Solution {

    public int[] assignBikes(int[][] workers, int[][] bikes) {
        List<int[]>[] buckets = new List[2001];
        for (int i = 0; i < workers.length; i++) {
            for (int j = 0; j < bikes.length; j++) {
                int dis = Math.abs(bikes[j][0] - workers[i][0]) + Math.abs(bikes[j][1] - workers[i][1]);
                if (buckets[dis] == null) {
                    buckets[dis] = new ArrayList<>();
                }
                buckets[dis].add(new int[] {i, j});
            }
        }
        int[] res = new int[workers.length];
        Arrays.fill(res, -1);
        Set<Integer> assignedBike = new HashSet<>();
        for (int i = 0; i < buckets.length && assignedBike.size() < workers.length; i++) {
            if (buckets[i] != null) {
                for (int[] pair : buckets[i]) {
                    if (res[pair[0]] < 0 && !assignedBike.contains(pair[1])) {
                        res[pair[0]] = pair[1];
                        assignedBike.add(pair[1]);
                    }
                }
            }
        }
        return res;
    }
}
```

```
All Paths from Source Lead to Destination

Given the edges of a directed graph, and two nodes source and destination of this graph, determine whether or not all paths starting from source eventually end at destination, that is:

    At least one path exists from the source node to the destination node
    If a path exists from the source node to a node with no outgoing edges, then that node is equal to destination.
    The number of possible paths from source to destination is a finite number.

Return true if and only if all roads from source lead to destination.

 

Example 1:

Input: n = 3, edges = [[0,1],[0,2]], source = 0, destination = 2
Output: false
Explanation: It is possible to reach and get stuck on both node 1 and node 2.

Example 2:

Input: n = 4, edges = [[0,1],[0,3],[1,2],[2,1]], source = 0, destination = 3
Output: false
Explanation: We have two possibilities: to end at node 3, or to loop over node 1 and node 2 indefinitely.

Example 3:

Input: n = 4, edges = [[0,1],[0,2],[1,3],[2,3]], source = 0, destination = 3
Output: true

Example 4:

Input: n = 3, edges = [[0,1],[1,1],[1,2]], source = 0, destination = 2
Output: false
Explanation: All paths from the source node end at the destination node, but there are an infinite number of paths, such as 0-1-2, 0-1-1-2, 0-1-1-1-2, 0-1-1-1-1-2, and so on.

Example 5:

Input: n = 2, edges = [[0,1],[1,1]], source = 0, destination = 1
Output: false
Explanation: There is infinite self-loop at destination node.

 

Note:

    The given graph may have self loops and parallel edges.
    The number of nodes n in the graph is between 1 and 10000
    The number of edges in the graph is between 0 and 10000
    0 <= edges.length <= 10000
    edges[i].length == 2
    0 <= source <= n - 1
    0 <= destination <= n - 1

```

```
class Solution {
    Map<Integer, List<Integer>> graph = new HashMap<>();
    public boolean leadsToDestination(int n, int[][] edges, int source, int destination) {
        buildGraph(edges);
        Queue<Integer> q = new LinkedList<>();
        q.add(source);
        boolean [] visited = new boolean[n];
        while(!q.isEmpty()){
            int node = q.poll();
            if(node == destination){
                // destination was not been added since it's the end, there was no edge from desitination
                if(!graph.containsKey(node)) continue;
            }
            visited[node] = true;
            if(!graph.containsKey(node))
                return false;
            for(int next : graph.get(node)){
                if(!visited[next])
                    q.add(next);
                else
                    return false;
            }
        }
        return true;
    }
    
    private void buildGraph(int [][] edges){
        for(int [] edge : edges){
            int src = edge[0];
            int des = edge[1];
            graph.compute(src, (k, v) -> v == null ? new ArrayList<>() : v).add(des);
            
        }   
    }
}

```

```
Given an array A of integers, return true if and only if it is a valid mountain array.

Recall that A is a mountain array if and only if:

    A.length >= 3
    There exists some i with 0 < i < A.length - 1 such that:
        A[0] < A[1] < ... A[i-1] < A[i]
        A[i] > A[i+1] > ... > A[A.length - 1]


 

Example 1:

Input: [2,1]
Output: false

Example 2:

Input: [3,5,5]
Output: false

Example 3:

Input: [0,3,2,1]
Output: true

 

Note:

    0 <= A.length <= 10000
    0 <= A[i] <= 10000 

```

```java
class Solution {
    public boolean validMountainArray(int[] A) {
        if(A == null || A.length < 3)
            return false;
        
        int peak = 0;
        while(peak + 1 < A.length){
            if(A[peak+1] <= A[peak]){
                break;
            }
            peak++;
        }
        
        if(peak == 0 || peak == A.length - 1){
            return false;
        }
        while(peak + 1 < A.length){
            if(A[peak] <= A[peak+1]){
                return false;
            }
            peak++;
        }
        return true;
    }
}
```

```
Minimum Area Rectangle

Given a set of points in the xy-plane, determine the minimum area of a rectangle formed from these points, with sides parallel to the x and y axes.

If there isn't any rectangle, return 0.

 

Example 1:

Input: [[1,1],[1,3],[3,1],[3,3],[2,2]]
Output: 4

Example 2:

Input: [[1,1],[1,3],[3,1],[3,3],[4,1],[4,3]]
Output: 2

 

Note:

    1 <= points.length <= 500
    0 <= points[i][0] <= 40000
    0 <= points[i][1] <= 40000
    All points are distinct.

```

```java
class Solution {
    public int minAreaRect(int[][] points) {
        Set<Integer> pointSet = new HashSet();
        for (int[] point: points)
            pointSet.add(40001 * point[0] + point[1]);

        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < points.length; ++i)
            for (int j = i+1; j < points.length; ++j) {
                if (points[i][0] != points[j][0] && points[i][1] != points[j][1]) {
                    if (pointSet.contains(40001 * points[i][0] + points[j][1]) &&
                            pointSet.contains(40001 * points[j][0] + points[i][1])) {
                        ans = Math.min(ans, Math.abs(points[j][0] - points[i][0]) *
                                            Math.abs(points[j][1] - points[i][1]));
                    }
                }
            }

        return ans < Integer.MAX_VALUE ? ans : 0;
    }
}
```

```
My Calendar II

Implement a MyCalendarTwo class to store your events. A new event can be added if adding the event will not cause a triple booking.

Your class will have one method, book(int start, int end). Formally, this represents a booking on the half open interval [start, end), the range of real numbers x such that start <= x < end.

A triple booking happens when three events have some non-empty intersection (ie., there is some time that is common to all 3 events.)

For each call to the method MyCalendar.book, return true if the event can be added to the calendar successfully without causing a triple booking. Otherwise, return false and do not add the event to the calendar.
Your class will be called like this: MyCalendar cal = new MyCalendar(); MyCalendar.book(start, end)

Example 1:

MyCalendar();
MyCalendar.book(10, 20); // returns true
MyCalendar.book(50, 60); // returns true
MyCalendar.book(10, 40); // returns true
MyCalendar.book(5, 15); // returns false
MyCalendar.book(5, 10); // returns true
MyCalendar.book(25, 55); // returns true
Explanation: 
The first two events can be booked.  The third event can be double booked.
The fourth event (5, 15) can't be booked, because it would result in a triple booking.
The fifth event (5, 10) can be booked, as it does not use time 10 which is already double booked.
The sixth event (25, 55) can be booked, as the time in [25, 40) will be double booked with the third event;
the time [40, 50) will be single booked, and the time [50, 55) will be double booked with the second event.

 

Note:

    The number of calls to MyCalendar.book per test case will be at most 1000.
    In calls to MyCalendar.book(start, end), start and end are integers in the range [0, 10^9].

```


```java
class MyCalendarTwo {

    List<int[]> calendar;
    List<int[]> overlaps;

    MyCalendarTwo() {
        calendar = new ArrayList();
        overlaps = new ArrayList();
    }

    public boolean book(int start, int end) {
        for (int[] iv: overlaps) {
            if (iv[0] < end && start < iv[1]) return false;
        }
        for (int[] iv: calendar) {
            if (iv[0] < end && start < iv[1])
                overlaps.add(new int[]{Math.max(start, iv[0]), Math.min(end, iv[1])});
        }
        calendar.add(new int[]{start, end});
        return true;
    }
}

/**
 * Your MyCalendarTwo object will be instantiated and called as such:
 * MyCalendarTwo obj = new MyCalendarTwo();
 * boolean param_1 = obj.book(start,end);
 */
 ```
 
 ```
 Plus One

Given a non-empty array of digits representing a non-negative integer, plus one to the integer.

The digits are stored such that the most significant digit is at the head of the list, and each element in the array contain a single digit.

You may assume the integer does not contain any leading zero, except the number 0 itself.

Example 1:

Input: [1,2,3]
Output: [1,2,4]
Explanation: The array represents the integer 123.

Example 2:

Input: [4,3,2,1]
Output: [4,3,2,2]
Explanation: The array represents the integer 4321.

```

```java
class Solution {
    public int[] plusOne(int[] digits) {
        if(digits == null || digits.length == 0)
            return digits;
        
        for(int i = digits.length-1 ; i >=0 ; i --){
            if(digits[i] != 9){
                digits[i]++;
                break;
            }else{
                digits[i] = 0;
            }
        }
        if(digits[0] == 0){
            int[] result = new int [digits.length+1];
            result[0] = 1;
            return result;
        }
        return digits;
    }
}
```

```
Find And Replace in String

To some string S, we will perform some replacement operations that replace groups of letters with new ones (not necessarily the same size).

Each replacement operation has 3 parameters: a starting index i, a source word x and a target word y.  The rule is that if x starts at position i in the original string S, then we will replace that occurrence of x with y.  If not, we do nothing.

For example, if we have S = "abcd" and we have some replacement operation i = 2, x = "cd", y = "ffff", then because "cd" starts at position 2 in the original string S, we will replace it with "ffff".

Using another example on S = "abcd", if we have both the replacement operation i = 0, x = "ab", y = "eee", as well as another replacement operation i = 2, x = "ec", y = "ffff", this second operation does nothing because in the original string S[2] = 'c', which doesn't match x[0] = 'e'.

All these operations occur simultaneously.  It's guaranteed that there won't be any overlap in replacement: for example, S = "abc", indexes = [0, 1], sources = ["ab","bc"] is not a valid test case.

Example 1:

Input: S = "abcd", indexes = [0,2], sources = ["a","cd"], targets = ["eee","ffff"]
Output: "eeebffff"
Explanation: "a" starts at index 0 in S, so it's replaced by "eee".
"cd" starts at index 2 in S, so it's replaced by "ffff".

Example 2:

Input: S = "abcd", indexes = [0,2], sources = ["ab","ec"], targets = ["eee","ffff"]
Output: "eeecd"
Explanation: "ab" starts at index 0 in S, so it's replaced by "eee". 
"ec" doesn't starts at index 2 in the original S, so we do nothing.

Notes:

    0 <= indexes.length = sources.length = targets.length <= 100
    0 < indexes[i] < S.length <= 1000
    All characters in given inputs are lowercase letters.

```

```java
class Solution {
    public String findReplaceString(String S, int[] indexes, String[] sources, String[] targets) {
        int N = S.length();
        int [] match = new int[N];
        Arrays.fill(match, -1);
        
        for(int i = 0; i < indexes.length; i++){
            int ix = indexes[i];
            if(S.substring(ix, ix+sources[i].length()).equals(sources[i])){
                match[ix] = i;
            }
        }
        
        StringBuilder ans = new StringBuilder();
        int ix = 0;
        while(ix < N){
            if(match[ix] >= 0){
                ans.append(targets[match[ix]]);
                ix += sources[match[ix]].length();
            }else{
                ans.append(S.charAt(ix++));
            }
        }
        return ans.toString();
    }
}
```

```
Merge Intervals

Given a collection of intervals, merge all overlapping intervals.

Example 1:

Input: [[1,3],[2,6],[8,10],[15,18]]
Output: [[1,6],[8,10],[15,18]]
Explanation: Since intervals [1,3] and [2,6] overlaps, merge them into [1,6].

Example 2:

Input: [[1,4],[4,5]]
Output: [[1,5]]
Explanation: Intervals [1,4] and [4,5] are considered overlapping.

NOTE: input types have been changed on April 15, 2019. Please reset to default code definition to get new method signature.

```


```java
class Solution {
  private class IntervalComparator implements Comparator<int[]> {
    @Override
    public int compare(int[] a, int[] b) {
      return a[0] < b[0] ? -1 : a[0] == b[0] ? 0 : 1;
    }
  }

  public int[][] merge(int[][] intervals) {
    Collections.sort(Arrays.asList(intervals), new IntervalComparator());

    LinkedList<int[]> merged = new LinkedList<>();
    for (int[] interval : intervals) {
      // if the list of merged intervals is empty or if the current
      // interval does not overlap with the previous, simply append it.
      if (merged.isEmpty() || merged.getLast()[1] < interval[0]) {
        merged.add(interval);
      }
      // otherwise, there is overlap, so we merge the current and previous
      // intervals.
      else {
        merged.getLast()[1] = Math.max(merged.getLast()[1], interval[1]);
      }
    }

    return merged.toArray(new int[merged.size()][]);
  }
}
```

```
On an NxN chessboard, a knight starts at the r-th row and c-th column and attempts to make exactly K moves. The rows and columns are 0 indexed, so the top-left square is (0, 0), and the bottom-right square is (N-1, N-1).

A chess knight has 8 possible moves it can make, as illustrated below. Each move is two squares in a cardinal direction, then one square in an orthogonal direction.

 

 

Each time the knight is to move, it chooses one of eight possible moves uniformly at random (even if the piece would go off the chessboard) and moves there.

The knight continues moving until it has made exactly K moves or has moved off the chessboard. Return the probability that the knight remains on the board after it has stopped moving.

 

Example:

Input: 3, 2, 0, 0
Output: 0.0625
Explanation: There are two moves (to (1,2), (2,1)) that will keep the knight on the board.
From each of those positions, there are also two moves that will keep the knight on the board.
The total probability the knight stays on the board is 0.0625.

 

Note:

    N will be between 1 and 25.
    K will be between 0 and 100.
    The knight always initially starts on the board.

```

```java
class Solution {
    public double knightProbability(int N, int K, int sr, int sc) {
        double [] [] dp = new double[N][N];
        int[] dr = new int[]{2, 2, 1, 1, -1, -1, -2, -2};
        int[] dc = new int[]{1, -1, 2, -2, 2, -2, 1, -1};
        dp[sr][sc] = 1;
        
        for(; K > 0; K--){
            double[][] dp2 = new double[N][N];
            for(int r = 0 ; r < N ; r++){
                for(int c = 0 ; c < N ; c++){
                    for(int k = 0; k < 8 ; k++){
                        int cr = r + dr[k];
                        int cc = c + dc[k];
                        if(0 <= cr && cr < N && 0 <= cc && cc < N){
                            dp2[cr][cc]  += dp[r][c]/8.0;
                        }
                    }
                }
            }
            dp = dp2;
        }
        double ans = 0.0;
        for(double [] row : dp){
            for(double x : row) ans += x;
        }
        return ans;
    }
}

```

```
Confusing Number

Given a number N, return true if and only if it is a confusing number, which satisfies the following condition:

We can rotate digits by 180 degrees to form new digits. When 0, 1, 6, 8, 9 are rotated 180 degrees, they become 0, 1, 9, 8, 6 respectively. When 2, 3, 4, 5 and 7 are rotated 180 degrees, they become invalid. A confusing number is a number that when rotated 180 degrees becomes a different number with each digit valid.

 

Example 1:

Input: 6
Output: true
Explanation: 
We get 9 after rotating 6, 9 is a valid number and 9!=6.

Example 2:

Input: 89
Output: true
Explanation: 
We get 68 after rotating 89, 86 is a valid number and 86!=89.

Example 3:

Input: 11
Output: false
Explanation: 
We get 11 after rotating 11, 11 is a valid number but the value remains the same, thus 11 is not a confusing number.

Example 4:

Input: 25
Output: false
Explanation: 
We get an invalid number after rotating 25.

 

Note:

    0 <= N <= 10^9
    After the rotation we can ignore leading zeros, for example if after rotation we have 0008 then this number is considered as just 8.

```

```java
class Solution {
    public boolean confusingNumber(int N) {
        Map<Integer, Integer> m = new HashMap<>();
        m.put(0, 0);
        m.put(1, 1);
        m.put(6, 9);
        m.put(8, 8);
        m.put(9, 6);
        int rotate = 0;
        int temp = N;
        
        while(temp != 0){
            int mod = temp % 10;
            if(!m.containsKey(mod))
                return false;
            rotate = 10 * rotate + m.get(mod);
            temp = temp/10;
        }
        return rotate != N;
    }
}
```

```
Shortest Way to Form String

From any string, we can form a subsequence of that string by deleting some number of characters (possibly no deletions).

Given two strings source and target, return the minimum number of subsequences of source such that their concatenation equals target. If the task is impossible, return -1.

 

Example 1:

Input: source = "abc", target = "abcbc"
Output: 2
Explanation: The target "abcbc" can be formed by "abc" and "bc", which are subsequences of source "abc".

Example 2:

Input: source = "abc", target = "acdbc"
Output: -1
Explanation: The target string cannot be constructed from the subsequences of source string due to the character "d" in target string.

Example 3:

Input: source = "xyz", target = "xzyxz"
Output: 3
Explanation: The target string can be constructed as follows "xz" + "y" + "xz".

 

Constraints:

    Both the source and target strings consist of only lowercase English letters from "a"-"z".
    The lengths of source and target string are between 1 and 1000.
```

```java
class Solution {
    public int shortestWay(String source, String target) {
        char [] sc = source.toCharArray();
        char[] ta = target.toCharArray();
        boolean [] map = new boolean[26];
        for(char c : sc){
            map[c-'a']= true;
        }
        
        int j = 0, res = 1;
        for(int i = 0; i < ta.length; i++, j++){
            if(!map[ta[i]-'a']) return -1;
            while(j < sc.length && sc[j] != ta[i]) j++;
            if(j == sc.length){
                res ++ ;
                j = -1;
                i--;
            }
        }
        return res;
    }
}
```

```
Moving Average from Data Stream

Given a stream of integers and a window size, calculate the moving average of all integers in the sliding window.

Example:

MovingAverage m = new MovingAverage(3);
m.next(1) = 1
m.next(10) = (1 + 10) / 2
m.next(3) = (1 + 10 + 3) / 3
m.next(5) = (10 + 3 + 5) / 3

```

```java
class MovingAverage {

    double sum;
    int size;
    LinkedList<Integer> list;
    /** Initialize your data structure here. */
    public MovingAverage(int size) {
        this.list = new LinkedList<>();
        this.size = size;
    }
    
    public double next(int val) {
        sum += val;
        list.offer(val);
        if(list.size() <= size){
            return sum/list.size();
        }
        sum -= list.poll();
        return sum/size;
    }
}
```


```
Missing Ranges

Given a sorted integer array nums, where the range of elements are in the inclusive range [lower, upper], return its missing ranges.

Example:

Input: nums = [0, 1, 3, 50, 75], lower = 0 and upper = 99,
Output: ["2", "4->49", "51->74", "76->99"]

```

```java
class Solution {
    public List<String> findMissingRanges(int[] nums, int lower, int upper) {
        List<String> result = new ArrayList<>();
        int start = lower;
        if(lower == Integer.MAX_VALUE)
            return result;
        
        for(int i = 0 ; i < nums.length; i++){
            if(i < nums.length-1 && nums[i] == nums[i+1])
                continue;
            if(nums[i] == start){
                start++;
            }else{
                result.add(getRange(start, nums[i]-1));
                if(nums[i] == Integer.MAX_VALUE){
                    return result;
                }
                start = nums[i] + 1;
            }
        }
        if(start <= upper)
            result.add(getRange(start, upper));
        return result;
    }
    
    private String getRange(int n1, int n2){
        return n1 == n2 ?
                String.valueOf(n1) :
                String.format("%d->%d", n1, n2);
    }
}
```


```
Add Bold Tag in String
Given a string s and a list of strings dict, you need to add a closed pair of bold tag <b> and </b> to wrap the substrings in s that exist in dict. If two such substrings overlap, you need to wrap them together by only one pair of closed bold tag. Also, if two substrings wrapped by bold tags are consecutive, you need to combine them.

Example 1:

Input: 
s = "abcxyz123"
dict = ["abc","123"]
Output:
"<b>abc</b>xyz<b>123</b>"

Example 2:

Input: 
s = "aaabbcc"
dict = ["aaa","aab","bc"]
Output:
"<b>aaabbc</b>c"

Note:

    The given dict won't contain duplicates, and its length won't exceed 100.
    All the strings in input have length in range [1, 1000].

```

```java
class Solution {
    public String addBoldTag(String s, String[] dict) {
        if (dict == null || dict.length == 0) {
            return s;
        }
 
        // step 1: find start and end pos of the substring
        //
        List<Interval> intervals = new ArrayList<>();
        for (String t : dict) {
            strStr(s, t, intervals);
        }
         
        if (intervals.isEmpty()) {
            return s;
        }
 
        // step 2: sort the intervals based on the start index
        //
        Collections.sort(intervals, new IntervalComparator());
 
        // step 3: merge intervals
        //
        List<Interval> mergedIntervals = mergeIntervals(intervals);
 
        // step 4: compose the result based on the merged intervals
        //
        StringBuilder sb = new StringBuilder();
        int prev = 0;
         
        for (int i = 0; i < mergedIntervals.size(); i++) {
            Interval curr = mergedIntervals.get(i);
            // prev seg
            //
            sb.append(s.substring(prev, curr.start));
            sb.append("<b>");
             
            // curr seg
            //
            sb.append(s.substring(curr.start, curr.end + 1));
            sb.append("</b>");
             
            prev = curr.end + 1;
        }
         
        // trailing substring
        //
        if (prev < s.length()) {
            sb.append(s.substring(prev));
        }
 
        return sb.toString();
    }
    
    private void strStr(String s, String t, List<Interval> list){
        for(int i = 0; i < s.length() - t.length() + 1; i++){
            int j = 0;
            while(j < t.length()){
                if(s.charAt(i+j) == t.charAt(j)){
                    j++;
                }else{
                    break;
                }
            }
            if(j == t.length()){
                Interval interval = new Interval(i, i+j-1);
                list.add(interval);
            }
        }
    }
    
    private List<Interval> mergeIntervals(List<Interval> intervals){
        List<Interval> ans = new ArrayList<>();
        if(intervals == null || intervals.isEmpty())
            return ans;
        
        Interval prev = intervals.get(0);
        for(int i = 1; i < intervals.size() ; i++){
            Interval curr = intervals.get(i);
            if(prev.end >= curr.start || prev.end + 1 == curr.start){
                prev.end = Math.max(prev.end, curr.end);
            }else{
                ans.add(new Interval(prev.start, prev.end));
                prev = curr;
            }
        }
        ans.add(prev);
        return ans;
    }
    
    class Interval {
        int start;
        int end;
 
        public Interval(int s, int e) {
            start = s;
            end = e;
        }
    }
 
    class IntervalComparator implements Comparator<Interval> {
        public int compare(Interval a, Interval b) {
            return a.start - b.start;
        }
    }
}
```
