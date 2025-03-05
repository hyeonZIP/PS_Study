import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws  IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[] bitCount = new int[2];

        int[] arr= new int[N];
        int[] omr = new int[M];

        int[] zeroStart = new int[N];
        int[] oneStart = new int[N];
        st= new StringTokenizer(br.readLine());
        for (int i=0; i<N; i++){
            int bit = Integer.parseInt(st.nextToken());
            arr[i] = bit;
            bitCount[bit]++;
        }

        st = new StringTokenizer(br.readLine());
        for (int i=0; i<M; i++){
            omr[i] = Integer.parseInt(st.nextToken());
        }

        int index = 0;
        for (int i=0; i<M; i++){
            for (int j = 0; j<omr[i]; j++){
                zeroStart[index] = i%2;
                oneStart[index] = 1-i%2;
                index++;
            }
        }

        int zeroCount = 0;
        boolean zeroFlag = false;
        int[] arrClone = arr.clone();
        for (int i=0; i<N; i++){
            if (arrClone[i]!=zeroStart[i]){
                zeroFlag = true;
                for (int j=i+1; j<N; j++){
                    if (arrClone[j]==zeroStart[i]){
                        zeroCount+=j-i;
                        int temp = arrClone[j];
                        arrClone[j] = arrClone[i];
                        arrClone[i] = temp;
                        zeroFlag = false;
                        break;
                    }
                }
            }
        }
        if (zeroFlag) zeroCount = Integer.MAX_VALUE;

        int oneCount = 0;
        boolean oneFlag = false;
        arrClone = arr.clone();
        for (int i=0; i<N; i++){
            if (arrClone[i]!=oneStart[i]){
                oneFlag = true;
                for (int j=i+1; j<N; j++){
                    if (arrClone[j]==oneStart[i]){
                        oneCount+=j-i;
                        int temp = arrClone[j];
                        arrClone[j] = arrClone[i];
                        arrClone[i] = temp;
                        oneFlag = false;
                        break;
                    }
                }
            }
        }

        if (oneFlag) oneCount = Integer.MAX_VALUE;
        System.out.println(Math.min(oneCount,zeroCount));
    }
}
