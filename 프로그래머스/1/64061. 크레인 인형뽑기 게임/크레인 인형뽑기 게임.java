import java.io.*;
import java.util.*;

class Solution {
    public int solution(int[][] board, int[] moves) {
        
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        
        int maxYIndex = board.length-1;
        
        int answer = 0;
        
        for(int move : moves){
            
            int yIndex = 0;
            int xIndex = move-1;
            
            int grepItem = -1;
            
            while(yIndex <= maxYIndex){
                
                if(board[yIndex][xIndex] == 0){
                    
                    yIndex++;
                    continue;
                }     
                
                grepItem = board[yIndex][xIndex];
                board[yIndex][xIndex] = 0;
                break;
            }
            
            if(grepItem == -1 || grepItem == 0) continue;
            
            if(stack.size() == 0){
                
                stack.push(grepItem);
                continue;
            }
            
            if(stack.peek().equals(grepItem)){
                
                stack.pop();
                answer += 2;
            }else{
                
                stack.push(grepItem);
            }
            
        }
        
        return answer;
    }
}