
import java.io.*;
import java.util.*;
/*
무조건 y좌표 먼저 이동 후 x좌표 이동이기 때문에
A에서 B로 가기위한 방법은
A의 y좌표를 B의 y좌표에 맞추고
이후에 x좌표를 맞춘다.

2차원 배열 map 원소 값으로 이동할 때마다 1씩 증가한 값을 넣는다
원소 값이 0일 경우 아무도 지나가지 않은 경우
나의 원소 값이 1인데 지나가야 하는 원소 값도 1인 경우 > 충돌 알람 +1
나의 원소 값이 2인데 지나가야 하는 원소 값은 1인 경우 > 이미 누군가 지나갔기 때문에 알람을 울리지 안않는다.
모든 경로의 도착 시간을 기록하기 위해 원소값의 데이터 타입은 list?
*/
class Solution {

    ArrayList[][] map = new ArrayList[101][101];
    int[][] pos;

    public int solution(int[][] points, int[][] routes) {
        
        pos = points;

        int len = routes.length;
        
        for(int i=0; i<routes.length; i++){
            markMovedRoutes(routes[i]);
        }
        
//         for(int i=0; i<10; i++){
//             for(int j=0; j<10; j++){
//                 System.out.print((map[i][j] == null ? 9 : map[i][j].size()) + " ");
//             }
//             System.out.println();
            
//         }
//         System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");

        int answer = detectCollision();

        return answer;
    }

    public int detectCollision(){

        int count = 0;

        for(int i=0; i<101; i++){
            for(int j=0; j<101; j++){
                if(map[i][j] == null || map[i][j].size() == 1) continue;
                HashMap<Integer,Integer> cnt = new HashMap<>();
                for(Object num : map[i][j]){
                    cnt.put((Integer) num, cnt.getOrDefault(num,0)+1);
                }

                for(Map.Entry<Integer,Integer> entry : cnt.entrySet()){
                    if(entry.getValue() != 1){
                        count++;
                    }
                }
            }
        }
        return count;
    }

    public void markMovedRoutes(int[] route){

        int fromY = -1;
        int fromX = -1;
        int time = 0;

        for(int i=0; i<route.length; i++){

            if(fromY == -1 || fromX == -1){
                //출발 지점 지정
                fromY = pos[route[i]-1][0];
                fromX = pos[route[i]-1][1];
                continue;
            }

            int toY = pos[route[i]-1][0];
            int toX = pos[route[i]-1][1];
          

            if(fromY < toY){
                for(int j=fromY; j<toY; j++){
                    if(map[j][fromX] == null){
                        map[j][fromX] = new ArrayList<>();
                    }
                    map[j][fromX].add(time);
                    time++;
                }
            }else{
                for(int j=fromY; j>toY; j--){
                    if(map[j][fromX] == null){
                        map[j][fromX] = new ArrayList<>();
                    }
                    map[j][fromX].add(time);
                    time++;
                }
            }

            if(fromX < toX){
                for(int j=fromX; j<toX; j++){
                    if(map[toY][j] == null){
                        map[toY][j] = new ArrayList<>();
                    }
                    map[toY][j].add(time);
                    time++;
                }
            }else{
                for(int j=fromX; j>toX; j--){
                    if(map[toY][j] == null){
                        map[toY][j] = new ArrayList<>();
                    }
                    map[toY][j].add(time);
                    time++;
                }
            }
            fromY = toY;
            fromX = toX;
        }
        if(map[fromY][fromX] == null){
            map[fromY][fromX] = new ArrayList<>();
        }
        map[fromY][fromX].add(time);
    }
}