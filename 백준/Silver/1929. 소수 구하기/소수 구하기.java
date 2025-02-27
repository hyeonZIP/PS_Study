import java.util.*;
import java.io.*;

public class Main {

    public static boolean[] prime;
    public static int M,N;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        prime = new boolean[N+1];

        makePrime();

        for (int i = M; i<prime.length; i++){
            if (!prime[i]){
                System.out.println(i);
            }
        }

    }

    private static void makePrime(){


        if (N < 2){
            return;
        }
        prime[0] = prime[1] = true;

        for (int i=2; i<=Math.sqrt(N); i++){
            if (prime[i]){
                continue;
            }

            for (int j = i*i; j<prime.length; j+=i){
                prime[j] = true;
            }
        }

    }
}
