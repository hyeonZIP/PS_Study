import java.io.*;
import java.util.*;

public class Main {
    static int N, K;
    static String num, answer;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        init();
        sol();
        print();
    }

    static void sol() {
        Deque<Character> stack = new ArrayDeque<>();

        for (char s : num.toCharArray()) {
            if (K <= 0) {
                stack.push(s);
                continue;
            }
            if (stack.isEmpty()) {
                stack.push(s);
                continue;
            }

            while (K > 0 && !stack.isEmpty() && stack.peek() < s) {
                stack.pop();
                K--;
            }
            stack.push(s);
        }

        for (int i = 0; i < K; i++) {
            stack.pop();
        }

        StringBuilder sb = new StringBuilder();

        while (!stack.isEmpty()) {
            sb.append(stack.pollLast());
        }

        answer = String.valueOf(sb);
    }

    static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());// 숫자 자릿수
        K = Integer.parseInt(st.nextToken());// 지울 숫자 갯수

        num = br.readLine();// N자리 숫자
    }

    static void print() throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(answer);
        bw.close();
    }

}