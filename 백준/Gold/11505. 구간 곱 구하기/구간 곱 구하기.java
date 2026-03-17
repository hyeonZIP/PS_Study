import java.io.*;
import java.util.*;

public class Main {
    static final int MOD = 1_000_000_007;
    static StringBuilder answer = new StringBuilder();
    static long[] segmentTree;
    static int[] arr;
    static int N, M, K;

    public static void main(String[] args) throws IOException {
        init();
        // sol();
        print();
    }

    static void print() throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(String.valueOf(answer));
        bw.close();
    }

    static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        arr = new int[N];
        segmentTree = new long[N * 4];

        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(br.readLine());
            arr[i] = num;
        }

        initSegment(1, 0, N - 1);
        // System.out.println(Arrays.toString(segmentTree));
        // System.out.println("초기");

        for (int i = 0; i < M + K; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());// 1이면 b번째 수를 c로 변경 / 2이면 b부터 c까지의 곱 출력
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            if (a == 1) {
                updateSegment(1, 0, N - 1, b - 1, c);
                // System.out.println(Arrays.toString(segmentTree));
                // System.out.println("변경후");
            } else {
                answer.append(printSegment(1, 0, N - 1, b - 1, c - 1)).append("\n");
            }
        }
    }

    static void updateSegment(int node, int left, int right, int index, int value) {
        if (index < left || index > right) {
            // 해당하지 않는 범위면 리턴
            return;
        }

        if (left == right) {
            // 리프노드라면
            segmentTree[node] = value;
            return;
        }

        int mid = (left + right) / 2;

        updateSegment(node * 2, left, mid, index, value);
        updateSegment(node * 2 + 1, mid + 1, right, index, value);

        segmentTree[node] = (segmentTree[node * 2] * segmentTree[node * 2 + 1]) % MOD;
    }

    static long printSegment(int node, int left, int right, int leftRange, int rightRange) {
        if (leftRange > right || rightRange < left) {
            // 범위 밖이면
            // System.out.println("0 반환");
            return 1;
        }

        if (leftRange <= left && rightRange >= right) {
            // 범위 안이면
            // System.out.println("반환 값 : " + segmentTree[node]);
            return segmentTree[node];
        }

        int mid = (left + right) / 2;

        return (printSegment(node * 2, left, mid, leftRange, rightRange)
                * printSegment(node * 2 + 1, mid + 1, right, leftRange, rightRange)) % MOD;

    }

    static void initSegment(int node, int left, int right) {
        if (left == right) {
            segmentTree[node] = arr[left];
            return;
        }

        int mid = (left + right) / 2;

        initSegment(node * 2, left, mid);
        initSegment(node * 2 + 1, mid + 1, right);

        segmentTree[node] = (segmentTree[node * 2] * segmentTree[node * 2 + 1]) % MOD;
    }
}
