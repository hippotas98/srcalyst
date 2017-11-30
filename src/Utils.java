import java.util.*;
import java.io.*;
import org.eclipse.jdt.core.dom.*;

class Utils{
	static List<String> readContentFromFile(String path){
        String line;
        List<String> strs = new ArrayList<String>();
        try(BufferedReader in = new BufferedReader(new FileReader(path))) {
            while((line=in.readLine())!=null){
                strs.add(line);
            }
            strs = removeComment(strs);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return strs;
    }
	static List<String> removeComment(List<String> lines){	
		List<String> list = new ArrayList<String>();
		int start = lines.size(),end = -1;
		for(int i = 0;i<lines.size();++i){
			String line = lines.get(i);
			String s = line.replaceAll(" ", "");
			if(s.indexOf("//")!=0) {
				if(s.indexOf("/*")==0){
					start = lines.indexOf(line);
					for(int indxComment = lines.indexOf(line);indxComment<lines.size();++indxComment) {
						if(lines.get(indxComment).contains("*/")) {
							end = indxComment;
							break;
						}
					}
				}
				if(i < start || i > end)
				{
					list.add(line);
				}
			}
		}
		return list;
	}
	public static List<String> readFileName(String path, List<String> ls)
    {
        File folder = new File(path);
        for(File file : folder.listFiles())
        {
            if(file.isDirectory()==false)
            {
                StringBuilder temp = new StringBuilder();
                temp.append(file.getParent());
                temp.append("/");
                if(file.getName().contains(".java") && file.getName().indexOf(".")!=0)
                {
                		temp.append(file.getName());
                		ls.add(temp.toString());
                }
            }
        }
        for(File file : folder.listFiles())
        {
            if(file.isDirectory())
            {
                ls = readFileName(file.getPath(),ls);
            }
        }
        return ls;
    }
	static final CompilationUnit generateAST(String source)
	{
		ASTParser parser = ASTParser.newParser(AST.JLS3);
		parser.setKind(ASTParser.K_COMPILATION_UNIT);
		parser.setSource(source.toCharArray());
		return (CompilationUnit) parser.createAST(null);
	}
	
}