import java.util.*;
import java.io.*;

//import java.nio.*;
//import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.*;
//import org.eclipse.jdt.core.dom.ClassDeclaration;

class Utils
{
	
	//static List<String> lsInterfacef = new ArrayList<String>();
	static List<String> readContentFromFile(String path)
    {
        String line;
        List<String> strs = new ArrayList<String>();
        try(BufferedReader in = new BufferedReader(new FileReader(path))) {
            while((line=in.readLine())!=null)
            {
                strs.add(line);
            }
        } catch (Exception e) {
            //TODO: handle exception
            e.printStackTrace();
        }
        return strs;
    }
	public static List<String> readFileName(String path, List<String> ls)
    {
        File folder = new File(path);
        for(File file : folder.listFiles())
        {
            if(file.isDirectory()==false)
            {
                StringBuilder temp = new StringBuilder();
                temp.append(file.getName());
                ls.add(temp.toString());
            }
        }
        for(File file : folder.listFiles())
        {
            if(file.isDirectory())
            {
                //ls.add(file.getPath());
                ls = readFileName(file.getPath(),ls);
            }
        }
        return ls;
    }
	static final CompilationUnit generateAST(String source)
	{
		ASTParser parser = ASTParser.newParser(AST.JLS8);
		parser.setKind(ASTParser.K_COMPILATION_UNIT);
		parser.setSource(source.toCharArray());
		parser.setResolveBindings(true);
		parser.setBindingsRecovery(true);
		parser.setUnitName(null);
		parser.setProject(null);;
		parser.setEnvironment(null, null, null, true);
		return (CompilationUnit) parser.createAST(null);
	}
	
}