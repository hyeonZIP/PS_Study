import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 임재현
 * 
 * 스위치 상태 1부터 N개
 * 
 * 남학생: 1
 * 여학생: 2
 * 
 * 학생들 1~N의 자연수 한개씩 가짐
 * 
 * 남학생은 자기가 받은 수의 배수인 스위치 번호의 상태를 바꾼다. 
 * 여학생은 자기가 받은 수의 스위치를 중심으로 좌우가 대칭이면서 가장 많은 스위치를 포함하는 구간을 찾아서,
 * 그 구간에 속한 스위치의 상태를 모두 바꾼다. 이때 구간에 속한 스위치 개수는 항상 홀수가 된다.
 */

public class Main {
	
	static int switchCount;
	
	static int[] switchState;
	
	static int studentCount;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		switchCount = Integer.parseInt(br.readLine());
		switchState = new int[switchCount+1];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=1; i<=switchCount; i++) {
			switchState[i] = Integer.parseInt(st.nextToken());
		}
		
		studentCount = Integer.parseInt(br.readLine());
		
		for(int i=0; i<studentCount; i++) {
			st = new StringTokenizer(br.readLine());
			int gender = Integer.parseInt(st.nextToken());
			int number = Integer.parseInt(st.nextToken());
			
			if(gender == 1) {//남학생
				for(int j=number; j<=switchCount;j+=number) {
					switchState[j] = switchState[j]==1 ? 0:1;
				}
			}
			else {//여학생
				switchState[number] = switchState[number] == 1 ? 0:1;				
				for(int j=number-1,k=number+1; 
						j>=1 && k<=switchCount && switchState[j]==switchState[k];
						j--,k++)
				{
					switchState[j] = switchState[j]==1 ? 0 : 1;
					switchState[k] = switchState[k]==1 ? 0 : 1;
				}
			}
		}
		
		for(int i=1; i<=switchCount; i++) {
			System.out.print(switchState[i] + " ");
			if(i%20==0) {
				System.out.println();
			}
		}
	}//main
}
