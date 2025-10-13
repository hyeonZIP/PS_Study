import java.io.*;
import java.util.*;

class Solution {
    
    public class Q{
        
        int peek;
        long initTotal;
        long total;
        ArrayList<Integer> arr = new ArrayList<>();
        
        public Q(int[] arr){
            
            this.peek = 0;
            
            for(int i=0; i<arr.length; i++){
                
                this.total += arr[i];
                
                this.arr.add(arr[i]);
            }
            
            this.initTotal = this.total;
        }
    }
    
    public int solution(int[] queue1, int[] queue2) {
        
        Q q1 = new Q(queue1);
        Q q2 = new Q(queue2);
        int maximum = queue1.length * 3;
        int answer = -1;
        int count = 0;
        while(count <= maximum){
            
            if(q1.total == q2.total){
                
                answer = count;
                
                break;
            }
            if(q1.total > q2.total){
                
                int peekValue = q1.arr.get(q1.peek);
                q1.peek++;
                
                q1.total -= peekValue;
                
                q2.arr.add(peekValue);
                q2.total += peekValue;
            }else{
                
                int peekValue = q2.arr.get(q2.peek);
                q2.peek++;
                
                q2.total -= peekValue;
                
                q1.arr.add(peekValue);
                q1.total += peekValue;
            }
            
            count++;
        }

        return answer;
    }
}