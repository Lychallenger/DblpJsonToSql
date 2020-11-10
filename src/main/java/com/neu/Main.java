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
    public  void printPareResult(Connection con,String fileName) throws IOException {
        new ParseJson().parseJson(con,fileName);
    }
//    public void importDblp(String fileName) throws ClassNotFoundException, SQLException {
//        Connection con;
//        String driver="com.mysql.jdbc.Driver";
//        //这里我的数据库是cgjr
//        String url="jdbc:mysql://219.216.65.36:3306/dblpdata?useUnicode=true&characterEncoding=utf-8&useSSL=false";
//        String user="root";
//        String password="123";
//        Class.forName(driver);
//        con=DriverManager.getConnection(url,user,password);
//        if(!con.isClosed()) System.out.println("connection success");
//        String sql = "insert into dblp_tbl  values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
//        //数据库操作语句（插入）
//
//        try {
//            JsonParser parser=new JsonParser();  //创建JSON解析器
//            JsonObject object=(JsonObject) parser.parse(new FileReader(fileName));  //创建JsonObject对象
//
//            JsonArray array=object.get("dblp").getAsJsonArray();    //得到为json的数组
//            System.out.println("Get Json Array");
//            for(int i=0;i<array.size();i++){
//                JsonObject subObject=array.get(i).getAsJsonObject();
//                String id=subObject.get("id").getAsString();
//                JsonArray authorArray=subObject.get("authors").getAsJsonArray();
//                String author=authorArray.get(0).getAsJsonObject().get("id").getAsString();
//                String title=subObject.get("title").getAsString();
//                int year=subObject.get("year").getAsInt();
//                String n_citation=subObject.get("n_citation").getAsString();
//                String page_start=subObject.get("page_start").getAsString();
//                String page_end=subObject.get("page_end").getAsString();
//                String doc_type=subObject.get("doc_type").getAsString();
//                String publisher=subObject.get("publisher").getAsString();
//                String volume=subObject.get("volume").getAsString();
//                String issue=subObject.get("issue").getAsString();
//                String doi=subObject.get("doi").getAsString();
//                String references_id=null;
//                if(subObject.has("references")){
//                    JsonArray referenceArray=subObject.get("references").getAsJsonArray();
//                    references_id=referenceArray.get(0).getAsString();
//                }
//                JsonObject venueObject=subObject.get("venue").getAsJsonObject();
//                String venue_id=null;
//                if(venueObject.has("id")){
//                    venue_id= venueObject.get("id").getAsString();
//                }
//
//                PreparedStatement pst = con.prepareStatement(sql);//用来执行SQL语句查询，对sql语句进行预编译处理
//                pst.setString(1, this.handleString(id));
//                pst.setString(2, this.isBlank(author)?"NONE":this.handleString(author) );
//                pst.setString(3,this.isBlank(title)?"NONE":this.handleString(title));
//                pst.setInt(5,year);
//                pst.setString(4,this.isBlank(n_citation)?"NONE":this.handleString(n_citation));
//                pst.setString(6,this.isBlank(page_start)?"-1":this.handleString(page_start));
//                pst.setString(7,this.isBlank(page_end)?"-1":this.handleString(page_end));
//                pst.setString(8,this.isBlank(doc_type)?"NONE":this.handleString(doc_type));
//                pst.setString(9,this.isBlank(publisher)?"NONE":this.handleString(publisher));
//                pst.setString(10,this.isBlank(volume)?"NONE":this.handleString(volume));
//                pst.setString(11,this.isBlank(issue)?"NONE":this.handleString(issue));
//                pst.setString(12,this.isBlank(doi)?"NONE":this.handleString(doi));
//                pst.setString(13,this.isBlank(references_id)?"NONE":this.handleString(references_id));
//                pst.setString(14,this.isBlank(venue_id)?"NONE":this.handleString(venue_id));
//                pst.executeUpdate();//解释在下
//            }
//        } catch (JsonIOException e) {
//            e.printStackTrace();
//        } catch (JsonSyntaxException e) {
//            e.printStackTrace();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        con.close();
//        System.out.println("数据库已关闭连接");
//    }

//    public void insertAuthor(String fileName) throws ClassNotFoundException, SQLException {
//        Connection con;
//        String driver="com.mysql.jdbc.Driver";
//        //这里我的数据库是cgjr
//        String url="jdbc:mysql://219.216.65.36:3306/dblpdata?useUnicode=true&characterEncoding=utf-8&useSSL=false";
//        String user="root";
//        String password="123";
//        Class.forName(driver);
//        con=DriverManager.getConnection(url,user,password);
//        if(!con.isClosed()) System.out.println("connection success");
//        String sql = "insert into author_tbl values(?,?,?)";//数据库操作语句（插入）
//        String querySql = "select * from author_tbl where id = ?";
//        try {
//            JsonParser parser=new JsonParser();  //创建JSON解析器
//            JsonObject object=(JsonObject) parser.parse(new FileReader(fileName));  //创建JsonObject对象
//
//            JsonArray array=object.get("dblp").getAsJsonArray();    //得到为json的数组
//            System.out.println("Get Json Array");
//            for(int i=0;i<array.size();i++){
//                JsonObject subObject=array.get(i).getAsJsonObject();
//                JsonArray authorArray=subObject.get("authors").getAsJsonArray();
//                String author=authorArray.get(0).getAsJsonObject().get("id").getAsString();
//                PreparedStatement pstmt = con.prepareStatement(querySql);
//                pstmt.setString(1, author);
//                ResultSet rs = pstmt.executeQuery();
//
//                if(rs.next())continue;
//                String name="NONE";
//                String org="NONE";
//                if(authorArray.get(0).getAsJsonObject().has("name")){
//                    name=authorArray.get(0).getAsJsonObject().get("name").getAsString();
//                }
//                if(authorArray.get(0).getAsJsonObject().has("org"))
//                    org=authorArray.get(0).getAsJsonObject().get("org").getAsString();
//                PreparedStatement pst = con.prepareStatement(sql);//用来执行SQL语句查询，对sql语句进行预编译处理
//                pst.setString(1, this.handleString(author));
//                pst.setString(2, this.isBlank(org)?"NONE":this.handleString(org));
//                pst.setString(3,this.isBlank(name)?"NONE":this.handleString(name));
//                pst.executeUpdate();//解释在下
//            }
//        } catch (JsonIOException e) {
//            e.printStackTrace();
//        } catch (JsonSyntaxException e) {
//            e.printStackTrace();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        con.close();
//        System.out.println("数据库已关闭连接");
//    }

//    public void insertVenue(String fileName) throws ClassNotFoundException, SQLException {
//        Connection con;
//        String driver="com.mysql.jdbc.Driver";
//        //这里我的数据库是cgjr
//        String url="jdbc:mysql://219.216.65.36:3306/dblpdata?useUnicode=true&characterEncoding=utf-8&useSSL=false";
//        String user="root";
//        String password="123";
//        Class.forName(driver);
//        con=DriverManager.getConnection(url,user,password);
//        if(!con.isClosed()) System.out.println("connection success");
//        String sql = "insert into venue_tbl  values(?,?,?)";//数据库操作语句（插入）
//        String querySql = "select * from venue_tbl where id = ?";
//        try {
//            JsonParser parser=new JsonParser();  //创建JSON解析器
//            JsonObject object=(JsonObject) parser.parse(new FileReader(fileName));  //创建JsonObject对象
//
//            JsonArray array=object.get("dblp").getAsJsonArray();    //得到为json的数组
//            System.out.println("Get Json Array");
//            for(int i=0;i<array.size();i++){
//                JsonObject subObject=array.get(i).getAsJsonObject();
//                JsonObject venueObject=subObject.get("venue").getAsJsonObject();
//                String venue_id=null;
//                if(venueObject.has("id")){
//                    venue_id= venueObject.get("id").getAsString();
//                }
//                else{
//                    continue;
//                }
//                PreparedStatement pstmt = con.prepareStatement(querySql);
//                pstmt.setString(1, venue_id);
//                ResultSet rs = pstmt.executeQuery();
//                if(rs.next())continue;
//                String raw="NONE";
//                String type="NONE";
//                if(venueObject.has("raw")){
//                    raw=venueObject.get("raw").getAsString();
//                }
//                if(venueObject.has("type")){
//                    type=venueObject.get("type").getAsString();
//                }
//                PreparedStatement pst = con.prepareStatement(sql);//用来执行SQL语句查询，对sql语句进行预编译处理
//                pst.setString(1, this.handleString(venue_id));
//                pst.setString(2, this.isBlank(raw)?"NONE":this.handleString(raw));
//                pst.setString(3,this.isBlank(type)?"NONE":this.handleString(type));
//                pst.executeUpdate();//解释在下
//            }
//        } catch (JsonIOException e) {
//            e.printStackTrace();
//        } catch (JsonSyntaxException e) {
//            e.printStackTrace();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        con.close();
//        System.out.println("数据库已关闭连接");
//    }


    public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {
        String fileName="dblp.json";
        Connection con=ParseJson.getConnect();
        new Main().printPareResult(con,fileName);
//        new Main().importDblp(fileName);
//        new Main().insertAuthor(fileName);
//        new Main().insertVenue(fileName);
    }
}
