import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

/**
 * N*M 크기 직사각형
 * 벽 또는 빈칸
 *
 * 1. 현재 칸이 청소되지 않은 경우 청소
 * 2. 현재 칸의 동서남북 중 청소되지 않은 빈 칸이 없는 경우
 *      2-1. 바라보는 방향 유지, 한 칸 후진 후 1번
 *      2-2. 후진이 안될 경우 작동 멈춤
 *      2-3. 현재 칸의 동서남북 중 청소되지 않은 빈 칸이 있는 경우,
 *          2-3-1. 반시계 방향으로 90도 회전
 *          2-3-2. 바라보는 방향 기준 앞쪽 칸이 청소되지 않은 빈 칸인 경우 전진
 *          2-3-3. 1번으로 돌아간다.
 */

public class Main {

    static final int NORTH = 0;
    static final int EAST = 1;
    static final int SOUTH = 2;
    static final int WEST = 3;

    static final int[][] DIRECTION = {{-1,0},{0,1},{1,0},{0,-1}};

    static final int WALL = 1;
    static final int DIRTY = 0;
    static final int CLEAN = 2;

    static int height, width, robotY,robotX,robotDir,answer;
    static int[][] map;

    static int[] dy = {0,0,1,-1};
    static int[] dx = {1,-1,0,0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        height = Integer.parseInt(st.nextToken());
        width = Integer.parseInt(st.nextToken());

        map = new int[height][width];

        st = new StringTokenizer(br.readLine());

        robotY = Integer.parseInt(st.nextToken());
        robotX = Integer.parseInt(st.nextToken());
        robotDir = Integer.parseInt(st.nextToken());

        for (int y=0; y<height; y++){
            st = new StringTokenizer(br.readLine());
            for (int x = 0; x < width; x++) {
                map[y][x] = Integer.parseInt(st.nextToken());
            }
        }

        activateRobot();
    }

    private static void activateRobot(){
        int[] robotPos = new int[]{robotY,robotX,robotDir};//로봇 초기위치 등록

        while(true){
            sol1(robotPos);
//            System.out.println("현재 로봇 청소기 위치 = " + robotPos[0] + " " + robotPos[1]);
//            System.out.println("바라보는 방향 = " + robotPos[2]);
//            System.out.println("청소 횟수 = " + answer);
            if (!sol2(robotPos)) break;
        }
        System.out.println(answer);

    }

    private static void sol1(int[] robotPos){

        int y = robotPos[0];
        int x = robotPos[1];
        if (map[y][x] == DIRTY){
            answer++;
            map[y][x] = CLEAN;
        }
//        for (int[] ints : map) {
//            for (int anInt : ints) {
//                System.out.print(anInt + " ");
//            }
//            System.out.println();
//        }
//        System.out.println();
    }

    private static boolean sol2(int[] robotPos){
        int y = robotPos[0];
        int x = robotPos[1];
        int dir = robotPos[2];

        for (int i=0; i<4; i++){
            int py = y+dy[i];//주변 4칸 조사 좌표
            int px = x+dx[i];//주변 4칸 조사 좌표

            if (map[py][px] == DIRTY){//현재 칸의 동서남북 중 청소되지 않은 빈 칸이 있는 경우
//                System.out.println("현재 칸의 동서남북 중 청소되지 않은 빈 칸이 있는 경우");
                dir = dir-1 >= 0 ? dir-1 : 3;//반시계 90도 회전
                robotPos[2] = dir;//회전 값 반영

                int dirY = DIRECTION[dir][0] + y;
                int dirX = DIRECTION[dir][1] + x;

                if (map[dirY][dirX] == DIRTY){//바라보는 방향으로 앞쪽 칸이 청소되지 않은 빈 칸인 경우 한 칸 전진
                    robotPos[0] = dirY;
                    robotPos[1] = dirX;
//                    System.out.println("바라보는 방향으로 앞쪽 칸이 청소되지 않은 빈 칸인 경우 한 칸 전진");
                    return true;
                }
//                System.out.println("dirY = " + dirY);
//                System.out.println("dirX = " + dirX);
                return true;
//                System.out.println("바라보는 방향으로 앞쪽 칸이 청소되지 않은 빈 칸이 아님, 한 칸 전진하지 않고 반복해서 회전");
            }
        }
//        System.out.println("현재 칸의 동서남북 중 청소되지 않은 빈 칸이 없는 경우");
        //현재 칸의 동서남북 중 청소되지 않은 빈 칸이 없는 경우
        if (dir == NORTH) {
            dir=SOUTH;
        }else if (dir == EAST){
            dir=WEST;
        }else if (dir == SOUTH){
            dir=NORTH;
        }else if (dir == WEST){
            dir=EAST;
        }

        int dirY = DIRECTION[dir][0] + y;
        int dirX = DIRECTION[dir][1] + x;
//        System.out.println("dirY = " + dirY);
//        System.out.println("dirX = " + dirX);
        if (map[dirY][dirX] != WALL){//바라보는 방향을 유지한 채로 한 칸 후진할 수 있다면
//            System.out.println("바라보는 방향을 유지한 채로 한 칸 후진할 수 있다면 후진");
            robotPos[0] = dirY;
            robotPos[1] = dirX;
            return true;
        }else{
//            System.out.println("후진할 수 없어서 종료");
            return false;
        }
    }

}
