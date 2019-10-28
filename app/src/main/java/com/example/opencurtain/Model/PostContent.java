package com.example.opencurtain.Model;

public class PostContent {
    private int ID;

//    int id = 0;
//    String user = "";
//    int board = 0;
//    String timestamp = "";
//    String title = "";
//    String content = "";

    private String subscribe;
    private String board;
    private String university;
    private String faculty;
    private String department;
    private String post;
    private String comment;

    public int getID(){
        return ID;
    }

    public void setID(int ID){
        this.ID = ID;
    }

    public String getSubscribe(){
        return subscribe;
    }

    public void setSubscribe(String subscribe){
        this.subscribe = subscribe;
    }

    public String getBoard(){
        return board;
    }

    public void setBoard(String board){
        this.board = board;
    }

    public String getUniversity(){
        return university;
    }

    public void setUniversity(String university){
        this.university = university;
    }

    public String getFaculty(){
        return faculty;
    }

    public void setFaculty(String faculty){
        this.faculty = faculty;
    }

    public String getDepartment(){
        return department;
    }

    public void setDepartment(String department){
        this.department = department;
    }

    public String getPost(){
        return post;
    }

    public void setPost(String post){
        this.post = post;
    }

    public String getComment(){
        return comment;
    }

    public void setComment(String comment){
        this.comment = comment;
    }
}
