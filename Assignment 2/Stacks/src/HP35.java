import java.io.*;
public class HP35 {
    public static void main(String[] args) throws IOException {
        Stack<Integer> stack = new DynamicStack<Integer>();
        System.out.println("HP-35 pocket calculator");
        boolean run = true;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while(run) {
            System.out.print(" > ");
            String input = br.readLine();
            switch (input) {
                case "+":
                    stack.push(stack.pop() + stack.pop());
                    break;
                case "-":
                    stack.push(-(stack.pop() - stack.pop()));
                    break;
                case "*":
                    stack.push(stack.pop() * stack.pop());
                    break;
                case "":
                run = false;
                break;
                default:
                Integer nr = Integer.parseInt(input);
                stack.push(nr);
                break;
            }
        }
        System.out.printf("the result is: %d\n\n", stack.pop());
        System.out.printf("I love reversed polish notation, don't you?\n");
    }
}
