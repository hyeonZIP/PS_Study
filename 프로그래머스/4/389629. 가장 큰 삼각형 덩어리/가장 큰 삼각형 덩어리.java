import java.io.*;
import java.util.*;

class Solution {
    public int answer;
    public int N,M;
    public int[][] map;
    public int[][][] visited;
    
    public int solution(int[][] grid) {        
        init(grid);
        
        dfs();
        
        return answer;
    }
    
    public void dfs(){             
        int index = 1;
        for(int y=0; y<N; y++){
            for(int x=0; x<M; x++){
                for(int z=0; z<2; z++){
                    if(visited[y][x][z] != 0) continue;
                    
                    dfs(y,x,z,index);
                    index++;
                }
            }
        } 
    }
    
    public void dfs(int initY, int initX, int initZ, int index){
        visited[initY][initX][initZ] = index;

        Deque<int[]> stack = new ArrayDeque<>();
        stack.push(new int[]{initY,initX,initZ});

        int count = 1;
        
        while(!stack.isEmpty()){
            int[] arr = stack.pop();

            int cy = arr[0];
            int cx = arr[1];
            int cz = arr[2];

            if(cz == 0){
                // 아래쪽 삼각형은 아래로 이동, 이동한 위치에서의 삼각형은 위쪽 삼각형
                count = move(cy+1,cx,1,stack, count,index);
                
                int nx = map[cy][cx] == 1 ? cx + 1 : cx - 1;
                
                if(isOutOfRange(cy,nx)) continue;
                
                int nz;
                
                if(nx > cx){//오른쪽으로 이동
                    nz = map[cy][nx] == 1 ? 1 : 0;
                }else{//왼쪽으로 이동
                    nz = map[cy][nx] == 1 ? 0 : 1;
                }

                count = move(cy,nx,nz,stack, count,index);
            }else{
                // 위쪽 삼각형은 위로 이동, 이동한 위치에서의 삼각형은 아래쪽 삼각형
                count = move(cy-1,cx,0,stack, count,index);
                
                int nx = map[cy][cx] == 1 ? cx - 1 : cx + 1;
                
                if(isOutOfRange(cy,nx)) continue;
                
                int nz;
                
                if(nx > cx){
                    nz = map[cy][nx] == 1 ? 1 : 0;
                }else{
                    nz = map[cy][nx] == 1 ? 0 : 1;
                }

                count = move(cy,nx,nz,stack, count,index);
            }
        }//while
        
        answer = Math.max(count, answer);
    }
    
    public int move(int y, int x,int z, Deque<int[]> stack, int count, int index){
        if(isOutOfRange(y,x) || visited[y][x][0] == index || visited[y][x][1] == index) return count;
        
        visited[y][x][z] = index;

        stack.push(new int[]{y,x,z});
        return count+1;
    }
    
    public boolean isOutOfRange(int y, int x){
        return y<0 || x<0 || y>=N || x>=M;
    }
    
    public void init(int[][] grid){
        map = grid;
        N = map.length;
        M = map[0].length;
        visited = new int[N][M][2];
    }
}