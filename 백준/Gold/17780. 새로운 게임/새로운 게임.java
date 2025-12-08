import java.io.*;
import java.util.*;

public class Main {
    private static final int WHITE = 0;
    private static final int RED = 1;
    private static final int BLUE = 2;
    private static final int MEANINGLESS = -1;
    private static final int RIGHT = 1;
    private static final int LEFT = 2;
    private static final int UP = 3;
    private static final int DOWN = 4;
    private static final int[] dy = new int[] { MEANINGLESS, 0, 0, -1, 1 };
    private static final int[] dx = new int[] { MEANINGLESS, 1, -1, 0, 0 };

    private static class Pawn {
        private int number;
        private int direction;

        public Pawn(int number, int direction) {
            this.number = number;
            this.direction = direction;
        }
    }

    private static int N, K;
    private static List<Pawn>[][] board;
    private static int[][] color;

    private static int answer;

    public static void main(String[] args) throws IOException {
        init();

        sol();

        answer();
    }

    private static void test() {
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (board[i][j].isEmpty()) {
                    System.out.print("0");
                    continue;
                }

                System.out.print("[");
                board[i][j].forEach(o -> System.out.print(o.number));
                System.out.print("]");
            }
            System.out.println();
        }
        System.out.println("-------------------------------------------------------");
    }

    private static void sol() {
        int count = 1;

        // test();

        while (count <= 1000) {
            int state = movePawn();
            // test();

            if (state == -1) {
                answer = -1;
                return;
            } else if (state == 1) {
                answer = count;
                return;
            } else {
                count++;
            }
        }

        answer = -1;
    }

    private static int movePawn() {
        int pawnNumber = 1;
        int emptyCount = 1;

        while (pawnNumber <= K) {
            List<int[]> pawns = findPawnsNextOrder(pawnNumber);

            if (!pawns.isEmpty()) {
                int y = pawns.get(0)[0];
                int x = pawns.get(0)[1];
                int direction = board[y][x].get(0).direction;

                int nextY = y + dy[direction];
                int nextX = x + dx[direction];
                int nextColor;

                if (nextY < 1 || nextX < 1 || nextY > N || nextX > N) {
                    nextColor = BLUE;
                } else {
                    nextColor = color[nextY][nextX];
                }

                switch (nextColor) {
                    case WHITE:
                        board[nextY][nextX].addAll(board[y][x]);
                        board[y][x].clear();
                        if (board[nextY][nextX].size() >= 4) {
                            return 1;
                        }
                        break;
                    case RED:
                        Collections.reverse(board[y][x]);
                        board[nextY][nextX].addAll(board[y][x]);
                        board[y][x].clear();
                        if (board[nextY][nextX].size() >= 4) {
                            return 1;
                        }
                        break;
                    case BLUE:
                        int reverseDirection;
                        if (direction >= 3) {
                            reverseDirection = direction == UP ? DOWN : UP;
                        } else {
                            reverseDirection = direction == RIGHT ? LEFT : RIGHT;
                        }

                        int nextNextY = y + dy[reverseDirection];
                        int nextNextX = x + dx[reverseDirection];
                        int nextNextColor;

                        if (nextNextY < 1 || nextNextX < 1 || nextNextY > N || nextNextX > N) {
                            nextNextColor = BLUE;
                        } else {
                            nextNextColor = color[nextNextY][nextNextX];
                        }

                        switch (nextNextColor) {
                            case WHITE:
                                board[y][x].get(0).direction = reverseDirection;
                                board[nextNextY][nextNextX].addAll(board[y][x]);
                                board[y][x].clear();
                                if (board[nextNextY][nextNextX].size() >= 4) {
                                    return 1;
                                }
                                break;
                            case RED:
                                board[y][x].get(0).direction = reverseDirection;
                                Collections.reverse(board[y][x]);
                                board[nextNextY][nextNextX].addAll(board[y][x]);
                                board[y][x].clear();
                                if (board[nextNextY][nextNextX].size() >= 4) {
                                    return 1;
                                }
                                break;
                            case BLUE:
                                board[y][x].get(0).direction = reverseDirection;
                        }

                        break;
                }
            } else {
                emptyCount++;
            }

            pawnNumber++;
        }

        if (emptyCount == K) {
            // 불가능한 경우
            return -1;
        } else {
            return 0;
        }
    }

    private static List<int[]> findPawnsNextOrder(int pawnNumber) {
        List<int[]> pawnsNextOrder = new ArrayList<>();

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (board[i][j].isEmpty() || board[i][j].get(0).number != pawnNumber) {
                    continue;
                }

                pawnsNextOrder.add(new int[] { i, j });
            }
        }

        return pawnsNextOrder;
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st;

        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        board = new ArrayList[N + 1][N + 1];
        color = new int[N + 1][N + 1];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 1; j <= N; j++) {
                board[i][j] = new ArrayList<>();
                color[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int number = 1; number <= K; number++) {
            st = new StringTokenizer(br.readLine());

            int y = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            int direction = Integer.parseInt(st.nextToken());

            board[y][x].add(new Pawn(number, direction));
        }
    }

    private static void answer() throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(String.valueOf(answer));
        bw.close();
    }
}