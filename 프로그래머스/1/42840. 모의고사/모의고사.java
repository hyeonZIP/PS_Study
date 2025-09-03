import java.io.*;
import java.util.*;

class Solution {
        
    public final int[][] pattern = {
        {1,2,3,4,5}
        ,{2,1,2,3,2,4,2,5}
        ,{3,3,1,1,2,2,4,4,5,5}
    };
    
    public int[] solution(int[] answers) {
        
        HashMap<Integer, Integer> students = new HashMap<>();
        
        for(int i=0; i<3; i++){
            
            students.put(i,0);
        }
        
        for(int i=0; i<answers.length; i++){
            
            for(int j=0; j<3; j++){
                
                if(pattern[j][i % pattern[j].length] == answers[i]){
                    
                    students.put(j,students.get(j)+1);
                }
            }
        }
        
        int maxScore = Collections.max(students.values());
        
        List<Integer> answer = new ArrayList<>();
        
        for(int i=0; i<students.size(); i++){
            
            if(students.get(i) == maxScore){
                
                answer.add(i+1);
            }
        }
        
        return answer.stream().sorted().mapToInt(i->i).toArray();
    }
}