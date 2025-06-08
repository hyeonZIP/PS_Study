import java.io.*;
import java.util.*;

class Solution {

    public Map<String, List<Integer>> map;
    
    public int[] solution(String[] info, String[] query) {
        
        int[] answer = new int[query.length];
        
        map = new HashMap<>();
        
        for(String i : info){
            String[] splited = i.split(" ");
            combine(splited, "", 0);
        }
        
        for(String key : map.keySet()){
            Collections.sort(map.get(key));
        }
        int index = 0;
        for(String q : query){
            q = q.replaceAll(" and ","");
            String[] qrr = q.split(" ");
            answer[index++] = map.containsKey(qrr[0]) ? binarySearch(qrr[0], Integer.parseInt(qrr[1])) : 0;
        }
        
        return answer;
        
    }
    
    public int binarySearch(String key, int point){
        List<Integer> l = map.get(key);
        int start = 0;
        int end = l.size() - 1;
        
        while(start <= end){
            int mid = (start + end)/2;
            if(l.get(mid) < point) start = mid +1;
            else end = mid - 1;
        }
        
        return l.size() - start;
    }
    
    public void combine(String[] info, String str, int depth){
        if(depth == 4){
            if(!map.containsKey(str)){
                List<Integer> l = new ArrayList<>();
                map.put(str, l);
            }
            map.get(str).add(Integer.parseInt(info[4]));
            return;
        }
        combine(info,str+"-",depth+1);
        combine(info,str+info[depth],depth+1);
    }
}