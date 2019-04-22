import java.io.*;

class PTAB
{
	String name;
}

class KPDTAB
{
	String name;
	String value;
}

class MNT
{
	String mname;
}

public class Macro_preprocessor 
{
	int Mptr;
	int m;
	int Iptr;
	int Pptr;
	int Kptr;
	
	PTAB ptab[] = new PTAB[50];
	KPDTAB kpd[] = new KPDTAB[50];
	MNT mnt[] = new MNT[5];
	
	Macro_preprocessor()
	{
		Mptr = 0;
		m = 0;
		Iptr = 0;
		Pptr = 0;
		Kptr = 0;
	}
	
	int search_kpd(String token)
	{
		for(int j=0; j < Pptr; j++)
		{
			if(kpd[j].name.equals(token))
			{
				return j;	
			}
		}
		return -1;
		
	}
	
	int search_ptab(String token)
	{
		for(int j=0; j < Pptr; j++)
		{
			if(ptab[j].name.equals(token))
			{
				return j;	
			}
		}
		return -1;
		
	}
	
	void PassOne() throws IOException
	{
		FileWriter fw = new FileWriter("MDTTAB.txt");
		
		File f = new File("input.txt");
		BufferedReader br = new BufferedReader(new FileReader(f));
		
		String line = new String();
		
		while((line = br.readLine()) != null)
		{
			String token[] = line.split(" ");
			
			if(token[0].equals("MACRO"))
			{
				System.out.println(token[0]);
				fw.write(token[0]);
				fw.write("\n");
				
				line = br.readLine();
				String _token[] = line.split(" ");
				
				String IR = _token[0];
				
				for(int i = 1; i < _token.length; i++)
				{
					
					String p = "";
					int j=0, flag=0;
					while(j != _token[i].length())
					{
						if(_token[i].charAt(j) == '=')
						{
							flag = 1;
							break;
						}
						else
						{
							p += (_token[i].charAt(j));
						}
						j++;
					}
					ptab[Pptr] = new PTAB();
					ptab[Pptr].name = p;
					
					if(flag == 1)
					{
						kpd[Kptr] = new KPDTAB();
						kpd[Kptr].name = p;
						p = "";
						for(int k=j+1; k != _token[i].length(); k++)
						{
							p += (_token[i].charAt(k));
						}
						kpd[Kptr].value = p;
						
						IR = IR.concat(" K" + Kptr);
						Kptr++;
					}
					else
					{
						IR = IR.concat(" P" + Pptr);
						Pptr++;
					}
				}
				
				mnt[m] = new MNT();		
				mnt[m].mname = IR;
				m++;
				
				System.out.println(IR + " ");
				fw.write(IR + "\n");
				
				String macro = new String();
				
				while(!(macro = br.readLine()).equals("MEND"))
				{
					IR = "";
					_token = macro.split(" ");
					
					int i = 0;
					while(i != _token.length)
					{
						if(_token[i].charAt(0) == '&')
						{
							int k = search_kpd(_token[i]);
							
							if(k != -1)
							{
								IR = IR.concat("K" + k);
							}
							else
							{								
								int j = search_ptab(_token[i]);
								
								if(j != -1)
								{
									IR = IR.concat("P" + j);
								}
								else
								{
									ptab[Pptr] = new PTAB();
									ptab[Pptr].name = _token[i];
									IR = IR.concat("P" + Pptr);
									Pptr++;
								}
							}
							
						}
						else
						{
							IR += (_token[i] + " ");
						}
						i++;
					}
					
					System.out.println(IR + " ");
					fw.write(IR + "\n");
				}	
				System.out.println("MEND");
				fw.write("MEND");	
			}

			fw.write("\n");
		}
		
		br.close();
		fw.close();
		
	}
	
	void table() throws IOException
	{
		FileWriter fw1 = new FileWriter("PTAB.txt");
		FileWriter fw3 = new FileWriter("KPDTAB.txt");
		FileWriter fw4 = new FileWriter("MNTTAB.txt");
		
		System.out.println("\nPTAB");
		for(int j=0; j < Pptr; j++)
		{
			fw1.write(j + " " + ptab[j].name + "\n");
			System.out.println(j + " " + ptab[j].name);
		}
		System.out.println();
		
		System.out.println("KPDTAB");
		for(int j=0; j < Kptr; j++)
		{
			fw3.write(j + " " + kpd[j].name + " " + kpd[j].value);
			System.out.println(j + " " + kpd[j].name + " " + kpd[j].value);
		}
		System.out.println();

		System.out.println("MNTTAB");
		for(int j=0; j < m; j++)
		{
			fw4.write(mnt[j].mname + "\n");
			System.out.println(mnt[j].mname);
		}
		
		fw1.close();
		fw3.close();
		fw4.close();
	}
	
	public static void main(String[] args) throws IOException
	{
		Macro_preprocessor m = new Macro_preprocessor();
		m.PassOne();
		m.table();

	}

}
