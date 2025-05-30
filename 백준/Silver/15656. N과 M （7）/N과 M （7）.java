import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int[] arr;
    static StringBuilder sb = new StringBuilder();
    static int N,M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st;

        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());//N개의 숫자 중
        M = Integer.parseInt(st.nextToken());//M개 선택

        arr = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i=0; i<N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        permutationWithRepetition(0, new int[M]);

        System.out.print(sb);
    }

    private static void permutationWithRepetition(int depth, int[] crr){

        if (depth == M){
            for (int i : crr){
                sb.append(i).append(" ");
            }
            sb.append("\n");

            return;
        }

        for (int i=0; i<N; i++){
            crr[depth] = arr[i];
            permutationWithRepetition(depth+1,crr);
        }
    }
}
