
/**
 * 문자열 P, T
 * T에서 P가 몇 번 어느 위치에 있는지
 * P: 패턴
 * T: 텍스트
 * 
 * n: T의 길이
 * m: P의 길이
 * 
 * 텍스트에서 패턴을 검사하며 나아간다
 * 탐색하는 첫번째 텍스트 이후로 동일한 패턴을 보이는 텍스트가 나오면 후보 탐색지를 업데이트 한다
 * 탐색을 진행하며 기존의 패턴이 틀렸을 경우 후보 번지부터 다시 시작한다
 */
import java.util.*;
import java.io.*;

public class Main {
	
	static List<Integer> answer = new ArrayList<>();
	static int[] lps;
	
	public static void main(String[] args) throws IOException {
		 BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		 BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		 
		 
		 String T = br.readLine();
		 String P = br.readLine();
		 
		 lps = new int[P.length()];
		 
		 findLps(P);
		 kmp(T,P);
		 
		 System.out.println(answer.size());
		 answer.forEach(o->System.out.print(o + " "));
	}
	
	private static void findLps(String pattern) {
		//0번째는 항상 0
		lps[0] = 0;
		int index = 0;
		for(int i=1; i<pattern.length(); i++) {
			
			while(index > 0 && pattern.charAt(i) != pattern.charAt(index)) {
				index = lps[index-1];
			}
			
			if(pattern.charAt(i) == pattern.charAt(index)) {
				index++;
				lps[i] = index;
			}
			
		}
//		System.out.println(Arrays.toString(lps));
	}
	
	private static void kmp(String text, String pattern) {
		
		int index = 0;
		
		for(int i=0; i<text.length(); i++) {
			while(index >0 && text.charAt(i) != pattern.charAt(index)) {
				index = lps[index-1];
			}
			
			if(text.charAt(i) == pattern.charAt(index)) {
				if(index == pattern.length()-1) {
					answer.add(i-index+1);
					index = lps[index];					
				}
				else {
					index++;
				}
			}
		}
	}
}
