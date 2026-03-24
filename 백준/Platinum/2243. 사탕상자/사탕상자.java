import java.io.*;
import java.util.*;

public class Main {
    static final int MAX_VALUE = 1_000_000;

    static StringBuilder answer = new StringBuilder();
    static int[] segmentTree = new int[MAX_VALUE * 4];// 사탕의 순위

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
        
        int n = Integer.parseInt(br.readLine());

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());

            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            if (A == 1) {
                // 사탕 꺼내기
                int rank = B;
                int flavor = getSegment(1, 1, MAX_VALUE, rank);
                updateSegment(1, 1, MAX_VALUE, flavor, -1);
                answer.append(flavor).append("\n");
                continue;
            }
            // 사탕 넣기
            int flavor = B;
            int count = Integer.parseInt(st.nextToken());

            updateSegment(1, 1, MAX_VALUE, flavor, count);
        }
    }

    static int getSegment(int node, int left, int right, int index) {
        if (left == right) {
            return left;
        }

        int mid = (left + right) / 2;

        if (index <= segmentTree[node * 2]) {
            return getSegment(node * 2, left, mid, index);
        } else {
            return getSegment(node * 2 + 1, mid + 1, right, index - segmentTree[node * 2]);
        }
    }

    static void updateSegment(int node, int left, int right, int index, int value) {
        if (index < left || index > right) {
            return;
        }

        if (left == right) {
            segmentTree[node] += value;
            return;
        }

        int mid = (left + right) / 2;

        updateSegment(node * 2, left, mid, index, value);
        updateSegment(node * 2 + 1, mid + 1, right, index, value);

        segmentTree[node] = segmentTree[node * 2] + segmentTree[node * 2 + 1];
    }
}
