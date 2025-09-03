import java.util.*;
class Solution {
    public List<Integer> solution(int[] prices) {
        int i = 0;
        int j = 1;

        List<Integer> answer = new ArrayList<>();
        int count = 0;
        while(i != prices.length-1){
            if (prices[i] <= prices[j]){
                count++;
                j++;
                if (j == prices.length){
                    answer.add(count);
                    count=0;
                    i++;
                    j=i+1;
                }
            }
            else if(prices[i] > prices[j]){
                count++;
                answer.add(count);
                count = 0;
                i++;
                j=i+1;
            }
        }
        answer.add(0);

        return answer;
    }
}