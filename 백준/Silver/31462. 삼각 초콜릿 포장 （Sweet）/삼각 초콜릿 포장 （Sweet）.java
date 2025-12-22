import java.io.*;
import java.util.*;

public class Main {
    private static int N, answer = 1;
    private static String[][] map;

    public static void main(String[] args) throws IOException {
        init();
        sol();
        print();
    }

    private static void sol() {
        if (N == 1 || map[0][0].equals("B") || map[1][0].equals("B") || map[1][1].equals("B")) {
            answer = 0;
            return;
        } else if (N == 2) {
            return;
        }

        boolean[][] visited = new boolean[N][N];

        for (int i = 2; i < N - 1; i++) {
            int len = map[i].length;

            if (len != i + 1) {
                answer = 0;
                return;
            }

            for (int j = 0; j < len; j++) {
                if (visited[i][j]) {
                    continue;
                }

                if (map[i][j].equals("R")) {
                    if (isValidRange(i + 1, j + 1) && !visited[i + 1][j] && !visited[i + 1][j + 1]
                            && map[i + 1][j].equals("R") && map[i + 1][j + 1].equals("R")) {
                        visited[i + 1][j] = true;
                        visited[i + 1][j + 1] = true;
                    } else {
                        answer = 0;
                        return;
                    }
                } else {
                    if (isValidRange(i, j + 1) && !visited[i][j + 1] && !visited[i + 1][j + 1]
                            && map[i][j + 1].equals("B") && map[i + 1][j + 1].equals("B")) {
                        visited[i][j + 1] = true;
                        visited[i + 1][j + 1] = true;
                    } else {
                        answer = 0;
                        return;
                    }
                }
            }
        }
    }

    private static boolean isValidRange(int y, int x) {
        return y >= 0 || x >= 0 || y < N || x < map[y].length;
    }

    private static void print() throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(String.valueOf(answer));
        bw.close();
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        map = new String[N][];

        for (int i = 0; i < N; i++) {
            map[i] = br.readLine().split("");
        }
    }
}