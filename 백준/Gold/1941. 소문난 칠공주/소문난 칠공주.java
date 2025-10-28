import java.io.*;
import java.util.*;

public class Main {
    public static final int[] dy = {1,-1,0,0};
    public static final int[] dx = {0,0,1,-1};
    public static int answer;
    public static String[][] map = new String[5][5];
    public static boolean[] globalVisited = new boolean[25];
    public static void main(String[] args) throws IOException{

        init();

        sol();

        print();
    }

    public static void sol() {
        combine(0,0,0);
    }

    private static void combine(int depth, int idx, int yeon){
        if(yeon >= 4){
            return;
        }

        if(depth == 7){
            int previousIdx = idx-1;
            bfs(previousIdx/5,previousIdx%5);
            return;
        }

        for(int i=idx; i<25; i++){
            globalVisited[i] = true;

            if (map[i/5][i%5].equals("Y")) {
                combine(depth+1, i+1, yeon+1);
            }else{
                combine(depth+1, i+1, yeon);
            }
            
            globalVisited[i] = false;//백트래킹
        }
    }

    private static void bfs(int y, int x){
        ArrayDeque<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{y,x});

        boolean[][] visited = new boolean[5][5];
        visited[y][x] = true;

        int count = 1;

        while(!q.isEmpty()){
            int[] arr = q.poll();

            for(int i=0; i<4; i++){
                int py = arr[0] + dy[i];
                int px = arr[1] + dx[i];
                int globalIdx = py * 5 + px;

                if (isOutOfRange(py,px)) continue;
                if (!globalVisited[globalIdx]) continue;//조합 사용 여부 체크
                if (visited[py][px]) continue;//BFS 내부 방문 체크

                visited[py][px] = true;
                q.offer(new int[]{py,px});
                count++;
            }
        }

        if(count == 7) answer++;
    }

    private static boolean isOutOfRange(int y, int x){
        return y<0 || x<0 || y>=5 || x>=5;
    }

    public static void init() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for(int i=0; i<5; i++){
            map[i] = br.readLine().split("");
        }
    }

    public static void print() throws IOException{
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        bw.write(String.valueOf(answer));
        bw.close();
    }
}
