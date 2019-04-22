import java.util.*;

public class bankers 
{
	void safe(int avail[], int alloc[][], int max[][], int need[][], int n, int m) 
	{
		boolean[] finish = new boolean[n];
		Arrays.fill(finish, false);
		boolean fnd = false;
		
		int cnt = 0;
		int work[] = avail;
		int[] safe_seq = new int[n];
		
		while(cnt < n)
		{
			int i;
			for(i=0; i<n; i++)
			{
				if(finish[i] == false)
				{
					int j;
					for(j=0; j<m; ++j)
					{
						if(need[i][j] > work[j])
							break;
					}
					if(j == m)
					{
						for(int k=0; k<m; k++)
							work[k] = work[k] + alloc[i][k];
						
						finish[i] = true;
						fnd = true;
						safe_seq[cnt] = i;
						cnt++;
					}
				}
			}
			if(fnd == false)
			{
				System.out.println("No safe sequence");
			}
		}
			
		System.out.println("Safe sequence is : ");
		for(int i=0; i<n; i++)
			System.out.print(safe_seq[i] + "\t");
	}
	

	public static void main(String[] args) 
	{
		int n, m;
		bankers b = new bankers();
		Scanner sc = new Scanner(System.in);
		
		System.out.print("Enter number of processes : ");
		n = sc.nextInt();
		
		System.out.print("Enter number of resources : ");
		m = sc.nextInt();
		
		int available[] = new int[m];
		int allocation[][] = new int[n][m];
		int maximum[][] = new int[n][m];
		int need[][] = new int[n][m];
		
		System.out.println("Enter the available array : ");
		for(int i=0; i<m; i++)
		{
			available[i] = sc.nextInt();
		}
		
		System.out.println("Enter the allocation matrix : ");
		for(int i=0; i<n; i++)
		{
			for(int j=0; j<m; j++)
			{
				allocation[i][j] = sc.nextInt();
			}
		}
		
		System.out.println("Enter the maximum matrix : ");
		for(int i=0; i<n; i++)
		{
			for(int j=0; j<m; j++)
			{
				maximum[i][j] = sc.nextInt();
			}
		}
		
		for(int i=0; i<n; i++)
		{
			for(int j=0; j<m; j++)
			{
				need[i][j] = maximum[i][j] - allocation[i][j];
			}
		}
		
		b.safe(available, allocation, maximum, need, n, m);
		sc.close();

	}

}
