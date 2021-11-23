package View;

import Controller.*;
import Model.ModelExceptions.ExpressionException;
import Model.ModelExceptions.StatementException;
import Repository.RepositoryException;

import java.io.FileNotFoundException;
import java.io.IOException;

public class RunExample extends Command{

    private final Controller controller;
    public RunExample(String key, String description, Controller controller)
    {
        super(key, description);
        this.controller = controller;
    }
    @Override
    public void execute() throws ControllerException, StatementException, RepositoryException, IOException, ExpressionException {
        try
        {
            this.controller.allSteps();
        }
        catch (ExpressionException | NumberFormatException | StatementException | RepositoryException | ControllerException | IOException | InterruptedException exception ) {
        System.out.println("THERE IS AN ERROR: ");
        System.out.println(exception.getMessage());
        System.out.println();
        }
        this.controller.getRepository().getFileWriter().close();
    }
}

