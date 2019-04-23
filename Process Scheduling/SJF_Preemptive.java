import java.util.*;

public class SJF_Preemptive 
{
	int wait[], tat[], finish_time[], count = 0, bt[], rt[], at[], process[], n, small, tot_wait=0, tot_tat=0;
	float avg_wait, avg_tat;
	
	void sjf()
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
		rt = new int[10];
		
		System.out.println("\nEnter arrival time and burst time of processes");
		for(int i=0; i<n; i++)
		{
			process[i] = i+1;
			System.out.print("\nProcess[" + (i+1) + "] : ");
			at[i] = sc.nextInt();
			bt[i] = sc.nextInt();
			rt[i] = bt[i];
		}
		
		System.out.println("\nProcess | Arrival Time |  Finish Time  |   Burst time   | Waiting Time | Turnaround Time");
		
		rt[9] = 999;
		int time;
		for(time=0; count!=n; time++)
		{
			small = 9;
			
			for(int i=0; i<n; i++)
			{
				if(at[i] <= time && rt[i] < rt[small] && rt[i] > 0)
					small = i;
			}
			
			rt[small]--;
			
			if(rt[small] == 0)
			{
				count++;
				finish_time[small] = time+1;
				tat[small] = finish_time[small] - at[small];
				wait[small] = tat[small] - bt[small];
				
				System.out.println("  P" + process[small] + "\t\t" + at[small] + "\t\t" + finish_time[small] + "\t\t" + bt[small] + "\t\t" + wait[small] + "\t\t" + tat[small]);
			}
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
		SJF_Preemptive s = new SJF_Preemptive();
		s.sjf();
		
	}

}
