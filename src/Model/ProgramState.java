package Model;

import Controller.ControllerException;
import Model.DataStructures.MyHeap;
import Model.DataStructures.MyIDictionary;
import Model.DataStructures.MyIList;
import Model.DataStructures.MyIStack;
import Model.ModelExceptions.ExpressionException;
import Model.ModelExceptions.StatementException;
import Model.Values.Value;
import Model.Statements.IStatements;
import Repository.RepositoryException;

import java.io.BufferedReader;
import java.io.FileDescriptor;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

public class ProgramState {

    private final MyIStack<IStatements> executionStack;
    private final MyIDictionary<String, Value> tableOfSymbols;
    private final MyIList<Value> output;
    private final MyIDictionary<String, BufferedReader> fileTable;
    private final MyHeap<Integer,Value> heap;
    private final IStatements originalProgram;
    private int id;
    private static int nextId=0;
    //private static final AtomicInteger programStatesCount = new AtomicInteger(0);

    public ProgramState(MyIStack<IStatements> stack, MyIDictionary<String,Value> tableOfSymbols, MyIList<Value> output, MyIDictionary<String,BufferedReader> fileTable, MyHeap<Integer,Value> heap, IStatements program, int newId)
    {
        id=newId;
        this.executionStack=stack;
        this.tableOfSymbols=tableOfSymbols;
        this.output=output;
        this.fileTable=fileTable;
        this.heap=heap;
        this.originalProgram=program.deepCopy();
        stack.push(program);
    }


    public int getId() {return id;}
    public MyIStack<IStatements> getExecutionStack() {return this.executionStack;}
    public MyIDictionary<String,Value> getTableOfSymbols() {return this.tableOfSymbols;}
    public MyIList<Value> getOutput() {return this.output;}
    public MyIDictionary<String,BufferedReader> getFileTable() {return this.fileTable;}
    public MyHeap<Integer,Value> getHeap(){return this.heap;}
    public IStatements getOriginalProgram(){return this.originalProgram;}
    public static synchronized int getNewProgramId() {return ++nextId;}
    public boolean isNotCompleted() {return !this.executionStack.isEmpty();}

    public void printProgramState() {

        System.out.println("Program State ID: "+ id+"\n");
        System.out.println("Execution Stack: " + executionStack.toString()+"\n");
        System.out.println("Table of Symbols: " + tableOfSymbols.toString()+"\n");
        System.out.println("Output:" + output.toString()+"\n");
        System.out.println("File table:" + fileTable.toString()+"\n");
        System.out.println("Heap: " + heap.toString()+"\n");
        System.out.println("------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("\n");

    }

    public ProgramState oneStep() throws ControllerException, StatementException, ExpressionException, IOException, RepositoryException {

        if(this.executionStack.isEmpty())
            throw new ControllerException("The execution stack of the current program state is empty");
        IStatements currentState=executionStack.pop();
        return currentState.execute(this);
    }

//    public ProgramState deepCopy()
//    {
//        return new ProgramState(this.executionStack,this.tableOfSymbols,this.output,this.fileTable,this.heap,this.originalProgram);
//    }
}
