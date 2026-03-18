import java.io.*;
import java.util.*;

public class Main {
    static final int[] plusDy = new int[] { 1, 0 };
    static final int[] plusDx = new int[] { 0, 1 };
    static final int[] minusDy = new int[] { -1, 0 };
    static final int[] minusDx = new int[] { 0, -1 };
    static final int[] dy = new int[] { 0, 0, 1, -1 };
    static final int[] dx = new int[] { 1, -1, 0, 0 };
    static StringBuilder answer = new StringBuilder();
    static int N, M, K, oneCount;
    static int[] yCount;
    static int[] xCount;
    static int[][] map;

    public static void main(String[] args) throws Exception {
        init();
        sol();
        print();
    }

    static void sol() {
        if (oneCount == 2 * K) {
            // K의 2배만큼 1이 존재할 경우, 겹치지 않는다는 뜻
            answer.append("0");
            return;
        }
        // yCount = 0311
        // xCount = 131 인 경우
        // -> 2,2 가 교차점

        // yCount = 21
        // xCount = 12 인 경우
        // -> 1,2 가 교차점

        // yCount = 030
        // xCount = 111
        // -> 모두 겹침

        // yCount = 11111
        // xCount = 5
        // K = 3 -> 1,3 겹침
        // k = 4 -> 1,2 1,3 1,4 겹침

        // 각 축에 K값이 존재하면 하나만 겹침
        // 한 축에만 K값이 존재하면 완전 겹침

        int rowWithK = -1, colWithK = -1;
        int firstRow = -1, lastRow = -1;
        int firstCol = -1, lastCol = -1;

        for (int i = 1; i <= N; i++) {
            if (xCount[i] > 0) {
                if (firstRow == -1)
                    firstRow = i;
                lastRow = i;
            }
            if (xCount[i] >= K)
                rowWithK = i;
        }

        for (int j = 1; j <= M; j++) {
            if (yCount[j] > 0) {
                if (firstCol == -1)
                    firstCol = j;
                lastCol = j;
            }
            if (yCount[j] >= K)
                colWithK = j;
        }

        // 교차점이 존재하는 경우
        if (rowWithK != -1 && colWithK != -1) {
            answer.append("1\n").append(rowWithK).append(" ").append(colWithK);
            return;
        }

        int duplicateCount = 2 * K - oneCount;
        answer.append(duplicateCount).append("\n");

        // 둘 다 같은 열에 있는 경우
        if (colWithK != -1) {
            int startRow = firstRow + (K - duplicateCount);
            int endRow = lastRow - (K - duplicateCount);
            for (int i = startRow; i <= endRow; i++) {
                answer.append(i).append(" ").append(colWithK).append("\n");
            }
        }
        // 둘 다 같은 행에 있는 경우
        else {
            int startCol = firstCol + (K - duplicateCount);
            int endCol = lastCol - (K - duplicateCount);
            for (int j = startCol; j <= endCol; j++) {
                answer.append(rowWithK).append(" ").append(j).append("\n");
            }
        }
    }

    static boolean isOutOfRange(int y, int x) {
        return y <= 0 || x <= 0 || y > N || x > M;
    }

    static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[N + 1][M + 1];
        yCount = new int[M + 1];
        xCount = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= M; j++) {
                int value = Integer.parseInt(st.nextToken());

                if (value == 1) {
                    oneCount++;
                    yCount[j]++;
                    xCount[i]++;
                }

                map[i][j] = value;
            }
        }
    }

    static void print() throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(String.valueOf(answer));
        bw.close();
    }

}
