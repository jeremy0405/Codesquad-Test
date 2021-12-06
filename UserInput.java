import java.util.Scanner;

public class UserInput {

    private Scanner sc = new Scanner(System.in);

    public char[] userInput() {
        System.out.print("SOKOBAN> ");
        String userinput = sc.nextLine().toUpperCase();
        return userinput.toCharArray();
    }

    public void close() {
        sc.close();
    }


}
