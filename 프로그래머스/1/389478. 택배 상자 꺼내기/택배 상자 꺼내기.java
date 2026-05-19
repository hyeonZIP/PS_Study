import java.io.*;
import java.util.*;

class Solution {
    public int solution(int n, int w, int num) {
        int answer = 0;
        int y = n/w+1;
        int x = w;
        
        int[][] map = new int[y][x];
        
        int startY = y-1;
        int startX = -1;
        
        int boxNumber = 1;
        boolean xFlag = true;
        while(n>0){
            if(xFlag){
                // 오른쪽으로 배치
                if(startX + 1 < w){
                    startX++;
                }else{
                    xFlag=false;
                    startY--;
                }
            }else{
                // 왼쪽으로 배치
                if(startX - 1 > -1){
                    startX--;
                }else{
                    xFlag = true;
                    startY--;
                }
            }
            
            map[startY][startX] = boxNumber;
            boxNumber++;
            n--;
        }
        
        for(int i=0; i<y; i++){
            for(int j=0; j<x; j++){
                if(map[i][j] == num){
                    for(int k=i; k>=0; k--){
                        if(map[k][j] != 0){
                            answer++;
                        }
                    }
                }
            }
        }
        
        
        
        return answer;
    }
    
    public void test(int[][] map){
        for(int[] a : map){
            System.out.println(Arrays.toString(a));
        }
        System.out.println();
    }
}