import java.util.*;
import org.eclipse.jdt.core.dom.*;
class ClassADT {
	String name;
	List<String> methods = new ArrayList<String>();
	List<String> interfaces = new ArrayList<String>();
	List<String> variables = new ArrayList<String>();
	String superclass = "null";
	static List<String> lsClass = new ArrayList<String>(); // list class
	static List<String> lsClass_name = new ArrayList<String>(); // class name
	boolean abs = false;
	List<String> hasaClass = new ArrayList<String>();
	public String getName(){
		return this.name;
	}
	void setName(String name){
		this.name = name;
	}
	public List<String> getMethod(){
		return this.methods;
	}
	public List<String> getVariables(){
		return this.variables;
	}
	public String getSuperClass(){
		return this.superclass;
	}
	public List<String> getInterfaces(){
		return this.interfaces;
	}
	public ClassADT(String name, List<String> method, List<String>variables, List<String> interfaces, List<String> hasaClass, boolean abs, String superclass){
		setName(name);
		this.methods = method;
		this.variables = variables;
		this.interfaces = interfaces;
		this.superclass = superclass;
		this.hasaClass = hasaClass;
		this.abs = abs;
	}
	public static ClassADT createClassADT(String content, String name){
		CompilationUnit cu = Utils.generateAST(content);
		List<String> method = new ArrayList<String>();
		List<String> variables = new ArrayList<String>();
		List<String> lsInterface = InterfaceADT.lsInterface_name;
		List<String> interfaces = InterfaceADT.getInterfaces(lsInterface, content);
		List<String> hasaClass = new ArrayList<String>();
		boolean abs = false;
		String spn = new String();
		ClassADT clas =  new ClassADT(name,method,variables,interfaces,hasaClass,abs,spn);
		clas.IsAbstract();
		clas.setVariable(cu);
		clas.setMethod(cu);
		clas.hasaFind();
		if(content.contains("extends")){
			clas.setSuperClass(cu);
		}
		else clas.superclass = "null";
		return clas;
	}
	void setVariable(CompilationUnit cu){
		cu.accept( new ASTVisitor() {
			public boolean visit(VariableDeclarationFragment node) {
				SimpleName name = node.getName();
				String type = new String();
				if(node.getParent() instanceof FieldDeclaration){
					FieldDeclaration fd = (FieldDeclaration) node.getParent();
					type = fd.getType().toString();
					String temp = type + " " + name.toString();
					variables.add(temp);
				}
				return true; 
			}});
	}
	void setSuperClass (CompilationUnit cu){
		//List<String> superClass = new ArrayList<String>();
		cu.accept(new ASTVisitor() {
			public boolean visit(TypeDeclaration node){
				if(node!=null){
					Type sc = node.getSuperclassType();
					String type = sc.toString();
					superclass = type;
				}
				else superclass = "null";
				return true;
			}});
		//this.superclass = superClass.get(0);
	}
	void setMethod (CompilationUnit cu){
		cu.accept(new ASTVisitor() {
			public boolean visit(MethodDeclaration node){
				String name = node.getName().toString();
				String type = node.getReturnType2().toString();
				List<SingleVariableDeclaration> parameter = node.parameters();
				String para_name = "(";
				String str="";
				for(SingleVariableDeclaration i : parameter){
					str += i.getType().toString() + " " + i.getName();
					if(parameter.listIterator().hasNext()==false)
						str += ",";
				}
				para_name = para_name +  str + ")";
				String method ="";
				if(abs==false) {
					method += type + " " + name + para_name;
				}
				else
				{
					if(node.getBody()==null)
					{
						method = "abstract ";
					}
					method += type + " " + name + para_name;
				}
				methods.add(method);
				return true;
			}});
	}
	public static void getClassContent(String fileName){
        List<String> temp = Utils.readContentFromFile(fileName);
        for(int i = 0;i<temp.size();){
            int index = 0;
            if(temp.get(i).contains("class")){
                StringBuilder tmp = new StringBuilder();
                for(int j = i + 1;j<temp.size();++j){
                    if(temp.get(j).contains("class")||temp.get(j).contains("interfaces")){
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
                lsClass.add(tmp.toString());
                i = index;
            }
            else i++;
        }    
    }
	public static void getClassName(){
		for(String classcontent : lsClass){
			String [] _1stline = classcontent.substring(0, classcontent.indexOf("{")-1).split(" ");
			for(int i = 0;i<_1stline.length;++i){
				if(_1stline[i].equals("class"))
					lsClass_name.add(_1stline[i+1]);
			}
		}
	}
	void IsAbstract(){
		String line = lsClass.get(lsClass_name.indexOf(this.name));
		line = line.substring(0,line.indexOf("{")-1);
		if(line.contains("abstract")){
			this.abs = true;
		}
	}
	public void hasaFind(){
		for(String variable : variables){
			String type = variable.split(" ")[0];
			for(String clas : lsClass_name){
				if(clas.equals(type)){
					hasaClass.add(clas);
				}
			}
		}
		if(hasaClass.size()==0){
			hasaClass.add("null");
		}
	}
}
