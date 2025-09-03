import java.io.*;
import java.util.*;

class Solution
{
    public int solution(String s)
    {
        String[] ss = s.split("");
        
        ArrayDeque<String> stack = new ArrayDeque<>();
        
        for(String s3 : ss){
            
            if(stack.size() < 1){
              
                stack.push(s3);
                continue;
            } 
            
            if(stack.peek().equals(s3)){
                
                stack.pop();
                
            }else{
                stack.push(s3);
            }
        }
        
        if(stack.isEmpty()){
            return 1;
        }
        return 0;
    }
}