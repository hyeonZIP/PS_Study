import java.io.*;
import java.util.*;

/*
이분 탐색으로 lower-bound 또는 upper-bound 찾기
*/

class Solution {
    int answer;
    public int solution(int[] diffs, int[] times, long limit) {
        
        int[] temp = diffs.clone();
        Arrays.sort(temp);
        int start = temp[0];
        int end = temp[diffs.length-1];
        int mid = 0;
        boolean flag = true;
        while(start<end){
            mid = (start+end)/2;
            if(binarySearch(mid, diffs, times) <= limit){
                end = mid;
                flag = true;
            }else{
                start = mid+1;
                flag = false;
            }
        }
        
        if(flag){
            return mid;
        }
        return mid+1;
    }
    
    public long binarySearch(int param,int[] diffs, int[] times){
        
        long sum = 0L;
        
        for(int i=0; i<diffs.length; i++){
            if(diffs[i]<=param){
                sum += times[i];
                continue;
            }
            
            sum += (diffs[i]-param)*(times[i]+times[i-1]) + times[i];
        }
        
        return sum;
    }
}