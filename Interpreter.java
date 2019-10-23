import structure5.*;

public class Interpreter {
	private static SinglyLinkedList<Token> stac;

  public Interpreter(){
    stac = new SinglyLinkedList<Token>();
  }

  public static void push(Token t){
    stac.addFirst(t);
  }

  public static Token pop(){
    return stac.removeFirst();
  }

  public static void pstack(){
    for(int i = 0; i < stac.size(); i++){
      System.out.println(stac.get(i).getNumber());
    }
  }

  public static void add(){
    double x = pop().getNumber();
    double y = pop().getNumber();
    Token toke = new Token(x+y);
    push(toke);
  }

  public static void interpret(Reader read){
    Token t;
    while(read.hasNext()){
      t = (Token)read.next();
      if(t.isSymbol() && t.getSymbol().equals("quit")){
        break;
      }
      else if(t.isNumber() || t.isBoolean()){
        push(t);
      }
      else if(t.isSymbol()){
        if(t.equals("add")){
          add();
        }
        else if(t.equals("pstack")){
          pstack();
        }
        /*switch(t){
          case "add":
            add();
            break;
          case "pstack":
            System.out.println(pstack());
          default:;
        }*/
      }
    }
  }

  public static void main(String[] args){
    Interpreter interp = new Interpreter();
    Reader r = new Reader();
    interpret(r);
    //System.out.println(pstack());
  }
}
