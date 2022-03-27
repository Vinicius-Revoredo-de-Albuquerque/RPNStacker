import java.io.File;
import java.lang.Thread.State;
import java.util.Stack;
import java.util.Scanner;

class RPNStacker {
    public static void main(String[] args) {

        Stack<Integer> stack = new Stack<>();
        String file_path = "C:/Users/vinic/Downloads/Calc1.stk";
        
        try {
            File file = new File(file_path);
            Scanner reader = new Scanner(file);

            while (reader.hasNextLine()) {
                String character = reader.nextLine();

                if(number(character)) {
                    int number = Integer.parseInt(character);
                    stack.push(number);
                } /* se for número empilha e aguarda comando para operação */
                else {
                    int pos_number = stack.pop();
                    int pre_number = stack.pop();
                    int current_result = solve(pre_number, pos_number, character);
                    stack.push(current_result);
                } /* se não for número é uma operação, então os 2 últimos
                    valores são desempilhados e é chamada a função para
                    identificar e realizar a operação */
            }

            System.out.println(stack.pop());
            reader.close();
        
        } catch (Exception error) {
            System.out.println("Error!");
            error.printStackTrace();
        }
    }
    
    private static boolean number(String character) {
        return character.matches("\\d+");
    }

    private static int solve(int a, int b, String op) {

        switch(op) {
            case "+":
                return (a + b);

            case "-":
                return (a - b);
            
            case "*":
                return (a * b);

            case "/":
                return (int) (a / b);

            default:
                System.out.println("Operation Nonexistent");
                return -1;
        }
    }
        
}