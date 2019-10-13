package com.gmail.nishigaki.quarzy.othello.service.util;

import java.util.List;

import com.gmail.nishigaki.quarzy.othello.model.PieceValue;

/**
 * @author nishigaki
 */
public final class Util {

	private Util() {}

	public static boolean judge(final int x, final int y, final List<List<PieceValue>> board) {
		return judge(x, board) && judge(y, board);
	}

	public static boolean judge(final int position, final List<List<PieceValue>> board) {
		return 0 <= position && position < board.size();
	}
}
