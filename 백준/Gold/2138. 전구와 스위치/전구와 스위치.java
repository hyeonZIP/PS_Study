import java.io.*;
import java.util.*;

public class Main {
    private static int N;
    private static int[] target;
    private static int[] current1;
    private static int[] current2;
    private static int answer;

    public static void main(String[] args) throws IOException {
        init();

        sol();

        answer();
    }

    private static void sol() {
        int current1Value = 0;
        int current2Value = 1;

        for (int i = 1; i < N; i++) {
            if (current1[i - 1] != target[i - 1]) {
                convert(current1, i);
                current1Value++;
            }
            if (current2[i - 1] != target[i - 1]) {
                convert(current2, i);
                current2Value++;
            }

            if (Arrays.equals(current1, target)) {
                answer = current1Value;
                return;
            }
            if (Arrays.equals(current2, target)) {
                answer = current2Value;
                return;
            }
        }

        answer = -1;
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        int[] current = new int[N];
        target = new int[N];

        current = Arrays.stream(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
        target = Arrays.stream(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();

        current1 = Arrays.copyOf(current, N);
        current2 = Arrays.copyOf(current, N);

        convert(current2, 0);
    }

    private static void answer() throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(String.valueOf(answer));
        bw.close();
    }

    private static void convert(int[] current, int index) {
        if (index == 0) {
            current[index] = current[index] == 0 ? 1 : 0;
            current[index + 1] = current[index + 1] == 0 ? 1 : 0;
            return;
        }

        if (index == N - 1) {
            current[index] = current[index] == 0 ? 1 : 0;
            current[index - 1] = current[index - 1] == 0 ? 1 : 0;
            return;
        }

        current[index - 1] = current[index - 1] == 0 ? 1 : 0;
        current[index] = current[index] == 0 ? 1 : 0;
        current[index + 1] = current[index + 1] == 0 ? 1 : 0;
    }
}