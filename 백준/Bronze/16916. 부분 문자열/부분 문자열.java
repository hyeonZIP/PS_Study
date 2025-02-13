
import java.util.*;
import java.io.*;

public class Main {
	
	static int[] lps;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String S = br.readLine();
		String P = br.readLine();
		
//		if(S.length() > P.length()) {
//			
//			makeLps(S,P);
//			kmp(S,P);
//		}
//		else {
//			makeLps(P,S);
//			kmp(P,S);
//		}
		makeLps(S,P);
		kmp(S,P);
		
	}
	
	private static void makeLps(String text, String pattern) {
		
		int idx = 0;
		int textLen = text.length();
		int patternLen = pattern.length();
		
		lps = new int[patternLen];//
		
		for(int i=1; i<patternLen; i++) {
			
			while(idx>0&&pattern.charAt(idx)!=pattern.charAt(i)) {
				idx = lps[idx-1];
			}
			
			if(pattern.charAt(idx) == pattern.charAt(i)) {
				idx++;
				lps[i] = idx;
			}	
		}
	}
	
	private static void kmp(String text, String pattern) {
		
		int idx = 0;
		int textLen = text.length();
		int patternLen = pattern.length();
		
		for(int i=0; i<textLen; i++) {
			
			while(idx>0&&pattern.charAt(idx)!=text.charAt(i)) {
				idx = lps[idx-1];
			}
			
			if(pattern.charAt(idx) == text.charAt(i)) {
				if(idx == patternLen-1) {
					System.out.println(1);
					return;
				}
				else {
					idx++;
				}
			}	
		}
		
		System.out.println(0);
		
	}
}
