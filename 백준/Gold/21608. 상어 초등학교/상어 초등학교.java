import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

//배치 기준
//1.선호 학생이 많이 인접한 곳
//2.1이 많은 경우 주변이 많이 비어있는 곳
//3.2가 많은 경우 좌상단 부터 우측으로 배치

//빈 map에는 항상 1,1에 처음 학생이 배치된다.
//1. 현재 꺼낼 학생의 번호와 선호 학생을 확인한다.
//2. 배치 기준 1번 계산
//3. 기준1번 계산 결과가 여러개 일 경우 2번 계산 > 3번 계산
//4. 배치
//5. 반복

public class Main {

    static int N, answer;
    static int[][] map, preferMap;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        preferMap = new int[N * N][5];

        for (int i = 0; i < N * N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 5; j++) {
                preferMap[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        sharkSchool();
        calcPrefer();

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(answer + "");
        bw.flush();
        bw.close();
        br.close();
    }

    private static void sharkSchool() {

        for (int i = 0; i < N * N; i++) {
            int[] pos;
            List<int[]> sol1Result = sol1(preferMap[i]);
            if (sol1Result.size() != 1) {
                List<int[]> sol2Result = sol2(sol1Result);
                pos = sol2Result.get(0);
            } else {
                pos = sol1Result.get(0);
            }
            map[pos[0]][pos[1]] = preferMap[i][0];
        }
    }

    private static void calcPrefer() {

        Arrays.sort(preferMap, Comparator.comparingInt(o -> o[0]));

        for (int y = 0; y < N; y++) {
            for (int x = 0; x < N; x++) {
                int student = map[y][x];
                int count = 0;
                for (int k = 0; k < 4; k++) {
                    int py = y + dy[k];
                    int px = x + dx[k];

                    if (isOutOfRange(py, px)) continue;

                    for (int i = 1; i < 5; i++) {
                        if (map[py][px] == preferMap[student - 1][i]) {
                            count++;
                            break;
                        }
                    }
                }
                if (count == 1) {
                    answer += 1;
                } else if (count == 2) {
                    answer += 10;
                } else if (count == 3) {
                    answer += 100;
                } else if (count == 4) {
                    answer += 1000;
                }
            }
        }
    }

    private static List<int[]> sol2(List<int[]> prefer) {
        int max = 0;
        List<int[]> pos = new ArrayList<>();

        for (int[] p : prefer) {
            int y = p[0];
            int x = p[1];

            int count = 0;

            for (int i = 0; i < 4; i++) {
                int py = y + dy[i];
                int px = x + dx[i];

                if (isOutOfRange(py, px)) continue;

                if (map[py][px] == 0) count++;
            }

            if (max < count) {
                pos = new ArrayList<>();
                max = count;
                pos.add(new int[]{y, x});
            } else if (max == count) {
                pos.add(new int[]{y, x});
            }
        }

        return pos;
    }

    private static List<int[]> sol1(int[] prefer) {
        int max = 0;
        List<int[]> pos = new ArrayList<>();
        for (int y = 0; y < N; y++) {
            for (int x = 0; x < N; x++) {

                if (map[y][x] != 0) continue;

                int count = 0;

                for (int i = 0; i < 4; i++) {
                    int py = y + dy[i];
                    int px = x + dx[i];

                    if (isOutOfRange(py, px)) continue;

                    for (int j = 1; j < 5; j++) {
                        if (map[py][px] == prefer[j]) {
                            count++;
                            break;
                        }
                    }
                }

                if (max < count) {
                    max = count;
                    pos = new ArrayList<>();
                    pos.add(new int[]{y, x});
                } else if (max == count) {
                    pos.add(new int[]{y, x});
                }
            }
        }

        return pos;
    }

    private static boolean isOutOfRange(int y, int x) {
        return x < 0 || y < 0 || x >= N || y >= N;
    }
}
