import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static final int[] dy = {-1, 0, 0, 1};
    static final int[] dx = {0, -1, 1, 0};
    static int babyShark = 2;
    static int ateFishCount = 0;
    static int[] babySharkPos = new int[2];
    static int N;
    static int answer = 0;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        init();

        while (true) {
            List<int[]> eatableFish = findEatableFish();

            if (eatableFish.isEmpty()) {
                //먹을 수 있는 물고기가 없음 > 엄마 상어에게 도움 요청
                break;
            }

            if (!bfs()) break;

            //먹을 수 있는 물고기가 여러마리 일 경우, 가장 가까운 물고기로 이동 BFS
        }

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        bw.write(answer + "");
        bw.close();
    }

    private static boolean bfs() {
        PriorityQueue<int[]> q = new PriorityQueue<>((o1, o2) -> {
            if (o1[2] == o2[2]) {
                if (o1[0] == o2[0]) {
                    return o1[1] - o2[1];
                } else {
                    return o1[0] - o2[0];
                }
            }
            return o1[2] - o2[2];
        });

        PriorityQueue<int[]> closerFish = new PriorityQueue<>((o1, o2) -> {

            if (o1[2] == o2[2]) {
                if (o1[0] == o2[0]) {
                    return o1[1] - o2[1];
                } else {
                    return o1[0] - o2[0];
                }
            }
            return o1[2] - o2[2];
        });
        boolean[][] visited = new boolean[N][N];
        q.offer(new int[]{babySharkPos[0], babySharkPos[1], 0});
        visited[babySharkPos[0]][babySharkPos[1]] = true;

        while (!q.isEmpty()) {
            int[] pos = q.poll();

            int y = pos[0];
            int x = pos[1];
            int time = pos[2];

            for (int i = 0; i < 4; i++) {
                int py = y + dy[i];
                int px = x + dx[i];

                if (isOutOfRange(py, px)) {
                    continue;
                }
                if (visited[py][px]) {
                    continue;
                }
                if (isEmptyPlace(py, px) || isSameBabyShark(py, px)) {
                    q.offer(new int[]{py, px, time + 1});
                    visited[py][px] = true;
                    continue;
                }
                if (isBiggerThanBabyShark(py, px)) {
                    continue;
                }
                closerFish.offer(new int[]{py, px, time + 1});
            }
        }//while
        if (closerFish.isEmpty()){
            return false;
        }
        int[] eatFish = closerFish.poll();

        int y = eatFish[0];
        int x = eatFish[1];
        int time = eatFish[2];

        map[babySharkPos[0]][babySharkPos[1]] = 0;
        map[y][x] = 9;
        babySharkPos[0] = y;
        babySharkPos[1] = x;
//
//        for (int[] ints : map) {
//            for (int anInt : ints) {
//                System.out.print(anInt + " ");
//            }
//            System.out.println();
//        }
//        System.out.println("@@@@@@@@@@@@@@@@@");

        ateFishCount++;
//        System.out.println("ateFishCount = " + ateFishCount);
        if (ateFishCount == babyShark) {
            ateFishCount = 0;
            babyShark++;
//            System.out.println("크기 갱신");
//            System.out.println("babyShark = " + babyShark);
        }

        answer += time;
//        System.out.println("answer = " + answer);
        return true;
    }

    private static boolean isSameBabyShark(int y, int x) {
        return babyShark == map[y][x];
    }

    private static boolean isEmptyPlace(int y, int x) {
        return map[y][x] == 0;
    }

    private static boolean isBiggerThanBabyShark(int y, int x) {
        return babyShark < map[y][x];
    }

    private static boolean isOutOfRange(int y, int x) {
        return y < 0 || x < 0 || y >= N || x >= N;
    }

    private static List<int[]> findEatableFish() {

        List<int[]> eatableFish = new ArrayList<>();

        for (int y = 0; y < N; y++) {
            for (int x = 0; x < N; x++) {
                //자기 자신의 위치가 아니고, 빈칸이 아니고, 자기보다 작은 물고기의 좌표만 수집
                if (map[y][x] != 9 && map[y][x] != 0 && babyShark > map[y][x]) {
                    eatableFish.add(new int[]{y, x});
                }
            }
        }

        return eatableFish;
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        StringTokenizer st;

        map = new int[N][N];

        for (int y = 0; y < N; y++) {
            st = new StringTokenizer(br.readLine());
            for (int x = 0; x < N; x++) {
                int input = Integer.parseInt(st.nextToken());
                map[y][x] = input;
                if (input == 9) {
                    babySharkPos = new int[]{y, x};
                }
            }
        }


    }
}
