import java.io.*;
import java.util.*;

class Solution {
    
    public final int MOD = 1_000_000_007;
    
    public int solution(int n, int[] money) {
        
        return dp(n,money);
    }
    
    private int dp(int n, int[] money){
        
        int[] dp = new int[n+1];
        
        dp[0] = 1;
        
        for(int i=0; i<money.length; i++){
            
            int coin = money[i];
            
            for(int j=1; j<=n; j++){
                
                if(coin <= j){
                    
                    dp[j] = (dp[j] + dp[j-coin])%MOD;
                }
            }
        }
                
        return dp[n];
    }
}