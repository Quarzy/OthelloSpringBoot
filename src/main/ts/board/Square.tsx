import * as React from "react";

interface SquareProps {
  x: number,
  piece: string,
  onClick(x: number): void,
}

export class Square extends React.Component<SquareProps> {

  private onClick(event: Event) {
    this.props.onClick(this.props.x);
  }

  private value() {
    switch (this.props.piece) {
      case 'EMPTY':
        return <span>　</span>;
      case 'SETTABLE':
        return <button type="button" onClick={event => this.props.onClick(this.props.x)}> </button>;
      case 'BLACK':
        return <span style={{color: '#000000'}}>●</span>;
      case 'WHITE':
        return <span style={{color: '#FFFFFF'}}>●</span>;
    }
  }

  render() {
    return (
      <td style={{backgroundColor: '#336633', textAlign: 'center'}}>{this.value()}</td>
    );
  }
}
