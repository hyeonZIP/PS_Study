import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 포탄은 벽돌로 만들어진 벽 또는 강철로 만들어진 벽에 충돌하거나 게임 맵 밖으로 나갈 때까지 직진한다.
 * 포탄이 벽에 부딪히면 포탄은 소멸하고, 부딪힌 벽이 벽돌로 만들어진 벽이라면 이 벽은 파괴되어 칸은 평지가 된다.
 * 강철로 만들어진 벽에 포탄이 부딪히면 아무 일도 일어나지 않는다.
 */

public class Solution {

    static Queue<String> q = new LinkedList<>();
    static String[][] map;
    static int state;
    static int posX;
    static int posY;
    static int X, Y;
    static int[] dx = {0, 0, 1, -1};//좌 우 하 상
    static int[] dy = {-1, 1, 0, 0};//L R D U

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            X = Integer.parseInt(st.nextToken());
            Y = Integer.parseInt(st.nextToken());

            map = new String[X][Y];

            for (int j = 0; j < X; j++) {
                String s = br.readLine();

                for (int k = 0; k < Y; k++) {
                    map[j][k] = s.charAt(k) + "";
                    switch (s.charAt(k)) {
                        case '<': {
                            savePlayerPosToMap("<", 0, j, k);
                        }
                        break;
                        case '>': {
                            savePlayerPosToMap(">", 1, j, k);
                        }
                        break;
                        case 'v': {
                            savePlayerPosToMap("v", 2, j, k);
                        }
                        break;
                        case '^': {
                            savePlayerPosToMap("^", 3, j, k);
                        }
                        break;
                    }
                    ;
                }
            }

            int N = Integer.parseInt(br.readLine());//커맨드 입력 갯수
            String command = br.readLine();
            q = new LinkedList<>();
            for (int j = 0; j < N; j++) {
                q.offer(command.charAt(j) + "");
            }
            int count =0;
            while (!q.isEmpty()) {
//                System.out.println("test : " + count);
//                count++;
//                for (int j = 0; j < X; j++) {
//                    for (int k = 0; k < Y; k++) {
//                        System.out.print(map[j][k]);
//                    }
//                    System.out.println();
//                }
                String c = q.poll();
                int currentPosX = posX;
                int currentPosY = posY;
                switch (c) {
                    case "S": {
                        while (true) {
                            currentPosX += dx[state];
                            currentPosY += dy[state];

                            if (isOutOfRange(currentPosX, currentPosY)) {
                                break;
                            }

                            if (map[currentPosX][currentPosY].equals("#")) {
                                //강철인 경우
                                break;
                            } else if (map[currentPosX][currentPosY].equals("*")) {
                                //벽돌인 경우
                                map[currentPosX][currentPosY] = ".";
                                break;
                            }
                        }
                        break;
                    }
                    case "L": {
                        state = 0;
                        map[posX][posY] = "<";
                        currentPosX += dx[state];
                        currentPosY += dy[state];
                        if (!isOutOfRange(currentPosX, currentPosY) && map[currentPosX][currentPosY].equals(".")) {
                            map[posX][posY] = ".";
                            posX = currentPosX;
                            posY = currentPosY;
                            map[currentPosX][currentPosY] = "<";
                        }
                        break;
                    }
                    case "R": {
                        state = 1;
                        map[posX][posY] = ">";
                        currentPosX += dx[state];
                        currentPosY += dy[state];
                        if (!isOutOfRange(currentPosX, currentPosY) && map[currentPosX][currentPosY].equals(".")) {
                            map[posX][posY] = ".";
                            posX = currentPosX;
                            posY = currentPosY;
                            map[currentPosX][currentPosY] = ">";
                        }
                        break;
                    }
                    case "D": {
                        state = 2;
                        map[posX][posY] = "v";
                        currentPosX += dx[state];
                        currentPosY += dy[state];
                        if (!isOutOfRange(currentPosX, currentPosY) && map[currentPosX][currentPosY].equals(".")) {
                            map[posX][posY] = ".";
                            posX = currentPosX;
                            posY = currentPosY;
                            map[currentPosX][currentPosY] = "v";
                        }
                        break;
                    }
                    case "U": {
                        state = 3;
                        map[currentPosX][currentPosY] = "^";
                        currentPosX += dx[state];
                        currentPosY += dy[state];
                        if (!isOutOfRange(currentPosX, currentPosY) && map[currentPosX][currentPosY].equals(".")) {
                            map[posX][posY] = ".";
                            posX = currentPosX;
                            posY = currentPosY;
                            map[currentPosX][currentPosY] = "^";
                        }
                        break;
                    }
                }
            }
            System.out.print("#" + (i+1) + " ");
            for (int j = 0; j < X; j++) {
                for (int k = 0; k < Y; k++) {
                    System.out.print(map[j][k]);
                }
                System.out.println();
            }
        }
    }//main 98개중 81개

    public static void savePlayerPosToMap(String s, int stateInfo, int x, int y) {
        map[x][y] = s;
        posX = x;
        posY = y;
        state = stateInfo;
    }

    public static boolean isOutOfRange(int x, int y) {
        return x < 0 || x >= X || y < 0 || y >= Y;
    }
}
