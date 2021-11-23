package View;

import Controller.ControllerException;
import Model.ModelExceptions.ExpressionException;
import Model.ModelExceptions.StatementException;
import Repository.RepositoryException;

import java.io.IOException;

public abstract class Command {

    private final String key;
    private final String description;
    public Command(String key,String description)
    {
        this.key = key;
        this.description = description;
    }
    public abstract void execute() throws ControllerException, StatementException, RepositoryException, IOException, ExpressionException;
    public String getKey(){
        return key;
    }

    public String getDescription(){
        return description;
    }

}
