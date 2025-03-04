import java.util.*;
import java.io.*;

/**
 * A1 은 좌측하단
 * H8 은 우측상단
 */

public class Main {
    static int[][] board = new int[8][8];
    static int kingX,kingY,stoneX,stoneY;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        String kingPos = st.nextToken();
        String stonePos  = st.nextToken();
        int moveCount = Integer.parseInt(st.nextToken());

        String[] arr = new String[]{"A","B","C","D","E","F","G","H"};

        kingX = kingPos.charAt(0)-'A';
        kingY = 8-Character.getNumericValue(kingPos.charAt(1));
        stoneX = stonePos.charAt(0)-'A';
        stoneY = 8-Character.getNumericValue(stonePos.charAt(1));

        board[kingY][kingX] = 1;
        board[stoneY][stoneX] = 2;


        for (int i=0; i<moveCount; i++){

            String cmd = br.readLine();

            switch(cmd){
                case "R":
                    if (moveKing(kingY,kingX+1,stoneY,stoneX+1)){
                        board[kingY][kingX] = 0;
                        kingX++;
                    }
                    break;
                case "L":
                    if (moveKing(kingY,kingX-1,stoneY,stoneX-1)){
                        board[kingY][kingX] = 0;
                        kingX--;
                    }
                    break;
                case "B":
                    if (moveKing(kingY+1,kingX,stoneY+1,stoneX)){
                        board[kingY][kingX] = 0;
                        kingY++;
                    }
                    break;
                case "T":
                    if (moveKing(kingY-1,kingX,stoneY-1,stoneX)){
                        board[kingY][kingX] = 0;
                        kingY--;
                    }
                    break;
                case "RT":
                    if (moveKing(kingY-1,kingX+1,stoneY-1,stoneX+1)){
                        board[kingY][kingX] = 0;
                        kingY--;
                        kingX++;
                    }
                    break;
                case "LT":
                    if (moveKing(kingY-1,kingX-1,stoneY-1,stoneX-1)){
                        board[kingY][kingX] = 0;
                        kingY--;
                        kingX--;
                    }
                    break;
                case "RB":
                    if (moveKing(kingY+1,kingX+1,stoneY+1,stoneX+1)){
                        board[kingY][kingX] = 0;
                        kingY++;
                        kingX++;
                    }
                    break;
                case "LB":
                    if (moveKing(kingY+1,kingX-1,stoneY+1,stoneX-1)){
                        board[kingY][kingX] = 0;
                        kingY++;
                        kingX--;
                    }
            }//switch
        }//for

        bw.write(arr[kingX]+(8-kingY)+"\n");
        bw.write(arr[stoneX]+(8-stoneY));
        bw.flush();
        bw.close();
        br.close();

    }//main

    private static boolean moveStone(int sY, int sX){
        if (isOutOfRange(sY,sX)) return false;
        board[sY][sX] = 2;
        return true;

    }

    private static boolean moveKing(int kY, int kX,int sY, int sX){
        if (isOutOfRange(kY,kX)) return false;

        if (board[kY][kX] == 2){
            if(moveStone(sY, sX)){
                board[stoneY][stoneX] = 0;
                stoneY = sY;
                stoneX = sX;
            }
            else{
                return false;
            }
        }
        board[kY][kX] = 1;
        return true;
    }

    private static boolean isOutOfRange(int y, int x){
        return x<0 || y<0 || x>=8 || y>=8;
    }
}
