package com.gmail.nishigaki.quarzy.othello.repository.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gmail.nishigaki.quarzy.othello.repository.BoardRepository;
import com.gmail.nishigaki.quarzy.othello.repository.component.BoardInitComponent;
import com.gmail.nishigaki.quarzy.othello.model.PieceValue;

/**
 * @author nishigaki
 */
@Repository
public class BoardRepositoryImpl implements BoardRepository {

	private static final int SIZE = 8;

	private static final int COUNT = 16;

	private final Map<String, List<List<PieceValue>>> boardMap = new HashMap<>();

	@Autowired
	private BoardInitComponent _boardInitComponent;

	@Override
	public final String append() {
		String name;
		do {
			name = RandomStringUtils.randomAlphanumeric(COUNT);
		} while (boardMap.containsKey(name));
		boardMap.put(name, _boardInitComponent.invoke(SIZE));
		return name;
	}

	@Override
	public final List<String> readNames() {
		return Collections.unmodifiableList(new ArrayList<>(boardMap.keySet()));
	}

	@Override
	public final void replace(final String name, final List<List<PieceValue>> board) {
		if (boardMap.containsKey(name)) {
			boardMap.put(name, board);
		}
	}

	@Override
	public final List<List<PieceValue>> read(final String name) {
		return boardMap.get(name);
	}
}
