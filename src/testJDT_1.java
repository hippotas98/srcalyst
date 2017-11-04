import java.util.*;

public class testJDT_1 {
	public static void main(String [] args){
			String path = "./src/cau3.java";
			ClassADT.getClassContent(path);
			ClassADT.getClassName();
			List<ClassADT> classes = new ArrayList<ClassADT>();
			for(String Cname : ClassADT.classname)
			{
				//System.out.println(name);
				int indx = ClassADT.classname.indexOf(Cname);
//				System.out.println(indx);
				String content = ClassADT.lsClass.get(indx);
				ClassADT clas = ClassADT.createClassADT(content, Cname);
				classes.add(clas);
			}
			ClassADT example = classes.get(2);
			System.out.println("Ten class: " + example.name);
			example.hasaFind(classes);
			System.out.println("Has a: ");
			for(ClassADT i : example.hasaClass)
			{
				System.out.println(i.getName());
			}
			System.out.println("Interfaces: ");
			for(String i : example.interfaces)
			{
				System.out.println(i);
			}
			System.out.println("Methods: ");
			for(String i : example.methods)
			{
				System.out.println(i);
			}
			System.out.println("Variables: ");
			for(String i : example.variables)
			{
				System.out.println(i);
			}
			System.out.println("Class cha: "+ example.superclass);
			System.out.println("Interface");
			InterfaceADT.readInterfaceFromFile(path);
			InterfaceADT.getInterfacesContent(path);
			List<InterfaceADT> interfaces = new ArrayList<InterfaceADT>();
			for(String Iname : InterfaceADT.itfname)
			{
				int index = InterfaceADT.itfname.indexOf(Iname);
				String Icontent = InterfaceADT.itfcontent.get(index);
				InterfaceADT iface = InterfaceADT.createInterface(Icontent, Iname);
				interfaces.add(iface);
			}
			InterfaceADT ex = interfaces.get(0);
			System.out.println("Interface: " + ex.name);
			System.out.println("Method: ");
			for(String method : ex.methods)
			{
				System.out.print(method +" ");
			}
			System.out.println();
			System.out.println("Interfaces: ");
			for(String impitf : ex.interfaces)
			{
				System.out.print(impitf +" ");
			}
			System.out.println();
//			for(String i : InterfaceADT.itfcontent)
//			{
//				System.out.println(i);
//			}
			
			
	}
	
}
