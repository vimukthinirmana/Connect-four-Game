package lk.ijse.dep.service;

public class AiPlayer extends Player{

    public AiPlayer(Board board) {
        super(board);
    }

    public void movePiece(int col) {
        do{
            col = (int) ((Math.random() * 6));
        }while(!board.isLegalMove(col));

        board.updateMove(col, Piece.GREEN);
        board.getBoardUI().update(col,false);

        Winner winner = board.findWinner();
        if(winner.getWinningPiece() != Piece.EMPTY){
            board.getBoardUI().notifyWinner(winner);
        }
        else if(!board.existLegalMoves()){
            board.getBoardUI().notifyWinner(new Winner(Piece.EMPTY));
        }
    }
}

/////////////////////////////////////////////////////////////////////////ak-ai////////////////////////////////////////////////////////////////////////////////////////////////////////
/*


package lk.ijse.dep.service;

public class AiPlayer extends Player {
    public AiPlayer(Board board) {
        super(board);
    }

    private int heuristicVal;

    private int getHeuristicVal() {
        int heuristicVal;
        do {
            heuristicVal = (int) ((Math.random() * (6 - 0)) + 0);
        } while (!board.isLegalMoves(heuristicVal));
        return heuristicVal;
    }
    /////////////////////////
    private int minimax(int depth, boolean maximizingPlayer){
        if(depth==0){
            heuristicVal=getHeuristicVal();
            return heuristicVal;
        } else if(maximizingPlayer){
            int maxEval=3;
            for (int i = 0; i < 6; i++) {
                heuristicVal=minimax(depth+1,false);
                if(heuristicVal>maxEval){
                    maxEval=heuristicVal;
                }
            }
            return maxEval;
        }else{
            int minEval=depth;
            for (int i = 0; i < 6; i++) {
                heuristicVal=minimax(depth+1,true);
                if(heuristicVal>minEval){
                    minEval=heuristicVal;
                }
            }
            return minEval;
        }
    }

    ///////////////////////////
    @Override
    public void movePiece(int col) {
        col= minimax(col,false);
        board.updateMove(col,Piece.GREEN);
        board.getBoardUI().update(col,false);
        Winner winner = this.board.findWinner();
        
        if(board.findWinner().getWinningPiece()!=Piece.EMPTY){
            board.getBoardUI().notifyWinner(winner);
        } else if (!board.existLegalMoves()) {
            board.getBoardUI().notifyWinner(new Winner(Piece.EMPTY));
        }
    }
}

*/
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////