import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int N;
	static int M;
	static int R;
	
	static int[][] arr;
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	public static void main(String[] args) throws IOException{

		st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
				
		arr = new int[N][M];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<R; i++) {
			int calc = Integer.parseInt(st.nextToken());
			
			switch(calc) {
			case 1:
				for(int j=0; j<N/2; j++) {
					int[] temp = arr[j];
					arr[j] = arr[N-j-1];
					arr[N-j-1] = temp;
				}
				break;
			case 2:
				for(int j=0; j<N; j++) {
					for(int k=0; k<M/2; k++) {
						int temp = arr[j][k];
						arr[j][k] = arr[j][M-k-1];
						arr[j][M-k-1] = temp;
					}
				}
				break;
			case 3:
				int[][] arr3 = new int[M][N];
				for(int j=0; j<N; j++) {
					for(int k=0; k<M; k++) {
						arr3[k][N-j-1] = arr[j][k];
					}
				}
				int temp1 = M;
				M = N;
				N = temp1;
				arr = arr3;
				break;
			case 4:
				int[][] arr4 = new int[M][N];
				for(int j=0; j<N; j++) {
					for(int k=0; k<M; k++) {
						arr4[M-k-1][j] = arr[j][k];
					}
				}
				int temp2 = M;
				M = N;
				N = temp2;
				arr = arr4;
				break;
			case 5:
				for(int j=0; j<N/2; j++) {
					for(int k=0; k<M/2; k++) {
						int temp = arr[j][k];
						arr[j][k] = arr[j+N/2][k];
						arr[j+N/2][k] = arr[j+N/2][k+M/2];
						arr[j+N/2][k+M/2] = arr[j][k+M/2];
						arr[j][k+M/2] = temp;
					}
				}
				break;
			case 6:
				for(int j=0; j<N/2; j++) {
					for(int k=0; k<M/2; k++) {
						int temp = arr[j][k];
						arr[j][k] = arr[j][k+M/2];
						arr[j][k+M/2] = arr[j+N/2][k+M/2];
						arr[j+N/2][k+M/2] = arr[j+N/2][k];
						arr[j+N/2][k] = temp;
					}
				}
				break;
			}//switch
		}//for
		
		for(int[] i : arr) {
			for(int j : i) {
				System.out.print(j + " ");
			}
			System.out.println();
		}
	}
}
