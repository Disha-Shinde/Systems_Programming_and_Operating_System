import java.util.*;

public class PageReplacement
{
	Scanner sc = new Scanner(System.in);
	String seq = "";
	String page[];
	int framesize;
	int fault = 0, hit = 0;
	
	void input()
	{		
		System.out.print("\nEnter the frame size : ");
		framesize = sc.nextInt();
		
		System.out.print("Enter the sequence : ");
		seq = sc.next();
		page = seq.split(",");
	}
	
	void fifo()
	{
		fault = 0;
		hit = 0;
		Queue<String> q = new LinkedList<>();
		
		for(int i=0; i<page.length; i++)
		{
			if(q.contains(page[i]))
			{
				hit++;
			}
			else
			{
				fault++;
				
				if(q.size() < framesize)
				{
					q.add(page[i]);
				}
				else
				{
					q.remove();
					q.add(page[i]);
				}
			}
			System.out.println(q);
		}
		
		System.out.println("Page fault : " + fault);
		System.out.println("Page hit : " + hit);
		
		float f = (float)fault/(page.length) * 100;
		float h = (float)hit/(page.length) * 100;
		
		System.out.println("Fault rate: " + f);
		System.out.println("Hit rate: " + h);
	}
	
	void lru()
	{
		fault = 0;
		hit = 0;
		List<String> l = new LinkedList<>();
		
		for(int i=0; i<page.length; i++)
		{
			if(l.contains(page[i]))
			{
				hit++;
				l.remove(page[i]);
				l.add(page[i]);
			}
			else
			{
				fault++;
				
				if(l.size() < framesize)
				{
					l.add(page[i]);
				}
				else
				{
					l.remove(0);
					l.add(page[i]);
				}
			}
			
			System.out.println(l);
		}
		
		System.out.println("Page fault : " + fault);
		System.out.println("Page hit : " + hit);
		
		float f = (float)fault/(page.length) * 100;
		float h = (float)hit/(page.length) * 100;
		
		System.out.println("Fault rate: " + f);
		System.out.println("Hit rate: " + h);
	}
	
	void optimal()
	{
		fault = 0;
		hit = 0;
		List<String> l = new LinkedList<>();
		
		for(int i=0; i<page.length; i++)
		{
			if(l.contains(page[i]))
			{
				hit++;
				l.remove(page[i]);
				l.add(page[i]);
			}
			else
			{
				fault++;
				
				if(l.size() < framesize)
				{
					l.add(page[i]);
				}
				else
				{
					List<String> a = new LinkedList<>();
					int j=1;
					
					for(int k = i+1; k != page.length; k++)
					{
						if(l.contains(page[k]) && !a.contains(page[k]))
						{
							l.remove(page[k]);
							l.add(page[k]);
							a.add(page[k]);
							j++;
						}
						
						if(j == framesize)
							break;
					}
					l.remove(0);
					l.add(page[i]);
				}
			}
			
			System.out.println(l);
		}
		
		System.out.println("Page fault : " + fault);
		System.out.println("Page hit : " + hit);
		
		float f = (float)fault/(page.length) * 100;
		float h = (float)hit/(page.length) * 100;
		
		System.out.println("Fault rate: " + f);
		System.out.println("Hit rate: " + h);
	}
	
	public static void main(String[] args) 
	{
		PageReplacement p = new PageReplacement();
		
		while(true)
		{
			System.out.println("\n1.FIFO \n2.LRU \n3.Optimal");
			System.out.print("Enter your choice : ");
			int ch = p.sc.nextInt();
			
			switch(ch)
			{
				case 1: p.input();
						p.fifo();
						break;
						
				case 2: p.input();
						p.lru();
						break;
						
				case 3: p.input();
						p.optimal();
			}
			
		}		
	}
}

