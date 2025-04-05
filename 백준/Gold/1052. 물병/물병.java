import java.util.*;
import java.io.*;

public class Main {

    static int N, K;

    public static void main(String[] args) throws IOException {

        init();

        int answer = 0;

        /**
         * 2진수 ... 8 4 2 1
         * 따라서 13의 경우 1101
         * 만약 K가 3일 경우 1로 표시된 부분인 8과 4, 1로 3개를 옮길 수 있다
         * 만약 K가 2일 경우 3개의 물병을 옮길 수 없기때문에 물병을 구매하는 과정인  answer += N & (-N) 해서  필요한 물병을 구매한다.
         * N이 28일 경우 > 11100 > 첫 동작에서 물병 4개를 구매한다.
         */
        while(Integer.bitCount(N) > K){
            answer += N & (-N);
            N += N & (-N);
        }

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        bw.write(answer+"");
        bw.close();

    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        br.close();
    }
}
