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
        // AABB BBAA
        // AA BBAA

        // VIRUS RUST STAND
        // VI RU STAND

        // 백트래킹

        dfs(new ArrayList<>(), new boolean[N]);
    }

    private static void deleteDuple(List<String> strings) {
        // System.out.println(strings);

        String leftString = strings.get(0);

        for (int i = 1; i < strings.size(); i++) {
            boolean flag = false;

            String rightString = strings.get(i);

            int maxSubString = Math.min(leftString.length(), rightString.length());

            for (int j = maxSubString; j > 0; j--) {
                String rightSubString = rightString.substring(0, j);
                String leftSubString = leftString.substring(leftString.length() - j, leftString.length());

                // System.out.println("rightString : " + rightSubString);
                // System.out.println("leftString : " + leftSubString);

                if (leftSubString.equals(rightSubString)) {
                    leftString = leftString.substring(0, leftString.length() - j) + rightString;
                    // System.out.println("중간 업데이트 : " + leftString);
                    flag = true;
                    break;
                }
            }

            if (!flag) {
                leftString += rightString;
            }
        }
        // System.out.println("final leftString : " + leftString);
        // System.out.println();
        answer = Math.min(answer, leftString.length());
    }

    private static void dfs(List<String> strings, boolean[] visited) {
        if (strings.size() == N) {
            deleteDuple(strings);
        }

        for (int i = 0; i < N; i++) {
            if (visited[i]) {
                continue;
            }

            strings.add(list[i]);
            visited[i] = true;

            dfs(strings, visited);

            strings.remove(strings.indexOf(list[i]));
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
