import java.io.*;
import java.util.*;

/*
    응답의 개수만큼 정답 후보를 고르면 정답이 아닌 후보도 같이 생성
    이전에 뽑은 후보에서 응답의 개수만큼 후보를 뽑을 수 없으면 백트래킹
*/

class Solution {
    
    public ArrayList<Integer> secretCode = new ArrayList<>();
    public int answer = 0;
    public int solution(int n, int[][] q, int[] ans) {
        dfs(1,0,n,q,ans);
        return answer;
    }
    
    private void validateCode(int[][] q, int[] ans){
        
        for(int i=0; i<q.length; i++){
            
            int response = 0;
            
            for(int j=0; j<5; j++){
                
                if(secretCode.contains(q[i][j])) response++;
                
            }
            
            if(response != ans[i]) return;
        }
        
        // System.out.println("secret : " + secretCode);
        
        answer++;
        
    }
    
    private void dfs(int index, int depth, int n, int[][] q, int[] ans){
        
        if(depth == 5){
            
            validateCode(q, ans);
            
            return;
        
        }
        
        for(int i=index; i<=n; i++){
            
            secretCode.add(i);
            
            dfs(i+1, depth+1, n, q, ans);
            
            secretCode.remove(Integer.valueOf(i));
            
        }
        
    }
}