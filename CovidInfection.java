import java.util.LinkedList;
import java.util.Queue;

class Solution {
    public static void main(String [] args){
        Solution s  = new Solution();
        System.out.print(s.infectedHumans(null));
    }
    /****
     * Assuming the humans can stand in a grid of some mXn
     * @param humans
     * @return
     */
    public int infectedHumans(int[][] humans) {
        if(null == humans || humans.length == 0){
            return 0;
        }

        int rows = humans.length;
        int cols = humans[0].length;

        /***
         * We will use infectedHumans queue to do the BFS
         */
        Queue<int[]> infectedHumans = new LinkedList<>();
        int nonInfectedHumans = 0;
        // put the current state
        for(int i = 0 ; i < rows; i++){
            for(int j = 0 ; j < cols ; j++){
                if(humans[i][j] == -1){
                    infectedHumans.offer(new int[] {i, j});
                }else if (humans[i][j] == 1){
                    nonInfectedHumans ++;
                }
            }
        }

        int days = 0;

        /***
         * If every one is infected, then we can return 0 the current moment
         */
        if(nonInfectedHumans == 0) return days;

        /**
         * Assuming virus can be passed in 4 directions in the cells up/down/lef/right
         */
        int[][] directions = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
        /**
         * Let's traverse in the four directions
         */
        while(!infectedHumans.isEmpty()){
            ++days;
            int size = infectedHumans.size();
            for(int i = 0 ; i < size ; i ++){
                int [] currentPos = infectedHumans.poll();
                for (int [] move : directions){
                    int nextMoveX = currentPos[0] + move[0];
                    int nextMoveY = currentPos[1] + move[1];

                    // if it's a valid move, not a infected human move || not an empty move
                    if(nextMoveX < 0 || nextMoveY < 0 || nextMoveX >= rows || nextMoveY >= cols || humans[nextMoveX][nextMoveY] == 0 || humans[nextMoveX][nextMoveY] == -1) continue;
                    humans[nextMoveX][nextMoveY] = -1;
                    infectedHumans.offer(new int[] {nextMoveX, nextMoveY});
                    nonInfectedHumans--;
                }
            }
        }
        return nonInfectedHumans == 0 ? (days - 1) : -1;
    }
}
