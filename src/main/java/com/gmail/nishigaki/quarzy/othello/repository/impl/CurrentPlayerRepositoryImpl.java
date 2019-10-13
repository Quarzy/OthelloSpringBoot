package com.gmail.nishigaki.quarzy.othello.repository.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.gmail.nishigaki.quarzy.othello.model.PieceValue;
import com.gmail.nishigaki.quarzy.othello.repository.CurrentPlayerRepository;

/**
 * @author nishigaki
 */
@Repository
public class CurrentPlayerRepositoryImpl implements CurrentPlayerRepository {

	private Map<String, PieceValue> playerMap = new HashMap<>();

	@Override
	public final void replace(final String name, final PieceValue pieceValue) {
		playerMap.put(name, pieceValue);
	}

	@Override
	public final PieceValue read(final String name) {
		return playerMap.get(name);
	}
}
