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
