import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[] arr;
    static int a, b, c;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        arr = new int[6];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 6; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        findABC();

        if (N == 1) {
            Arrays.sort(arr);
            bw.write( (arr[0]+arr[1]+arr[2]+arr[3]+arr[4]+""));
        } else if (N == 2) {
            bw.write(edge() + "");
        } else {
            bw.write((edge() + mid() + center()) + "");
//            System.out.println(edge());
//            System.out.println(mid());
//            System.out.println(center());
        }

        bw.flush();
        bw.close();
        br.close();
    }

    private static void findABC(){
        //자신과 마주보는 면의 숫자와 비교해서 가장 작은 3개의 값을 고르면 된다
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i=0; i<3; i++){
            pq.offer(Math.min(arr[i],arr[5-i]));
        }

        a = pq.poll();
        b = pq.poll();
        c = pq.poll();
    }

    private static long edge() {//4개의 모서리 주사위의 최소값
        return 4L * N * a + 4L * N * b + 4L * c;
    }

    private static long mid() {
        return (N-2)*(4L *N * a) +(N - 2) * b * 4L;
    }

    private static long center() {
        return (long)(Math.pow((N - 2), 2) * a);
    }

}
