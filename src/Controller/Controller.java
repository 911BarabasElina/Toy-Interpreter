package Controller;

import Model.DataStructures.MyIStack;
import Model.ModelExceptions.ExpressionException;
import Model.ModelExceptions.StatementException;
import Model.ProgramState;
import Model.Statements.IStatements;
import Model.Values.RefValue;
import Model.Values.Value;
import Repository.IRepository;
import Repository.RepositoryException;
import java.util.stream.Collectors;
import java.util.Map;
import java.util.List;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.*;

public class Controller implements IController{

    private final IRepository repository;
    private ExecutorService executor;


    public Controller(IRepository repository)
    {
        this.repository=repository;
    }

    @Override
    public IRepository getRepository() {
        return this.repository;
    }


    @Override
    public void allSteps() throws ControllerException, StatementException, ExpressionException, RepositoryException, IOException, InterruptedException {

        executor=Executors.newFixedThreadPool(2);
        List<ProgramState> programStateList=removeCompletedProgramStates(repository.getProgramStatesList());
        while(programStateList.size()>0)
        {
            programStateList.get(0).getHeap().setContent(
                    safeGarbageCollector(getAddressesFromTableOfSymbols
                        (programStateList.get(0).getTableOfSymbols().getContent().values()),
                            getAddressesFromMemoryHeap(programStateList.get(0).getHeap().getContent().values()),
                                programStateList.get(0).getHeap().getContent()));

            try{
                oneStepForAllProgramStates(programStateList);
            }
            catch(Exception ex){
                throw new ControllerException(ex.getMessage());
            }
            programStateList=removeCompletedProgramStates(repository.getProgramStatesList());
        }
        executor.shutdownNow();
        repository.setProgramStatesList(programStateList);
    }


    void oneStepForAllProgramStates(List<ProgramState> programStatesList) throws InterruptedException, ExpressionException {


        programStatesList.forEach(programState -> {
            try {
                programState.printProgramState();
                repository.logProgramStateExecution(programState);
            } catch (RepositoryException | IOException e) {
                e.printStackTrace();
            }
        });

        List<Callable<ProgramState>> callList = programStatesList.stream()
                .map((ProgramState programState) -> (Callable<ProgramState>)(programState::oneStep))
                .collect(Collectors.toList());

        List<ProgramState> newProgramsList = executor.invokeAll(callList).stream()
                .map(future -> {
                    try {
                        return future.get();
                    } catch (InterruptedException | ExecutionException e) {
                        throw new RuntimeException(e.getMessage());
                    }
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());


        programStatesList.addAll(newProgramsList);
        programStatesList.forEach(programState -> {
            try {
                programState.printProgramState();
                repository.logProgramStateExecution(programState);
            } catch (RepositoryException | IOException e ) {
                e.printStackTrace();
            }
        });

        repository.setProgramStatesList(programStatesList);
    }


    public Map<Integer, Value> safeGarbageCollector(List <Integer> tableOfSymbolsAddress, List<Integer> heapAddresses, Map<Integer,Value> heap ) {
        return heap.entrySet().stream()
                .filter(e -> tableOfSymbolsAddress.contains(e.getKey()) || heapAddresses.contains(e.getKey()) )
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public List<Integer> getAddressesFromTableOfSymbols(Collection<Value> tableOfSymbolsValue)
    {
        return tableOfSymbolsValue.stream()
                .filter(v -> v instanceof RefValue)
                .map(v -> { RefValue v1 = (RefValue) v; return v1.getAddress();})
                .collect(Collectors.toList());
    }

    public java.util.List<Integer> getAddressesFromMemoryHeap(Collection<Value> memoryHeapValues) {
        return memoryHeapValues.stream()
                .filter(value -> value instanceof RefValue)
                .map(value -> ((RefValue) value).getAddress())
                .collect(Collectors.toList());
    }

    List<ProgramState> removeCompletedProgramStates(List<ProgramState> programStateList) {
        return programStateList.stream()
                .filter(ProgramState::isNotCompleted)
                .collect(Collectors.toList());
    }
}

//
//    @Override
//    public void allSteps() throws ControllerException, StatementException, ExpressionException, RepositoryException, IOException {
//
//
//        ProgramState currentProgramState = this.repository.getCurrentProgramState();
//        currentProgramState.printProgramState();
//        try {
//            repository.logProgramStateExecution();
//        } catch (RepositoryException repositoryException) {
//            repositoryException.printStackTrace();
//        }
//        while (!currentProgramState.getExecutionStack().isEmpty()) {
//            oneStep(currentProgramState);
//            currentProgramState.printProgramState();
//            try {
//                repository.logProgramStateExecution();
//            } catch (RepositoryException repositoryException) {
//                repositoryException.printStackTrace();
//            }
//            currentProgramState.getHeap().setContent(safeGarbageCollector(getAddressesFromTableOfSymbols(currentProgramState.getTableOfSymbols().getContent().values()),getAddressesFromMemoryHeap(currentProgramState.getHeap().getContent().values()),currentProgramState.getHeap().getContent()));
//            currentProgramState.printProgramState();
//            try {
//                repository.logProgramStateExecution();
//            } catch (RepositoryException repositoryException) {
//                repositoryException.printStackTrace();
//            }
//        }
//
//    }


//    @Override
//    public ProgramState oneStep(ProgramState state) throws ControllerException, StatementException, ExpressionException, IOException, RepositoryException {
//        MyIStack<IStatements> stack=state.getExecutionStack();
//        if(stack.isEmpty())
//            throw new ControllerException("The execution stack of the current program state is empty");
//        IStatements currentState=stack.pop();
//        return currentState.execute(state);
//    }









//    @Override
//    public void allSteps() throws ControllerException, StatementException, ExpressionException, RepositoryException, IOException {
//
//
//        ProgramState currentProgramState = this.repository.getProgramStatesList().get(0);
//        currentProgramState.printProgramState();
//        try {
//            repository.logProgramStateExecution(currentProgramState);
//        } catch (RepositoryException repositoryException) {
//            repositoryException.printStackTrace();
//        }
//        while (!currentProgramState.getExecutionStack().isEmpty()) {
//            currentProgramState.oneStep();
//            currentProgramState.printProgramState();
//            try {
//                repository.logProgramStateExecution(currentProgramState);
//            } catch (RepositoryException repositoryException) {
//                repositoryException.printStackTrace();
//            }
//            currentProgramState.getHeap().setContent(safeGarbageCollector(getAddressesFromTableOfSymbols(currentProgramState.getTableOfSymbols().getContent().values()),getAddressesFromMemoryHeap(currentProgramState.getHeap().getContent().values()),currentProgramState.getHeap().getContent()));
//            currentProgramState.printProgramState();
//            try {
//                repository.logProgramStateExecution(currentProgramState);
//            } catch (RepositoryException repositoryException) {
//                repositoryException.printStackTrace();
//            }
//        }
//
//    }
