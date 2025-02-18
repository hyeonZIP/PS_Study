
import java.nio.Buffer;
import java.util.*;
import java.io.*;

/**
 * DFS/BFS로 연합이 될 수 있는 나라 탐색
 *
 */

public class Main {

    static int N,L,R;
    static int[][] map;

    static int[] dx = {0,0,1,-1};
    static int[] dy = {1,-1,0,0};
    static boolean[][] visited;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        map = new int[N][N];

        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int answer = purgeDay();



        bw.write(answer+"");
        bw.flush();
        bw.close();
        br.close();
    }

    private static int purgeDay(){
        int answer = 0;
        while (true) {
            visited = new boolean[N][N];

            boolean flag = false;
            for(int i=0; i<N; i++){
                for(int j=0; j<N; j++){
                    if(!visited[i][j]){
                        if(bfs(i,j)){
                            flag = true;
                        }
                    }
                }
            }
            if(!flag) break;
            answer++;
        }

        return answer;
    }

    private static boolean bfs(int x, int y){
        ArrayDeque<int[]> s = new ArrayDeque<>();
        ArrayDeque<int[]> sum = new ArrayDeque<>();
        int count = 0;
        s.offer(new int[]{x,y});
        visited[x][y] = true;

        while(!s.isEmpty()){
            int[] xy = s.pop();
//            System.out.println("s.size() : " + s.size());
            for(int i=0; i<4; i++){
                int px = xy[0] + dx[i];
                int py = xy[1] + dy[i];

                if(px < 0 || py < 0 || px >=N || py >= N || visited[px][py]) continue;

                if(Math.abs(map[xy[0]][xy[1]] - map[px][py]) >= L && Math.abs(map[xy[0]][xy[1]] - map[px][py]) <=R){
                    visited[px][py] = true;
                    count += map[px][py];
                    sum.offer(new int[]{px,py});
                    s.push(new int[]{px,py});
                }
            }
        }//while
        if(count == 0){
            return false;
        }

        count += map[x][y];
        sum.offer(new int[]{x,y});

        int a = sum.size();

        while(!sum.isEmpty()){
            int[] xy = sum.poll();
            int px = xy[0];
            int py = xy[1];

            map[px][py] = count/a;
        }

//        for(int[] i : map){
//            for(int j : i){
//                System.out.print(j + " ");
//            }
//            System.out.println();
//        }

        return true;
    }
}
