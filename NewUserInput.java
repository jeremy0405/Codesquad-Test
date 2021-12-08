import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class NewUserInput {

    private final Scanner sc = new Scanner(System.in);

    public String[] userInput() {
        System.out.print("Jerry's Sokoban> ");
        String userinput = sc.nextLine();
        String[] splitCommand = splitInput(userinput);
        String[] commands = validateInput(splitCommand);
        return commands;
    }

    private String[] validateInput(String[] command) {
        for (int i = 0; i < command.length; i++) {
            if (!command[i].equalsIgnoreCase("W") &&
                !command[i].equalsIgnoreCase("A") &&
                !command[i].equalsIgnoreCase("S") &&
                !command[i].equalsIgnoreCase("D") &&
                !command[i].equalsIgnoreCase("Q") &&
                !command[i].equalsIgnoreCase("U") &&
                !command[i].equalsIgnoreCase("R") &&
                !command[i].equals("1S") &&
                !command[i].equals("2S") &&
                !command[i].equals("3S") &&
                !command[i].equals("4S") &&
                !command[i].equals("5S") &&
                !command[i].equals("1L") &&
                !command[i].equals("2L") &&
                !command[i].equals("3L") &&
                !command[i].equals("4L") &&
                !command[i].equals("5L")
            ) {
                System.out.println("잘못된 입력값이 포함되어 있습니다. 다시 입력해주세요!");
                return userInput();
            }
        }
        return command;
    }

    private String[] splitInput(String userinput) {
        List<String> commandlist = new LinkedList<>();
        for (int i = 0; i < userinput.length(); i++) {

            if (i < userinput.length() - 1 && (userinput.charAt(i) == '1' || userinput.charAt(i) == '2'
                || userinput.charAt(i) == '3' || userinput.charAt(i) == '4'
                || userinput.charAt(i) == '5') && (userinput.charAt(i + 1) == 'S' || userinput.charAt(i + 1) == 'L')) {
                int idx = commandlist.size();
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

    public static void main(String[] args) {
        NewUserInput input = new NewUserInput();
        for (var a: input.userInput()) {
            System.out.println(a);
        }
    }

}
