import java.io.*;
import java.util.*;

public class Main {
    private static final int RED = 1;
    private static final int BLUE = 2;
    private static final int IMPOSSIBLE = -1;
    private static final int FOUND_ANSWER = 1;
    private static final int NEXT = 0;
    private static final int RIGHT = 1;
    private static final int LEFT = 2;
    private static final int UP = 3;
    private static final int DOWN = 4;
    private static final int[] dy = new int[] { IMPOSSIBLE, 0, 0, -1, 1 };
    private static final int[] dx = new int[] { IMPOSSIBLE, 1, -1, 0, 0 };

    private static class Pawn {
        private int number;
        private int direction;

        public Pawn(int number, int direction) {
            this.number = number;
            this.direction = direction;
        }
    }

    private static class Position {
        private int y, x;

        public Position(int y, int x) {
            this.y = y;
            this.x = x;
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

    private static void sol() {
        for (int turn = 1; turn <= 1000; turn++) {
            int state = movePawn();

            if (state == FOUND_ANSWER) {
                answer = turn;
                return;
            } else if (state == IMPOSSIBLE) {
                answer = IMPOSSIBLE;
                return;
            }
        }
        answer = IMPOSSIBLE;
    }

    private static int movePawn() {
        int movedCount = 0;

        for (int pawnNumber = 1; pawnNumber <= K; pawnNumber++) {
            Position pos = findPawnPosition(pawnNumber);

            if (pos == null) {
                continue;
            }

            movedCount++;

            Pawn bottomPawn = board[pos.y][pos.x].get(0);
            int result = movePawnStack(pos.y, pos.x, bottomPawn.direction);

            if (result == FOUND_ANSWER) {
                return FOUND_ANSWER;
            }
        }

        return movedCount == 0 ? IMPOSSIBLE : NEXT;
    }

    private static int movePawnStack(int y, int x, int direction) {
        int nextY = y + dy[direction];
        int nextX = x + dx[direction];
        int nextColor = findNextColor(nextY, nextX);

        if (nextColor != BLUE) {
            return executeMove(y, x, nextY, nextX, nextColor);
        }

        int reversedDirection = reverseDirection(direction);
        board[y][x].get(0).direction = reversedDirection;

        int nextnextY = y + dy[reversedDirection];
        int nextnextX = x + dx[reversedDirection];
        int nextnextColor = findNextColor(nextnextY, nextnextX);

        if (nextnextColor == BLUE) {
            return NEXT;
        }

        return executeMove(y, x, nextnextY, nextnextX, nextnextColor);
    }

    private static int executeMove(int y, int x, int nextY, int nextX, int color) {
        if (color == RED) {
            Collections.reverse(board[y][x]);
        }

        board[nextY][nextX].addAll(board[y][x]);
        board[y][x].clear();

        return board[nextY][nextX].size() >= 4 ? FOUND_ANSWER : NEXT;
    }

    private static Position findPawnPosition(int pawnNumber) {
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (!board[i][j].isEmpty() && board[i][j].get(0).number == pawnNumber) {
                    return new Position(i, j);
                }
            }
        }
        return null;
    }

    private static int reverseDirection(int direction) {
        if (direction >= 3) {
            return direction == UP ? DOWN : UP;
        }

        return direction == RIGHT ? LEFT : RIGHT;
    }

    private static int findNextColor(int y, int x) {
        if (y < 1 || x < 1 || y > N || x > N) {
            return BLUE;
        }
        return color[y][x];
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