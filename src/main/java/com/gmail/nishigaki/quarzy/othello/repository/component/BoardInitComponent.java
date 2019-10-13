package com.gmail.nishigaki.quarzy.othello.repository.component;

import java.util.List;

import com.gmail.nishigaki.quarzy.othello.model.PieceValue;

/**
 * @author nishigaki
 */
public interface BoardInitComponent {

	public List<List<PieceValue>> invoke(int size);
}
