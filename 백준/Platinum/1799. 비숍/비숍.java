import java.io.*;
import java.util.*;

public class Main {
    private static final int PLACEABLE = 1;
    private static boolean[] topLeftCross;
    private static boolean[] topRightCross;
    private static int answer, white, black;
    private static int chessBoardSize;
    private static int[][] chessBoard;

    public static void main(String[] args) throws IOException {
        init();
        sol();
        print();
    }

    private static void sol() {
        dfsWhiteTile(0, 0, 0);
        dfsBlackTile(0, 1, 0);

        answer = white + black;
    }

    private static void dfsWhiteTile(int y, int x, int bishopCount) {
        if (y >= chessBoardSize) {
            white = Math.max(white, bishopCount);
            return;
        }

        if (x >= chessBoardSize) {
            dfsWhiteTile(y + 1, (y + 1) % 2 == 0 ? 0 : 1, bishopCount);
            return;
        }

        if (chessBoard[y][x] == PLACEABLE && !topLeftCross[y + x] && !topRightCross[y - x + chessBoardSize - 1]) {
            topLeftCross[y + x] = true;
            topRightCross[y - x + chessBoardSize - 1] = true;

            dfsWhiteTile(y, x + 2, bishopCount + 1);

            topLeftCross[y + x] = false;
            topRightCross[y - x + chessBoardSize - 1] = false;
        }

        dfsWhiteTile(y, x + 2, bishopCount);
    }

    private static void dfsBlackTile(int y, int x, int bishopCount) {
        if (y >= chessBoardSize) {
            black = Math.max(black, bishopCount);
            return;
        }

        if (x >= chessBoardSize) {
            dfsBlackTile(y + 1, (y + 1) % 2 == 0 ? 1 : 0, bishopCount);
            return;
        }

        if (chessBoard[y][x] == PLACEABLE && !topLeftCross[y + x] && !topRightCross[y - x + chessBoardSize - 1]) {
            topLeftCross[y + x] = true;
            topRightCross[y - x + chessBoardSize - 1] = true;

            dfsBlackTile(y, x + 2, bishopCount + 1);

            topLeftCross[y + x] = false;
            topRightCross[y - x + chessBoardSize - 1] = false;
        }

        dfsBlackTile(y, x + 2, bishopCount);
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        chessBoardSize = Integer.parseInt(br.readLine());

        chessBoard = new int[chessBoardSize][chessBoardSize];

        StringTokenizer st;

        for (int i = 0; i < chessBoardSize; i++) {

            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < chessBoardSize; j++) {
                chessBoard[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        topLeftCross = new boolean[chessBoardSize * 2];
        topRightCross = new boolean[chessBoardSize * 2];
    }

    private static void print() throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(String.valueOf(answer));
        bw.close();
    }
}
