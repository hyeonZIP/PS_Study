import java.io.*;
import java.util.*;

class Solution {
    public int solution(int storey) {
        int answer = 0;
        int num = 10;
        
        while(storey > 0){
            int digit = storey % 10;
            storey /= 10;
            
            
            
            if(digit > 5){
                answer += 10-digit;
                storey += 1;
            }else if(digit < 5){
                answer += digit;
            }else{
                answer += 5;
                
                if(storey % 10 >= 5) storey += 1;
            }
        }
        
        return answer;
    }
}