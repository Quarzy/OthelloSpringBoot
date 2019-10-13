package com.gmail.nishigaki.quarzy.othello.service;

import java.util.List;
import java.util.function.Consumer;

import com.gmail.nishigaki.quarzy.othello.model.GameInitResult;
import com.gmail.nishigaki.quarzy.othello.model.GameReadResult;
import com.gmail.nishigaki.quarzy.othello.model.Piece;
import com.gmail.nishigaki.quarzy.othello.model.GameResult;
import com.gmail.nishigaki.quarzy.othello.model.PieceValue;

/**
 * @author nishigaki
 */
public interface GameService {

	public GameInitResult init();

	public GameReadResult read(String name, PieceValue pieceValue);

	public List<String> readNames();

	public GameResult replace(String name, Piece piece);

	public void invoke(String name, Piece piece, Consumer<GameResult> result);
}
