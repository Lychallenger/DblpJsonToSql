package com.neu;

/**
 * @author ：liwan012@ke.com
 * @description：main
 * @date ：2020/11/7 19:10
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import java.util.*;
import java.sql.*;
public class Main {
    public boolean isBlank(String s){
        if(s==null||s.isEmpty()||s.equals("")||s.trim().equals("")||s.length()>200){
            return true;
        }else {
            return false;
        }
    }
    public String handleString(String s){
        s=s.replaceAll("\r"," ");
        s=s.replaceAll("\n"," ");
        s=s.replaceAll(","," ");
        return s;
    }
    public  void printPareResult(Connection con,String fileName) throws IOException, SQLException {
        new ParseJson().parseJson(con,fileName);
    }


    public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {
        String fileName="dblp.json";
        Connection con=ParseJson.getConnect();
        new Main().printPareResult(con,fileName);
    }
}
