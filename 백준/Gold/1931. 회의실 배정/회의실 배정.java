import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 문제
한 개의 회의실이 있는데 이를 사용하고자 하는 N개의 회의에 대하여 회의실 사용표를 만들려고 한다.
각 회의 I에 대해 시작시간과 끝나는 시간이 주어져 있고, 
각 회의가 겹치지 않게 하면서 회의실을 사용할 수 있는 회의의 최대 개수를 찾아보자.
단, 회의는 한번 시작하면 중간에 중단될 수 없으며 한 회의가 끝나는 것과 동시에 다음 회의가 시작될 수 있다.

입력
첫째 줄에 회의의 수 N(1 ≤ N ≤ 100,000)이 주어진다. 
둘째 줄부터 N+1 줄까지 각 회의의 정보가 주어지는데 이것은 공백을 사이에 두고 회의의 시작시간과 끝나는 시간이 주어진다.

출력
첫째 줄에 최대 사용할 수 있는 회의의 최대 개수를 출력한다.
 */

public class Main {
	
	static int N;
	static MeetingRoom[] arr;
	static int maxCount = 0;
	
	public static class MeetingRoom implements Comparable<MeetingRoom>{
		int start;
		int end;
		
		public MeetingRoom(int start, int end) {
			this.start = start;
			this.end = end;
		}

		@Override
		public int compareTo(MeetingRoom meetingroom) {
			
			if(end == meetingroom.end) {
				return start - meetingroom.start;
			}
			return end-meetingroom.end;
		}
		
		
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		arr = new MeetingRoom[N];
		
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			
			arr[i] = new MeetingRoom(start,end);
		}
		
		Arrays.sort(arr);
		
		int count = 0;
		int currentTime = 0;
		
		for(int i=0; i<N; i++) {
			if(currentTime <= arr[i].start) {
				count++;
				currentTime = arr[i].end;
			}
		}
		
		System.out.println(count);
		
	}
}
