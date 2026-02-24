import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    private static final int[] dy = new int[]{0, 0, -1, -1, -1, 0, 1, 1, 1};
    private static final int[] dx = new int[]{0, -1, -1, 0, 1, 1, 1, 0, -1};

    private static final int[] sy = new int[]{0, -1, 0, 1, 0};// 상 좌 하 우
    private static final int[] sx = new int[]{0, 0, -1, 0, 1};// 1 2 3 4

    private static class Fish {

        List<Integer> fish;

        public Fish(List<Integer> fish) {
            this.fish = fish;
        }

        public void addFish(int direction) {
            this.fish.add(direction);
        }

        public void addAllFish(List<Integer> fish) {
            this.fish.addAll(fish);
        }

        public Fish copy() {
            return new Fish(List.copyOf(this.fish));
        }

        public boolean isExistingFish() {
            return !this.fish.isEmpty();
        }

        public int getFishCount() {
            return this.fish.size();
        }

        public void removeFish() {
            this.fish = new ArrayList<>();
        }
    }

    private static class Shark {

        int y, x, point, moveCount, path;
        boolean[][] visited;

        public Shark(int y, int x, int point, int moveCount, int path, boolean[][] visited) {
            this.y = y;
            this.x = x;
            this.point = point;
            this.moveCount = moveCount;
            this.path = path;
            this.visited = visited;
        }
    }

    private static int answer;
    private static int M, S, sharkY, sharkX;
    private static Fish[][] fishMap;
    private static int[][] smell;

    public static void main(String[] args) throws IOException {
        init();
        sol();
        print();
    }

    private static void sol() {
        while (S-- > 0) {
            Fish[][] copiedFishMap = copyFish();

            moveFish();

            moveShark();

            removeSmell();

            applyCopiedAllFish(copiedFishMap);

        }
        calculateAllFish();

    }

    private static Fish[][] copyFish() {
        Fish[][] copiedFishMap = initFishMap();

        for (int i = 1; i <= 4; i++) {
            for (int j = 1; j <= 4; j++) {
                copiedFishMap[i][j] = fishMap[i][j].copy();
            }
        }

        return copiedFishMap;
    }

    private static void moveFish() {
        Fish[][] newFishMap = initFishMap();

        for (int y = 1; y <= 4; y++) {
            for (int x = 1; x <= 4; x++) {
                if (fishMap[y][x].isExistingFish()) {
                    List<Integer> fishDirections = fishMap[y][x].fish;

                    for (int direction : fishDirections) {
                        boolean canMove = false;
                        int ny = -1;
                        int nx = -1;
                        int nd = direction;
                        for (int i = 0; i < 8; i++) {
                            ny = y + dy[nd];
                            nx = x + dx[nd];

                            if (isOutOfRange(ny, nx) || isSharkPlaced(ny, nx) || isFishSmell(ny,
                                nx)) {
                                nd = nd - 1 > 0 ? nd - 1 : 8;
                            } else {
                                canMove = true;
                                break;
                            }
                        }

                        if (canMove) {
                            newFishMap[ny][nx].addFish(nd);
                        } else {
                            newFishMap[y][x].addFish(direction);
                        }
                    }
                }
            }
        }

        fishMap = newFishMap;
    }

    private static void moveShark() {
        Deque<Shark> stack = new ArrayDeque<>();
        stack.push(new Shark(sharkY, sharkX, 0, 0, 0, new boolean[5][5]));

        int minimumPath = 0;
        int maximumPoint = 0;

        while (!stack.isEmpty()) {
            Shark s = stack.pop();

            int y = s.y;
            int x = s.x;
            int point = s.point;
            int moveCount = s.moveCount;
            int path = s.path;
            boolean[][] visited = s.visited;

            if (moveCount == 3) {
                if (maximumPoint <= point) {
                    maximumPoint = point;
                    minimumPath = path;
                }

                continue;
            }

            for (int direction = 1; direction <= 4; direction++) {
                int ny = y + sy[direction];
                int nx = x + sx[direction];

                if (isOutOfRange(ny, nx)) {
                    continue;
                }

                boolean[][] nextVisited = new boolean[5][5];
                for (int i = 1; i <= 4; i++) {
                    nextVisited[i] = visited[i].clone();
                }

                int nextPoint = point;
                if (!nextVisited[ny][nx]) {
                    nextPoint += fishMap[ny][nx].getFishCount();
                    nextVisited[ny][nx] = true;
                }
                int nextMoveCount = moveCount + 1;
                int nextPath = path * 10 + direction;

                stack.push(new Shark(ny, nx, nextPoint, nextMoveCount, nextPath, nextVisited));
            }
        } // while

        int[] sharkDirections = Arrays.stream(String.valueOf(minimumPath).split(""))
            .mapToInt(Integer::parseInt)
            .toArray();

        for (int direction : sharkDirections) {
            sharkY += sy[direction];
            sharkX += sx[direction];

            if (fishMap[sharkY][sharkX].isExistingFish()) {
                smell[sharkY][sharkX] = 3;
                fishMap[sharkY][sharkX].removeFish();
            }
        }
    }

    private static void removeSmell() {
        for (int i = 1; i <= 4; i++) {
            for (int j = 1; j <= 4; j++) {
                if (smell[i][j] > 0) {
                    smell[i][j]--;
                }
            }
        }
    }

    private static void applyCopiedAllFish(Fish[][] copiedFishMap) {
        for (int i = 1; i <= 4; i++) {
            for (int j = 1; j <= 4; j++) {
                fishMap[i][j].addAllFish(copiedFishMap[i][j].fish);
            }
        }
    }

    private static void calculateAllFish() {
        for (int i = 1; i <= 4; i++) {
            for (int j = 1; j <= 4; j++) {
                answer += fishMap[i][j].fish.size();
            }
        }
    }

    private static boolean isOutOfRange(int y, int x) {
        return y < 1 || x < 1 || y > 4 || x > 4;
    }

    private static boolean isSharkPlaced(int y, int x) {
        return y == sharkY && x == sharkX;
    }

    private static boolean isFishSmell(int y, int x) {
        return smell[y][x] > 0;
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());// 물고기 수
        S = Integer.parseInt(st.nextToken());// 마법 연습 횟수

        fishMap = initFishMap();
        smell = new int[5][5];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int y = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());

            fishMap[y][x].addFish(d);
        }

        st = new StringTokenizer(br.readLine());

        sharkY = Integer.parseInt(st.nextToken());
        sharkX = Integer.parseInt(st.nextToken());

    }

    private static void print() throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(String.valueOf(answer));
        bw.close();
    }

    private static Fish[][] initFishMap() {
        Fish[][] fishMap = new Fish[5][5];

        for (int i = 1; i <= 4; i++) {
            for (int j = 1; j <= 4; j++) {
                fishMap[i][j] = new Fish(new ArrayList<>());
            }
        }

        return fishMap;
    }
}
