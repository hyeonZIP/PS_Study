import java.io.*;
import java.util.*;

public class Main {
    private static char[][] board;
    private static int k, n, questionMarkIndex;
    private static char[] result;
    private static StringBuilder answer = new StringBuilder();

    public static void main(String[] args) throws IOException {
        init();
        sol();
        print();
    }

    private static void sol() {
        for (int i = 0; i < k; i++) {
            int downIndex = 2 * (result[i] - 'A');
            int upIndex = 2 * i;

            for (int j = 0; j <= questionMarkIndex; j++) {

                if (downIndex + 1 < k + k - 1) {
                    if (board[j][downIndex + 1] == '-') {
                        downIndex += 2;
                        continue;
                    }
                }

                if (downIndex - 1 >= 0) {
                    if (board[j][downIndex - 1] == '-') {
                        downIndex -= 2;
                        continue;
                    }
                }
            }

            for (int j = n - 1; j >= questionMarkIndex; j--) {

                if (upIndex + 1 < k + k - 1) {
                    if (board[j][upIndex + 1] == '-') {
                        upIndex += 2;
                        continue;
                    }
                }

                if (upIndex - 1 >= 0) {
                    if (board[j][upIndex - 1] == '-') {
                        upIndex -= 2;
                        continue;
                    }
                }
            }
            int min = Math.min(upIndex, downIndex);
            int max = Math.max(upIndex, downIndex);

            if (max - min == 2) {
                if (max + 1 < k + k - 1) {
                    if (board[questionMarkIndex][max + 1] == '?') {
                        board[questionMarkIndex][max + 1] = '*';
                    } else if (board[questionMarkIndex][max + 1] != '*') {
                        fail();
                        return;
                    }
                }
                if (min - 1 >= 0) {
                    if (board[questionMarkIndex][min - 1] == '?') {
                        board[questionMarkIndex][min - 1] = '*';
                    } else if (board[questionMarkIndex][min - 1] != '*') {
                        fail();
                        return;
                    }
                }
                if (board[questionMarkIndex][min + 1] == '?') {
                    board[questionMarkIndex][min + 1] = '-';
                } else if (board[questionMarkIndex][min + 1] != '-') {
                    fail();
                    return;
                }

            } else if (max - min == 0) {
                if (max - 1 >= 0) {
                    if (board[questionMarkIndex][max - 1] == '?') {
                        board[questionMarkIndex][max - 1] = '*';
                    } else if (board[questionMarkIndex][max - 1] != '*') {
                        fail();
                        return;
                    }

                }

                if (max + 1 < k + k - 1) {
                    if (board[questionMarkIndex][max + 1] == '?') {
                        board[questionMarkIndex][max + 1] = '*';
                    } else if (board[questionMarkIndex][max + 1] != '*') {
                        fail();
                        return;
                    }
                }
            } else {
                fail();
                return;
            }
        } // for

        for (int i = 1; i < k + k - 1; i += 2) {
            answer.append(board[questionMarkIndex][i]);
        }
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        k = Integer.parseInt(br.readLine());// ABCDE
        n = Integer.parseInt(br.readLine());// 가로줄 수

        board = new char[n][k + k - 1];

        result = br.readLine().toCharArray();

        for (int i = 0; i < n; i++) {
            char[] ladder = br.readLine().toCharArray();
            if (ladder[0] == '?') {
                questionMarkIndex = i;
            }
            board[i][0] = '|';
            for (int j = 1, index = 0; j < k + k - 1; j += 2, index++) {
                board[i][j] = ladder[index];
                board[i][j + 1] = '|';
            }
        }

    }

    private static void fail() {
        for (int i = 0; i < k - 1; i++) {
            answer.append('x');
        }
    }

    private static void print() throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(answer.toString());
        bw.close();
    }
}