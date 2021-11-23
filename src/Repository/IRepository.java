package Repository;

import Model.ProgramState;

import java.util.*;
import java.io.FileWriter;
import java.io.IOException;

public interface IRepository {

//    ProgramState getCurrentProgramState() throws RepositoryException;
    void addProgramState(ProgramState newProgramState);
    void clearRepository();
    void logProgramStateExecution(ProgramState programState) throws RepositoryException, IOException;
    FileWriter getFileWriter();
    List<ProgramState> getProgramStatesList();
    void setProgramStatesList(List<ProgramState> newProgramStatesList);
}

