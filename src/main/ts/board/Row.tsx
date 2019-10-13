import * as React from "react";
import { Square } from "./Square";

interface RowProps {
  pieces: Array<string>,
  y: number,
  onClick(x: number, y: number): void,
}

export class Row extends React.Component<RowProps> {

  render() {
    return (
      <tr>
        {this.props.pieces.map((piece, index) => <Square key={index} piece={piece} x={index} onClick={x => this.props.onClick(x, this.props.y)} />)}
      </tr>
    );
  }
}
