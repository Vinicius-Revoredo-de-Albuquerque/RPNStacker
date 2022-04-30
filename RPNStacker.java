package RPNStacker;

import java.io.File;
import java.lang.Thread.State;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RPNStacker {
    public static void main(String[] args) {

        ArrayList<task02.Token> tokenlist = new ArrayList<task02.Token>();
        String file_path = "Calc1.stk";
        
        try {
            File file = new File(file_path);
            Scanner reader = new Scanner(file);

            while(reader.hasNextLine()) {
                String character = reader.nextLine();

                if(number(character)) {
                    tokenlist.add(new task02.Token(task02.TokenType.NUM, character));
                }
                else {
                    switch(character) {
                        case "+":
                            tokenlist.add(new task02.Token(task02.TokenType.PLUS, character));
                            break;

                        case "-":
                            tokenlist.add(new task02.Token(task02.TokenType.MINUS, character));
                            break;

                        case "*":
                            tokenlist.add(new task02.Token(task02.TokenType.STAR, character));
                            break;

                        case "/":
                            tokenlist.add(new task02.Token(task02.TokenType.SLASH, character));
                            break;

                        default:
                            System.out.println("Operation Nonexistent");
                            break;
                    }
                }

                // System.out.println(tokenlist);
            }

            while(tokenlist.size() > 1) { 
                task02.Token num1 = tokenlist.remove(0);
                if(num1.type != task02.TokenType.NUM) {throw new Exception("Error: not a numbera: " + num1.lexeme);}

                task02.Token num2 = tokenlist.remove(0);
                if(num2.type != task02.TokenType.NUM) {throw new Exception("Error: not a numberb: " + num2.lexeme);}

                task02.Token operation = tokenlist.remove(0);
                if(operation.type == task02.TokenType.NUM) {throw new Exception("Error: not a operation: " + operation.lexeme);}

                task02.Token newToken = calculate(num1, num2, operation);
                tokenlist.add(0, newToken); 
            }

            System.out.println("Resultado: " + tokenlist.remove(0).lexeme);
            reader.close();
        
        } catch (Exception error) {
            System.out.println("Error!");
            error.printStackTrace();
        }
    }
    
    private static boolean number(String character) {
        return character.matches("\\d+");
    }

    private static task02.Token calculate(task02.Token a, task02.Token b, task02.Token operation) {

        int num1 = Integer.parseInt(a.lexeme);
        int num2 = Integer.parseInt(b.lexeme);

        int  result = -1;

        if(operation.type.equals(task02.TokenType.PLUS)) {  
            result = num1 + num2; 
        }
        if(operation.type.equals(task02.TokenType.MINUS)) {  
            result = num1 - num2; 
        }
        if(operation.type.equals(task02.TokenType.STAR)) {  
            result = num1 * num2; 
        }
        if(operation.type.equals(task02.TokenType.SLASH)) {  
            result = num1 / num2; 
        }

        return new task02.Token(task02.TokenType.NUM, String.valueOf(result));
    }
}