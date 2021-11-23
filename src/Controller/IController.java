package Controller;

import Model.ModelExceptions.ExpressionException;
import Model.ModelExceptions.StatementException;
import Model.ProgramState;
import Repository.IRepository;
import Repository.RepositoryException;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface IController {

    IRepository getRepository();
    //ProgramState oneStep(ProgramState state) throws ControllerException, StatementException, ExpressionException, IOException, RepositoryException;
    void allSteps() throws ControllerException, StatementException, ExpressionException, RepositoryException, IOException, InterruptedException;
}
