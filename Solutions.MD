```
N-Queens
Hard

The n-queens puzzle is the problem of placing n queens on an n×n chessboard such that no two queens attack each other.

Given an integer n, return all distinct solutions to the n-queens puzzle.

Each solution contains a distinct board configuration of the n-queens' placement, where 'Q' and '.' both indicate a queen and an empty space respectively.

Example:

Input: 4
Output: [
 [".Q..",  // Solution 1
  "...Q",
  "Q...",
  "..Q."],

 ["..Q.",  // Solution 2
  "Q...",
  "...Q",
  ".Q.."]
]
Explanation: There exist two distinct solutions to the 4-queens puzzle as shown above.

```

```
class Solution {
    List<List<String>> result = new ArrayList<>();
    public List<List<String>> solveNQueens(int n) {
        
        char[][] mat = new char[n][n];

		// initialize mat[][] by '-'
		for (int i = 0; i < n; i++) {
			Arrays.fill(mat[i], '.');
		}
        nQueen(mat, 0, n);
        return result;
    }
    public void nQueen(char [][] mat, int r, int n){
        if(r == n){
            List<String> ans = new ArrayList<>();
            for(int i = 0; i < n; i ++){
                StringBuilder sb = new StringBuilder();
                for(int j = 0; j < n ; j ++){
                    sb.append(mat[i][j]);
                }
                ans.add(sb.toString());
            }
            result.add(ans);
        }
        
        for(int i = 0; i < n ; i ++){
            if(isSafe(mat, r, i, n)){
                mat[r][i] = 'Q';
                
                // recurr for next
                nQueen(mat, r+1, n);
                
                mat[r][i] = '.';
            }
        }
    }
    
    public boolean isSafe(char [][] mat, int r, int c, int n){
        // if the two queues are in the same column
        for(int i = 0 ; i < r; i ++){
            if(mat[i][c] == 'Q')
                return false;
        }
        
        // if the two queens share the same diagonal
        for(int i = r, j = c; i>=0 && j>=0 ; i --, j--){
            if(mat[i][j] == 'Q')
                return false;
        }
        
        // if in the same / diagonal
        for (int i = r, j = c ; i >= 0 && j < n; i--, j++){
            if(mat[i][j] == 'Q')
                return false;
        }
        return true;
        
    }
}
```
