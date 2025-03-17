import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Solution {

    //북 동 남 서 방향으로 들어왔을 때 방향을 바꿔줌
    static final int[][][] BLOCK = {
            {{1, 0,-1}, {0, -1,-1}, {0, 1,1}, {-1, 0,0}}
            , {{0, 1,1}, {0, -1,-1}, {-1, 0,-1}, {1, 0,2}}
            , {{0, -1,3}, {1, 0,2}, {-1, 0,-1}, {0, 1,-1}}
            , {{1, 0,-1}, {-1, 0,0}, {0, -1,3}, {0, 1,-1}}
            , {{1, 0,-1}, {0, -1,-1}, {-1, 0,-1}, {0, 1,-1}}
    };

    static final int[] dy = {-1, 0, 1, 0};
    static final int[] dx = {0, 1, 0, -1};

    static final int EMPTY = 0;
    static final int BLACK_HOLE = -1;

    static ArrayList<int[]>[] wormHole;

    static int[][] map;

    static int N,answer = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int testcase = Integer.parseInt(br.readLine());

        for (int t = 0; t < testcase; t++) {

            wormHole = new ArrayList[11];

            for (int i = 6; i <= 10; i++) {
                wormHole[i] = new ArrayList<>();
            }

            N = Integer.parseInt(br.readLine().trim());

            map = new int[N][N];

            for (int y = 0; y < N; y++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int x = 0; x < N; x++) {
                    int obj = Integer.parseInt(st.nextToken());
                    map[y][x] = obj;
                    if (obj >= 6) {
                        wormHole[obj].add(new int[]{y, x});
                    }
                }
            }

            for (int y = 0; y < N; y++) {
                for (int x = 0; x < N; x++) {
                    if (map[y][x] == EMPTY) {
                        for (int dir = 0; dir < 4; dir++) {
                            answer = Math.max(answer, activePinball(y, x, dy[dir], dx[dir], dir));
                        }
                    }
                }
            }

            sb.append("#").append(t+1).append(" ").append(answer).append("\n");
            answer = Integer.MIN_VALUE;
        }//testcase

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    private static int activePinball(int startY, int startX, int dirY, int dirX, int direction) {
        int y = startY + dirY;
        int x = startX + dirX;
        int point = 0;
        while(true){
            if (isOutOfRange(y, x)) {
                return point*2 + 1;
            } else if (map[y][x] == BLACK_HOLE || (y == startY && x == startX)){
                break;
            }
            else if (map[y][x] == 0) {
                y += dirY;
                x += dirX;
            } else if (map[y][x] > 0 && map[y][x] < 6) {
                dirY = BLOCK[map[y][x]-1][direction][0];//방향 전환
                dirX = BLOCK[map[y][x]-1][direction][1];//방향 전환
                direction = BLOCK[map[y][x]-1][direction][2];//방향 전환
                if (direction == -1){
                    return point*2 + 1;
                }
                y += dirY;
                x += dirX;
                point++;
            } else if (map[y][x]>=6) {
                for (int[] arr : wormHole[map[y][x]]) {
                    int wormholeY = arr[0];
                    int wormholeX = arr[1];

                    if (y != wormholeY || x != wormholeX){
                        y = wormholeY;
                        x = wormholeX;
                        y += dirY;
                        x += dirX;
                        break;
                    }
                }
            }

            if (isOutOfRange(y, x)) {
                return point*2 + 1;
            }
        }

        return point;
    }

    private static boolean isOutOfRange(int y, int x) {
        return y < 0 || x < 0 || y >= N || x >= N;
    }
}
