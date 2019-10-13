import * as React from "react";
import * as ReactDOM from 'react-dom';
import { Board } from "./board/Board";
import { PieceValue } from "./board/PieceValue";

declare function init(): {
  name: string,
  piece: string,
  player: boolean,
  board: Array<Array<string>>,
}

window.onload = () => {
  const initValue = init();
  ReactDOM.render(
    <Board name={initValue.name} piece={initValue.piece} player={initValue.player} board={initValue.board} />,
    document.getElementById('main')
  );
};
