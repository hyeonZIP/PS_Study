import java.io.*;
import java.util.*;

public class Main {
    private static int N;
    private static int answer = Integer.MAX_VALUE;
    private static String[] list;

    public static void main(String[] args) throws IOException {
        init();

        sol();

        print();
    }

    private static void sol() {
        // 백트래킹
        dfs("", new boolean[N], 0);
    }

    private static String deleteDuple(String leftString, String rightString) {
        int maxSubString = Math.min(leftString.length(), rightString.length());

        for (int j = maxSubString; j > 0; j--) {
            String rightSubString = rightString.substring(0, j);
            String leftSubString = leftString.substring(leftString.length() - j, leftString.length());

            if (leftSubString.equals(rightSubString)) {
                return leftString.substring(0, leftString.length() - j) + rightString;
            }
        }
        return leftString + rightString;
    }

    private static void dfs(String leftString, boolean[] visited, int depth) {
        if (leftString.length() >= answer) {
            return;
        }
        if (depth == N) {
            answer = Math.min(answer, leftString.length());
            return;
        }

        for (int i = 0; i < N; i++) {
            if (visited[i]) {
                continue;
            }
            visited[i] = true;

            if (leftString.equals("")) {
                dfs(list[i], visited, depth + 1);
            } else {
                dfs(deleteDuple(leftString, list[i]), visited, depth + 1);
            }

            visited[i] = false;
        }
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        list = new String[N];

        for (int i = 0; i < N; i++) {
            list[i] = br.readLine();
        }
    }

    private static void print() throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        bw.write(String.valueOf(answer));
        bw.close();
    }
}
