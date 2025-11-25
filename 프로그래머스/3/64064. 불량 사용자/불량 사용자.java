import java.io.*;
import java.util.*;

class Solution {
    public Set<Set<String>> answer = new HashSet<>();
    public int solution(String[] user_id, String[] banned_id) {
        // 조합
        
        dfs(new HashSet<>(),banned_id, user_id, 0);

        return answer.size();
    }
    
    private void dfs(Set<String> set, String[] bannedIds, String[] userIds, int depth){
        if(depth == bannedIds.length){
            // System.out.println("체크 : " + set);
            answer.add(set);
            return;
        }
        
        for(String userId : userIds){
            if(set.contains(userId)){
                continue;
            }
            
            if(isBannedId(bannedIds[depth], userId)){
                set.add(userId);
                
                dfs(new HashSet<>(set), bannedIds, userIds, depth+1);
                
                set.remove(userId);
            }
        }
    }
    
    private boolean isBannedId(String bannedId, String userId){
        if(userId.length() != bannedId.length()) return false;
            
        String[] splittedBannedId = bannedId.split("");
        String[] splittedUserId = userId.split("");
        
        for(int i=0; i<splittedBannedId.length; i++){
            if(splittedBannedId[i].equals("*")){
                continue;    
            }
            
            if(splittedBannedId[i].equals(splittedUserId[i])){
                continue;
            }else{
                return false;
            }
        }
        
        return true;
    }
}