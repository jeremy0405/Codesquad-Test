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

    private String[] splitInput(String userInput) {
        List<String> commandlist = new LinkedList<>();
        for (int i = 0; i < userInput.length(); i++) {

            if (isSaveAndLoadCommand(userInput, i)) {
                commandlist.add(userInput.charAt(i) + "" + userInput.charAt(i + 1));
                i++;
                continue;
            }
            commandlist.add(userInput.charAt(i) + "");
        }
        return commandlist.toArray(new String[0]);
    }

    private boolean isSaveAndLoadCommand(String userInput, int i) {
        return i < userInput.length() - 1 && (userInput.charAt(i) == '1' || userInput.charAt(i) == '2'
            || userInput.charAt(i) == '3' || userInput.charAt(i) == '4'
            || userInput.charAt(i) == '5') && (
            userInput.charAt(i + 1) == 'S' || userInput.charAt(i + 1) == 'L');
    }

    public void close() {
        sc.close();
    }

}
