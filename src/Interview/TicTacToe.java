package Interview;

enum Piece{empty, red, blue}
public class TicTacToe {
    int size;
    public Piece[][] board;

    TicTacToe(int x){
        board = new Piece[x][x];
    }
}
