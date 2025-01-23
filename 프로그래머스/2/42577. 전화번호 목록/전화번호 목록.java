import java.util.HashMap;
class Solution {
    public boolean solution(String[] phone_book) {
        HashMap<String, Integer> map = new HashMap<>();

        for (String s : phone_book) {
            map.put(s, 0);
        }

        for (String i : map.keySet()) {
            for (int j = 0; j<i.length(); j++){
                if (map.containsKey(i.substring(0,j))) {
                    return false;
                }
            }
        }

        return true;
    }
}