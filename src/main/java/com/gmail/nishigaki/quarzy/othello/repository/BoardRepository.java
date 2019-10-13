package com.gmail.nishigaki.quarzy.othello.repository;

import java.util.List;

import com.gmail.nishigaki.quarzy.othello.model.PieceValue;

/**
 * @author nishigaki
 */
public interface BoardRepository {

	public String append();

	public List<String> readNames();

	public void replace(String name, List<List<PieceValue>> board);

	public List<List<PieceValue>> read(String name);
}
