/*
문제 풀이: 2회차
풀이 방법: dfs
풀이 날짜: 25-01-23
*/
import java.util.*;

class Solution {
    
    public static int solution(int[] numbers, int target) {
        
        return dfs(numbers, target, 0,0);
    }
    
    public static int dfs(int[] numbers, int target, int index, int sum){
        
        if(index == numbers.length){
            if(sum == target){
                return 1;
            }
            return 0;
        }
        int count = 0;
        count += dfs(numbers, target, index+1, sum+numbers[index]);
        count += dfs(numbers, target, index+1, sum-numbers[index]);
        
        return count;
    }
}