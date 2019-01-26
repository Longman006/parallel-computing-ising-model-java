package tomek.szypula;

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

}
