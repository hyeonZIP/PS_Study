import java.io.*;
import java.util.*;

class Solution {
    final int[] dy = new int[]{0,0,-1,1};// 동 서 남 북
    final int[] dx = new int[]{1,-1,0,0};
    
    final int RIGHT = 0;
    final int LEFT = 1;
    final int UP = 2;
    final int DOWN = 3;
    
    final int EMPTY = 0;
    final int BLOCKED = -1;
    
    int answer = 0,n,m;
    int[][] map;
    
    public int solution(int[][] grid) {
        init(grid);
        
        int[][] visited = new int[n][m];
        visited[0][0] = 1;
        visited[n-1][m-1] = 1;
        
        backtracking(0,1,RIGHT, visited);
        
        return answer;
    }
    
    private void backtracking(int y, int x, int direction, int[][] visited){
        if(isOutOfRange(y,x)) return;
        
        if(isBlocked(y,x)) return;
        
        if(isGoal(y,x)){
            
            if(isVisitedAllRail(visited) && isValidDirection(y,x,direction)){
                answer++;
            }
            
            return;
        }
        
        int[] nextPosition;
        
        if(isEmpty(y,x)){
            if(visited[y][x] > 0) return;
            visited[y][x]++;
            
            switch(direction){
                case RIGHT:
                    map[y][x] = 1;
                    nextPosition = getNextPosition(y,x,direction);
                    backtracking(nextPosition[0], nextPosition[1], nextPosition[2],visited);
                    
                    map[y][x] = 3;
                    nextPosition = getNextPosition(y,x,direction);
                    backtracking(nextPosition[0], nextPosition[1], nextPosition[2],visited);
                    
                    map[y][x] = 4;
                    nextPosition = getNextPosition(y,x,direction);
                    backtracking(nextPosition[0], nextPosition[1], nextPosition[2],visited);
                    
                    map[y][x] = 7;
                    nextPosition = getNextPosition(y,x,direction);
                    backtracking(nextPosition[0], nextPosition[1], nextPosition[2],visited);
                    break;
                case LEFT:
                    map[y][x] = 1;
                    nextPosition = getNextPosition(y,x,direction);
                    backtracking(nextPosition[0], nextPosition[1], nextPosition[2],visited);
                    
                    map[y][x] = 3;
                    nextPosition = getNextPosition(y,x,direction);
                    backtracking(nextPosition[0], nextPosition[1], nextPosition[2],visited);
                    
                    map[y][x] = 5;
                    nextPosition = getNextPosition(y,x,direction);
                    backtracking(nextPosition[0], nextPosition[1], nextPosition[2],visited);
                    
                    map[y][x] = 6;
                    nextPosition = getNextPosition(y,x,direction);
                    backtracking(nextPosition[0], nextPosition[1], nextPosition[2],visited);
                    break;
                case DOWN:
                    map[y][x] = 2;
                    nextPosition = getNextPosition(y,x,direction);
                    backtracking(nextPosition[0], nextPosition[1], nextPosition[2],visited);
                    
                    map[y][x] = 3;
                    nextPosition = getNextPosition(y,x,direction);
                    backtracking(nextPosition[0], nextPosition[1], nextPosition[2],visited);
                    
                    map[y][x] = 4;
                    nextPosition = getNextPosition(y,x,direction);
                    backtracking(nextPosition[0], nextPosition[1], nextPosition[2],visited);
                    
                    map[y][x] = 5;
                    nextPosition = getNextPosition(y,x,direction);
                    backtracking(nextPosition[0], nextPosition[1], nextPosition[2],visited);
                    break;
                case UP:
                    map[y][x] = 2;
                    nextPosition = getNextPosition(y,x,direction);
                    backtracking(nextPosition[0], nextPosition[1], nextPosition[2],visited);
                    
                    map[y][x] = 3;
                    nextPosition = getNextPosition(y,x,direction);
                    backtracking(nextPosition[0], nextPosition[1], nextPosition[2],visited);
                    
                    map[y][x] = 6;
                    nextPosition = getNextPosition(y,x,direction);
                    backtracking(nextPosition[0], nextPosition[1], nextPosition[2],visited);
                    
                    map[y][x] = 7;
                    nextPosition = getNextPosition(y,x,direction);
                    backtracking(nextPosition[0], nextPosition[1], nextPosition[2],visited);
                    break;
            }//switch
            map[y][x] = 0;
            visited[y][x]--;
        }else{     
            visited[y][x]++;
            if(isValidDirection(y,x,direction)){
                nextPosition = getNextPosition(y,x,direction);
                backtracking(nextPosition[0], nextPosition[1], nextPosition[2],visited);
            }
            visited[y][x]--;
        }
    }
    
    // 레일 모양과 올바른 이동방향인지 체크
    private boolean isValidDirection(int y, int x, int direction){
        int currentRail = map[y][x];
        
        switch(currentRail){
            case 1:
                return direction == RIGHT || direction == LEFT;
            case 2:
                return direction == UP || direction == DOWN;
            case 3:
                return true;
            case 4:
                return direction == RIGHT || direction == DOWN;
            case 5:
                return direction == LEFT || direction == DOWN;
            case 6:
                return direction == LEFT || direction == UP;
            case 7:
                return direction == RIGHT || direction == UP;
            default:
                System.out.println("예외 케이스 발생");
                return false;
        }
    }
    
    private int[] getNextPosition(int y,int x,int direction){
        int[] nextPosition = new int[3];
        
        int currentRail = map[y][x];
        
        switch(currentRail){
            case 1,2,3:
                break;
            case 4:
                direction = direction == DOWN ? LEFT : UP;
                break;
            case 5:
                direction = direction == DOWN ? RIGHT : UP;
                break;
            case 6:
                direction = direction == UP ? RIGHT : DOWN;
                break;
            case 7:
                direction = direction == UP ? LEFT : DOWN;
                break;
        }
        
        nextPosition[0] = y + dy[direction];
        nextPosition[1] = x + dx[direction];
        nextPosition[2] = direction;
        
        return nextPosition;
    }
    
    private boolean isVisitedAllRail(int[][] visited){
        for(int y=0; y<n; y++){
            for(int x=0; x<m; x++){
                if(map[y][x] == 3 && visited[y][x] != 2){
                    return false;
                }
                
                if(map[y][x] >= 1 && map[y][x] <= 7 && visited[y][x] < 1){
                    return false;
                }
            }
        }
        
        return true;
    }
    
    private boolean isGoal(int y, int x){
        return y == n-1 && x == m-1;
    }
    
    private boolean isBlocked(int y, int x){
        return map[y][x] == -1;
    }
    
    private boolean isEmpty(int y ,int x){
        return map[y][x] == 0;
    }
    
    private boolean isOutOfRange(int y, int x){
        return y<0 || x<0 || y>=n || x>=m;
    }
    
    private void init(int[][] grid){
        n = grid.length;
        m = grid[0].length;
        
        map = grid;
    }
}
