import java.io.*;
import java.util.*;

/*
    사전적으로 작은 경로를 출력
    dddddduuu
    dddlllrrr
    
    u가 제일 큰 경로라도 앞이 d가 올 수 있다면 상관없다
    S가 E보다 아래에 있을 경우 최단 경로로 이동하면 시작부터 u가 나오기 때문에 큰 숫자가 나옴
    따라서 최단 경로를 기반으로 계산 후 d로 이동할 수 있다면 d부터 이동
    
*/

class Solution {
    
    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        StringBuilder sb = new StringBuilder();
        int[] dy = {1,0,0,-1};
        int[] dx = {0,-1,1,0};
        String[] dstr = {"d","l","r","u"};
        int remainK = k;
        int curY = y;
        int curX = x;
        
        boolean flag = true;
        
        while(remainK > 0 && flag){
            flag = false;
            for(int i=0; i<4; i++){
                String cmd = dstr[i];
                int nextY = dx[i] + curY;
                int nextX = dy[i] + curX;

                
                if(nextY < 1 || nextX < 1 || nextY > n || nextX > n){
                    continue;
                } 
                
                int dist = Math.abs(nextX - r) + Math.abs(nextY -c);
                
                if(remainK > 1){
                    dist += 1;
                }
                
                if(remainK >= dist){
                    remainK -= 1;
                    curX = nextX;
                    curY = nextY;
                    sb.append(cmd);
                    flag = true;
                    break;
                }
            }  
        }
        if(curX == r && curY == c){
            return sb.toString();
        }else{
            return "impossible";
        }
    }
}