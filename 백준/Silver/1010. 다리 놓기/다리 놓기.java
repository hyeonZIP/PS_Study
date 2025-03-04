import java.util.Scanner;
import java.util.Vector;

public class Main {
	public static void main(String[] args)
	{
		Scanner s = new Scanner(System.in);
		Vector v = new Vector<Integer>();
		
		int test , N, M = 0;
		
		test = s.nextInt();
		
		for(int i=0; i<test; i++)
		{
			int arr[][] = new int[100][100];
			N = s.nextInt();
			M = s.nextInt();
			
			for(int j=0; j<=M; j++)
			{
				arr[1][j] = j;//N이 1일 경우 M의 크기만큼 경우의 수가 생긴다.
			}
			
			for(int j=2; j<=M; j++)
			{
				for(int k=j; k<=M; k++)
				{
					for(int l=k; l>=j; l--)
					{
						arr[j][k] = arr[j][k] + arr[j-1][l-1];
						
					}
				}
			}
			v.add(arr[N][M]);
		}
		
		for(int i=0; i<test; i++)
		{
			System.out.println(v.get(i));
		}
	}
}
