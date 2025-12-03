import java.io.*;
import java.util.*;

public class Main {
    private static class PostOffice {
        private int x;
        private int a;

        public PostOffice(int x, int a) {
            this.x = x;
            this.a = a;
        }
    }

    private static PostOffice[] postOffices;
    private static int N;
    private static long total;
    private static int answer;

    public static void main(String[] args) throws IOException {
        init();

        sol();

        answer();
    }

    private static void sol() {
        Arrays.sort(postOffices, Comparator.comparingInt(o -> o.x));

        long current = 0l;

        long minimumDiffPosition = total % 2 == 0 ? total / 2 : (total + 1) / 2;

        for (PostOffice postOffice : postOffices) {
            current += Long.valueOf(postOffice.a);

            if (current >= minimumDiffPosition) {
                answer = postOffice.x;
                return;
            }
        }
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        postOffices = new PostOffice[N];

        for (int i = 0; i < N; i++) {
            int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            postOffices[i] = new PostOffice(input[0], input[1]);
            total += Long.valueOf(input[1]);
        }
    }

    private static void answer() throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(String.valueOf(answer));
        bw.close();
    }
}