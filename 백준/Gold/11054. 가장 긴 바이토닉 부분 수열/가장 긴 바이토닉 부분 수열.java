import java.util.*;
import java.io.*;


public class Main {

    static int[] biggerDp, smallerDp,arr;
    static int N, answer;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());

        arr = new int[N+1];
        biggerDp = new int[N+1];
        smallerDp = new int[N+1];
        for(int i=1; i<=N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.fill(biggerDp,1);
        Arrays.fill(smallerDp,1);

        biggerNumber();
//        Arrays.sort(biggerDp);
        smallerNumber();
//        Arrays.sort(smallerDp);

//        for(int i : biggerDp){
//            System.out.print(i + " ");
//        }
//        System.out.println();
//        for (int j : smallerDp){
//            System.out.print(j + " ");
//        }
//        System.out.println();

        for(int i=1; i<=N; i++){
//            System.out.println(" i : " + i);
//            System.out.println(biggerDp[i-1] + " + " + smallerDp[i]);
            answer = Math.max(answer, biggerDp[i]+smallerDp[i]-1);
        }

        bw.write(answer+"");
        bw.flush();
        bw.close();
        br.close();
    }//main

    private static void biggerNumber(){
        for(int i=2; i<=N; i++){
            for(int j=1; j<i; j++){
                if(arr[i] > arr[j]){
                    biggerDp[i] = Math.max(biggerDp[i], biggerDp[j]+1);
                }
            }
        }
    }

    private static void smallerNumber(){

        for(int i = N-1; i>=1; i--){
            for(int j = N; j>i; j--){
                if(arr[i]>arr[j]){
                    smallerDp[i] = Math.max(smallerDp[i],smallerDp[j]+1);
                }
            }
        }
    }
}
