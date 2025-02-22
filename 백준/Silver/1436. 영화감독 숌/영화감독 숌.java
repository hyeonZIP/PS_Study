
import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());

        String shom = "666";
        int number = 665;
        int answer = 0;
        while(answer != n){
            number++;
            if (String.valueOf(number).contains(shom)){
                answer++;
            }
        }



        bw.write(number+"");
        bw.flush();
        bw.close();
        br.close();
    }
}
