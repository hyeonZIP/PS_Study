import java.io.*;
import java.util.*;

class Solution {
    public int solution(String[] strs, String t) {
        int[] dp = new int[t.length()+1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        
        Set<String> set = new HashSet<>();
        
        for(String s : strs){
            set.add(s);
        }
        
        dp[0] = 0;
        
        for(int i=1; i <= t.length(); i++){
            for(int j=1; j<=5; j++){
                if(i-j < 0) continue;
                if(dp[i - j] == Integer.MAX_VALUE) continue;
                
                String sub = t.substring(i-j,i);
                                
                if(set.contains(sub)){
                    dp[i] = Math.min(dp[i], dp[i-j]+1);
                }
            }
        }
        
        return dp[t.length()] == Integer.MAX_VALUE ? -1 : dp[t.length()];
    }
}