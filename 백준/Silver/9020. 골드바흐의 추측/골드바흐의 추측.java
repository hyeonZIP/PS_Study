import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        for (int testcase = 0; testcase < T; testcase++) {
            int n = Integer.parseInt(br.readLine());

            boolean[] isPrime = new boolean[n + 1];

            isPrime[0] = isPrime[1] = true;

            for (int i = 2; i <= Math.sqrt(n); i++) {
                if (isPrime[i]) {
                    continue;
                }
                for (int j = i * i; j < isPrime.length; j += i) {
                    isPrime[j] = true;
                }
            }

            int i = n/2;
            int j = n/2;
            while(true){
                if (!isPrime[i] && !isPrime[j]){
                    sb.append(i).append(" ").append(j).append("\n");
                    break;
                }
                i--;
                j++;
            }

        }

        bw.write(sb + "");
        bw.flush();
        bw.close();
        br.close();
    }
}
