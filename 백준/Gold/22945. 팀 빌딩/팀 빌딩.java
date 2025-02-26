import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int[] arr = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int len = arr.length;
        int startPointer = 0;
        int endPointer = len-1;

        int maxTeamStatus = 0;


        while(startPointer <= endPointer){
            maxTeamStatus = Math.max(maxTeamStatus, Math.min(arr[startPointer],arr[endPointer])*(endPointer-startPointer-1));

            if (arr[startPointer]<arr[endPointer]){
                startPointer++;
            }else{
                endPointer--;
            }
        }

//        for (int i = 0; i < len; i++) {
//            for (int j = i + 2; j < len; j++) {
//                maxTeamStatus = Math.max(maxTeamStatus, Math.min(arr[i], arr[j]) * (j - i - 1));
//            }
//        }

        System.out.println(maxTeamStatus);
    }
}
