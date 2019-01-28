package tomek.szypula;

public class Main {

    public static void main(String[] args) {
	// write your code here
        Board board = new Board(12,12);
        System.out.println(board.toString());
        ModelManager model = new ModelManager(board);
        model.startSimH(0.05,1000,0.01,200);
    }
}
