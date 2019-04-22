import java.io.*;
import java.util.HashMap;

class Symbol
{
	String sname;
	int addr;
}

class Literal
{
	String sname;
	int addr;
}

public class Assembler
{
	int LC, Sptr, Lptr;
	
	Symbol ST[] = new Symbol[20];
	Literal LT[] = new Literal[10];
	
	HashMap <String, Integer>MOT = new HashMap<String, Integer>();
	HashMap <String, Integer>POT = new HashMap<String, Integer>();
	HashMap <String, Integer>REG = new HashMap<String, Integer>();
	
	Assembler()
	{
		LC = 0;
		Sptr = 0;
		Lptr = 0;
		
		MOT.put("STOP", 0);
		MOT.put("ADD", 1);
		MOT.put("SUB", 2);
		MOT.put("MULT", 3);
		MOT.put("MOVER", 4);
		MOT.put("MOVEM", 5);
		MOT.put("COMP", 6);
		MOT.put("BC", 7);
		MOT.put("DIV", 8);
		MOT.put("READ", 9);
		MOT.put("PRINT", 10);
		
		POT.put("START", 1);
		POT.put("END", 2);
		POT.put("LTORG", 3);
		POT.put("ORIGIN", 4);
		POT.put("EQU", 5);
		
		REG.put("AREG", 1);
		REG.put("BREG", 2);
		REG.put("CREG", 3);
		REG.put("DREG", 4);
	}
	
	int search(String token)
	{
		for(int j=0; j < Sptr; j++)
		{
			if(ST[j].sname.equals(token))
				return j;
		}
		return -1;
	}
		
	void PassOne() throws IOException
	{
		FileWriter fw = new FileWriter("IR.txt");
		File f = new File("input.txt");
		BufferedReader br = new BufferedReader(new FileReader(f));
		String line = new String();while((line = br.readLine()) != null)
		{
			String token[] = line.split(" ");
			int i=0;
			while(i != token.length)
			{
				if(token.length > 4)
				{
					System.out.print("INVALID!!!!!");
					fw.write("INVALID\n");
				}
				else
				{
					if(token.length == 4)
					{
						search(token[i]);
					}
					else
					{
						if(POT.containsKey(token[i]))
						{
							System.out.print("AD " + POT.get(token[i]) + " ");
							fw.write("AD " + POT.get(token[i]) + " ");
							if(token[i].equals("START") || token[i].equals("ORIGIN"))
							{
								i++;
								LC = (Integer.parseInt(token[i])) - 1;
							}
							else if(token[i].equals("EQU"))
							{
								int a=0;
								for(int x=0; x<Sptr; x++)
								{
									if(ST[x].sname.equals(token[i+1]))
									{
										a = ST[x].addr;
										break;
									}
								}
								
								for(int y=0; y<Sptr; y++)
								{
									if(ST[y].sname.equals(token[i-1]))
									{
										ST[y].addr = a;
										break;
									}
								}
								i++;
							}
							else if(token[i].equals("LTORG"))
							{
								for(int x=0; x<Lptr; x++)
								{
									if(LT[x].addr == 99999)
									{
										LT[x].addr = LC;
										LC++;
									}
								}
								LC--;
							}
							else if(token[i].equals("END"))
							{
								for(int x=0; x<Lptr; x++)
								{
									if(LT[x].addr == 99999)
									{
										LT[x].addr = LC;
										LC++;
									}
								}
							}
						}
						else if(MOT.containsKey(token[i]))
						{
							System.out.print("IS " + MOT.get(token[i]) + " ");
							fw.write("IS " + MOT.get(token[i]) + " ");
						}
						else if(REG.containsKey(token[i]))
						{
							System.out.print("REG " + REG.get(token[i]) + " ");
							fw.write("REG " + REG.get(token[i]) + " ");
						}
						else if(token[i].charAt(0) == '=')
						{
							LT[Lptr] = new Literal();
							LT[Lptr].sname = token[i];
							LT[Lptr].addr = 99999;
							System.out.print("L " + Lptr + " ");
							fw.write("L " + Lptr + " ");
							Lptr++;
						}
						else if(token[i].equals("DS") || token[i].equals("DC"))
						{
							System.out.print(token[i] + " " + token[i+1] + " ");
							fw.write(token[i] + " " + token[i+1] + " ");
							int len = Integer.parseInt(token[i+1]);
							int s = search((String)token[i-1]);
							
							if(s == -1)
							{
								ST[Sptr] = new Symbol();
								ST[Sptr].sname = token[i-1];
								ST[Sptr].addr = LC;
								Sptr++;
							}
							else
							{
								ST[s].addr = LC;
							}
							
							if(token[i].equals("DS"))
							{
								LC = LC + (len-1);
							}
							i++;
						}
						else
						{
							int s = search((String)token[i]);
							System.out.print("S " + Sptr + " ");
							fw.write("S " + Sptr + " ");
							
							if(s == -1)
							{
								ST[Sptr] = new Symbol();
								ST[Sptr].sname = token[i];
								Sptr++;
							}
						}
					}
					i++;
				}
			}
			
			LC++;
			System.out.println();
			fw.write("\n");
		}
		
		br.close();
		fw.close();
	}
	
	void table() throws IOException
	{
		FileWriter fw1 = new FileWriter("sym.txt");
		FileWriter fw2 = new FileWriter("lit.txt");
		
		System.out.println();
		System.out.println("SYMTAB");
		for(int j=0; j < Sptr; j++)
		{
			System.out.println(ST[j].sname + " " + ST[j].addr);
			fw1.write(j + " " + ST[j].sname + " " + ST[j].addr + "\n");
		}
		
		System.out.println();
		System.out.println("LITTAB");
		for(int j=0; j < Lptr; j++)
		{
			System.out.println(LT[j].sname + " " + LT[j].addr);
			fw2.write(j + " " + LT[j].sname + " " + LT[j].addr + "\n");
		}
		
		fw1.close();
		fw2.close();
	}
	
	public static void main(String[] args) throws IOException
	{
		Assembler a = new Assembler();
		a.PassOne();
		a.table();
	}
}
