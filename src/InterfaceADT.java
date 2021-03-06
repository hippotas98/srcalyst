import java.util.*;
class InterfaceADT {
	String name;
	List<String> interfaces = new ArrayList<String>();
	List<String> methods = new ArrayList<String>();
	static List<String> lsInterface = new ArrayList<String>();
	static List<String> lsInterface_name = new ArrayList<String>();
	static List<String> getInterfaces(List<String> lsInterface, String classCon){
		List<String> rs = new ArrayList<String>();
		String line = classCon.substring(0, classCon.indexOf("{"));
		if(line.contains("implements")==false)
			rs.add("null");
		for(String itf : lsInterface){
			if(line.contains(itf)){
				rs.add(itf);
			}
		}
		return rs;
	}
	public static void readInterfaceFromFile(String fileName){
        List<String> temp = Utils.readContentFromFile(fileName);
        for(int i = 0;i<temp.size();){
            int index = 0;
            if(temp.get(i).contains("interface")){
                StringBuilder tmp = new StringBuilder();
                for(int j = i + 1;j<temp.size();++j){
                    if(temp.get(j).contains("interface")){
                        index = j; 
                        break;
                    } 
                    else if (j==temp.size()-1){
                        index = j;
                        break;
                    }
                }
                List<String> strs = temp.subList(i,index);
                for(String str : strs){
                    tmp.append(str);
                    tmp.append("\n");
                }
                lsInterface_name.add(strs.get(0).substring(strs.get(0).lastIndexOf(" ")).replaceAll(" ", ""));
                i = index;
            }
            else i++;
        }
    }
	static void getInterfacesContent(String fileName){
		List<String> temp = Utils.readContentFromFile(fileName);
        for(int i = 0;i<temp.size();){
            int index = 0;
            if(temp.get(i).contains("interface")){
                StringBuilder tmp = new StringBuilder();
                for(int j = i + 1;j<temp.size();++j){
                    if(temp.get(j).contains("interface")||temp.get(j).contains("class")){
                        index = j; 
                        break;
                    } 
                }
                List<String> strs = temp.subList(i,index);
                for(String str : strs){
                    tmp.append(str);
                    tmp.append("\n");
                }
                lsInterface.add(tmp.toString());
                i = index;
            }
            else i++;
        }
	}
	public String getName(){
		return this.name;
	}
	public void setName(String name){
		this.name = name;
	}
	public List<String> getMethods(){
		return this.methods;
	}
	void setMethods(){
		String stline = lsInterface.get(lsInterface_name.indexOf(this.name));
		stline = stline.substring(stline.indexOf("{")+1, stline.lastIndexOf(";"));
		String lsmethods [] = stline.split(" \n");
		for(String i : lsmethods){
			if(i!=null&&i.indexOf("//")!=0){
				while(i.indexOf(" ")==0){
					i = i.substring(1);
				}
				methods.add(i.replaceAll("\n", ""));
			}
		}
	}
	
	void setInterfaces(){
		String stline = lsInterface.get(lsInterface_name.indexOf(this.name));
		stline = stline.substring(0,stline.indexOf("{"));
		if(stline.contains("implements")==false){
			interfaces.add("null");
		}
		for(String i : lsInterface_name){
			if(stline.contains(i) && i!=this.name){
				interfaces.add(i);
			}
		}
	}
	public static InterfaceADT createInterface(String content, String name){
		List<String> interfaces = new ArrayList<String>();
		List<String> methods = new ArrayList<String>();
		InterfaceADT ex = new  InterfaceADT(name,interfaces,methods);
		ex.setInterfaces();
		ex.setMethods();
		return ex;
	}
	public InterfaceADT(String name, List<String> interfaces, List<String> methods){
		this.name = name;
		this.interfaces = interfaces;
		this.methods = methods;
	}
}
