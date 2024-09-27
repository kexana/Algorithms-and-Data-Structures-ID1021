public class App {
    public static void main(String[] args) throws Exception {
        Stack s = new DynamicStack();
        s.push(32);
        s.push(33);
        s.push(36);
        s.push(37);
        s.push(34);
        System.out.println("pop : " + s.pop());
        System.out.println("pop : " + s.pop());
        System.out.println("pop : " + s.pop());
        System.out.println("pop : " + s.pop());
        System.out.println("pop : " + s.pop());
    }
}
