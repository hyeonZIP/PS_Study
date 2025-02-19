
import java.util.*;
import java.io.*;

public class Main {

    static int[] arrA;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        arrA = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arrA[i] = Integer.parseInt(st.nextToken());
        }

        int M = Integer.parseInt(br.readLine());
        int[] arrM = new int[M];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            arrM[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arrA);//탐색되는 수열 정렬

        for (int i = 0; i < M; i++) {

            if (binarySearch(arrM[i])) {
                sb.append("1").append("\n");
            }
            else{
                sb.append("0").append("\n");
            }
        }

        bw.write(sb+"");
        bw.flush();
        bw.close();
        br.close();

    }

    private static boolean binarySearch(int target) {

        int start = 0;
        int end = arrA.length-1;
        int mid;

        while(start<=end){
            mid = (start+end)/2;

            if(arrA[mid] == target) return true;
            else if(arrA[mid] < target){
                start = mid + 1;
            }
            else if(arrA[mid] > target){
                end = mid-1;
            }
        }
        return false;
    }
}
