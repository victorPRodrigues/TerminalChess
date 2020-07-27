package chess.pieces;

import board.Board;
import board.Position;
import chess.ChessPiece;
import chess.Color;

public class King extends ChessPiece {
    public King(Board board, Color color) {
        super(board, color);
    }

    @Override
    public String toString() {
        return "K";
    }

    private boolean canMove(Position position) {
        ChessPiece p = (ChessPiece)getBoard().piece(position);
        return p == null || p.getColor() != getColor();
    }

    @Override
    public boolean[][] possibleMoves() {
        boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];

        Position p = new Position(0, 0);

        // Above
        p.setValues(position.getRow() - 1, position.getColumn());
        if (getBoard().positionExists(p) && canMove(p)) {
            mat[position.getRow() - 1][position.getColumn()] = true;
        }

        // Below
        p.setValues(position.getRow() + 1, position.getColumn());
        if (getBoard().positionExists(p) && canMove(p)) {
            mat[position.getRow() + 1][position.getColumn()] = true;
        }

        // Left
        p.setValues(position.getRow(), position.getColumn() - 1);
        if (getBoard().positionExists(p) && canMove(p)) {
            mat[position.getRow() ][position.getColumn() - 1] = true;
        }

        // Right
        p.setValues(position.getRow() , position.getColumn() + 1);
        if (getBoard().positionExists(p) && canMove(p)) {
            mat[position.getRow()][position.getColumn() + 1] = true;
        }

        // NW
        p.setValues(position.getRow() - 1, position.getColumn() - 1);
        if (getBoard().positionExists(p) && canMove(p)) {
            mat[position.getRow() - 1][position.getColumn() - 1] = true;
        }

        // NE
        p.setValues(position.getRow() - 1, position.getColumn() + 1);
        if (getBoard().positionExists(p) && canMove(p)) {
            mat[position.getRow() - 1][position.getColumn() + 1] = true;
        }

        // SW
        p.setValues(position.getRow() + 1, position.getColumn() - 1);
        if (getBoard().positionExists(p) && canMove(p)) {
            mat[position.getRow() + 1][position.getColumn() - 1] = true;
        }

        // SE
        p.setValues(position.getRow() + 1, position.getColumn() + 1);
        if (getBoard().positionExists(p) && canMove(p)) {
            mat[position.getRow() + 1][position.getColumn() + 1] = true;
        }

        return mat;
    }
}
