package View;

import Controller.Controller;
import Controller.ControllerException;
import Model.DataStructures.*;
import Model.Expressions.*;
import Model.ModelExceptions.ExpressionException;
import Model.ModelExceptions.StatementException;
import Model.ProgramState;
import Model.Statements.*;
import Model.Statements.FileStatements.CloseRFile;
import Model.Statements.FileStatements.OpenRFile;
import Model.Statements.FileStatements.ReadFile;
import Model.Types.*;
import Model.Values.BoolValue;
import Model.Values.IntValue;
import Model.Values.StringValue;
import Model.Values.Value;
import Repository.IRepository;
import Repository.Repository;
import Repository.RepositoryException;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.HashMap;

public class Interpreter {

    public static void main(String[] args) throws IOException, ControllerException, StatementException, RepositoryException, ExpressionException {

        IStatements example1 = new CompoundStatement(new VariableDeclarationStatement("v", new IntType()),
                new CompoundStatement(new AssignmentStatement("v", new ValueExpression(new IntValue(2))), new PrintStatement(new
                        VariableExpression("v"))));

        ProgramState programState1 = initializeDataStructures(example1);
        String logFileName1 = "log1.txt";
        IRepository repository1 = new Repository(programState1,logFileName1);
        Controller controller1 = new Controller(repository1);




        IStatements example2 = new CompoundStatement(new VariableDeclarationStatement("a", new IntType()),
                new CompoundStatement(new VariableDeclarationStatement("b", new IntType()),
                        new CompoundStatement(new AssignmentStatement("a", new ArithmeticExpression('+', new ValueExpression(new IntValue(2)), new
                                ArithmeticExpression('/', new ValueExpression(new IntValue(10)), new ValueExpression(new IntValue(0))))),
                                new CompoundStatement(new AssignmentStatement("b", new ArithmeticExpression('+', new VariableExpression("a"), new ValueExpression(new
                                        IntValue(1)))), new PrintStatement(new VariableExpression("b"))))));


        ProgramState programState2 = initializeDataStructures(example2);
        String logFileName2 = "log2.txt";
        IRepository repository2 = new Repository(programState2,logFileName2);
        Controller controller2 = new Controller(repository2);



        IStatements example3 = new CompoundStatement(new VariableDeclarationStatement("a", new BoolType()),
                new CompoundStatement(new VariableDeclarationStatement("v", new IntType()),
                        new CompoundStatement(new AssignmentStatement("a", new ValueExpression(new BoolValue(true))),
                                new CompoundStatement(new IfStatement(new VariableExpression("a"), new AssignmentStatement("v", new ValueExpression(new
                                        IntValue(2))), new AssignmentStatement("v", new ValueExpression(new IntValue(3)))), new PrintStatement(new
                                        VariableExpression("v"))))));

        ProgramState programState3 = initializeDataStructures(example3);
        String logFileName3 = "log3.txt";
        IRepository repository3 = new Repository(programState3,logFileName3);
        Controller controller3 = new Controller(repository3);



        IStatements example4 = new CompoundStatement(new VariableDeclarationStatement("a",new IntType()),
                new CompoundStatement(new VariableDeclarationStatement("b",new BoolType()),
                        new CompoundStatement(new VariableDeclarationStatement("c",new IntType()),
                                new CompoundStatement(new AssignmentStatement("c",new ValueExpression(new IntValue(7))),
                                        new CompoundStatement(new IfStatement(new RelationalExpression(new VariableExpression("c"),
                                                new VariableExpression("a"), ">"), new AssignmentStatement("b",new ValueExpression(new BoolValue(true))),
                                                new AssignmentStatement("b",new ValueExpression(new BoolValue(false)))),new PrintStatement(new VariableExpression("b")))))));


        ProgramState programState4 = initializeDataStructures(example4);
        String logFileName4 = "log4.txt";
        IRepository repository4 = new Repository(programState4,logFileName4);
        Controller controller4 = new Controller(repository4);


        IStatements example5 = new CompoundStatement(new VariableDeclarationStatement("varf", new StringType()),
                new CompoundStatement(new AssignmentStatement("varf" ,new ValueExpression(new StringValue("src\\Repository\\test.in"))),
                        new CompoundStatement(new OpenRFile(new VariableExpression("varf")),
                                new CompoundStatement(new VariableDeclarationStatement("varc",new IntType()),
                                        new CompoundStatement(new ReadFile(new VariableExpression("varf"), new StringValue("varc")),
                                                new CompoundStatement(new ReadFile(new VariableExpression("varf"), new StringValue("varc")),
                                                        new CompoundStatement(new PrintStatement(new VariableExpression("varc")),new CloseRFile(new VariableExpression("varf")))))))));


        ProgramState programState5 = initializeDataStructures(example5);
        String logFileName5 = "log5.txt";
        IRepository repository5 = new Repository(programState5,logFileName5);
        Controller controller5 = new Controller(repository5);


        IStatements example6 = new CompoundStatement(new VariableDeclarationStatement("v",new RefType(new IntType())),
                new CompoundStatement(new HeapAllocationStatement(new StringValue("v"),new ValueExpression(new IntValue(20))),
                        new CompoundStatement(new VariableDeclarationStatement("a",new RefType(new RefType(new IntType()))),
                                new CompoundStatement(new HeapAllocationStatement(new StringValue("a"),new VariableExpression("v")),
                                        new CompoundStatement(new PrintStatement(new VariableExpression("v")),new PrintStatement(new VariableExpression("a")))))));

        ProgramState programState6=initializeDataStructures(example6);
        String logFileName6 = "log6.txt";
        IRepository repository6 = new Repository(programState6,logFileName6);
        Controller controller6 = new Controller(repository6);



        IStatements example7 = new CompoundStatement(new VariableDeclarationStatement("v",new RefType(new IntType())),
                new CompoundStatement(new HeapAllocationStatement(new StringValue("v"),new ValueExpression(new IntValue(20))),
                        new CompoundStatement(new VariableDeclarationStatement("a",new RefType(new RefType(new IntType()))),
                                new CompoundStatement(new HeapAllocationStatement(new StringValue("a"),new VariableExpression("v")),
                                        new CompoundStatement(new PrintStatement(new HeapReadingExpression(new VariableExpression("v"))),new PrintStatement(new ArithmeticExpression('+',new HeapReadingExpression(new HeapReadingExpression(new VariableExpression("a"))),new ValueExpression(new IntValue(5)))))))));

        ProgramState programState7 = initializeDataStructures(example7);
        String logFileName7 = "log7.txt";
        IRepository repository7 = new Repository(programState7,logFileName7);
        Controller controller7 = new Controller(repository7);




        IStatements example8 = new CompoundStatement(new VariableDeclarationStatement("v",new RefType(new IntType())),
                new CompoundStatement(new HeapAllocationStatement(new StringValue("v"),new ValueExpression(new IntValue(20))),
                        new CompoundStatement(new PrintStatement(new HeapReadingExpression(new VariableExpression("v"))),
                                new CompoundStatement(new HeapWritingStatement(new StringValue("v"),new ValueExpression(new IntValue(30))),
                                        new PrintStatement(new ArithmeticExpression('+',new HeapReadingExpression(new VariableExpression("v")),new ValueExpression(new IntValue(5))))))));

        ProgramState programState8 = initializeDataStructures(example8);
        String logFileName8 = "log8.txt";
        IRepository repository8 = new Repository(programState8,logFileName8);
        Controller controller8 = new Controller(repository8);



        IStatements example9 = new CompoundStatement(new VariableDeclarationStatement("v",new IntType()),
                new CompoundStatement(new AssignmentStatement("v",new ValueExpression(new IntValue(4))),
                        new CompoundStatement(new WhileStatement(new RelationalExpression(new VariableExpression("v"),new ValueExpression(new IntValue(0)), ">"),
                                new CompoundStatement(new PrintStatement(new VariableExpression("v")),
                                        new AssignmentStatement("v",new ArithmeticExpression('-', new VariableExpression("v"),new ValueExpression(new IntValue(1)))))),
                                        new PrintStatement(new VariableExpression("v")))));



//        IStatements example9 = new CompoundStatement(new VariableDeclarationStatement("v",new IntType()),
//                new CompoundStatement(new AssignmentStatement("v",new ValueExpression(new IntValue(4))),
//                        new CompoundStatement(new WhileStatement(new VariableExpression("v"),
//                                new CompoundStatement(new PrintStatement(new VariableExpression("v")),
//                                        new AssignmentStatement("v",new ArithmeticExpression('-', new VariableExpression("v"),new ValueExpression(new IntValue(1)))))),
//                                new PrintStatement(new VariableExpression("v")))));


        ProgramState programState9 = initializeDataStructures(example9);
        String logFileName9 = "log9.txt";
        IRepository repository9 = new Repository(programState9,logFileName9);
        Controller controller9 = new Controller(repository9);




        IStatements example10 = new CompoundStatement(new VariableDeclarationStatement("v", new RefType(new IntType())),
                new CompoundStatement(new HeapAllocationStatement(new StringValue("v"),new ValueExpression(new IntValue(20))),
                        new CompoundStatement(new VariableDeclarationStatement("a",new RefType(new RefType(new IntType()))),
                                new CompoundStatement(new HeapAllocationStatement(new StringValue("a"),new VariableExpression("v")),
                                        new CompoundStatement(new HeapAllocationStatement(new StringValue("v"),new ValueExpression(new IntValue(30))),
                                                new PrintStatement(new HeapReadingExpression(new HeapReadingExpression(new VariableExpression("a")))))))));


        ProgramState programState10 = initializeDataStructures(example10);
        String logFileName10 = "log10.txt";
        IRepository repository10 = new Repository(programState10,logFileName10);
        Controller controller10 = new Controller(repository10);



        IStatements example11= new CompoundStatement(new VariableDeclarationStatement("v",new IntType()),
                new CompoundStatement(new VariableDeclarationStatement("a",new RefType(new IntType())),
                        new CompoundStatement(new AssignmentStatement("v",new ValueExpression(new IntValue(10))),
                                new CompoundStatement(new HeapAllocationStatement(new StringValue("a"),new ValueExpression(new IntValue(22))),
                                        new CompoundStatement(new ForkStatement(new CompoundStatement(new HeapWritingStatement(new StringValue("a"),new ValueExpression(new IntValue(30))),
                                                new CompoundStatement(new AssignmentStatement("v",new ValueExpression(new IntValue(32))),
                                                        new CompoundStatement(new PrintStatement(new VariableExpression("v")),new PrintStatement(new HeapReadingExpression(new VariableExpression("a"))))))),
                                                                new CompoundStatement(new PrintStatement(new VariableExpression("v")),new PrintStatement(new HeapReadingExpression(new VariableExpression("a")))))))));



        ProgramState programState11 = initializeDataStructures(example11);
        String logFileName11 = "log11.txt";
        IRepository repository11 = new Repository(programState11,logFileName11);
        Controller controller11 = new Controller(repository11);





        TextMenu menu = new TextMenu();
        System.out.println("\n");
        menu.addCommand(new ExitCommand("0", "exit"));
        menu.addCommand(new RunExample("1", example1.toString(), controller1));
        menu.addCommand(new RunExample("2", example2.toString(), controller2));
        menu.addCommand(new RunExample("3", example3.toString(), controller3));
        menu.addCommand(new RunExample("4", example4.toString(), controller4));
        menu.addCommand(new RunExample("5", example5.toString(), controller5));
        menu.addCommand(new RunExample("6", example6.toString(), controller6));
        menu.addCommand(new RunExample("7", example7.toString(), controller7));
        menu.addCommand(new RunExample("8", example8.toString(), controller8));
        menu.addCommand(new RunExample("9", example9.toString(), controller9));
        menu.addCommand(new RunExample("10", example10.toString(), controller10));
        menu.addCommand(new RunExample("11", example11.toString(), controller11));

        menu.show();
    }

    private static ProgramState initializeDataStructures(IStatements example) throws StatementException, ExpressionException {
        MyIStack<IStatements> stack1 = new MyStack<>();
        MyIDictionary<String, Value> tableOfSymbols1 = new MyDictionary<>();
        MyIList<Value> output1 = new MyList<>();
        MyIDictionary<String, BufferedReader> fileName1 = new MyDictionary<>();
        MyHeap<Integer,Value> heap1= new MyHeap<>();

        MyIDictionary<String, Type> types = new MyDictionary<>();
        example.typeCheck(types);

        return new ProgramState(stack1, tableOfSymbols1, output1, fileName1,heap1,example,ProgramState.getNewProgramId());
    }
}
