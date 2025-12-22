import java.io.*;
import java.util.*;

public class Main {
    private static int N, answer = Integer.MAX_VALUE;
    private static int[] arr;

    public static void main(String[] args) throws IOException {
        init();
        sol();
        answer();
    }

    private static void sol() {
        int left = 0;
        int right = N - 1;

        while (left < right) {
            int current = arr[left] + arr[right];

            if (Math.abs(answer) > Math.abs(current)) {
                answer = current;
            }

            if (current > 0) {
                right--;
            } else {
                left++;
            }
        }
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    }

    private static void answer() throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(String.valueOf(answer));
        bw.close();
    }
}