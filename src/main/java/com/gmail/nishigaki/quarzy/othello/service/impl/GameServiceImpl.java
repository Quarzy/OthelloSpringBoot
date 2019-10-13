package com.gmail.nishigaki.quarzy.othello.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gmail.nishigaki.quarzy.othello.model.Game;
import com.gmail.nishigaki.quarzy.othello.model.GameInitResult;
import com.gmail.nishigaki.quarzy.othello.model.GameReadResult;
import com.gmail.nishigaki.quarzy.othello.model.Piece;
import com.gmail.nishigaki.quarzy.othello.model.PieceValue;
import com.gmail.nishigaki.quarzy.othello.model.GameResult;
import com.gmail.nishigaki.quarzy.othello.repository.BoardRepository;
import com.gmail.nishigaki.quarzy.othello.repository.CurrentPlayerRepository;
import com.gmail.nishigaki.quarzy.othello.service.GameService;
import com.gmail.nishigaki.quarzy.othello.service.component.BoardComponent;
import com.gmail.nishigaki.quarzy.othello.service.component.SettableAssignComponent;

/**
 * @author nishigaki
 */
@Service
public class GameServiceImpl implements GameService {

	@Autowired
	private BoardRepository boardRepository;

	@Autowired
	private CurrentPlayerRepository currentPlayerRepository;

	@Autowired
	private BoardComponent boardComponent;

	@Autowired
	private SettableAssignComponent settableAssignComponent;

	private Map<String, Consumer<GameResult>> CONSUMER_MAP = new HashMap<>();

	@Override
	public final GameInitResult init() {
		final String name = boardRepository.append();
		currentPlayerRepository.replace(name, PieceValue.BLACK);
		final List<List<PieceValue>> board = settableAssignComponent.invoke(boardRepository.read(name), PieceValue.BLACK);
		return new GameInitResult(name, PieceValue.BLACK, board);
	}

	@Override
	public GameReadResult read(final String name, final PieceValue pieceValue) {
		final List<List<PieceValue>> board = boardRepository.read(name);
		if (board == null) {
			throw new RuntimeException();
		}
		final PieceValue player = currentPlayerRepository.read(name);
		if (player == pieceValue) {
			return new GameReadResult(true, pieceValue, settableAssignComponent.invoke(board, pieceValue));
		} else {
			return new GameReadResult(false, pieceValue, board);
		}
	}

	@Override
	public final List<String> readNames() {
		return boardRepository.readNames();
	}

	@Override
	public final GameResult replace(final String name, final Piece piece) {
		final List<List<PieceValue>> board = boardRepository.read(name);
		if (board == null) {
			throw new RuntimeException();
		}
		final PieceValue player = currentPlayerRepository.read(name);
		if (player == piece.getPiece()) {
			currentPlayerRepository.replace(name, player == PieceValue.BLACK ? PieceValue.WHITE : PieceValue.BLACK);
			return boardComponent.invoke(board, piece);
		} else {
			return new GameResult(false, board);
		}
	}

	@Override
	public final void invoke(final String name, final Piece piece, final Consumer<GameResult> result) {

	}
}
