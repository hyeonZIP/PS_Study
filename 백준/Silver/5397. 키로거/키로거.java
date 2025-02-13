
/*
 * 백스페이스, 화살표로 인한 입력 위치 변동
 * 빈번한 원소의 삽입과 삭제
 */

import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		for(int i=0; i<T; i++) {
			LinkedList<Character> ll = new LinkedList<>();
			String pwd = br.readLine();
			
			int cursor = 0;
			for(char c : pwd.toCharArray()) {
				switch(c) {
				case '<':
					if(cursor - 1 >= 0) {
						cursor--;
					}
					break;
				case '>':
					if(cursor + 1 <= ll.size()) {
						cursor++;
					}
					break;
				case '-':
					if(cursor - 1 >=0) {
						cursor--;
						ll.remove(cursor);						
					}
					break;
				default:
					ll.add(cursor, c);
					cursor++;
					
				}//switch
			}//for
			
			ll.forEach(o->sb.append(o));
			sb.append("\n");
		}//for
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
}
