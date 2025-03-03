import java.util.*;
import java.io.*;

public class Main {

    static int N,d,k,c,answer;
    static int[] arr, visited;
    public static void main(String[] args) {
        init();
        tft();

        System.out.println(answer);
    }

    private static void tft(){
        int count = 0;

        for (int i=0; i<k; i++){
            if (visited[arr[i]] == 0) count++;
            visited[arr[i]]++;
        }

        for (int i=0; i<N; i++){
            if (visited[c] == 0) answer = Math.max(count+1,answer);
            else answer = Math.max(count,answer);

            visited[arr[i]]--;
            if (visited[arr[i]] == 0) count--;

            if (visited[arr[(i+k) % N]] == 0) count++;
            visited[arr[(i+k) % N]]++;
        }
    }

    private static void init(){
        try{
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());//접시 수
            d = Integer.parseInt(st.nextToken());//초밥 종류
            k = Integer.parseInt(st.nextToken());//연속 접시 수
            c = Integer.parseInt(st.nextToken());//쿠폰

            arr = new int[N];
            visited = new int[d+1];

            for (int i=0; i<N; i++){
                arr[i] = Integer.parseInt(br.readLine());
            }

        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
