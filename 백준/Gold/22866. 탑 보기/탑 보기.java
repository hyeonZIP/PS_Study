import java.io.*;
import java.util.*;

public class Main {
    static StringBuilder answer = new StringBuilder();
    static int N;
    static int[] nearIdx, count, buildingHeights;

    public static void main(String[] args) throws IOException {
        init();
        sol();
        print();
    }

    static void sol() {
        Deque<Integer> s = new ArrayDeque<>();
        nearIdx = new int[N + 1];
        count = new int[N + 1];

        Arrays.fill(nearIdx, -1);

        for (int i = 1; i <= N; i++) {
            while (!s.isEmpty() && buildingHeights[s.peek()] <= buildingHeights[i]) {
                s.pop();
            }

            count[i] += s.size();

            if (!s.isEmpty()) {
                nearIdx[i] = s.peek();
            }

            s.push(i);
        }

        s.clear();

        for (int i = N; i >= 1; i--) {
            while (!s.isEmpty() && buildingHeights[s.peek()] <= buildingHeights[i]) {
                s.pop();
            }

            count[i] += s.size();

            if (!s.isEmpty()) {
                int rightNearIdx = s.peek();

                if (nearIdx[i] == -1 || Math.abs(rightNearIdx - i) < Math.abs(i - nearIdx[i])) {
                    nearIdx[i] = rightNearIdx;
                }
            }

            s.push(i);
        }

        for (int i = 1; i <= N; i++) {
            int cnt = count[i];

            answer.append(cnt);

            if (cnt >= 1) {
                answer.append(" ").append(nearIdx[i]);
            }

            answer.append("\n");
        }
    }

    static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        buildingHeights = new int[N + 1];

        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 1; i <= N; i++) {
            buildingHeights[i] = Integer.parseInt(st.nextToken());
        }
    }

    static void print() throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(String.valueOf(answer));
        bw.close();
    }
}