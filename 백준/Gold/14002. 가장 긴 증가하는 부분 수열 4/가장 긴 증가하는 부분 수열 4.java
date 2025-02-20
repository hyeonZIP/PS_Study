import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int[] arr, lis, record;
    static int lisPointer = 1;
    static int recordPointer = 1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        lis = new int[N+1];
        arr = new int[N+1];
        record = new int[N+1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=1; i<=N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        lis[1] = arr[1];
        record[1] = 1;

        for (int i = 2; i <= N ; i++) {

            if(lis[lisPointer] < arr[i]){
                lisPointer++;
                lis[lisPointer] = arr[i];
                recordPointer++;
                record[recordPointer] = lisPointer;
            }
            else{
                binarySearch(arr[i]);
            }
        }
        System.out.println(lisPointer);
        int index = lisPointer;

        int[] answer = new int[index];

        for(int i = N; i>=1; i--){
            if(record[i] == index){
                answer[index-1] = arr[i];
                index--;
            }
        }
        Arrays.stream(answer).sorted().forEach(o-> System.out.print(o + " "));

    }//main

    private static void binarySearch(int target){
        int start = 1;
        int end = lisPointer;
        int mid;

        while(start<=end){

            mid = (start+end)/2;

            if(lis[mid] == target){
                lis[mid] = target;
                recordPointer++;
                record[recordPointer] = mid;
                return;
            } else if (lis[mid] < target) {
                start = mid+1;
            } else if (lis[mid] > target) {
                end = mid-1;
            }
        }

        lis[start] = target;
        recordPointer++;
        record[recordPointer] = start;
    }
}
