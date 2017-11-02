import java.util.*;
class InterfaceADT {
	String name;
	List<String> interfaces = new ArrayList<String>();
	List<String> methods = new ArrayList<String>();
	//String content;
	static List<String> itfcontent = new ArrayList<String>();
	static List<String> itfname = new ArrayList<String>();
	static List<String> getInterfaces(List<String> lsInterface, String classCon)
	{
		List<String> rs = new ArrayList<String>();
		String line = classCon.substring(0, classCon.indexOf("{"));
		if(line.contains("implements")==false)
			rs.add("null");
		for(String itf : lsInterface)
		{
			if(line.contains(itf))
			{
				rs.add(itf);
			}
		}
		return rs;
	}
	public static void readInterfaceFromFile(String fileName)
    {
		//List<String> lsInterface = new ArrayList<String>();
        List<String> temp = Utils.readContentFromFile(fileName);
        //System.out.println(temp.size());
        for(int i = 0;i<temp.size();)
        {
            int index = 0;
            if(temp.get(i).contains("interface"))
            {
                
                StringBuilder tmp = new StringBuilder();
                for(int j = i + 1;j<temp.size();++j)
                {
                    if(temp.get(j).contains("interface"))
                    {
                        index = j; 
                        break;
                    } 
                    else if (j==temp.size()-1)
                    {
                        index = j;
                        break;
                    }
                }
                List<String> strs = temp.subList(i,index);
                for(String str : strs)
                {
                    tmp.append(str);
                    tmp.append("\n");
                }
                //lsInterface.add(tmp.toString());
                //lsInterface.add(strs.get(0).substring(strs.get(0).lastIndexOf(" ")).replaceAll(" ", ""));
                itfname.add(strs.get(0).substring(strs.get(0).lastIndexOf(" ")).replaceAll(" ", ""));
                i = index;
            }
            else i++;
        }
        //return lsInterface;
    }
	static void getInterfacesContent(String fileName)
	{
		List<String> temp = Utils.readContentFromFile(fileName);
        //System.out.println(temp.size());
        for(int i = 0;i<temp.size();)
        {
            int index = 0;
            if(temp.get(i).contains("interface"))
            {
                
                StringBuilder tmp = new StringBuilder();
                for(int j = i + 1;j<temp.size();++j)
                {
                    if(temp.get(j).contains("interface")||temp.get(j).contains("class"))
                    {
                        index = j; 
                        break;
                    } 
                }
                List<String> strs = temp.subList(i,index);
                for(String str : strs)
                {
                    tmp.append(str);
                    tmp.append("\n");
                }
                itfcontent.add(tmp.toString());
                //lsInterface.add(strs.get(0).substring(strs.get(0).lastIndexOf(" ")).replaceAll(" ", ""));
                i = index;
            }
            else i++;
        }
	}
	public String getName()
	{
		return this.name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public List<String> getMethods()
	{
		return this.methods;
	}
	void setMethods()
	{
		//List<String> rs = new ArrayList<String>();
		String stline = itfcontent.get(itfname.indexOf(this.name));
		stline = stline.substring(stline.indexOf("{")+1, stline.lastIndexOf(";"));
		//System.out.println(stline);
		String lsmethods [] = stline.split(" \n");
		for(String i : lsmethods)
		{
			//System.out.print(i+"\n");
			if(i!=null)
			{
				while(i.indexOf(" ")==0)
				{
					i = i.substring(1);
				}
				methods.add(i.replaceAll("\n", ""));
			}
		}
		//return rs;
	}
	
	void setInterfaces()
	{
		//List<String> rs = new ArrayList<String>();
		String stline = itfcontent.get(itfname.indexOf(this.name));
		stline = stline.substring(0,stline.indexOf("{"));
		//System.out.println(stline);
		if(stline.contains("implements")==false)
		{
			interfaces.add("null");
		}
		for(String i : itfname)
		{
			if(stline.contains(i) && i!=this.name)
			{
				interfaces.add(i);
			}
		}
		//return rs;
	}
	public static InterfaceADT createInterface(String content, String name)
	{
		List<String> interfaces = new ArrayList<String>();
		List<String> methods = new ArrayList<String>();
		InterfaceADT ex = new  InterfaceADT(name,interfaces,methods);
		ex.setInterfaces();
		ex.setMethods();
		return ex;
	}
	public InterfaceADT(String name, List<String> interfaces, List<String> methods)
	{
		this.name = name;
		this.interfaces = interfaces;
		this.methods = methods;
	}
}
