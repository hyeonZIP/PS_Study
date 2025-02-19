
import java.util.*;
import java.io.*;

public class Main {

    static int[] lis;
    static int lisPointer;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw= new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());


        int[] arr = new int[N];
        lis = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        lis[0] = arr[0];


        lisPointer=0;
        for(int i=1; i<N; i++){
            if(lis[lisPointer] < arr[i]){
                lisPointer++;
                lis[lisPointer] = arr[i];
            }
            else if(lis[lisPointer] >= arr[i]){
                binarySearch(lisPointer, arr[i]);
            }
        }


        bw.write(lisPointer+1+"");
        bw.flush();
        bw.close();
        br.close();
    }//main

    private static void binarySearch(int j, int target){

        int start = 0;
        int end = j;
        int mid;

        while(start<=end){
            mid  = (start+end)/2;

            if(lis[mid] == target){
                lis[mid] = target;
                return;
            }
            else if(lis[mid] < target){
                start = mid+1;
            }
            else if(lis[mid] > target){
                end = mid-1;
            }
        }

        lis[start] = target;
    }
}
