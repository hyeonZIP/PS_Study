import java.util.*;
import java.io.*;

public class Main {

    static int[] lis, arr;
    static int lisPointer = 1;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());

        arr = new int[N+1];
        lis = new int[N+1];

        for(int i=1; i<=N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        lis[lisPointer] = arr[1];

        for(int i=2; i<=N; i++){
            if(lis[lisPointer] < arr[i]){
                lisPointer ++;
                lis[lisPointer] = arr[i];
            }
            else{
                binarySearch(arr[i]);
            }
        }

        System.out.println(lisPointer);

    }//main

    private static void binarySearch(int target){
        int start = 0;
        int end = lisPointer;
        int mid;

        while(start<=end){
            mid = (start+end)/2;

            if(lis[mid] == target){
                lis[mid] = target;
                return;
            }
            else if(lis[mid] > target){
                end = mid -1;
            }
            else if(lis[mid] < target){
                start = mid + 1;
            }
        }

        lis[start] = target;
    }
}
