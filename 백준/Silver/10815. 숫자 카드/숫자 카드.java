import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[] nrr;
    static int M;
    static int[] mrr;

    public static void main(String[] args) throws IOException {
        init();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M; i++) {
            sb.append(binarySearch(mrr[i])).append(" ");
        }

        System.out.println(sb);
    }

    private static int binarySearch(int param) {

        int start = 0;
        int end = N - 1;

        while (start <= end) {
            int mid = (start + end) / 2;

            if (nrr[mid] == param) {
                return 1;
            } else if (nrr[mid] > param) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return 0;
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        nrr = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            nrr[i] = Integer.parseInt(st.nextToken());
        }

        M = Integer.parseInt(br.readLine());
        mrr = new int[M];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            mrr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(nrr);
    }
}
