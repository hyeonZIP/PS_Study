import java.io.*;
import java.util.*;

class Solution {
    
    private int y,x;
    private int[][] prefixSum;
    
    public int solution(int[][] board, int[][] skill) {
        
        init(board);
        
        getPrefixSum(skill);
        
        int answer = countUndamagedBuilding(board);

        return answer;
    }
    
    private int countUndamagedBuilding(int[][] board){
        
        int count = 0;
        
        for(int i=0; i<y; i++){
            
            for(int j=0; j<x; j++){
                
                if(board[i][j] + prefixSum[i][j] > 0) count++;
            }
        }
        
        return count;
    }
    
    private void getPrefixSum(int[][] skill){
        
        inputBoundary(skill);
        
        calcPrefixSum();
    }
    
    private void test(){
        
        System.out.println("----------------");
        
        for(int i=0; i<=y; i++){
            
            for(int j=0; j<=x; j++){
                
                System.out.print(prefixSum[i][j] + " ");
            }
            System.out.println();
        }
    }
    
    private void calcPrefixSum(){
        
        for(int i=0; i<=y; i++){
            
            int prefix = 0;
            
            for(int j=0; j<=x; j++){
                
                prefixSum[i][j] += prefix;
                
                prefix = prefixSum[i][j];
            }
        }
        
        for(int i=0; i<=x; i++){
            
            int prefix = 0;
            
            for(int j=0; j<=y; j++){
                
                prefixSum[j][i] += prefix;
                
                prefix = prefixSum[j][i];
            }
        }
    }
    
    private void inputBoundary(int[][] skill){
        
        for(int[] s : skill){
            
            int type = s[0];
            int y1 = s[1];
            int x1 = s[2];
            int y2 = s[3];
            int x2 = s[4];
            int degree = s[5];
            
            int op = type == 1 ? -1 : 1;
            
            prefixSum[y1][x1] += degree * op;
            prefixSum[y1][x2+1] += degree * -op;
            
            prefixSum[y2+1][x1] += degree * -op;
            prefixSum[y2+1][x2+1] += degree * op;
        }
    }
    
    private void init(int[][] board){
        
        y = board.length;
        x = board[0].length;
        
        prefixSum = new int[y+1][x+1];
    }
}