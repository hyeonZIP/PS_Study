import java.io.*;
import java.util.*;

/*
    초침-시침
    초침-분침
    겹친다는 의미 = 각도가 동일
*/

class Solution {
    
    public int solution(int h1, int m1, int s1, int h2, int m2, int s2) {
        
        int start = h1*3600 + m1*60 + s1;
        int end = h2*3600 + m2*60 + s2;
        
        int answer = 0;
        
        if(start%360 == 0 || start%360 == 12) answer++;
        
        while(start < end){
            
            //초당 각도
            double curHourAnglePerSec = (start/(double)120)%360;
            double curMinAnglePerSec = (start/(double)10)%360;
            double curSecAnglePerSec = (start*6)%360;
            
            double nextHourAnglePerSec = ((start+1)/(double)120)%360;
            double nextMinAnglePerSec = ((start+1)/(double)10)%360;
            double nextSecAnglePerSec = ((start+1)*6)%360;
            
            if(nextHourAnglePerSec == 0) nextHourAnglePerSec = 360;
            if(nextMinAnglePerSec == 0) nextMinAnglePerSec = 360;
            if(nextSecAnglePerSec == 0) nextSecAnglePerSec = 360;
            
            if(curSecAnglePerSec < curHourAnglePerSec &&
              nextSecAnglePerSec >= nextHourAnglePerSec) answer++;
            
            if(curSecAnglePerSec < curMinAnglePerSec &&
              nextSecAnglePerSec >= nextMinAnglePerSec) answer++;
            
            //중복 제거
            if(nextMinAnglePerSec == nextHourAnglePerSec) answer--;
            
            start++;
        }
        
        return answer;
    }
}