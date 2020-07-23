package app;

import chess.ChessMatch;
import chess.ChessPiece;

public class Game {

    public static void main(String[] args) {

        ChessMatch chessMatch = new ChessMatch();
        ChessPiece[][] pieces = chessMatch.getPieces();
        UI.printBoard(chessMatch.getPieces());
    }

}
