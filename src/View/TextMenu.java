package View;
import Controller.ControllerException;
import Model.ModelExceptions.ExpressionException;
import Model.ModelExceptions.StatementException;
import Repository.RepositoryException;

import java.io.IOException;
import java.util.Map;
import java.util.HashMap;
import java.util.Scanner;

public class TextMenu {
    private final Map<String,Command> commands;
    public TextMenu(){ this.commands = new HashMap<>();}
    public void addCommand(Command command)
    {
       this.commands.put(command.getKey(),command);
    }
    private void printMenu()
    {
        for(Command command:commands.values())
        {
            String line=String.format("%4s: %s",command.getKey(),command.getDescription());
            System.out.println(line);
        }
    }
    public void show() throws ControllerException, StatementException, RepositoryException, IOException, ExpressionException {
        Scanner scanner=new Scanner(System.in);
        while(true)
        {
            printMenu();
            System.out.println("\n");
            System.out.printf("Input the option: ");
            System.out.println("\n");
            String key=scanner.nextLine();
            Command command=commands.get(key);
            if(command==null)
            {
                System.out.println("Invalid Option");
                System.out.println("\n");
                continue;
            }
            command.execute();
        }
    }


}
