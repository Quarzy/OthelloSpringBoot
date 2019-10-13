package com.gmail.nishigaki.quarzy.othello.model;

import lombok.Data;

/**
 * @author nishigaki
 */
@Data
public final class Piece {

	public Piece() {

	}

	public Piece(final PieceValue piece, final int x, final int y) {
		this.piece = piece;
		this.x = x;
		this.y = y;
	}

	private PieceValue piece;

	private int x;

	private int y;
}
