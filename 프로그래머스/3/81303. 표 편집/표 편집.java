import java.io.*;
import java.util.*;

class Solution {
    
    public int[] up;
    public int[] down;
    public ArrayDeque<Integer> undoTable = new ArrayDeque<>();
    
    public String solution(int n, int k, String[] cmd) {
        
        init(n);
        
        perform(n,k,cmd);
        
        return calcAnswer(n);
    }
    
    private String calcAnswer(int n){
        
        char[] answer = new char[n];
        
        Arrays.fill(answer, 'O');
        
        while(!undoTable.isEmpty()){
            
            answer[undoTable.pop()-1] = 'X';
        }
        
        return new String(answer);
    }
    
    private void perform(int n, int k, String[] cmd){
        
        // 첫 행과 마지막 행에 데이터가 들어올 때를 위한 가상의 공간이 추가되었음으로
        // 현재 포인터의 위치도 한칸 증가한다.
        k++;
        
        for(String command : cmd){
            
            if(isMovingPointerCommand(command)){
                
                k = movePointer(k, command);
                
                continue;
            }

            if(isDeletingCommand(command)){
                
                undoTable.push(k);
                
                // 삭제된 행의 아랫 행의 up을 삭제된 행의 up으로
                up[down[k]] = up[k];
                
                // 삭제된 행의 윗 행의 down을 삭제된 행의 down으로
                down[up[k]] = down[k];
                
                // 마지막 행을 삭제한 것이라면 up[k] 로 커서 위치 업데이트
                k = down[k] > n ? up[k] : down[k];
                
                continue;
            }
            
            if(isUndoingCommand(command)){
                
                int undoK = undoTable.pop();
                
                down[up[undoK]] = undoK;
                up[down[undoK]] = undoK;
            }
        }//for
    }//perform
    
    private int movePointer(int k, String command){
        
        String[] commandDetails = command.split(" ");

        String movingCommand = commandDetails[0];
        int movingPointer = Integer.parseInt(commandDetails[1]);

        for(int i=0; i<movingPointer; i++){
            
            k = movingCommand.equals("U") ? up[k] : down[k];
        }
        
        return k;
    }
    
    private boolean isMovingPointerCommand(String command){
        
        return !isUndoingCommand(command) && !isDeletingCommand(command);
    }
    
    private boolean isUndoingCommand(String command){

        return command.equals("Z");
    }
    
    private boolean isDeletingCommand(String command){
        
        return command.equals("C");
    }
    
    private void init(int n){
        
        up = new int[n+2];
        down = new int[n+2];
        
        for(int i=0; i<n+2; i++){
            
            up[i] = i-1;
            down[i] = i+1;     
        }
    }
}