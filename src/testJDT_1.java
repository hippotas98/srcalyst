import java.util.*;
import java.io.*;
public class testJDT_1 {
	public static void main(String [] args) throws IOException {
//			String folder = ".";
////			List<String> lsFile = new ArrayList<String>();
////			lsFile = Utils.readFileName(folder, lsFile);
////			for(String file : lsFile)
////			{
////				System.out.println(file);
////			}
			String path = "./src/source.java";
			ClassADT.getClassContent(path);
			ClassADT.getClassName();
			List<ClassADT> classes = new ArrayList<ClassADT>();
			for(String Cname : ClassADT.lsClass_name){
				int indx = ClassADT.lsClass_name.indexOf(Cname);
				String content = ClassADT.lsClass.get(indx);
				ClassADT clas = ClassADT.createClassADT(content, Cname);
				classes.add(clas);
				ImageUtils.createImagefromString(clas.getInformation(), Cname);
			}
			//ImageUtils.createImagefromString(infor,classes.get(8).name);
			System.out.println("DONE");
	}
}
