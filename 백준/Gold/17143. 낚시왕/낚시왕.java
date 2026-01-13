import java.io.*;
import java.util.*;

public class Main {
    private static final int[] dy = new int[] { -1, -1, 1, 0, 0 };
    private static final int[] dx = new int[] { -1, 0, 0, 1, -1 };

    private static int R, C, M;
    private static Shark[][] map;

    private static class Shark {
        int y, x, speed, direction, z;

        public Shark(int r, int c, int s, int d, int z) {
            this.y = r;
            this.x = c;
            this.speed = s;
            this.direction = d;
            this.z = z;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st;

        st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());// Y
        C = Integer.parseInt(st.nextToken());// X
        M = Integer.parseInt(st.nextToken());// 상어 수

        map = new Shark[R + 1][C + 1];

        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());

            int r = Integer.parseInt(st.nextToken());// Y
            int c = Integer.parseInt(st.nextToken());// X
            int s = Integer.parseInt(st.nextToken());// 속력
            int d = Integer.parseInt(st.nextToken());// 이동 방향
            int z = Integer.parseInt(st.nextToken());// 크기

            map[r][c] = new Shark(r, c, s, d, z);
        }

        int fishKing = 1;
        int answer = 0;
        while (fishKing <= C) {
            answer += fishing(fishKing);
            fishKing++;
            moving();

        }

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(String.valueOf(answer));
        bw.close();
    }

    private static void moving() {
        Shark[][] newMap = new Shark[R + 1][C + 1];

        for (int i = 1; i <= R; i++) {
            for (int j = 1; j <= C; j++) {
                if (map[i][j] == null) {
                    continue;
                }
                Shark shark = map[i][j];

                map[i][j] = null;

                int y = shark.y;
                int x = shark.x;
                int currentS = shark.speed;
                int currentZ = shark.z;
                int direction = shark.direction;

                for (int k = 0; k < currentS; k++) {
                    int py = y + dy[direction];
                    int px = x + dx[direction];

                    if (py <= 0 || py > R) {
                        direction = direction == 1 ? 2 : 1;
                        y += dy[direction];
                        continue;
                    }

                    if (px <= 0 || px > C) {
                        direction = direction == 3 ? 4 : 3;
                        x += dx[direction];
                        continue;
                    }

                    y = py;
                    x = px;
                }

                if (newMap[y][x] == null || newMap[y][x].z < currentZ) {
                    newMap[y][x] = new Shark(y, x, currentS, direction, currentZ);
                }
            }
        }

        map = newMap;
    }

    private static int fishing(int fishKing) {
        int point = 0;
        for (int i = 1; i <= R; i++) {
            if (map[i][fishKing] != null) {
                point = map[i][fishKing].z;
                map[i][fishKing] = null;
                return point;
            }
        }
        return point;
    }

    private static void test() {
        for (int i = 1; i <= R; i++) {
            for (int j = 1; j <= C; j++) {
                if (map[i][j] == null) {
                    System.out.print("0 ");
                } else {
                    System.out.print("1 ");
                }
            }
            System.out.println();
        }

        System.out.println("-------------");
    }
}