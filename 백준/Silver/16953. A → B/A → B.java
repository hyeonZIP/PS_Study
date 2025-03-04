import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    static int answer = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        long A = Long.parseLong(st.nextToken());
        long B = Long.parseLong(st.nextToken());

        sol(A,B,0);

        if (answer==Integer.MAX_VALUE){
            bw.write(-1+"");
        }
        else{
            bw.write(answer+1+"");
        }
        bw.flush();
        bw.close();
        br.close();
    }//main

    private static void sol(long A, long B, int count){

        if (A==B){
            answer = Math.min(answer, count);
        } else if (A>B) {

        } else{
            sol(A*2,B,count+1);
            String s = A+"1";
            sol(Long.parseLong(s),B,count+1);
        }
    }
}
