# OthelloSpringBoot
Spring Bootによるオセロゲームのサンプルです。

動作方法
IntelliJ IDEAによるテスト動作にみ確認

1.IntelliJ IDEAで [File]->[New]->[Project from Version Control]->[Git]
2.URLに当該URLを入力し[Clone]を押下
3.ウィンドウの右下のプログレスバーが完了するまで待機
4.ウィンドウの右下の [Non-managed pom.xml file found:] をクリック
5.[Add as Maven Project] をクリック
6.ウィンドウの右下の [Processes Running] が消えるまで待機
7.Projectペインから othello.iml を選択し右クリック、[Build Module 'OthelloSpringBoot'] を選択
8.ポップアップの[OK]をクリック
９．Project SDK を 11 に変更（変更できない場合はJava11をインストールしIDEAに登録）
10.Project language ｌevel を 11 に変更
11.Project compiler output を <プロジェクトのパス>/out に変更
12.[OK]をクリック

13.Projectペインから src->main->java->com.gmail.nishigaki.quarzy.othello->OthelloApplicationを右クリックし [Debug 'OthelloApplication']をクリック

14.ブラウザを新しく2つ開く（シークレットウィンドウが望ましい）
15.２つとも localhost:8080 を開く
16.一方で [Start Game] をクリック
17.任意のコマをクリック
18.もう一方を更新し、表示されたリンクをクリック
19.任意のコマをクリック
20.任意のコマをクリックするたび、もう一方のブラウザが自動的に更新される
