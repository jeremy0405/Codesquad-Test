import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class UserInput {

    private final Scanner sc = new Scanner(System.in);

    public String[] userInput() {
        System.out.print("Jerry's Sokoban> ");
        String userinput = sc.nextLine();
        return splitInput(userinput);
    }

    private String[] splitInput(String userinput) {
        List<String> commandlist = new LinkedList<>();
        for (int i = 0; i < userinput.length(); i++) {

            if (i < userinput.length() - 1 && (userinput.charAt(i) == '1' || userinput.charAt(i) == '2'
                || userinput.charAt(i) == '3' || userinput.charAt(i) == '4'
                || userinput.charAt(i) == '5') && (userinput.charAt(i + 1) == 'S' || userinput.charAt(i + 1) == 'L')) {
                commandlist.add(userinput.charAt(i) + "" + userinput.charAt(i + 1));
                i++;
                continue;
            }
            commandlist.add(userinput.charAt(i) + "");
        }
        return commandlist.toArray(new String[0]);
    }

    public void close() {
        sc.close();
    }

}
