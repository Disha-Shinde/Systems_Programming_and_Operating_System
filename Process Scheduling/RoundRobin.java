import java.util.*;

public class RoundRobin 
{
	int wait[], tat[], bt[], rt[], process[], n, rp, quantum, tot_wait=0, tot_tat=0;
	float avg_wait, avg_tat;
	
	void round()
	{
		Scanner sc = new Scanner(System.in);
		
		System.out.print("Enter number of process : ");
		n = sc.nextInt();
		rp = n;
		
		System.out.print("Enter quantum : ");
		quantum = sc.nextInt();

		bt = new int[n];
		process = new int[n];
		wait = new int[n];
		tat = new int[n];
		rt = new int[n];
		
		System.out.println("\nEnter burst time of processes");
		for(int i=0; i<n; i++)
		{
			process[i] = i+1;
			System.out.print("\nProcess[" + (i+1) + "] : ");
			
			bt[i] = sc.nextInt();
			rt[i] = bt[i];
		}
		
		System.out.println("\nGantt Chart");
		System.out.print("\n| 0 ");
		
		int i=0, time=0;
		
		while(rp!=0)
		{
			if(rt[i] > quantum)
			{
				rt[i] = rt[i] - quantum;
				time += quantum;
				System.out.print(" | P" + (i+1) + " | ");
				System.out.print(time);
			}
			else if(rt[i] <= quantum && rt[i] > 0)
			{
				time += rt[i];
				rt[i] = 0;
				System.out.print(" | P" + (i+1) + " | ");
				System.out.print(time);
				
				rp--;
				
				tat[i] = time;
				wait[i] = tat[i] - bt[i];
			}
			
			i++;
			if(i == n)
				i = 0;
		}
		System.out.println(" |");
		
		System.out.println("\n\nProcess |   Burst time   | Waiting Time | Turnaround Time");
		for(i=0; i<n; i++)
		{	
			System.out.println("  P" + process[i] + "\t\t" + bt[i] + "\t\t" + wait[i] + "\t\t" + tat[i]);
		}
		
		
		for(i=0; i<n; i++)
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
		RoundRobin r = new RoundRobin();
		r.round();
		
	}

}
