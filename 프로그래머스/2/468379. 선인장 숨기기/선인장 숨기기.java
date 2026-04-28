import java.io.*;
import java.util.*;

class Solution {
    public int[] solution(int m, int n, int h, int w, int[][] drops) {
        int[][] time = new int[m][n];
        
        for (int[] row : time) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }
        
        for (int i = 0; i < drops.length; i++) {
            time[drops[i][0]][drops[i][1]] = i + 1;
        }

        int[][] rowMins = new int[m][n - w + 1];
        for (int y = 0; y < m; y++) {
            Deque<int[]> dq = new ArrayDeque<>();
            for (int x = 0; x < n; x++) {
                while (!dq.isEmpty() && dq.peekLast()[0] >= time[y][x]){
                    dq.pollLast();
                }
                
                dq.addLast(new int[]{time[y][x], x});
                
                while (dq.peekFirst()[1] <= x - w){
                    dq.pollFirst();
                }
                
                if (x >= w - 1){
                    rowMins[y][x - w + 1] = dq.peekFirst()[0];
                }
            }
        }

        int cols = n - w + 1;
        int rows = m - h + 1;
        int[][] result = new int[rows][cols];

        for (int x = 0; x < cols; x++) {
            Deque<int[]> dq = new ArrayDeque<>();
            for (int y = 0; y < m; y++) {
                while (!dq.isEmpty() && dq.peekLast()[0] >= rowMins[y][x]){
                    dq.pollLast();
                }
                
                dq.addLast(new int[]{rowMins[y][x], y});
                
                while (dq.peekFirst()[1] <= y - h){
                    dq.pollFirst();
                }
                
                if (y >= h - 1){
                    result[y - h + 1][x] = dq.peekFirst()[0];
                }
            }
        }

        int max = Integer.MIN_VALUE, answerY = 0, answerX = 0;
        
        for (int y = 0; y < rows; y++) {
            for (int x = 0; x < cols; x++) {
                if (result[y][x] > max) {
                    max = result[y][x];
                    answerY = y;
                    answerX = x;
                }
            }
        }

        return new int[]{answerY, answerX};
    }
}