/**
 * N*N 크기 각 셀에 높이 값
 * 높이가 동일할 경우 건설 가능
 * 높이가 다를 경우 경사로 설치 필요
 * 경사로 높이 1, 길이 X
 *
 */

import java.util.*;
import java.io.*;
public class Solution {

    static int[][] map;
    static int N,X;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        int testCase = Integer.parseInt(br.readLine());

        for (int t=0; t<testCase; t++){
            st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());
            X = Integer.parseInt(st.nextToken());

            map = new int[N][N];

            for (int y=0; y<N; y++){
                st = new StringTokenizer(br.readLine());
                for (int x=0; x<N; x++){
                    map[y][x] = Integer.parseInt(st.nextToken());
                }
            }
            //map[0][x] 에 있는 숫자들을 기준으로 오른쪽으로 탐색했을 때 기준 숫자와 다른(+-1차이) 숫자가 X만큼 반복돼야 경사로 설치 가능
            //map[y][0] "

            bw.write("#"+(t+1)+ " "+findRunway()+"\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }

    private static int findRunway(){
        int answer = 0;
        for (int x = 0; x<N; x++){
            answer += dfs(0,x,map[0][x], new int[]{1,0});
        }
//        System.out.println("###################### : " + answer);

        for (int y = 0; y<N; y++){
            answer += dfs(y,0,map[y][0],new int[]{0,1});
        }
        return answer;
    }

    private static int dfs(int y, int x, int initNum, int[] direction){
        ArrayDeque<int[]> stack = new ArrayDeque<>();
        int addY = direction[0];
        int addX = direction[1];
        stack.push(new int[]{y,x,initNum,0,1});
        while(!stack.isEmpty()){
            int[] arr = stack.pop();

            int py = arr[0]+addY;
            int px = arr[1]+addX;
            int preNum = arr[2];
            int curX = arr[3];
            int curHorizontal = arr[4];

            if (isOutOfRange(py,px)){
                if (curX != 0){
//                    System.out.println("내리막 중인데 범위 밖임");
                    return 0;
                }
//                System.out.println("범위 밖");
                continue;
            }
            if (curX != 0){//경사로 설치중
                if (map[py][px] == preNum){
//                    System.out.println("내리막 설치 중");
                    stack.push(new int[]{py,px,preNum,curX-1,0});//경사로 설치중+평야임 > 경사로 카운트-1, 평야 카운트+1
                }else{
//                    System.out.println("내리막 설치 불가");
                    return 0;
                }
            }else{
                //경사로 설치 중이 아님
                //만약 높아지는 곳이면 경사로를 지나온 위치에 깔아야 한다.
                if (map[py][px] == preNum){
//                    System.out.println("평야 지속");
                    stack.push(new int[]{py,px,preNum,0,curHorizontal+1});//다음 블럭은 평야 > 평야 카운트+1
                } else if (map[py][px]-preNum == 1) {
                    //높아지는 경사로를 깔아야 한다.
                    //지나온 평야 개수가 X보다 크면 가능
//                    System.out.println("오르막 설치 가능 판별");
                    if (curHorizontal >= X) {
//                        System.out.println("오르막 설치 [가능]");
                        stack.push(new int[]{py,px,map[py][px],0,1});//평야 개수 초기화,오르막 설치 카운트
                    }
                    else {
//                        System.out.println("오르막 설치 [불가]");
                        return 0;
                    }
                }else if (map[py][px]-preNum == -1){
                    //내려가는 경사로
                    //앞으로 전진하며 카운트 해야함
//                    System.out.println("내리막 설치 flag on");
                    stack.push(new int[]{py,px,map[py][px],X-1,0});//내려가는 오르막 설치 > 오르막 설치 시작 + 평야 카운트 초기화
                }else if (Math.abs(map[py][px]-preNum) >= 2){
//                    System.out.println("너무 높음");
                    return 0;
                }
            }
        }//while
//        System.out.println("y = " + y);
//        System.out.println("x = " + x);
//        System.out.println("설치 가능");
        return 1;
    }

    private static boolean isOutOfRange(int x,int y){
        return x<0 || y<0 || x>=N || y>=N;
    }
}
