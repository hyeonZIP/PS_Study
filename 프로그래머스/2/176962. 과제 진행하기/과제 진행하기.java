import java.io.*;
import java.util.*;

class Solution {
    
    public class Subject{
        
        private String name;
        private int startTime;
        private int remainTime;
        
        public Subject(String name, int startTime, int remainTime){
            
            this.name = name;
            this.startTime = startTime;
            this.remainTime = remainTime;
        }
        
        public Subject(String name, int remainTime){
            
            this.name = name;
            this.remainTime = remainTime;
        }
    }
    
    public int subjectCount;
    
    public PriorityQueue<Subject> pq = new PriorityQueue<>(Comparator.comparingInt(o->o.startTime));
    
    public ArrayDeque<Subject> stack = new ArrayDeque<>();
    
    public String[] solution(String[][] plans) {
        
        init(plans);
        
        String[] answer = sol();
        
        return answer;
    }
    
    private String[] sol(){
        
        String[] answer = new String[subjectCount];
        
        int answerIndex = 0;

        String currentName;
        int currentStartTime;
        int currentRemainTime;
        
        Subject nextSub = pq.poll();

        String nextName = nextSub.name;
        int nextStartTime = nextSub.startTime;
        int nextRemainTime = nextSub.remainTime;
        
        
        
        while(!pq.isEmpty()){
            
            currentName = nextName;
            currentStartTime = nextStartTime;
            currentRemainTime = nextRemainTime;
            
            nextSub = pq.poll();

            nextName = nextSub.name;
            nextStartTime = nextSub.startTime;
            nextRemainTime = nextSub.remainTime;
            
            for(int i = currentStartTime, j = currentRemainTime; i<=nextStartTime; i++, j--){
                
                System.out.println(currentName + " - " + "i : " + i + " j : " + j);
                // 다음 작업 시간이고 현재 작업이 남았을 경우
                if(i == nextStartTime && j > 0){
                    
                    stack.push(new Subject(currentName, j));
                    
                    continue;
                }
                
                // 다음 작업 시간이고 현재 작업이 완료된 경우
                if(i == nextStartTime && j == 0){
                    
                    answer[answerIndex] = currentName;
                    answerIndex++;
                    
                    continue;
                }
                
                // 다음 작업시간 전이고 작업 중인 경우
                if(i < nextStartTime && j > 0){
                    
                    continue;
                }
                
                // 다음 작업시간 전이고 작업이 완료된 경우
                if(i < nextStartTime && j == 0){
                    
                    answer[answerIndex] = currentName;
                    answerIndex++;
                    
                    if(!stack.isEmpty()){
                        
                        Subject previousSub = stack.pop();
                    
                        currentName = previousSub.name;
                        j = previousSub.remainTime; 
                    }
                }  
            }//for
            
            if(pq.isEmpty()){
                
                answer[answerIndex] = nextName;
                answerIndex++;
            }
            
        }//while
        int i = 0;
        while(!stack.isEmpty()){
            
            // System.out.println("stack : " + stack.pop().name);
            
            if(answer[i] == null){
                
                answer[i] = stack.pop().name;
            }
            i++;
        }
        
        return answer;
    }

    private void init(String[][] plans){
        
        for(String[] plan : plans){
            
            String name = plan[0];
            
            String[] time = plan[1].split(":");
            
            int hour = Integer.parseInt(time[0]);
            int min = Integer.parseInt(time[1]);
            
            int startTime = hour*60 + min;
            
            int remainTime = Integer.parseInt(plan[2]);
            
            pq.offer(new Subject(name, startTime, remainTime));
        }
        
        subjectCount = plans.length;
    }
}