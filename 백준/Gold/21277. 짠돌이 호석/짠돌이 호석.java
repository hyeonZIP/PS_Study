import java.io.*;
import java.util.*;

public class Main {
    private static final int EMPTY = 0;
    private static final int PIECE = 1;

    private static int N1, M1, N2, M2;
    private static int[][] map1, map2, frame;
    private static int answer;

    public static void main(String[] args) throws IOException {
        init();
        sol();
        print();
    }

    private static void print() throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(String.valueOf(answer));
        bw.close();
    }

    private static void sol() {
        answer = (N1 + N2) * (M1 + M2);

        for (int i = 0; i < 4; i++) {
            rotate90Degree();

            initFrame();

            combine();
        }

    }

    private static void initFrame() {
        frame = new int[N1 + map2.length + map2.length][M1 + map2[0].length + map2[0].length];

        for (int i = 0; i < N1; i++) {
            for (int j = 0; j < M1; j++) {
                frame[map2.length + i][map2[0].length + j] = map1[i][j];
            }
        }
    }

    private static void combine() {
        for (int i = 0; i < frame.length - map2.length; i++) {
            for (int j = 0; j < frame[0].length - map2[0].length; j++) {
                if (isDuplicate(i, j)) {
                    continue;
                }

                calculateMinimumFrameSize(i, j);
            }
        }
    }

    private static boolean isDuplicate(int y, int x) {
        for (int i = 0; i < map2.length; i++) {
            for (int j = 0; j < map2[0].length; j++) {

                if (map2[i][j] == PIECE && frame[i + y][j + x] == PIECE) {
                    return true;
                }
            }
        }

        return false;
    }

    private static void calculateMinimumFrameSize(int y, int x) {
        int minY = Integer.MAX_VALUE;
        int minX = Integer.MAX_VALUE;
        int maxY = Integer.MIN_VALUE;
        int maxX = Integer.MIN_VALUE;

        int[][] testFrame = new int[frame.length][frame[0].length];

        for (int i = 0; i < frame.length; i++) {
            for (int j = 0; j < frame[0].length; j++) {
                testFrame[i][j] = frame[i][j];
            }
        }

        for (int i = 0; i < map2.length; i++) {
            for (int j = 0; j < map2[0].length; j++) {
                testFrame[i + y][j + x] = map2[i][j];
            }
        }

        for (int i = 0; i < testFrame.length; i++) {
            for (int j = 0; j < testFrame[0].length; j++) {
                if (testFrame[i][j] == PIECE) {
                    minY = Math.min(minY, i);
                    minX = Math.min(minX, j);
                    maxY = Math.max(maxY, i);
                    maxX = Math.max(maxX, j);
                }
            }
        }

        answer = Math.min(answer, (maxY - minY + 1) * (maxX - minX + 1));

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