package com.example.opencurtain.Model;

public class UniversityContent {
    public int id = 0;
    public String university_name = "";
    public int board = 0;

    public UniversityContent(){

    }

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getUniversity_name(){
        return university_name;
    }

    public void setUniversity_name(String university_name){
        this.university_name = university_name;
    }

    public int getBoard(){
        return board;
    }

    public void setBoard(int board){
        this.board = board;
    }
}
