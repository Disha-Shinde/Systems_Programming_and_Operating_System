import java.util.*;

public class FCFS_arrival 
{
	int wait[], tat[], finish_time[], bt[], at[], process[], n, tot_wait=0, tot_tat=0;
	float avg_wait, avg_tat;
	
	void fcfs()
	{
		Scanner sc = new Scanner(System.in);
		
		System.out.print("Enter number of process : ");
		n = sc.nextInt();
		
		at = new int[n];
		bt = new int[n];
		process = new int[n];
		wait = new int[n];
		tat = new int[n];
		finish_time = new int[n];
		
		System.out.println("\nEnter arrival time and burst time of processes");
		for(int i=0; i<n; i++)
		{
			process[i] = i+1;
			System.out.print("\nProcess[" + (i+1) + "] : ");
			at[i] = sc.nextInt();
			bt[i] = sc.nextInt();
		}
		
		//Sort according to arrival time
		for(int i=0; i<n; i++)
		{
			for(int j=0; j<n-(i+1); j++)
			{
				if(at[j] > at[j+1])
				{
					int temp = at[j+1];
					at[j+1]= at[j];
					at[j] = temp;
					
					temp = bt[j+1];
					bt[j+1]= bt[j];
					bt[j] = temp;
					
					temp = process[j+1];
					process[j+1]= process[j];
					process[j] = temp;
				}
			}
		}
		
		System.out.println("\nProcess | Arrival Time |  Finish Time  |   Burst time   | Waiting Time | Turnaround Time");
		
		//Calculate finish time
		for(int i=0; i<n; i++)
		{
			if(i==0 || at[i] > finish_time[i-1])
			{
				finish_time[i] = at[i] + bt[i];
			}
			else
			{
				finish_time[i] = finish_time[i-1] + bt[i];
			}
			
			tat[i] = finish_time[i] - at[i];
			wait[i] = tat[i] - bt[i];
			
			System.out.println("  P" + process[i] + "\t\t" + at[i] + "\t\t" + finish_time[i] + "\t\t" + bt[i] + "\t\t" + wait[i] + "\t\t" + tat[i]);
		}
		
		for(int i=0; i<n; i++)
		{
			tot_wait = tot_wait + wait[i];
			tot_tat = tot_tat + tat[i];
		}
		
		avg_wait = (float)tot_wait / n;
		avg_tat = (float)tot_tat / n;
		
		System.out.println("\nAverage Waiting time : " + avg_wait);
		System.out.println("Average Turnaround Time : " + avg_tat);		
		
		sc.close();
	}

	public static void main(String[] args) 
	{
		FCFS_arrival f = new FCFS_arrival();
		f.fcfs();
	}

}
