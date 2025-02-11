
import java.util.*;
import java.io.*;

public class Main {
	
	static ArrayList<Integer> list;
	static boolean[] visited;
	static int[] arr;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int N = Integer.parseInt(br.readLine());
		
		arr = new int[N+1];
		visited = new boolean[N+1];
		list = new ArrayList<>();
		for(int i=1; i<=N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		
		for(int i=1; i<=N; i++) {
			ArrayDeque<Integer> stack = new ArrayDeque<>();
			dfs(i, i, stack);
		}
		System.out.println(list.size());
		list.stream().sorted().forEach(System.out::println);
	}
	
	private static void dfs(int start, int end, ArrayDeque<Integer> stack) {
		//start 값은 계속 변하고 end는 변하지 않음
		
		//처음 들어올 떄 stack은 비어 있어서 처음 들어온 값은 추가되지 않음
		if(stack.size()>0 && start==end) {
			list.add(end);
		}
		
		if(!visited[arr[start]]) {
			
			if(!stack.contains(arr[start])) {
				stack.push(arr[start]);
			}
			
			visited[arr[start]] = true;
			dfs(arr[start],end,stack);
			visited[arr[start]] = false;
		}
	}
}
