package tomek.szypula;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ModelManager {
    private final int numberOfThreads;
    private final Board board;
    double field = 0 ;
    double temperature = 0 ;
    List<Runnable> isingTasks = new ArrayList<>();
    List<Thread> threads = new ArrayList<>();
    ExecutorService executor;

    public ModelManager(Board board) {
        this.board = board;
        this.numberOfThreads = Runtime.getRuntime().availableProcessors();
        executor = Executors.newFixedThreadPool(numberOfThreads);
    }

    public void startSim(double temperature, int field, int iter){
        this.temperature = temperature;
        this.field = field;
        isingTasks = new ArrayList<>();
        threads = new ArrayList<>();

        for (int i = 0; i < numberOfThreads; i++) {
            Runnable runnable = () -> {
                for (int j = 0; j < iter; j++)
                    board.flipRandomSpin(temperature, field);
            };
            Thread thread = new Thread(runnable);
            executor.submit(runnable);
            threads.add(new Thread(runnable));
            isingTasks.add(runnable);
        }

        executor.shutdown();
        try {
            executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
        }
        System.out.println("T : "+temperature+" M : "+board.getMeanMagnetization());

    }
    public void startSimT(int field,int steps, double step,int iter) {
        temperature = 0 ;
        List<Double> temperatures = new ArrayList<>();
        List<Double> magnetization = new ArrayList<>();

        while (temperature < step * steps) {
            this.temperature = this.temperature + step;
            this.field = field;
            isingTasks = new ArrayList<>();
            threads = new ArrayList<>();

            for (int i = 0; i < numberOfThreads; i++) {
                Runnable runnable = () -> {
                    for (int j = 0; j < iter; j++)
                        board.flipRandomSpin(temperature, field);
                };
                Thread thread = new Thread(runnable);
                //executor.submit(runnable);
                threads.add(new Thread(runnable));
                isingTasks.add(runnable);
                thread.start();
            }

            for (Thread thread :
                    threads) {
                try {thread.join();}
                catch (Exception e){}
            }
            temperatures.add(temperature);
            magnetization.add(board.getMeanMagnetization());
        }
        try{
            Utils.writeToFile("IsingTodM.txt",temperatures,magnetization,"T","M");
        }
        catch (IOException e) {
            System.out.println(e);
        }


    }
    public void startSimH(double temperature,int steps, double step,int iter) {
        this.temperature = temperature ;
        field = (-1)*step*steps ;
        List<Double> fields = new ArrayList<>();
        List<Double> magnetization = new ArrayList<>();

        while (field < step * steps) {
            this.field = this.field + step;
            isingTasks = new ArrayList<>();
            threads = new ArrayList<>();

            for (int i = 0; i < numberOfThreads; i++) {
                Runnable runnable = () -> {
                    for (int j = 0; j < iter; j++)
                        board.flipRandomSpin(temperature, field);
                };
                Thread thread = new Thread(runnable);
                //executor.submit(runnable);
                threads.add(new Thread(runnable));
                isingTasks.add(runnable);
                thread.start();
            }

            for (Thread thread :
                    threads) {
                try {thread.join();}
                catch (Exception e){}
            }
            fields.add(field);
            magnetization.add(board.getMeanMagnetization());
        }
        try{
            Utils.writeToFile(new StringBuilder().append("IsingHodMT=").append(temperature).append(".txt").toString(),fields,magnetization,"H","M");
        }
        catch (IOException e) {
            System.out.println(e);
        }


    }

}
