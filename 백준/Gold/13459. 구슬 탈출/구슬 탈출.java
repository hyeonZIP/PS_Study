import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

    static final int[] dy = new int[]{0, 0, -1, 1};
    static final int[] dx = new int[]{-1, 1, 0, 0};

    static int answer = 0, N, M, startRedY, startRedX, startBlueY, startBlueX, goalY, goalX;
    static String[][] map;

    public static void main(String[] args) throws IOException {
        init();
        sol();
        print();
    }

    static void sol() {
        dfs(0, new int[]{startRedY, startRedX, startBlueY, startBlueX});
    }

    static void dfs(int depth, int[] balls) {
        if (depth >= 10) {
            return;
        }

        for (int i = 0; i < 4; i++) {
            int[] nextBalls = forwardUntilEnd(balls, i);

            if (nextBalls.length == 0) {
                continue;
            }

//            System.out.println(Arrays.toString(nextBalls));

            if (nextBalls[0] == goalY && nextBalls[1] == goalX && !(nextBalls[2] == goalY
                && nextBalls[3] == goalX)) {
                answer = 1;
                return;
            }

            dfs(depth + 1, nextBalls);
        }
    }

    static int[] forwardUntilEnd(int[] balls, int d) {
        int redY = balls[0];
        int redX = balls[1];
        int blueY = balls[2];
        int blueX = balls[3];

        int rCount = 0;
        int bCount = 0;
        while (!map[redY + dy[d]][redX + dx[d]].equals("#")) {
            redY += dy[d];
            redX += dx[d];
            rCount++;
            if (map[redY][redX].equals("O")) {
                break;
            }
        }

        while (!map[blueY + dy[d]][blueX + dx[d]].equals("#")) {
            blueY += dy[d];
            blueX += dx[d];
            bCount++;
            if (map[blueY][blueX].equals("O")) {
                return new int[]{};
            }
        }

        if (redY == blueY && redX == blueX) {
            if (rCount > bCount) {
                redY -= dy[d];
                redX -= dx[d];
            } else {
                blueY -= dy[d];
                blueX -= dx[d];
            }
        }

        return new int[]{redY, redX, blueY, blueX};
    }

    static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new String[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            String[] s = st.nextToken().split("");

            for (int j = 0; j < M; j++) {
                String input = s[j];

                if (input.equals("B")) {
                    startBlueY = i;
                    startBlueX = j;
                    map[i][j] = ".";
                } else if (input.equals("R")) {
                    startRedY = i;
                    startRedX = j;
                    map[i][j] = ".";
                } else if (input.equals("O")) {
                    goalY = i;
                    goalX = j;
                    map[i][j] = input;
                } else {
                    map[i][j] = input;
                }
            }
        }
    }

    static void print() throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(String.valueOf(answer));
        bw.close();
    }
}
