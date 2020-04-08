/**
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
*/
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


