import java.util.HashMap;

class Solution {
    public int solution(int[] nums) {
        HashMap<Integer,Integer> map = new HashMap<>();

        for(int i=0; i<nums.length; i++){
            map.put(nums[i],map.getOrDefault(nums[i],0)+1);
        }

        int ponketmonType = map.keySet().size();
        int takableNumber = nums.length/2;

        return Math.min(ponketmonType,takableNumber);
    }
}