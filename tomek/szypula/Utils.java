package tomek.szypula;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by longman on 16.01.19.
 */
public class Utils {
    public static List<List<Spin>> createBoardFromRawList(List<Spin> spins,int nRows, int nColumns){
        List<List<Spin>> board = new ArrayList<>();
        for (int i = 0; i < nRows; i++) {
            board.add(new ArrayList<>());
            for (int j = 0; j < nColumns; j++) {
               if(!board.get(i).add(spins.get(i*nColumns+j))){
                   board.get(i).add(new Spin());
                }
            }
        }
        return board;
    }
    public static double getRandomDouble(){
        return Math.random();
    }
    public static int randomInRange(int min, int max)
    {
        int range = (max - min) + 1;
        return (int)(Math.random() * range) + min;
    }

    public static void writeToFile(String fileName,List<Double> values1, List<Double> values2,String column1,String column2)
            throws IOException {

        FileWriter fileWriter = new FileWriter(fileName);
        PrintWriter printWriter = new PrintWriter(fileWriter);
        printWriter.print(column1+"\t"+column2+"\n");
        for (int i = 0; i < values1.size() ; i++) {
            printWriter.printf("%f\t%f\n", values1.get(i), values2.get(i));
        }
        printWriter.close();
    }

}
