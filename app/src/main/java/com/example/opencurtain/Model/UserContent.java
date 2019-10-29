package com.example.opencurtain.Model;

import java.io.Serializable;

public class UserContent implements Serializable {
    public int id = 0;
    public String user_name = "";
    public String email = "";
    public String password = "";
    public int university = 0;
    public int faculty = 0;
    public int department = 0;
    public int authcode = 0;

    public UserContent(){

    }
}
