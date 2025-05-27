import java.io.*;
import java.util.*;

class Solution {
    
    public int answer = Integer.MAX_VALUE;
    
    public int solution(String s) {
        
        char[] arr = s.toCharArray();
        
        for(int i=1; i<=s.length()/2; i++){
            
            compress(i, arr);
            
        }
        
        if(s.length() < 2) answer = 1;
        
        return answer;
    }
    
    public void compress(int compressionWeight, char[] arr){
        
        Map<String, Integer> map = new HashMap<>();
        
        String previous = "";
        int sum = 0;
        boolean flag = true;
        int compressionCount = 1;
        
        for(int i=0; i<arr.length; i = i + compressionWeight){
            
            StringBuilder sb = new StringBuilder();
                    
            int index = i;
            
            for(int ii=0; ii<compressionWeight; ii++){
                
                sb.append(arr[index]);
                index++;
                if(index >= arr.length){
                    break;
                }
            }
            
            
            if(previous.isEmpty()){
                
                previous = sb.toString();
                sum += previous.length();
                // System.out.println("초기값 지정 sum = " + sum);
                continue;
            }
            
            if(!previous.isEmpty() && previous.equals(sb.toString())){
                compressionCount++;
                // System.out.println("compressionCount : " + compressionCount);
                continue;
            }
            
        if(compressionCount != 1){
                
                if(compressionCount >= 1000){
                    sum += 4;
                }else if(compressionCount >= 100){
                    sum += 3;
                }else if(compressionCount >= 10){
                    sum += 2;
                }else{
                    sum += 1;
                }
                compressionCount = 1;
        }
            
            previous = sb.toString();
            sum += previous.length();
        }
        if(compressionCount != 1){
                
                if(compressionCount >= 1000){
                    sum += 4;
                }else if(compressionCount >= 100){
                    sum += 3;
                }else if(compressionCount >= 10){
                    sum += 2;
                }else{
                    sum += 1;
                }
                compressionCount = 1;
        }
        answer = Math.min(sum, answer);

    }
}