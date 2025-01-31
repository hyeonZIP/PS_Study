//방법의 수 > dfs

import java.util.*;

class Solution {
    static int answer = 0;
    public int solution(int[] numbers, int target) 
    {
        int startIndex = 0;
        int sum = 0;
        
        dfs(sum,numbers,startIndex,target);
            
        return answer;
        
    }
    
    public void dfs(int sum, int[] numbers , int currentIndex, int target){
        if(currentIndex == numbers.length){
            if(sum == target){
                answer++;
            }
            return;
        }
        dfs(sum - numbers[currentIndex], numbers, currentIndex + 1 , target);
        dfs(sum + numbers[currentIndex], numbers, currentIndex + 1, target);
    }
}