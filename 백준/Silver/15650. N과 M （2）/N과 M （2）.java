
import java.util.*;
import java.io.*;

public class Main{
    
    static int N,M;
    static boolean[] selected;
    static int[] numbers;
    static StringBuilder sb = new StringBuilder();
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        numbers = new int[M];
        
        dfs(0,1);
    }
    
    private static void dfs(int index, int number){
        
        if(index == M){
        	for(int i : numbers) {
        		System.out.print(i + " ");
        	}
        	System.out.println();
            return;
        }
        
        for(int i=number; i<=N; i++){
            numbers[index] = i;
            dfs(index+1,i+1);
        }
    }
}