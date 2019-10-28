//I am the sole author of the work in this repository.

import structure5.*;
import java.util.Iterator;

public class Interpreter {
	private StackList<Token> stack;
	private SymbolTable sym;

	public Interpreter(){
		stack = new StackList<Token>();
		sym = new SymbolTable();
	}

	//pre: stack is not empty
	//post: removes last element in stack
	public void pop(){
		Assert.pre(!stack.empty(), "Empty stack.");
		stack.pop();
	}

	//pre: none
	//post: prints out every element of stack in the same line
	public void pstack(){
		Iterator<Token> i = stack.iterator();
		Token t;
		if(stack.empty()){
			System.out.println("");
		} else {
			while(i.hasNext()){
				t = i.next();
				System.out.print(t+" ");
			}
			System.out.println("");
		}
	}

	//pre: the size of stack is greater than or equal to 2
	//post: the last two elements in stack are removed and their sum is added to stack
	public void add(){
		Assert.pre(stack.size() >= 2, "Not enough elements.");
		double x = stack.pop().getNumber();
		double y = stack.pop().getNumber();
		Token toke = new Token(x+y);
		stack.push(toke);
	}

	//pre: the size of stack is greater than or equal to 2
	//post: the last two elements in stack are removed and their difference is added to stack
	public void sub(){
		Assert.pre(stack.size() >= 2, "Not enough elements.");
		double x = stack.pop().getNumber();
		double y = stack.pop().getNumber();
		Token toke = new Token(y-x);
		stack.push(toke);
	}

	//pre: the size of stack is greater than or equal to 2
	//post: the last two elements in stack are removed and the result of their multiplication is added to stack
	public void mul(){
		Assert.pre(stack.size() >= 2, "Not enough elements.");
		double x = stack.pop().getNumber();
		double y = stack.pop().getNumber();
		Token toke = new Token(x*y);
		stack.push(toke);
	}

	//pre: the size of stack is greater than or equal to 2
	//post: the last two elements in stack are removed and the result of their division is added to stack
	public void div(){
		Assert.pre(stack.size() >= 2, "Not enough elements.");
		double x = stack.pop().getNumber();
		double y = stack.pop().getNumber();
		Token toke = new Token(y/x);
		stack.push(toke);
	}

	//pre: the size of stack is greater than or equal to 1
	//post: the last element in stack is removed and it is returned to stack twice
	public void dup(){
		Assert.pre(stack.size() >= 1, "Not enough elements.");
		Token toke = stack.pop();
		stack.push(toke);
		stack.push(toke);
	}

	//pre: the size of stack is greater than or equal to 2
	//post: the last two elements in stack are removed and they are added back to stack in reverse order
	public void exch(){
		Assert.pre(stack.size() >= 2, "Not enough elements.");
		Token tokeX = stack.pop();
		Token tokeY = stack.pop();
		stack.push(tokeX);
		stack.push(tokeY);
	}

	//pre: the size of stack is greater than or equal to 2
	//post: the last two elements in stack are removed and the result of their comparison is added to stack;
	//				returns true if elements are equal and false if they are not equal
	public void eq(){
		Assert.pre(stack.size() >= 2, "Not enough elements.");
		Token tokeX = stack.pop();
		Object tokeY = stack.pop();
		Token toke = new Token(tokeX.equals(tokeY));
		stack.push(toke);
	}

	//pre: the size of stack is greater than or equal to 2
	//post: the last two elements in stack are removed and the result of their comparison is added to stack;
	//				returns true if elements are not equal and false if equal
	public void ne(){
		Assert.pre(stack.size() >= 2, "Not enough elements.");
		Token tokeX = stack.pop();
		Object tokeY = stack.pop();
		Token toke = new Token(!tokeX.equals(tokeY));
		stack.push(toke);
	}

	//pre: the size of stack is greater than or equal to 1 and t is a valid Token
	//post: the last element in stack is removed and along with t, is passed as a new entry in SymbolTable
	public void def(Token t){
		Assert.pre(stack.size() >= 1, "Not enough elements.");
		String y = stack.pop().getSymbol().substring(1);
		sym.add(y, t);
	}

	//pre: none
	//post: prints all the elements of SymbolTable
	public void ptable(){
		System.out.print(sym.toString());
	}

	//pre: the size of stack is greater than or equal to 2
	//post: the last two elements in stack are removed and the result of their comparison is added to stack;
	//				returns true if y is less than x, false if otherwise
	public void lt(){
		Assert.pre(stack.size() >= 2, "Not enough elements.");
		double x = stack.pop().getNumber();
		double y = stack.pop().getNumber();
		Token toke;
		if(y < x){
			toke = new Token(true);
		} else{
			toke = new Token(false);
		}
		stack.push(toke);
	}

	//pre: read is a valid Reader
	//post: every element in read is analyzed and their corresponding code is executed
	public void interpret(Reader read){
		Token t;
		//will run as long as there is input from read
		while(read.hasNext()){
			t = read.next();
			if(t.isSymbol() && t.getSymbol().equals("quit")){
				break;
			}
			else if(t.isNumber() || t.isBoolean()){
				stack.push(t);
			}
			//calls interpret on the value of the symbol if symbol exists in SymbolTable
			else if(t.isSymbol() && sym.contains(t.getSymbol())){
				interpret(new Reader(sym.get(t.getSymbol())));
			}
			//creates a new symbol definition if symbol does not exist in SymbolTable
			else if(t.isSymbol() && t.getSymbol().substring(0,1).equals("/")){
				stack.push(t);
				t = read.next();
				def(t);
				t = read.next();
			}
			else if(t.isProcedure()){
				Reader pReader = new Reader(t.getProcedure());
				stack.push(t);
			}
			else if(t.isSymbol() && t.getSymbol().equals("if")){
				Assert.pre(stack.size() >= 2, "Not enough elements.");
				Token x = stack.pop();
				boolean y = stack.pop().getBoolean();
				if(y){
					interpret(new Reader(x));
				}
			}
			//runs a switch that checks for every defined operation that calls the corresponding method
			else if(t.isSymbol()){
				switch(t.getSymbol()){
					case "pop":
					pop();
					break;
					case "pstack":
					pstack();
					break;
					case "add":
					add();
					break;
					case "sub":
					sub();
					break;
					case "mul":
					mul();
					break;
					case "div":
					div();
					break;
					case "dup":
					dup();
					break;
					case "exch":
					exch();
					break;
					case "eq":
					eq();
					break;
					case "ne":
					ne();
					break;
					case "ptable":
					ptable();
					break;
					case "lt":
					lt();
					break;
					default:
					System.out.println("Not a valid symbol.");
				}
			}
		}
	}

	//pre: none
	//post: a new Interpreter object is created and a new Reader is created and passed into interpret()
	public static void main(String[] args){
		Interpreter interp = new Interpreter();
		Reader r = new Reader();
		interp.interpret(r);
	}
}
