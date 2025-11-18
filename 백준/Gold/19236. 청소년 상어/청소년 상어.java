import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Main {
    private static final int BOUNDARY = 4;

    private static class Fish {
        private int fishNumber;
        private int direction;
        private boolean isAlive;

        public Fish(int fishNumber, int direction) {
            this.fishNumber = fishNumber;
            this.direction = direction;
            this.isAlive = true;
        }
    }

    private static int answer = Integer.MIN_VALUE;
    private static Fish[][] fishMap = new Fish[BOUNDARY][BOUNDARY];
    private static int[] dy = { -1, -1, 0, 1, 1, 1, 0, -1 };
    private static int[] dx = { 0, -1, -1, -1, 0, 1, 1, 1 };

    public static void main(String[] args) throws IOException {
        init();

        sol();

        print();
    }

    private static void sol() {
        // dfs + 백트래킹
        // 물고기 초기화 -> 상어 배치(상어 (0,0)으로 이동) -> 물고기 이동 -> 상어 이동 -> 물고기 이동 -> 상어 이동 ... ->
        // 상어 이동 불가능? 최대값 갱신 시도
        dfs(0, 0, fishMap[0][0].fishNumber, fishMap[0][0].direction, fishMap);
    }

    private static void dfs(int sharkY, int sharkX, int sharkPoint, int sharkDirection, Fish[][] fishMap) {
        fishTurn(sharkY, sharkX, fishMap);

        while (true) {
            sharkY += dy[sharkDirection];
            sharkX += dx[sharkDirection];

            if (isOutOfRange(sharkY, sharkX)) {
                answer = Math.max(sharkPoint, answer);
                break;
            }

            if (fishMap[sharkY][sharkX].isAlive) {
                Fish[][] copiedFishMap = copyFishMap(fishMap);

                copiedFishMap[sharkY][sharkX].isAlive = false;

                dfs(sharkY, sharkX, sharkPoint + copiedFishMap[sharkY][sharkX].fishNumber,
                        copiedFishMap[sharkY][sharkX].direction, copiedFishMap);
            } else {
                continue;
            }
        }
    }

    private static void fishTurn(int sharkY, int sharkX, Fish[][] copiedFishMap) {
        for (int findingFishNumber = 1; findingFishNumber <= 16; findingFishNumber++) {
            for (int j = 0; j < BOUNDARY; j++) {
                for (int k = 0; k < BOUNDARY; k++) {
                    if (copiedFishMap[j][k].fishNumber == findingFishNumber) {
                        if (copiedFishMap[j][k].isAlive) {
                            moveFish(sharkY, sharkX, j, k, copiedFishMap);
                        }
                        j = BOUNDARY;
                        break;
                    }
                }
            }
        }
    }

    private static void moveFish(int sharkY, int sharkX, int y, int x, Fish[][] copiedFishMap) {
        int fishDirection = copiedFishMap[y][x].direction;

        for (int i = 0; i < 8; i++) {
            int nextY = y + dy[fishDirection];
            int nextX = x + dx[fishDirection];

            if (isOutOfRange(nextY, nextX) || (nextY == sharkY && nextX == sharkX)) {
                fishDirection = (fishDirection + 1) % 8;
                continue;
            }

            copiedFishMap[y][x].direction = fishDirection;
            Fish temp = copiedFishMap[nextY][nextX];
            copiedFishMap[nextY][nextX] = copiedFishMap[y][x];
            copiedFishMap[y][x] = temp;
            break;
        }
    }

    private static Fish[][] copyFishMap(Fish[][] fishMap) {
        Fish[][] newFishMap = new Fish[BOUNDARY][BOUNDARY];

        for (int i = 0; i < BOUNDARY; i++) {
            for (int j = 0; j < BOUNDARY; j++) {
                Fish original = fishMap[i][j];
                newFishMap[i][j] = new Fish(original.fishNumber, original.direction);
                newFishMap[i][j].isAlive = original.isAlive;
            }
        }

        return newFishMap;
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st;

        for (int i = 0; i < BOUNDARY; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < BOUNDARY; j++) {
                int fishNumber = Integer.parseInt(st.nextToken());
                int direction = Integer.parseInt(st.nextToken()) - 1;

                fishMap[i][j] = new Fish(fishNumber, direction);
            }
        }

        fishMap[0][0].isAlive = false;
    }

    private static void print() throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        bw.write(String.valueOf(answer));
        bw.close();
    }

    private static boolean isOutOfRange(int y, int x) {
        return y < 0 || x < 0 || y >= BOUNDARY || x >= BOUNDARY;
    }
}
