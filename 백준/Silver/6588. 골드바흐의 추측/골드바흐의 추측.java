import java.io.*;
import java.util.*;

public class Main {

    static int n;
    static boolean[] isPrime;

    public static void main(String[] args) throws IOException{
        BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        isPrime = new boolean[1000001];
        findPrimeNumber();

        while(true){
            n = Integer.parseInt(br.readLine());

            if (n == 0){
                break;
            }


            int[] result = new int[2];
            for (int i=1; i<=n; i++){
                if (!isPrime[i]&&!isPrime[n-i]){
                    result[0] = i;
                    result[1] = n-i;
                    sb.append(n).append(" = ").append(result[0]).append(" + ").append(result[1]).append("\n");
                    break;
                }
            }

            if (result[0] == 0){
                sb.append("Goldbach's conjecture is wrong.");
            }
        }//while

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        bw.write(sb+"");
        bw.flush();
        bw.close();
        br.close();
    }

    private static void findPrimeNumber(){
        isPrime[0] = isPrime[1] = true;

        for (int i=2; i<=Math.sqrt(1000000); i++){
            if (isPrime[i]){
                continue;
            }
            for (int j=i*i; j< isPrime.length; j+=i){
                isPrime[j] = true;
            }
        }
    }
}
