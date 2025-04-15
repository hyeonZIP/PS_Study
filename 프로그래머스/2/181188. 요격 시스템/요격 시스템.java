import java.io.*;
import java.util.*;

class Solution {
    public int solution(int[][] targets) {
        int answer = 0;
        //시작점이 작은 것부터
        //시작점이 동일하면 길이가 짧은 것부터
        Arrays.sort(targets, (o1,o2)->{
            if(o1[0] == o2[0]){
                return (o1[1]-o1[0]) - (o2[1]-o2[0]);
            }
            return o1[0]-o2[0];
        });
        
        int len = targets.length;
        
        int previousStart = targets[0][0];
        int previousEnd = targets[0][1];
        boolean remain = true;
        
        
        //요격 가능 flag
        boolean intercept = false;
        for(int i=1; i<len; i++){
            
            int currentStart = targets[i][0];
            int currentEnd = targets[i][1];
            
            if(intercept){
                //요격 가능한 상태일 때
                //이전 좌표값을 업데이트하지 않음
                if(currentStart < previousEnd && currentStart >= previousStart){
                    previousStart = currentStart;
                    previousEnd = previousEnd > currentEnd ? currentEnd : previousEnd;
                    remain =false;
                    continue;  
                }else{
                    previousStart = currentStart;
                    previousEnd = currentEnd;
                    intercept = false;
                    remain = true;
                    continue;
                }
            }
            
            
            //동시 요격 가능한 미사일이 왔을 때
            if(currentStart < previousEnd){
                answer++;
                intercept = true;
                remain = false;
                previousStart = currentStart;
                previousEnd = previousEnd > currentEnd ? currentEnd : previousEnd;
                continue;
            }
            //동시 요격 불가능한 미사일이 왔을 때
            if(currentStart >= previousEnd){
                answer++;
                previousStart = currentStart;
                previousEnd = currentEnd;
                remain = true;
                continue;
            }            
        }
        
        if(remain){
            answer++;
        }
        
        if(len == 1){
            answer = 1;
        }
        
        return answer;
    }
}

//[[0, 4], [1, 2], [1, 3], [3, 4]] => 2
// [[0, 4], [0, 1], [2, 3]] => 2