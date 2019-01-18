package tomek.szypula;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by longman on 16.01.19.
 */
public class Board {
    private List<List<Spin>> board;

    public Board(List<Spin> boardRaw, int nRows, int nColumns) {
        this.board = createBoardFromRawList(boardRaw,nRows,nColumns);
    }
    
    public Board(int nRows, int nColumns) {
         createBoardFromRawList(new ArrayList<>(),nRows,nColumns);
    }

    private List<List<Spin>> createBoardFromRawList(List<Spin> spins,int nRows, int nColumns){
        List<List<Spin>> board = new ArrayList<>();
        Spin spin = new Spin();
        for (int i = 0; i < nRows; i++) {
            board.add(new ArrayList<>());
            for (int j = 0; j < nColumns; j++) {
                if (spins.size() > i*nColumns+j)
                    spin = spins.get(i*nColumns+j);
                else
                    spin = new Spin();

                board.get(i).add(new Spin());

            }
        }
        this.board = board;
        addNeighbours(board);
        return board;
    }

    private void addNeighbours(List<List<Spin>> board) {
        List<Spin> neighbours = new ArrayList<>();
        for (int i = 0; i < board.size(); i++) {
            for (int j = 0; j < board.get(i).size(); j++) {
                setNeighbours(board.get(i).get(j),i,j);
            }

        }
    }

    private void setNeighbours(Spin spin,int i,int j){
        for (int k = -1; k <2 ; k++) {
            for (int l = -1 ; l < 2 ; l++) {
                try {
                    spin.addNeighbour(board.get(i+k).get(j+l));
                }
                catch (Exception e){

                }

            }
        }
    }

    @Override
    public String toString() {
        String string = "";
        for (List<Spin> wiersz :
                board) {
            for (Spin spin :
                    wiersz) {
                string+=spin.toString();
            }
            string+="\n";

        }
        return string;
    }
}
