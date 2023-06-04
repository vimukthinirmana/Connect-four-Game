package lk.ijse.dep.service;

public class BoardImpl implements Board {
    private BoardUI boardUI;
    private Piece pieces[][] ;

    public BoardImpl(BoardUI boardUI) {
        pieces = new Piece[NUM_OF_ROWS][NUM_OF_COLS];
        this.boardUI=boardUI;

        // change all array values to EMPTY
        for(int i = 0; i< pieces.length; i++){
            for(int j = 0; j< pieces[i].length; j++) {
                pieces[i][j] = Piece.EMPTY;
            }
        }
    }

    @Override
    public BoardUI getBoardUI() {
        return boardUI;
    }

    @Override
    public int findNextAvailableSpot(int col) {
        for(int i = 0; i< pieces.length; i++){
            if(pieces[i][col] == Piece.EMPTY){
                return i; // legal move
            }
        }
        return -1; // illegal move
    }

    @Override
    public  boolean isLegalMove(int col) {
        if(findNextAvailableSpot(col)!=-1){ // if legal move return true, else return false
            return true;
        }
        return false;
    }

    @Override
    public boolean existLegalMoves() {
        for(int col=0; col<NUM_OF_COLS; col++){
                if(isLegalMove(col)){
                    return true;
                }
        }
        return false;
    }

    @Override
    public void updateMove(int col, Piece move) {
        int row = findNextAvailableSpot(col);
        pieces[row][col]=move;   // put the passed piece (move) to next available spot
    }

    @Override
    public Winner findWinner() {

      /*  for (int row = 0; row< pieces.length; row++){ // Row
            for (int col=0; col<3; col++){
                if(pieces[row][col]== pieces[row][col+1] && pieces[row][col+1]== pieces[row][col+2] && pieces[row][col+2]== pieces[row][col+3]){
                    Winner winner = new Winner(pieces[row][col],row,col,row,col+3);
                    return winner;
                }
            }
        }


        for (int col=0; col<5; col++){ // columns
            for (int row=0; row<2; row++){
                if(pieces[row][col]== pieces[row+1][col] && pieces[row+1][col]== pieces[row+2][col] && pieces[row+2][col]== pieces[row+3][col]){
                    Winner winner = new Winner(pieces[row][col],row,col,row+3,col);
                    return winner;
                }
            }
        }*/

        for (int i = 0; i< pieces.length; i++){ // check in rows --> GREEN
            for (int j=0; j<3; j++){
                if(pieces[i][j]== pieces[i][j+1] && pieces[i][j+1]== pieces[i][j+2] && pieces[i][j+2]== pieces[i][j+3] && pieces[i][j]==Piece.GREEN){
                    Winner winner = new Winner(Piece.GREEN,i,j,i,j+3);
                    return winner;
                }
            }
        }

        for (int i = 0; i< pieces.length; i++){ // check in rows --> BLUE
            for (int j=0; j<3; j++){
                if(pieces[i][j]== pieces[i][j+1] && pieces[i][j+1]== pieces[i][j+2] && pieces[i][j+2]== pieces[i][j+3] && pieces[i][j]==Piece.BLUE){
                    Winner winner = new Winner(Piece.BLUE,i,j,i,j+3);
                    return winner;
                }
            }
        }

        for (int j=0; j<5; j++){ // check in columns --> GREEN
            for (int i=0; i<2; i++){
                if(pieces[i][j]== pieces[i+1][j] && pieces[i+1][j]== pieces[i+2][j] && pieces[i+2][j]== pieces[i+3][j] && pieces[i][j]== Piece.GREEN ){
                    Winner winner = new Winner(Piece.GREEN,i,j,i+3,j);
                    return winner;
                }
            }
        }

        for (int j=0; j<5; j++){ // check in columns --> BLUE
            for (int i=0; i<2; i++){
                if(pieces[i][j]== pieces[i+1][j] && pieces[i+1][j]== pieces[i+2][j] && pieces[i+2][j]== pieces[i+3][j] && pieces[i][j]== Piece.BLUE ){
                    Winner winner = new Winner(Piece.BLUE,i,j,i+3,j);
                    return winner;
                }
            }
        }

        Winner winner = new Winner(Piece.EMPTY); // if no winner (game is tied)
        return winner;
    }

    //--------------getters and setters

    public Piece[][] getPieces() {
        return pieces;
    }
}





////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/*
package lk.ijse.dep.service;


public class BoardImpl implements Board {
    private Piece[][] pieces;
    private BoardUI boardUI;
    public BoardImpl(BoardUI boardUI){
        this.boardUI=boardUI;
        pieces=new Piece[NUM_OF_COLS][NUM_OF_ROWS];

        for (int i=0; i< pieces.length; i++){
            for (int j=0; j<pieces[i].length; j++){
                pieces[i][j]=Piece.EMPTY;
            }
        }
    }

    @Override
    public BoardUI getBoardUI() {
        return boardUI;
    }

    @Override
    public int findNextAvailableSpot(int col) {
        int count=5;
        for(int i=0; i<pieces[i].length;i++){
            if(pieces[col][i]==Piece.EMPTY){
                count--;
            }
        }
        if(count==5){
            count=-1;
        }
        return count;

    }

    @Override
    public boolean isLegalMoves(int col) {
        boolean legal=true;

        int count= findNextAvailableSpot(col);

        if(count==-1) legal =false;
        return legal;
    }


    @Override
    public boolean existLegalMoves() {
        boolean legal=false;

        for (int i=0; i< pieces.length; i++){
            for (int j=0; j< pieces[i].length; j++){
                if(pieces[i][j]==Piece.EMPTY){
                    legal=true;
                }
            }
        }

        return legal;
    }

    @Override
    public void updateMove(int col, Piece move) {pieces[col][findNextAvailableSpot(col)]=move;
    }

    @Override
    public void updateMove(int col, int row, Piece move) {
        pieces[col][row]=move;
    }

    @Override
    public Winner findWinner() {
        Piece winningPiece=Piece.EMPTY;

        int col1=0;
        int col2=0;
        int row1=0;
        int row2=0;

        for(int i=0; i< pieces.length;i++){
            if(findNextAvailableSpot(i)==4 || findNextAvailableSpot(i)==-1){
                if(pieces[i][0]==pieces[i][1] && pieces[i][1]==pieces[i][2] && pieces[i][2]==pieces[i][3]){
                    winningPiece=pieces[i][0];
                    col1=i;
                    col2=i;
                    row1=0;
                    row2=3;

                } else if ( pieces[i][1]==pieces[i][2] && pieces[i][2]==pieces[i][3] && pieces[i][3] ==pieces[i][4]) {
                    winningPiece=pieces[i][1];
                    col1=i;
                    col2=i;
                    row1=1;
                    row2=4;

                }
            }
        }

        for(int i=0; i< pieces[i].length;i++){
            if(findAvailability(i)==4 || findAvailability(i)==5 || findAvailability(i)==-1){
                if(pieces[0][i]==pieces[1][i] && pieces[1][i]==pieces[2][i] && pieces[2][i]==pieces[3][i]){
                    winningPiece=pieces[0][i];
                    col1=0;
                    col2=3;
                    row1=i;
                    row2=i;
                } else if (pieces[1][i]==pieces[2][i] && pieces[2][i]==pieces[3][i] && pieces[3][i]==pieces[4][i]) {
                    winningPiece=pieces[1][i];
                    col1=1;
                    col2=4;
                    row1=i;
                    row2=i;
                } else if (pieces[2][i] == pieces[3][i] && pieces[3][i] == pieces[4][i] && pieces[4][i] == pieces[5][i]) {
                    winningPiece=pieces[2][i];
                    col1=2;
                    col2=5;
                    row1=i;
                    row2=i;
                }
            }
        }

        Winner winner;

        if(winningPiece==Piece.EMPTY){
            winner= new Winner(winningPiece);
        }else {
            winner= new Winner(winningPiece, col1, row1, col2, row2 );
        }

        return winner;
    }

    private int findAvailability(int row) {
        int count=6;
        for(int i=0; i<pieces.length;i++){
            if(pieces[i][row]==Piece.EMPTY){
                count--;
            }
        }
        if(count==6){
            count=-1;
        }
        return count;

    }
}




*/
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////