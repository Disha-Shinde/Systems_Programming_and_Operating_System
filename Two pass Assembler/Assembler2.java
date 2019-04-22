import java.io.*;
import java.util.HashMap;

public class Assembler2
{
	HashMap <String, String>Symbol = new HashMap<String, String>();
	HashMap <String, String>Literal = new HashMap<String, String>();

	void getInput() throws IOException
	{
		File f1 = new File("sym.txt");
		BufferedReader br1 = new BufferedReader(new FileReader(f1));
		File f2 = new File("lit.txt");
		BufferedReader br2 = new BufferedReader(new FileReader(f2));
		String line = new String();
		
		while((line = br1.readLine()) != null)
		{
			String token[] = line.split(" ");
			Symbol.put(token[0], token[2]);
		}
		
		while((line = br2.readLine()) != null)
		{
			String token[] = line.split(" ");
			Literal.put(token[0], token[2]);
		}
		
		br1.close();
		br2.close();
	}
	
	void PassTwo() throws IOException
	{
		FileWriter fw = new FileWriter("output.txt");
		File f = new File("IR.txt");
		BufferedReader br = new BufferedReader(new FileReader(f));
		String line = new String();
		
		while((line = br.readLine()) != null)
		{
			String token[] = line.split(" ");
			int i=0;
			
			while(i != token.length)
			{
				if(!token[i].equals("S") && !token[i].equals("L"))
				{
					fw.write(token[i] + " ");
				}
				else if(token[i].equals("S"))
				{
					String addr = (String)Symbol.get(token[i+1]);
					fw.write(addr + " ");
					i++;
				}
				else if(token[i].equals("L"))
				{
					String addr = (String)Literal.get(token[i+1]);
					fw.write(addr + " ");
					i++;
				}
				i++;
			}
			fw.write("\n");
		}
		fw.close();
		br.close();
	}
	
	public static void main(String[] args) throws IOException
	{
		Assembler2 a = new Assembler2();
		a.getInput();
		a.PassTwo();
	}
}