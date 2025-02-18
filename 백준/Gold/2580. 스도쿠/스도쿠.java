import java.util.*;
import java.io.*;

/**
 * 가로 체크 메서드
 * 세로 체크 메서드
 * 9칸 체크 메서드
 * 반복 도중 불가능한 영역이 생길 경우 백트래킹
 *
 * 시간 초과 해결 방안
 * 0 좌표 큐에 입력 하기
 */

public class Main {

    static int[][] map;
    static StringBuilder sb = new StringBuilder();
    static boolean one = true;
    static ArrayDeque<int[]> stack = new ArrayDeque<>();

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st;

        map = new int[9][9];

        for(int i=0; i<9; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<9; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 0){
                    stack.push(new int[]{i,j});
                }
            }
        }

        sudokuDFS();

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }


    private static void sudokuDFS(){
        /**
         * 스도쿠에 존재하는 빈칸(0)을 찾기위한 반복문
         * 해당 반복문이 종료되면 모든 빈칸을 채운것
         */
        if(stack.isEmpty()){
            for(int[] i : map){
                for(int j : i){
                    sb.append(j).append(" ");
                }
                sb.append("\n");
            }
            System.out.println(sb);
            System.exit(0);
        }

        while(!stack.isEmpty()){

            int[] xy = stack.pop();
            int px = xy[0];
            int py = xy[1];

            for(int i=1; i<=9; i++){
                if(rowCheck(px,py,i)){
                    map[px][py] = i;
                    sudokuDFS();
                }
            }
            map[px][py] = 0;
            stack.push(new int[]{px,py});
            return;
        }
    }



    /**
     * 가로 체크
     */
    private static boolean rowCheck(int x, int y, int number){
        /**
         * 가로 줄에 1과 같은 숫자가 있는지 검사한다
         * 만약 같은 숫자가 있다면 탐색할 필요가 없으므로 리턴한다
         * 전부 통과했다면 세로로 체크하러간다
         */
        for(int i=0; i<9; i++){
            if(map[x][i] == number){
                return false;
            }
        }
        return colCheck(x,y,number);
    }
    /**
     * 세로 체크
     */
    private static boolean colCheck(int x, int y, int number){
        /**
         * 가로 체크와 동일하게 세로로 체크하며
         * 동일한 숫자가 있을 경우 그 즉시 리턴한다
         */
        for(int i=0; i<9; i++){
            if(map[i][y] == number){
                return false;
            }
        }
        return nineCheck(x,y,number);
    }
    /**
     * 9칸 체크
     */
    private static boolean nineCheck(int x, int y, int number){
        /**
         * 현재 0의 좌표값 만으로 자기가 9칸중 어느 위치에 존재하는지 알아야 한다
         * 각 x, y좌표에 3으로 모듈러 연산을 한다
         * 각 연산 결과가 3x3칸에서 위치한 좌표값이 된다
         */
        int xOfNine = x-x%3;
        int yOfNine = y-y%3;

//        System.out.println("탐색 좌표 : " + xOfNine + " "+ yOfNine);

        for(int i=0; i<3; i++){
            for(int j=0; j<3; j++){
                if(map[xOfNine+i][yOfNine+j] == number){
                    return false;
                }
            }
        }

//        System.out.println("RETURN : " + number);

        /**
         * 해당 유효한 number를 찾았을 경우 값을 리턴한다
         * 하지만 해당 값도 백트래킹 되어야 한다
         */
        return true;
    }
}
