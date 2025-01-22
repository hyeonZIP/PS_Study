/*
+ begin: 초기 단어 String 
+ target: 목표 단어 String
+ words: 단어 리스트 String List

-[ ] 각 단어는 알파벳 소문자, 길이는 3이상 10이하, 단어의 길이 동일
-[ ] words 중복 허용X, 3개 이상 50개 이하
-[ ] begin과 target 동일X
-[ ] 변경 횟수 return
    -[ ] 변환할 수 없는 경우 0 return
-[ ] 한 번에 한 개의 알파벳만 변경 가능
-[ ] begin은 words에 있는 단어로만 변경 가능
-[ ] bfs
-[ ] hit에서 hot > hot에서 dot > dot에서 dog > dog에서 log > log에서 cog 성공 
*/

import java.util.*;


class Solution {
    
    static String b;
    static String t;
    static String[] w;
    static int wordLength;
    static int answer;
    static boolean[] visited;

    public class Pair {
        String word;
        int cnt;

        public Pair(String word, int cnt) {
            this.word = word;
            this.cnt = cnt;
        }

    }

    public int solution(String begin, String target, String[] words) {

        b = begin;
        t = target;
        w = words;
        wordLength = b.length();
        visited = new boolean[w.length];

        return bfs();
    }

    public int bfs() {

        Queue<Pair> q = new LinkedList<>();
        q.offer(new Pair(b, 0));

        while (!q.isEmpty()) {
            Pair p = q.poll();
            if (p.word.equals(t)){
                return p.cnt;
            }
            for (int i = 0; i < w.length; i++) {

                int limit = 0;//중복 제한 확인
                if (!visited[i]) {
                    for (int j = 0; j < wordLength; j++) {
                        if (p.word.charAt(j) != w[i].charAt(j)) {
                            limit++;
                        }
                        if (limit > 1) {
                            break;
                        }
                        if (wordLength == j + 1 && limit == 1) {
                            visited[i] = true;
//                            System.out.println("교체 단어 : " + p.word + " " + w[i]);
                            q.offer(new Pair(w[i], p.cnt+1));
                        }
                    }
                }
            }
        }
        return answer;
    }
}