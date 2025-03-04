import java.util.*;
import java.io.*;

/**
 * N*M 크기 직사각형
 * 각 꼭짓점에 쓰인 숫자가 같은 가장 큰 정사각형 찾기
 * 정사각형 크기 출력
 * <p>
 * 전체 탐색을 하며 발견한 숫자를 기준으로 하단, 우측으로 동일한 숫자가 있는지 탐색한다.
 * 탐색을 줄이려면 최하단과 최우측 부터 탐색을 시작하여 거꾸로 진행한다 > 발견 즉시 다은 숫자로 탐색을 시작한다
 */
public class Main {

    static int height,width,answer=1;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        height = Integer.parseInt(st.nextToken());
        width = Integer.parseInt(st.nextToken());

        int[][] arr = new int[height][width];
        
        for (int y = 0; y < height; y++) {
            String s = br.readLine();
            for (int x = 0; x < width; x++) {
                arr[y][x] = s.charAt(x) - '0';
            }
        }

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                findRectangle(y, x, arr);
            }
        }

        bw.write(answer+"");
        bw.flush();
        bw.close();
        br.close();
    }//main

    private static void findRectangle(int y, int x, int[][] arr) {
        int fixedX = x;
        int fixedY = y;
        int fixedNumber = arr[y][x];
        while(true){
            x++;
            y++;
            if (y >= height || x >= width) break;
            if (arr[y][fixedX] == fixedNumber && arr[fixedY][x]==fixedNumber && arr[y][x]==fixedNumber){
                answer = Math.max(answer, (x-fixedX+1)*(y-fixedY+1));
            }
        }
    }

}
