import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        long A = Long.parseLong(st.nextToken());
        long B = Long.parseLong(st.nextToken());
        long C = Long.parseLong(st.nextToken());

        System.out.println(sol(A, B, C));
    }

    private static long sol(long A, long B, long C) {

        if (B == 1) return A%C;

        long temp = sol(A, B >>1 , C);

        if ((B & 1) == 1) return (temp * temp % C * A % C) % C;
        return temp * temp % C;
    }
}
