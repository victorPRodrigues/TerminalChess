package chess;

import board.Board;
import board.Position;
import chess.pieces.Rook;

public class ChessMatch {

    private Board board;

    public ChessMatch() {
        this.board = new Board(8,8);
        initialSetup();
    }

    private void initialSetup() {
        board.placePiece(new Rook(board, Color.WHITE), new Position(7, 1));
    }

    public ChessPiece[][] getPieces() {
        ChessPiece[][] mat = new ChessPiece[board.getRows()][board.getColumns()];
        for(int i = 0; i < board.getRows(); ++i) {
            for(int j = 0; j < board.getColumns(); ++j) {
                mat[i][j] = (ChessPiece) board.piece(i, j);
            }
        }

        return mat;
    }

}
