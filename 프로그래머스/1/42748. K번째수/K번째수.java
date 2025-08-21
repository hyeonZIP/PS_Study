import java.io.*;
import java.util.*;

class Solution {
    public int[] solution(int[] array, int[][] commands) {
        
        //array 원본은 보장
        //commands 에 n번 반복할 i j k 변수 존재
        
        int testCount = commands.length;
        
        int[] answer = new int[testCount];
        int answerIndex = 0;
        
        for(int testCase=0; testCase< testCount; testCase++){
            
            int i = commands[testCase][0];
            int j = commands[testCase][1];
            int k = commands[testCase][2];
            
            int[] arr = Arrays.copyOfRange(array, i-1,j);
            
            Arrays.sort(arr);
            
            answer[answerIndex] = arr[k-1];
            answerIndex++;
        }
        
        return answer;
    }
}