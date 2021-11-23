package Repository;

import Model.ProgramState;
import Model.Statements.IStatements;
import Model.Values.Value;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Vector;

public class Repository implements IRepository{

    private List<ProgramState> programStatesList;
    private final String logFileName;
    private final FileWriter fileWriter;

    public Repository(ProgramState programState1, String logFileName1) throws IOException {

        logFileName=logFileName1;
        FileWriter fileWriter1;
        fileWriter1 = new FileWriter(logFileName, false);
        fileWriter=fileWriter1;
        programStatesList = new ArrayList<>();
        addProgramState(programState1);

    }


//    @Override
//    public ProgramState getCurrentProgramState() throws RepositoryException{
//        if(programStatesList.isEmpty())
//            throw new RepositoryException("The list of program states is empty");
//        return programStatesList.get(0);
//    }

    @Override
    public void addProgramState(ProgramState newProgramState) {
        this.programStatesList.add(newProgramState);
    }

    @Override
    public void clearRepository() {
        this.programStatesList.clear();
    }

    @Override
    public void logProgramStateExecution(ProgramState programState) throws RepositoryException, IOException {

        Integer sizeStack=programState.getExecutionStack().size();
        Integer sizeList=programState.getOutput().size();
        Vector<IStatements> iStatementsVector;
        iStatementsVector=new Vector<>(sizeStack);


        fileWriter.write("PROGRAM STATE ID: ");
        fileWriter.write(programState.getId()+"\n");
        fileWriter.write("\n");

        fileWriter.write("EXECUTION STACK:\n");
        for(int i=0; i<sizeStack; i++)
        {
            iStatementsVector.add(i,programState.getExecutionStack().pop());
            fileWriter.write(iStatementsVector.get(i).toString()+"\n");

        }
        for(int i=sizeStack-1; i>=0; i--)
        {
            //fileWriter.write(iStatementsVector.get(i).toString()+"\n");
            programState.getExecutionStack().push(iStatementsVector.get(i));
        }
        fileWriter.write("\n");

        fileWriter.write("TABLE OF SYMBOLS:\n");
        for(Map.Entry<String, Value> entry: programState.getTableOfSymbols().entrySet()) {
            fileWriter.write(entry.getKey()+"->"+entry.getValue().toString()+"\n");
        }
        fileWriter.write("\n");

        fileWriter.write("OUTPUT:\n");
        for(int i=0; i<sizeList; i++)
        {
            fileWriter.write(programState.getOutput().get(i).toString()+"\n");
        }
        fileWriter.write("\n");

        fileWriter.write("TABLE OF FILES:\n");
        for(Map.Entry<String, BufferedReader> entry: programState.getFileTable().entrySet()) {
            fileWriter.write(entry.getKey()+"->"+entry.getValue().toString()+"\n");
        }
        fileWriter.write("\n");
        fileWriter.write("HEAP:\n");
        for(Map.Entry<Integer,Value> entry: programState.getHeap().entrySet()) {
            fileWriter.write(entry.getKey()+"->"+entry.getValue().toString()+"\n");
        }


        fileWriter.write("\n");
        fileWriter.write("--------------------------------------------------------------------------------------");
        fileWriter.write("\n");
        fileWriter.write("\n");
    }

    @Override
    public FileWriter getFileWriter() {
        return this.fileWriter;
    }

    @Override
    public List<ProgramState> getProgramStatesList() {
        return this.programStatesList;
    }

    @Override
    public void setProgramStatesList(List<ProgramState> newProgramStatesList) {
        this.programStatesList=newProgramStatesList;
    }


}
//
//    @Override
//    public void logProgramStateExecution(ProgramState programState) throws RepositoryException, IOException {
//
//        Integer sizeStack=this.programStatesList.get(0).getExecutionStack().size();
//        Integer sizeList=this.programStatesList.get(0).getOutput().size();
//        Vector<IStatements> iStatementsVector;
//        iStatementsVector=new Vector<>(sizeStack);
//
//        fileWriter.write("EXECUTION STACK:\n");
//        for(int i=0; i<sizeStack; i++)
//        {
//            iStatementsVector.add(i,this.programStatesList.get(0).getExecutionStack().pop());
//            fileWriter.write(iStatementsVector.get(i).toString()+"\n");
//
//        }
//        for(int i=sizeStack-1; i>=0; i--)
//        {
//            //fileWriter.write(iStatementsVector.get(i).toString()+"\n");
//            this.programStatesList.get(0).getExecutionStack().push(iStatementsVector.get(i));
//        }
//        fileWriter.write("\n");
//
//        fileWriter.write("TABLE OF SYMBOLS:\n");
//        for(Map.Entry<String, Value> entry: this.programStatesList.get(0).getTableOfSymbols().entrySet()) {
//            fileWriter.write(entry.getKey()+"->"+entry.getValue().toString()+"\n");
//        }
//        fileWriter.write("\n");
//
//        fileWriter.write("OUTPUT:\n");
//        for(int i=0; i<sizeList; i++)
//        {
//            fileWriter.write(this.programStatesList.get(0).getOutput().get(i).toString()+"\n");
//        }
//        fileWriter.write("\n");
//
//        fileWriter.write("TABLE OF FILES:\n");
//        for(Map.Entry<String, BufferedReader> entry: this.programStatesList.get(0).getFileTable().entrySet()) {
//            fileWriter.write(entry.getKey()+"->"+entry.getValue().toString()+"\n");
//        }
//        fileWriter.write("\n");
//        fileWriter.write("HEAP:\n");
//        for(Map.Entry<Integer,Value> entry: this.programStatesList.get(0).getHeap().entrySet()) {
//            fileWriter.write(entry.getKey()+"->"+entry.getValue().toString()+"\n");
//        }
//
//
//        fileWriter.write("\n");
//        fileWriter.write("--------------------------------------------------------------------------------------");
//        fileWriter.write("\n");
//        fileWriter.write("\n");
//    }
