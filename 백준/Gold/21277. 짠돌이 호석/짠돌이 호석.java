import java.io.*;
import java.util.*;

public class Main {
    private static final int EMPTY = 0;
    private static final int PIECE = 1;

    private static int N1, M1, N2, M2;
    private static int[][] map1, map2, frame;
    private static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        init();
        sol();
        print();
    }

    private static void print() {
        System.out.println(answer);
    }

    private static void sol() {
        // 둘 중 한 퍼즐만 회전시켜서 대입하기
        // 90 180 270 360
        // 전부 안되는 예외를 위해 기본값 필요
        for (int i = 0; i < 4; i++) {
            rotate90Degree();

            initFrame();

            combine();
        }

    }

    private static void initFrame() {
        frame = new int[N1 + map2.length][M1 + map2[0].length];

        for (int i = 0; i < N1; i++) {
            for (int j = 0; j < M1; j++) {
                frame[i][j] = map1[i][j];
            }
        }
    }

    private static void combine() {
        for (int i = 0; i < N1; i++) {
            for (int j = 0; j < M1; j++) {
                if (canCombine(i, j)) {
                    calculateMinimumFrameSize(i, j);
                }
            }
        }
    }

    private static void calculateMinimumFrameSize(int y, int x) {
        int minY = 0;
        int minX = 0;

        for (int i = 0; i < map2.length; i++) {
            for (int j = 0; j < map2[0].length; j++) {
                frame[i + y][j + x] = map2[i][j];
            }
        }

        for (int i = 0; i < frame.length; i++) {
            for (int j = 0; j < frame[0].length; j++) {
                if (frame[i][j] == PIECE) {
                    minX = Math.max(minX, j + 1);
                    minY = Math.max(minY, i + 1);
                }
            }
        }

        answer = Math.min(answer, minY * minX);
    }

    private static boolean canCombine(int y, int x) {

        for (int i = 0; i < map2.length; i++) {
            for (int j = 0; j < map2[0].length; j++) {

                if (map2[i][j] == PIECE && frame[i + y][j + x] == PIECE) {
                    return false;
                }
            }
        }

        return true;
    }

    private static void rotate90Degree() {
        int[][] newMap = new int[map2[0].length][map2.length];

        for (int i = 0; i < map2[0].length; i++) {
            for (int j = 0; j < map2.length; j++) {
                newMap[i][j] = map2[map2.length - 1 - j][i];
            }
        }

        map2 = newMap;
    }

    private static void test(int[][] map) {
        for (int i = 0; i < map.length; i++) {
            System.out.println(Arrays.toString(map[i]));
        }

        System.out.println("--------------------");
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N1 = Integer.parseInt(st.nextToken());
        M1 = Integer.parseInt(st.nextToken());

        map1 = new int[N1][M1];

        for (int i = 0; i < N1; i++) {
            map1[i] = Arrays.stream(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
        }

        st = new StringTokenizer(br.readLine());
        N2 = Integer.parseInt(st.nextToken());
        M2 = Integer.parseInt(st.nextToken());

        map2 = new int[N2][M2];

        for (int i = 0; i < N2; i++) {
            map2[i] = Arrays.stream(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
        }
    }
}