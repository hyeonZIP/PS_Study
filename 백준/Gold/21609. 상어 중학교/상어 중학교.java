import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 검은색 -1
 * 무지개 0
 * 이외의 M가지 자연수 1<=M<=5
 * 그룹에 속한 블록의 개수는 2보다 크거나 같다
 * 무지개 블록은 검정 이외의 모든 숫자와 그룹이 될 수 있기 때문에 별도의 방문체크를 한다
 *
 * sol1. 가장 큰 블록 그룹 찾기 > 0은 아무 숫자와 합칠 수 있고 그 외의 블록은 동일한 블록끼리 그룹이 되어야 한다
 * sol2. 가장 큰 블록 제거 및 점수 계산
 * sol3. 중력
 * sol4. 반시계 방향으로 90도 회전
 * sol5. 중력
 * 반복
 */

public class Main {

    static int N,M,answer,maxRainbow;
    static List<int[]> maxPos;
    static int[][] map;
    static boolean[][] visited;
    static int[] dy = {0,0,-1,1};
    static int[] dx = {-1,1,0,0};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        visited = new boolean[N][N];
        maxPos = new ArrayList<>();

        for (int y=0; y<N; y++){
            st = new StringTokenizer(br.readLine());

            for (int x=0; x<N; x++){
                map[y][x] = Integer.parseInt(st.nextToken());
            }
        }

        while(true){
            maxPos = new ArrayList<>();
            maxRainbow = 0;
            visited = new boolean[N][N];
            sharkSchool();
            if (maxPos.size()<2) break;

            answer += maxPos.size() * maxPos.size();

            deleteBlock();

            activateGravity();


            leftRotate();


            activateGravity();


        }
        System.out.println(answer);
    }
    private static void leftRotate(){
        int[][] rotatedMap = new int[N][N];
        for (int i=0; i<N; i++){
            for (int j=0; j<N; j++){
                rotatedMap[i][j] = map[j][N-1-i];
            }
        }
        map = rotatedMap;
    }

    private static void activateGravity(){
        for (int y = N-1; y > 0; y--) {
            for (int x = 0; x < N; x++) {
                if (map[y][x] == -2){
                    ArrayDeque<int[]> stack = new ArrayDeque<>();
                    stack.push(new int[]{y,x});

                    while(!stack.isEmpty()){
                        int[] arr = stack.pop();
                        int py = arr[0]-1;
                        int px = arr[1];
                        if (isOutOfRange(py,px)) continue;
                        if (map[py][px] == -1) continue;
                        else if (map[py][px] == -2) {
                            stack.push(new int[]{py,px});
                        }else{
                            map[y][x] = map[py][px];
                            map[py][px] = -2;
                        }
                    }
                }
            }
        }
    }

    private static void deleteBlock(){

        for (int[] pos : maxPos) {
            int y = pos[0];
            int x = pos[1];

            map[y][x] = -2;
        }
    }

    private static void sharkSchool(){
        for (int y=0; y<N; y++){
            for (int x=0; x<N; x++){
                if (map[y][x] == 0 || map[y][x] == -1 || map[y][x] == -2) continue;
                sol1(y,x, map[y][x]);//최대 크기 그룹 선정
            }
        }
    }


    private static void sol1(int y, int x, int standardNum){
        ArrayDeque<int[]> stack = new ArrayDeque<>();
        List<int[]> pos = new ArrayList<>();
        boolean[][] visitedRainbow = new boolean[N][N];

        int rainbowCount = 0;

        visited[y][x] = true;
        stack.push(new int[]{y,x});
        pos.add(new int[]{y,x});

        while(!stack.isEmpty()){
            int[] arr = stack.pop();

            for (int i=0; i<4; i++){
                int py = arr[0] + dy[i];
                int px = arr[1] + dx[i];

                if (isOutOfRange(py,px)) continue;

                if (map[py][px] == standardNum && !visited[py][px]){
                    //같은 숫자인데 방문 안했으면
                    stack.push(new int[]{py,px});
                    visited[py][px] = true;
                    pos.add(new int[]{py,px});
                }else if (map[py][px] == 0 && !visitedRainbow[py][px]){
                    //무지개 블럭인데 방문 안했으면
                    rainbowCount++;
                    stack.push(new int[]{py,px});
                    visitedRainbow[py][px] = true;
                    pos.add(new int[]{py,px});
                }
            }
        }

        if (maxPos.size() < pos.size()){
            maxPos = pos;
            maxRainbow = rainbowCount;
        }else if (maxPos.size() == pos.size() && maxRainbow < rainbowCount){
            maxPos = pos;
            maxRainbow = rainbowCount;
        }else if (maxPos.size() == pos.size() && maxRainbow == rainbowCount){
            maxPos = pos;
            maxRainbow =rainbowCount;
        }
    }

    private static boolean isOutOfRange(int y, int x){
        return y<0 || x<0 || y>=N || x>=N;
    }
}
