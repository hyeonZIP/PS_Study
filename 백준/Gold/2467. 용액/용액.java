import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        arr = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int[] answer = binarySearch();

        System.out.println(arr[answer[0]] + " " + arr[answer[1]]);

    }

    private static int[] binarySearch(){

        int start = 0;
        int end = N-1;
        int min = Integer.MAX_VALUE;
        int answerStart = 0;
        int answerEnd = 0;
        while(start<end){
            int mix = arr[start]+arr[end];

            if(Math.abs(mix) < min){
                min = Math.abs(mix);
                answerStart = start;
                answerEnd = end;
            }

            if(mix == 0) return new int[]{answerStart,answerEnd};

            if(mix < 0) start++;
            else end--;

        }
        return new int[]{answerStart,answerEnd};
    }
}
