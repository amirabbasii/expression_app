
import java.util.Scanner;
/**
 * UI 
 * @author Asus
 *
 */
public class Intter {
public static void main(String[] args) {
	Scanner inupt=new Scanner(System.in);
	String str=inupt.nextLine();
	Console console=null;
	while(!str.contains("#end")) {
if(str.contains("#new")){
			console=new Console();
			
		}
else if(console!=null)
	console.reader(str);
		 str=inupt.nextLine();
	}
}

}

class NotFoundVariableException extends Exception{

}
class NotFoundFunctionException extends Exception{
	
}class WrongSyntaxException extends Exception{
	
}
/**
 * stacke halate khas ke hengame push(),azaye ba olaviate kamtar ra pop() karade va be sorate char[] return mikonad
 * @author Asus
 *
 */
class EspecialStack extends Stack<Character>{
/**
 * azaye ba olaviate kamtar ra pop() karade va be sorate char[] return mikonad
 * az check() baraye check kardane olaviat estefade mikonad
 * @param e
 * @return
 */
	public char[] push(char e) {
		Stack<Character> stack=new Stack<Character>();
	
		while(getSize()>0 && check(getTop(), e)==1) {

			stack.push(pop());
		}
		char[] ans=new char[(int)stack.getSize()];
		int t=0;
		while(!stack.isEmpty()) 
		{
			ans[t++]=stack.pop();
		}
		super.push(e);
		return ans;
	}
	/**
	 * prority top var ozve morede nazar ra moghayese mikonad.agar top>str 1 vagarna -1
	 * @param top
	 * @param str
	 * @return
	 */
	public int check(char top,char str) {
		if(top=='^' && str=='^')
			return -1;
		if(getPriority(str)<=getPriority(top))
			return 1;
		else
			return -1;
	}
	/**
	 * priortiy amalagar ra midahad
	 * 
	 * @param ch
	 * @return
	 */
	public int getPriority(char ch) {
		int t=0;
		switch (ch) {
		case '_'://manfi
		t=4;
		break;
		case '^':
			t=3;
			break;
		case '*':
		case '/':
			t=2;
			break;
		case '+':
		case '-':
			t=1;
			break;
		default:
			break;
		}
		return t;
	}
}
/**
 * shamele tabe ha va motaghayer hast
 * @author Asus
 *
 */
class Console{
	Variables var=new Variables();//motaghayer ha
	Functions fun=new Functions();//tabeha
	
	/**
	 * str ra bar asas parantez baresi mikonad.agar parantez az tabe bood tabe ra joda vagarna ebarate dakhele parante ra joda mikonad.baraye hal anhara be solve() miferestad va javab ra jaygozari mikonad
	 * @param str
	 * @return
	 * @throws ArithmeticException
	 * @throws NotFoundVariableException
	 * @throws NotFoundFunctionException
	 * @throws StringIndexOutOfBoundsException
	 * @throws WrongSyntaxException
	 */
	public String amir(String str) throws ArithmeticException,NotFoundVariableException,NotFoundFunctionException,StringIndexOutOfBoundsException,WrongSyntaxException{
	
		if(str.contains("(")) {
			
			if(str.indexOf("(")!=0 && ((((int)str.charAt(str.indexOf("(")-1)>=97 && (int)str.charAt(str.indexOf("(")-1)<=122))||(((int)str.charAt(str.indexOf("(")-1)>=65 && (int)str.charAt(str.indexOf("(")-1)<=90)))){
				String tmp=String.valueOf(str.charAt(str.indexOf("(")-1))+"(";
				int counter=1;
				int index=str.indexOf("(");
				do {
					index++;
					tmp+=str.charAt(index);
					if(str.charAt(index)=='(')
						counter++;
					if(str.charAt(index)==')')
						counter--;
					
				}
				while(counter!=0);
				String[] vars=solveBracket(tmp);
				for(int i=0;i<vars.length;i++)
					vars[i]=amir(vars[i]);
				
				String name=getNameOfFunction(tmp);
				String replaced=fun.findFunction(name).replaceIn(vars);
				return amir(str.replace(tmp,String.valueOf( solve(replaced))));
			}else {
				String tmp="";
				int counter=1;
				int index=str.indexOf("(")+1;
				while( counter!=0) {
					
					if(str.charAt(index)=='(')
						counter++;
					if(str.charAt(index)==')')
						counter--;
					if(counter==0)
						break;
						tmp+=str.charAt(index);
					index++;
				}
			
				return amir(str.replace("("+tmp+")", amir(tmp)));
			}
		}else
			return String.valueOf(solve(str));
	}
	
	public  String[] solveBracket(String str) throws WrongSyntaxException {
		if(!str.contains("(") || !str.contains(")"))
				throw new WrongSyntaxException();
		str=str.substring(str.indexOf("(")+1,str.lastIndexOf(")"));
		String[] ans=str.split(",");
		for(int i=0;i<ans.length;i++) {
		
			if(ans[i].equals(""))
				throw new WrongSyntaxException();}
		return  ans;

	}
	/**
	 * name tabe ra midahad
	 * @param str
	 * @return
	 */
	public  String getNameOfFunction(String str) {
		String ans="";
		int i=0;
		while(str.charAt(i)!='(') 
			ans+=str.charAt(i++);
		return ans;
	}
	/**
	 * ebarate shamele ^,*,/,-,+ ra hal mikonad
	 * @param str
	 * @return
	 * @throws ArithmeticException
	 * @throws NotFoundVariableException
	 * @throws NotFoundFunctionException
	 * @throws WrongSyntaxException
	 */
	public  float solve(String str) throws ArithmeticException,NotFoundVariableException,NotFoundFunctionException,WrongSyntaxException{
		if(str.contains("("))
			str=amir(str);
str=str+="+";
	Stack<String> stack1=new Stack<>();
	EspecialStack signs=new EspecialStack();
	String tmp="";
	int i=0;
		while(i<str.length()){
			if(((int)str.charAt(i)>=48 && (int)str.charAt(i)<=57)|| ((((int)str.charAt(i)>=97 && (int)str.charAt(i)<=122))||(((int)str.charAt(i)>=65 && (int)str.charAt(i)<=90))) || (int)str.charAt(i)==46) {
				tmp+=str.charAt(i);
			}else {
				
				char[] ert;
				if(tmp.equals("")) {
					ert=signs.push('_');
				}
				else {
			
					stack1.push(tmp);
				ert=signs.push(str.charAt(i));
		
				for(int j=ert.length-1;j>=0;j--) {
					String a="";
					String b="";
					switch (ert[j]) {
					case '_':
						a=stack1.pop();
						a=varChecker(a);
					
						stack1.push("-"+a);
						break;
						
					case '+':
						 b=stack1.pop();
							a=stack1.pop();
							a=varChecker(a);
							b=varChecker(b);
							stack1.push(String.valueOf(Double.parseDouble(a)+Double.parseDouble(b)));

					
					break;
					case '-':
						 b=stack1.pop();
							a=stack1.pop();
							a=varChecker(a);
							b=varChecker(b);
							stack1.push(String.valueOf(Double.parseDouble(a)-Double.parseDouble(b)));

						
					break;
					case '*':
						 b=stack1.pop();
							a=stack1.pop();
							a=varChecker(a);
							b=varChecker(b);

							stack1.push(String.valueOf(Double.parseDouble(a)*Double.parseDouble(b)));
		
					break;
					case '/':
						 b=stack1.pop();
							a=stack1.pop();
							a=varChecker(a);
							b=varChecker(b);
						if(Float.parseFloat(b)==0)
							throw new ArithmeticException();
						stack1.push(String.valueOf(Double.parseDouble(a)/Double.parseDouble(b)));
					break;
					case '^':
						 b=stack1.pop();
							a=stack1.pop();
							a=varChecker(a);
							b=varChecker(b);
						stack1.push( String.valueOf(Math.pow(Double.parseDouble(a), Double.parseDouble(b))));
						break;
					}
				
					}
				}	
				tmp="";
			}
	
			i++;
	
		}
		stack1.push(varChecker(stack1.pop()));
		return Float.parseFloat(stack1.pop());
	}
	/**
	 * str ra migirad va check mikonad aya name motaghaye hast.agar bood meghdarash ra jaygozin vagarna khodash ra bar migradanad
	 * @param str
	 * @return
	 */
	public  String varChecker(String str) throws NotFoundVariableException{
		if(str.length()==1 && ((((int)str.charAt(0)>=97 && (int)str.charAt(0)<=122))||(((int)str.charAt(0)>=65 && (int)str.charAt(0)<=90)))) {
			for(int i=0;i<var.variables.getSize();i++) {
			if(str.equals(var.variables.get(i).data.name)) {
			return String.valueOf(var.variables.get(i).data.data);}
		}
			throw new NotFoundVariableException();
	}
		return str;
		}
	
	/**
	 * str ra baresi mikonad ke shamele kodam dastoor ast:define ,print
	 * @param str
	 */
	public void reader(String str) {

		if(str.contains("#define")) {//tarife motaghayer ya tabe jadid
			
			str=str.replaceAll("#define ", "");
			str=str.replace(" ", "");//hazfe fasele
			String[] forCheck=str.split("=");
		if(forCheck[0].contains(")") || forCheck[0].contains("(") )
 {
				String[] pieces=str.split("=");
				try {
				fun.newFunction(getNameOfFunction(pieces[0]), pieces[1], solveBracket(pieces[0]));}
				catch (WrongSyntaxException e) {
					System.out.println(">>wrong syntax");
				}
				catch (ArrayIndexOutOfBoundsException e) {
					System.out.println(">>wrong syntax");
				}
			}else {
				if(forCheck[0].length()==1)
				{
	String[] pieces=str.split("=");
	try {
	var.newVariable(pieces[0],Float.parseFloat( amir(pieces[1])));}
	catch (Exception e) {
		System.out.println(">>undefined exp");
	}}else
		System.out.println(">>wrong syntax exp");
			}
		
		}
		else if(str.contains("#print ")) {
			str=str.replaceAll("#print ", "");
			str=str.replace(" ", "");
			try {
			System.out.println(">>"+amir(str));}
			catch (ArithmeticException e) {
				System.out.println(">>undefind value");
			}
			catch (NotFoundVariableException e) {
//				System.out.println(">>undefined exp");
			}
			catch (NotFoundFunctionException e) {
				System.out.println(">>undefined exp");
			}
//			catch (StringIndexOutOfBoundsException e) {
//				e.printStackTrace();
//			}
			catch (WrongSyntaxException e) {
				System.out.println(">>wrong syntax");
			}
		}
		else if(str.contains("#solve ")) {
		
		}
		else
			System.out.println(">>wrong syntax");
	
		
	}
	class Variables{
		 ArrayList<Variable> variables=new ArrayList<Variable>();
			
			class Variable{
				String name;
				float data;
			public Variable(String name,float data) {
				this.name=name;
				this.data=data;
			}
			public boolean equals(Object var) {
				if(var instanceof Variable)
				{
					if(((Variable) var).name.equals(name))
						return true;
				}
				return false;
			}
			}
			public void newVariable(String name,float data) {
				if(findVariable(name)==-1) {
				variables.addLast(new Variable(name, data));}
				else
					variables.get(findVariable(name)).data.data=data;
			
			}
			public  int findVariable(String name) {
				return variables.indexOf(new Variable(name, 0) );
			}
			
			
		}
		class Functions{

		 ArrayList<Function> functions=new ArrayList<>();
		 class Function{
			String name;
			String func;
			String[] vars;
			public Function(String name,String func,String[] vars) {
				this.name=name;
				this.func=func;
				this.vars=vars;
			}
			public boolean equals(Object var) {
				if(var instanceof Function)
				{
					if(((Function) var).name.equals(name))
						return true;
				}
				return false;
			}
			public  String replaceIn(String[] x) {
				String str=func;
				for(int i=0;i<x.length;i++) {
					str=str.replaceAll(vars[i],x[i] );
				}
				
				return str;
			}
		 }
		 
			public  void newFunction(String name,String func,String[] vars) {
				if(functions.indexOf(new Function(name, null,null) )==-1)
				functions.addLast(new Function(name, func, vars));
				else
					functions.get(functions.indexOf(new Function(name, null,null) )).data=new Function(name, func, vars);
			}
			public Function findFunction(String name) throws NotFoundFunctionException{
				if(functions.getSize()==0)
					throw new NotFoundFunctionException();
				return functions.get(functions.indexOf(new Function(name, null,null) )).data;
			}


		}
}
class ArrayList<E> extends LinkedList<E>{
	public Node get(int i) {
		Node node=super.start;
		int n=0;
		while(n!=i && node.next!=null) {
			node=node.next;
			n++;
		}
		return node;
		
	}
	public int indexOf(E ob) {
		Node tmp=super.start;
		int n=0;
		while(tmp!=null) {
			
			
		
			if(tmp.data.equals(ob))
				return n;
			else
				tmp=tmp.next;
			n++;
		}
		return -1;
	}
}
