import java.io.*;
import java.util.*;

/*
이진수 저장 문자열
루트 노드 유지, 포화 이진트리
좌측 노드부터 우측 노드 순으로 탐색
더미노드 ? 0추가 : 1추가
2진수 > 10진수 변환

포화 이진트리로 만들고
가능한지 탐색
숫자를 이진수로 바꾸고 해당 이진수를 이진트리로 변경 가능한지 탐색

101 > 불가능
110011 > 가능?
                    1
                /       \
            /               \
        0                       0
    /       \               /       \
1               0       1               1

이렇게 연결 되어 있다면 
0001011

루트 노드 기준으로 가능한 규칙이 바뀔까?
루트 기준 좌측 서브 트리
가장 좌측 2진수 부터 3개씩 끊어서 보기

부모 1 자식 1 or 0
부모 0 자식 0

depth가 2 일 때 노드 수 7개
좌자-부모1-우자-루트-좌자-부모1-우자

depth가 3일 때 노드 수 15개
좌자-부모2-우자-부모1-좌자-부모2-우자-
루트
-좌자-부모2-우자-루트-좌자-부모2-우자

1           o 1
0 1 0         o 2
0 1 1         o 3

1 0 0         x 4
1 0 1         x 5

1 1 0         o 6
1 1 1         o 7

0?0 1 000     o 8
000 1 001     x 9
000 1 010     o 10
000 1 011     o 11

dp로 해결할 수도?

01100       o 12
01101       o 13
01110       o 14
01111       o 15
10000       o 16
01101       o 13
        

*/

class Solution {
    public int[] solution(long[] numbers) {
        int[] answer = new int[numbers.length];
        
        for(int i=0; i<numbers.length; i++){
            String bitString = Long.toBinaryString(numbers[i]);
            
            int len = bitString.length();
            int nodeCount = 1;
            int depth = 2;
            while(len > nodeCount){
                nodeCount += depth;
                depth = depth*2;
            }
            
            String prefix = "";
            
            for(int ii=0; ii<nodeCount-len; ii++){
                bitString = "0" + bitString;
            }
            
            if(bitString.length() == 1){
                answer[i] = 1;
                continue;
            }

            // System.out.println("init : " + bitString);
            
            boolean flag = true;
            while(flag){
                
                String newBitString = "";
            
                for(int ii=0; ii<bitString.length(); ii = ii+4){
                     
                    String bit = isPossibleBinaryTree(bitString.substring(ii,ii+3));
                    if(bit.equals("-1")){
                        flag = false;
                        break;
                    }
                    if(ii+4 >= bitString.length()){
                        newBitString += bit;
                    }
                    else{
                        newBitString += bit+bitString.charAt(ii+3);
                    }
                }
                
                if(newBitString.length() == 1) {
                    answer[i] = 1;
                    break;
                }
                bitString = newBitString;
            }
            
        }
        
        
        return answer;
    }
    
    public String isPossibleBinaryTree(String bitString){
        // System.out.println(bitString);
        char[] bitArray = bitString.toCharArray();
        if(bitArray[1] == '1') return "1";
        else if(bitArray[0] == '1' || bitArray[2] == '1') return "-1";
        
        return "0";
    }
}