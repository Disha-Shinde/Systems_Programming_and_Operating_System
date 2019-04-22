import java.io.*;
import java.util.HashMap;

public class Macro_preprocessor2
{
	HashMap <String, String>PTAB = new HashMap<String, String>();
	HashMap <String, String>KPDTAB = new HashMap<String, String>();
	
	String MDTTAB[] = new String[50];
	String MNTTAB[] = new String[5];
	String k[] = new String[5];
	
	void getInput() throws IOException
	{	
		File f1 = new File("MNTTAB.txt");
		BufferedReader br1 = new BufferedReader(new FileReader(f1));
		MNTTAB[0] = br1.readLine();
		br1.close();
		
		File f2 = new File("PTAB.txt");
		BufferedReader br2 = new BufferedReader(new FileReader(f2));
		
		File f3 = new File("KPDTAB.txt");
		BufferedReader br3 = new BufferedReader(new FileReader(f3));
		
		String line = new String();
		
		while((line = br2.readLine()) != null)
		{
			String token[] = line.split(" ");
			PTAB.put(token[0], token[1]);
		}
		while((line = br3.readLine()) != null)
		{
			String token[] = line.split(" ");
			KPDTAB.put(token[0], token[1]);
			
			int i=0;
			if(token.length == 3)
			{
				k[i] = token[2];
				i++;
			}
		}
		

		br2.close();
		br3.close();
	}
	
	
	void PassTwo() throws IOException
	{
		FileWriter fw = new FileWriter("output.txt");

		File f = new File("IR.txt");
		BufferedReader br = new BufferedReader(new FileReader(f));
		
		File f1 = new File("MDTTAB.txt");
		BufferedReader br1 = new BufferedReader(new FileReader(f1));
		
		System.out.println("**** OUTPUT ****");
		
		String line_ir = new String();
		String line_mdt = new String();
		
		String macroname[] = MNTTAB[0].split(" ");
		int flag = 0;
		
		while((line_ir = br.readLine()) != null)
		{
			String token[] = line_ir.split(" ");
			if(token[0].equals(macroname[0]))
			{
				//token contains macro call (gives values of parameters)
				//macroname contains indices of parameters

				for(int i=1; i!=macroname.length; i++)
				{
					String m[] = macroname[i].split("");
					if(m[0].equals("P"))
					{
						PTAB.put(m[1], token[i]);
					}
					else if(m[0].equals("K"))
					{
						if(token.length != macroname.length)
						{ 
							int x = new Integer(m[1]);
							KPDTAB.put(m[1], k[x]);
						}
						else
							KPDTAB.put(m[1], token[i]);
					}
				}
				
				while(!(line_mdt = br1.readLine()).equals("MEND"))
				{
					String mdt[] = line_mdt.split(" ");
					String IR = "";
					//System.out.println(flag);
					if(flag == 0)
					{
						flag = 1;
						continue;
					}
					else if(flag == 1)
					{
						flag = 2;
						
						continue;
					}

					for(int i=0; i!=mdt.length; i++)
					{
						if(mdt[i].charAt(0) == 'P')
						{
							String p[] = mdt[i].split("");
							IR += (PTAB.get(p[1]) + " ");
							
						}
						else if(mdt[i].charAt(0) == 'K')
						{
							String k[] = mdt[i].split("");
							IR += (KPDTAB.get(k[1]) + " ");
						}
						else
						{
							IR += (mdt[i] + " ");
						}
					}
					fw.write(IR + "\n");
					System.out.println(IR);
				}
			}
			else
			{
				fw.write(line_ir + "\n");
				System.out.println(line_ir);
			}
		}
		fw.close();
		br.close();
		br1.close();
	}
	public static void main(String[] args) throws IOException
	{
		Macro_preprocessor2 m = new Macro_preprocessor2();
		m.getInput();
		m.PassTwo();
	}
}