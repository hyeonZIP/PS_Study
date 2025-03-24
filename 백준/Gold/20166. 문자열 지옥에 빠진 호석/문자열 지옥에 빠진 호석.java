import java.io.*;
import java.util.*;

public class Main {
	
	static public class Pos{
		private int y;
		private int x;
		private String collected;
		private int searchCount;
		
		public Pos(int y, int x, String collected, int searchCount) {
			this.y = y;
			this.x = x;
			this.collected = collected;
			this.searchCount = searchCount;
		}
	}
	
	static int height,width,K, maxSearchCount;
	static String[] godLike;
	static String[][] map;
	static HashMap<String,Integer> god = new HashMap<>();
	static int[] dy = {0,0,1,-1,1,-1,1,-1};
	static int[] dx = {1,-1,0,0,1,-1,-1,1};
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args)throws IOException{
		
		init();
		
		for(int y=0; y<height; y++) {
			
			for(int x=0; x<width; x++) {
				
				bfs(y,x);
				
			}
		}
		
		for(String s : godLike) {
			sb.append(god.get(s)).append("\n");
		}
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		bw.write(sb+"");
		bw.flush();
		bw.close();
	}
	
	private static void bfs(int initY, int initX) {
		ArrayDeque<Pos> s = new ArrayDeque<>();
		
		
		s.push(new Pos(initY,initX,map[initY][initX],1));
		
		while(!s.isEmpty()) {
			Pos p = s.pop();
			
			int y = p.y;
			int x = p.x;
			String currentString = p.collected;
			int searchCount = p.searchCount;
			
			if(god.containsKey(currentString)) god.put(currentString,god.get(currentString)+1);
			
			if(searchCount >= maxSearchCount) continue;
			
			for(int i=0; i<8; i++) {
				int py = y + dy[i];
				int px = x + dx[i];
				
				if(py<0) py=height-1;
				if(py>=height) py=0;
				if(px<0) px=width-1;
				if(px>=width) px=0;
				
				s.push(new Pos(py,px,currentString + map[py][px],searchCount+1));
			}
		}
	}
	
	private static void init() throws IOException {
		
		BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		height = Integer.parseInt(st.nextToken());
		width = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		godLike = new String[K];
		map = new String[height][width];
		
		for(int y = 0; y<height; y++) {
			String s = br.readLine();
			for(int x = 0; x<width; x++) {
				map[y][x] = Character.toString(s.charAt(x));
			}
		}
		
		for(int y = 0; y<K; y++) {
			String s = br.readLine();
			maxSearchCount = Math.max(maxSearchCount, s.length());
			god.put(s, 0);
			godLike[y] = s;
		}
	}
}
