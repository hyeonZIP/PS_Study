import java.util.*;
import java.io.*;

/*
정수 N이 주어지면 1부터 N까지 존재하는 소수의 배열 생성
N은 최대 4,000,000
해당 배열에서 투포인터 알고리즘 사용
 */

public class Main {

    static boolean[] isPrime;
    static List<Integer> primeList;
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        primeList = new ArrayList<>();
        isPrime = new boolean[N + 1];
        makePrime();

        int start = 0;
        int end = 0;
        int answer = 0;
        int sum = 0;

        while(start<=end && end < primeList.size()){
            if (sum<N){
                sum+= primeList.get(end++);
            }else{
                if (sum == N){
                    answer++;
                }
                sum -= primeList.get(start++);
            }
        }

        System.out.println(answer);
    }

    private static void makePrime() {
        isPrime[0] = isPrime[1] = true;

        for (int i = 2; i <= Math.sqrt(N); i++) {
            if (isPrime[i]) {
                continue;
            }

            for (int j = i * i; j < isPrime.length; j += i) {
                isPrime[j] = true;
            }
        }

        for (int i=2; i< isPrime.length; i++){
            if (!isPrime[i]){
                primeList.add(i);
            }
        }

        primeList.add(0);
    }
}
