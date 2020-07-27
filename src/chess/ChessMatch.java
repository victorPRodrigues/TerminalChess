package chess;

import board.Board;
import board.Piece;
import board.Position;
import chess.pieces.King;
import chess.pieces.Rook;

public class ChessMatch {

    private final Board board;
    private int turn;
    private Color currentPlayer;

    public ChessMatch() {
        this.board = new Board(8, 8);
        this.currentPlayer = Color.WHITE;
        this.turn = 1;
        initialSetup();
    }

    public int getTurn() {
        return this.turn;
    }

    public Color getCurrentPlayer() {
        return this.currentPlayer;
    }

    private void initialSetup() {
        placeNewPiece('a', 1, new Rook(board, Color.WHITE));
        placeNewPiece('h', 1, new Rook(board, Color.WHITE));
        placeNewPiece('a', 8, new Rook(board, Color.BLACK));
        placeNewPiece('h', 8, new Rook(board, Color.BLACK));
        placeNewPiece('e', 1, new King(board, Color.WHITE));
    }

    public boolean[][] possibleMoves(ChessPosition sourcePosition) {
        Position position = sourcePosition.toPosition();
        validateSourcePosition(position);
        return board.piece(position).possibleMoves();
    }

    public ChessPiece performChessMove(ChessPosition sourcePosition, ChessPosition targetPosition) {
        Position source = sourcePosition.toPosition();
        Position target = targetPosition.toPosition();
        validateSourcePosition(source);
        validateTargetPosition(source, target);
        Piece capturedPieced = makeMove(source, target);
        nextTurn();
        return (ChessPiece)capturedPieced;

    }

    private void validateSourcePosition(Position position) {
        if (!board.thereIsAPiece(position)) {
            throw new ChessException("No piece in sourcePosition");
        }
        if (currentPlayer != ((ChessPiece)board.piece(position)).getColor()) {
            throw new ChessException("The chosen piece isn't yours");
        }
        if (!board.piece(position).isThereAnyPossibleMove()) {
            throw new ChessException("There is no possible moves for the chosen piece");
        }
    }

    private void validateTargetPosition(Position source, Position target) {
        if (!board.piece(source).possibleMove(target)) {
            throw new ChessException("Impossible move");
        }
    }

    private void nextTurn() {
        turn++;
        currentPlayer = (currentPlayer == Color.WHITE) ? Color.BLACK : Color.WHITE;
    }

    private Piece makeMove(Position source, Position target) {
        Piece p = board.removePiece(source);
        Piece captured = board.removePiece(target);
        board.placePiece(p, target);
        return captured;
    }

    private void placeNewPiece(char column, int row, ChessPiece piece) {
        board.placePiece(piece, new ChessPosition(column, row).toPosition());
    }

    public ChessPiece[][] getPieces() {
        ChessPiece[][] mat = new ChessPiece[board.getRows()][board.getColumns()];
        for (int i = 0; i < board.getRows(); ++i) {
            for (int j = 0; j < board.getColumns(); ++j) {
                mat[i][j] = (ChessPiece) board.piece(i, j);
            }
        }

        return mat;
    }

}
