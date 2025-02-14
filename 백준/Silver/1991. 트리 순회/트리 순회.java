/*
 * 전위 순회 > dfs로 재귀문으로 들어가기 전에 출력
 * 중위 순회 > dfs로 재귀문으로 들어가면서 return될 때 출력
 * 후위 순회 > dfs로 좌측 노드로 진행이 불가능하면 출력 후 return
 * 인접 리스트로 구현
 */

import java.util.*;
import java.io.*;

public class Main {
	
	static int N;
	static ArrayList<Character>[] adj;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		N = Integer.parseInt(br.readLine());
		
		adj = new ArrayList[N]; 
		
		for(int i=0; i<N; i++) {
			adj[i] = new ArrayList<>();
		}

		StringTokenizer st;
		
		for(int i = 0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			char node = st.nextToken().charAt(0);
			int nodeIdx = node - 65;//첫번째 입력 받은 문자에서 아스키 값을 빼서 인덱스화
			adj[nodeIdx].add(node);
			for(int j = 0; j<2; j++) {
				adj[nodeIdx].add(st.nextToken().charAt(0));
			}
		}
		
		preorder();
		inorder();
		postorder();
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
	
	
	
	
	private static void preorder() {
		preorderDFS(0);
		sb.append("\n");
	}
	
	private static void preorderDFS(int startNode) {
		sb.append(adj[startNode].get(0));
		for(int i=1; i<3; i++) {
			
			if(adj[startNode].get(i) == '.') {
				continue;
			}

			preorderDFS(adj[startNode].get(i)-65);
		}
	}
	
	
	
	
	
	private static void inorder() {
		inorderDFS(0);
		sb.append("\n");
	}
	
	private static void inorderDFS(int startNode) {

		if(adj[startNode].get(1) != '.') {
			inorderDFS(adj[startNode].get(1)-65);				
		}
		sb.append(adj[startNode].get(0));
		if(adj[startNode].get(2) != '.') {
			inorderDFS(adj[startNode].get(2)-65);			
		}
	}
	
	
	
	
	
	
	private static void postorder() {
		postorderDFS(0);
	}
	
	private static void postorderDFS(int startNode) {
		for(int i=1; i<3; i++) {
			
			if(adj[startNode].get(i) == '.') {
				continue;
			}
			postorderDFS(adj[startNode].get(i)-65);
		}
		
		sb.append(adj[startNode].get(0));
	}
}
