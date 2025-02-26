import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());

        int[] arr = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int minLen = Integer.MAX_VALUE;
        int start = 0;
        int end = 1;
        int sum = arr[start];
        while (start < N) {
            if (sum>=S){
                minLen = Math.min(minLen,end-start);
                sum-=arr[start];
                start++;
            }else{
                if (end == N){
                    break;
                }
                sum += arr[end];
                end++;
            }
        }

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        if (minLen == Integer.MAX_VALUE){
            bw.write("0");
        }
        else{
            bw.write(minLen+"");
        }

        bw.flush();
        bw.close();
        br.close();
    }
}
