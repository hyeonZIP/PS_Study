import java.util.*;
class Solution {
    public int[] solution(int[] numbers) {
        
        int numbersLength = numbers.length;
        Set<Integer> answer = new HashSet<>();
        
        for(int i=0; i<numbersLength; i++){
            
            for(int j=i+1; j<numbersLength; j++){
                answer.add(numbers[i] + numbers[j]);
            }
        }
        
        return answer.stream().sorted().mapToInt(Integer::new).toArray();
    }
}