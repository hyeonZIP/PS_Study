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
        int end = 0;
        int sum = arr[start];
        while (end < N) {
            if (sum>=S){
                minLen = Math.min(minLen,end-start+1);
                sum-=arr[start++];
            }else{
                end++;
                if (end < N) sum += arr[end];
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
