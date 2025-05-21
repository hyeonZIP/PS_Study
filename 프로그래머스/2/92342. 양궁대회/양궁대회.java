import java.io.*;
import java.util.*;

class Solution {
    
    public int[] answer;
    public int answerPoint = 0;
    public int[] record = new int[11];
    public int[] pointValue = {10,9,8,7,6,5,4,3,2,1,0};
    
    public int[] solution(int n, int[] info) {
        
        dfs(n, 0, info);
        
        if(answer == null) answer = new int[]{-1};
    
        return answer;
    }
    
    private int getRestPitchPoint(int[] info, int index){
        
        int pitchPoint = 0;
        
        for(int i=index; i<11; i++){
            if(info[i] != 0 && info[i] >= record[i]){
                pitchPoint += pointValue[i];
            }
        }
        
        return pitchPoint;
    }
    
    private void dfs(int remainShot, int index, int[] info){
        
        // System.out.println(" remainShot= " +remainShot );
        // System.out.println("index = "+index );
        // System.out.println("lionPoint = "+ lionPoint);
        // System.out.println(" pitchPoint= "+pitchPoint );
        // System.out.println("answerPoint = "+answerPoint );
        
        if(remainShot == 0 || index == 10){
            
            if(remainShot > 0){
                record[10] = remainShot;
            }
            
            int lionPoint = 0;
            int peachPoint = 0;
            
            for(int i=0; i<11; i++){
                if(info[i] != 0 &&info[i] >= record[i]){
                    peachPoint += pointValue[i];
                }else if(info[i] < record[i]){
                    lionPoint += pointValue[i];
                }
            }
            // System.out.println("lionPoint = " + lionPoint);
            // System.out.println("peachPoint= " + peachPoint );
            
            // 피치보다 점수가 낮거나 같을 경우 리턴
            if(peachPoint >= lionPoint){
                record[10] = 0;
                return;
            }
            
            // 초기값 지정
            if(answer == null){
                answer = record.clone();
                answerPoint = Math.abs(peachPoint - lionPoint);
                record[10] = 0;
                return;
            }
            
            // 정답 후보보다 점수 차이가 작을 경우 리턴
            if(answerPoint > Math.abs(peachPoint - lionPoint)){
                record[10] = 0;
                return;
            }
            
            // 정답 후보와 점수 차이가 동일할 경우 판별 메서드 동작
            if(answerPoint == Math.abs(peachPoint - lionPoint)){
                findMinPointMaxShot();
                record[10] = 0;
                return;
            }
            
            if(answerPoint < Math.abs(peachPoint - lionPoint)){
                answer = record.clone();
                answerPoint = Math.abs(peachPoint - lionPoint);
                record[10] = 0;
                return;
            }
            
        }
        
        if(info[index]+1 <= remainShot){
            record[index] = info[index] + 1;
            
            dfs(remainShot-(info[index]+1), index+1,info);
            
            record[index] = 0;
        }
        
        dfs(remainShot, index+1, info);
    }
    
    private void findMinPointMaxShot(){
        
        for(int i=10; i>=0; i--){
            
            if(answer[i] == record[i]){
                continue;
            }
            else if(answer[i] < record[i]){
                answer = record.clone();
                // System.out.println("answer 업데이트, 최소 점수 많이 쏨 전형" + Arrays.toString(answer));
                return;
            }
            return;
        }
        
    }
}