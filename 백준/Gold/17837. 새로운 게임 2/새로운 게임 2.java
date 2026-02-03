import java.io.*;
import java.util.*;

public class Main {
    private static final int WHITE = 0;
    private static final int RED = 1;
    private static final int BLUE = 2;
    private static final int[] dy = new int[] { 0, 0, -1, 1 };
    private static final int[] dx = new int[] { 1, -1, 0, 0 };

    private static class Board {
        private List<Chess> chessPieces;

        public Board() {
            this.chessPieces = new ArrayList<>();
        }

        public void add(Chess chessPiece) {
            chessPieces.add(chessPiece);
        }

        public void addAll(List<Chess> chess) {
            chessPieces.addAll(chess);
        }

        public void addAllReverse(List<Chess> chess) {
            Collections.reverse(chess);

            chessPieces.addAll(chess);
        }

        public boolean isStackedEndCondition() {
            return chessPieces.size() >= 4;
        }

        public boolean containsPiece(int id) {
            for (Chess chess : chessPieces) {
                if (chess.id == id) {
                    return true;
                }
            }
            return false;
        }

        public List<Chess> getChessPieceesAbove(int id) {

            for (int i = 0; i < chessPieces.size(); i++) {
                Chess chess = chessPieces.get(i);
                if (chess.id == id) {
                    List<Chess> result = chessPieces.subList(i, chessPieces.size());
                    chessPieces = new ArrayList<>(chessPieces.subList(0, i));

                    return result;
                }
            }

            return new ArrayList<>();
        }
    }

    private static class Chess {
        int id, direction;

        public Chess(int id, int direction) {
            this.id = id;
            this.direction = direction - 1;
        }
    }

    private static int answer;
    private static int N, K;
    private static int[][] color;
    private static Board[][] board;

    public static void main(String[] args) throws IOException {
        init();
        sol();
        print();
    }

    private static void sol() {
        int count = 0;

        while (true) {
            count++;
            for (int id = 0; id < K; id++) {
                moveChess(id);

                if (isEndCondition()) {
                    answer = count;
                    return;
                }
            }

            if (count > 1000) {
                answer = -1;
                return;
            }
        }
    }

    private static void moveChess(int id) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (board[i][j].containsPiece(id)) {
                    List<Chess> chess = board[i][j].getChessPieceesAbove(id);

                    int direction = chess.get(0).direction;

                    int nextY = i + dy[direction];
                    int nextX = j + dx[direction];

                    if (isOutOfRange(nextY, nextX) || color[nextY][nextX] == BLUE) {
                        int nextDirection = direction % 2 == 0 ? direction + 1 : direction - 1;
                        chess.get(0).direction = nextDirection;

                        nextY = i + dy[nextDirection];
                        nextX = j + dx[nextDirection];

                        if (isOutOfRange(nextY, nextX) || color[nextY][nextX] == BLUE) {
                            board[i][j].addAll(chess);
                        } else if (color[nextY][nextX] == WHITE) {
                            board[nextY][nextX].addAll(chess);
                        } else if (color[nextY][nextX] == RED) {
                            board[nextY][nextX].addAllReverse(chess);
                        }

                        return;
                    } else if (color[nextY][nextX] == WHITE) {
                        board[nextY][nextX].addAll(chess);

                        return;
                    } else if (color[nextY][nextX] == RED) {
                        board[nextY][nextX].addAllReverse(chess);

                        return;
                    }
                }
            }
        }
    }

    private static boolean isOutOfRange(int y, int x) {
        return y < 0 || x < 0 || y >= N || x >= N;
    }

    private static boolean isEndCondition() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (board[i][j].isStackedEndCondition()) {
                    return true;
                }
            }
        }
        return false;
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());// 체스판의 크기
        K = Integer.parseInt(st.nextToken());// 말의 개수

        color = new int[N][N];
        board = new Board[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                color[i][j] = Integer.parseInt(st.nextToken());
                board[i][j] = new Board();
            }
        }

        for (int id = 0; id < K; id++) {
            st = new StringTokenizer(br.readLine());

            int y = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            int direction = Integer.parseInt(st.nextToken());

            board[y - 1][x - 1].add(new Chess(id, direction));
        }
    }

    private static void print() throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(String.valueOf(answer));
        bw.close();
    }
}