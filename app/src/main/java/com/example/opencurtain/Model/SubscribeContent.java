package com.example.opencurtain.Model;

public class SubscribeContent {
    public int id = 0;



    public int board = 0 ;
    public String boardname = "";
    public String title = "";

    @Override
    public String toString() {
        return id + "/" + title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setBoard(int board) {
        this.board = board;
    }
    public String getBoard() {
        return boardname;
    }

    public void setBoardname(String boardname) {
        this.boardname = boardname;
    }
}
