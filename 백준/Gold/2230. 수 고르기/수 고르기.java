import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
1 2 3 4 5
M == 3   M 이상인 경우의 쌍 > 가장 작은 차이 출력

입력 1 5 3 > 정렬 1 3 5 > 1 3 , 1 5 탐색 > 가장 먼저 일치하는 쌍이 1로 시작하는 가장 작은 쌍
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] arr = new int[N];

        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(arr);

        int start = 0;
        int end = 1;
        int minLen = Integer.MAX_VALUE;

        while(start<N){

            int number = arr[end]-arr[start];

            if (number >= M){
                minLen = Math.min(minLen,number);
                start++;
            }else{
                end++;
                if (end == N){
                    break;
                }
            }
        }

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        bw.write(minLen+"");
        bw.flush();
        bw.close();
        br.close();
    }
}
