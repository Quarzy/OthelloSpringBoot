import * as $ from "jquery";
import * as React from "react";
import * as Stomp from "@stomp/stompjs";
import { Row } from "./Row";
import {IFrame, IMessage} from "@stomp/stompjs";

interface BoardProps {
  name: string,
  piece: string,
  player: boolean,
  board: Array<Array<string>>,
}

interface BoardState {
  board: Array<Array<string>>,
}

export class Board extends React.Component<BoardProps, BoardState> {

  private inCallback: boolean = false;

  private client: Stomp.CompatClient;

  constructor(props: BoardProps) {
    super(props);
    this.client = Stomp.Stomp.client("ws:/localhost:8080/websocket/websocket");
    this.client.connect({},
      (event) => {
        if (!props.player) {
          this.socket();
//      this.socket2("ws:/localhost:8080/websocket/websocket");
//      this.socket2("ws:/localhost:8080/app/game");
        }
        // alert('connected!');
        // client.send("/app/game3", { simpUser: 'hogehoge', user: 'mogemoge' }, 'hoge');
        // client.send("/app/game3", {}, JSON.stringify({
        //   name: this.props.name,
        //   piece: this.props.piece
        // }));
        // client.subscribe("/game", (message) => {
        //   alert(message);
        // });
        // client.subscribe("/websocket/websocket", (message) => {
        //   alert(message);
        // });
      },
      (error) => {
        alert('error!');
      });
    this.state = {
      board: props.board,
    };
  }

  private socket() {
    this.client.subscribe("/topic/game3", (message) => {
      console.log('subscribed');
      const game = JSON.parse(message.body) as {
        player: boolean;
        pieceValue: string;
        board: Array<Array<string>>;
      };
      if (this.props.piece === game.pieceValue) {
        this.setState({ board: game.board });
      } else {
        this.socket();
      }
    });
    // const client = Stomp.Stomp.client("ws:/localhost:8080/app/game");
    // this.client.connect({},
    //   (event) => {
    //     // alert('connected!');
    //     this.client.subscribe("/topic/game3", (message) => {
    //       console.log('subscribed');
    //       console.log(message);
    //       console.log(message.body);
    //     });
    //     // client.send("/app/game3", { simpUser: 'hogehoge', user: 'mogemoge' }, 'hoge');
    //     // client.send("/app/game3", {}, JSON.stringify({
    //     //   name: this.props.name,
    //     //   piece: this.props.piece
    //     // }));
    //     // client.subscribe("/game", (message) => {
    //     //   alert(message);
    //     // });
    //     // client.subscribe("/websocket/websocket", (message) => {
    //     //   alert(message);
    //     // });
    //   },
    //   (error) => {
    //     alert('error!');
    //   });

    // const socket = new WebSocket("ws:/localhost:8080/websocket");
    // socket.onopen = () => {
    //   socket.send(JSON.stringify({x, y}));
    // };
    // socket.onmessage = (message: MessageEvent): any => {
    //   console.log(message.data);
    //   this.inCallback = false;
    // };
  }

  // private socket2(url: string) {
  //   const socket = new WebSocket(url);
  //   socket.onopen = (event: Event): any => {
  //     console.log('open');
  //     console.log(event);
  //     socket.send("hogehjoge");
  //   };
  //   socket.onerror = (event: Event): any => {
  //     console.log('error');
  //     console.log(event);
  //   };
  //   socket.onclose = (event: CloseEvent): any => {
  //     console.log('close');
  //     console.log(event);
  //   };
  //   socket.onmessage = (event: MessageEvent): any => {
  //     console.log('message');
  //     console.log(event);
  //   }
  // }



  private onClick(x: number, y: number) {
    if (this.inCallback) {
      return;
    }
    this.inCallback = true;
    $.ajax({
      url: '/game/assign',
      method: 'POST',
      data: JSON.stringify({
        name: this.props.name,
        piece: this.props.piece,
        x, y
      }),
      contentType: 'application/json'
    }).done((data, statusName, b) => {
      if (data.assigned) {
        this.setState({ board: data.board });
        this.client.send("/app/game3", {}, JSON.stringify({
          name: this.props.name,
          piece: this.props.piece
        }));
        console.log('sended');
        this.socket();
      } else {
        alert("can't assigned");
      }
      this.inCallback = false;
    }).fail((fail, a, b) => {
      alert('fail');
      this.inCallback = false;
    });
  }

  render() {
    return (
      <table cellSpacing={1} cellPadding={2}>
        <tbody>
        {this.state.board.map((pieces, index) => <Row key={index} pieces={pieces} y={index} onClick={(x, y) => this.onClick(x, y)} />)}
        </tbody>
      </table>
    );
  }
}
