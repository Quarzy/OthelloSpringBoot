package com.gmail.nishigaki.quarzy.othello.model;

import java.util.List;

import lombok.Data;

/**
 * @author nishigaki
 */
@Data
public final class Game {

	private int blackId;

	private int whiteId;

	private int currentId;

	private List<List<PieceValue>> board;
}
