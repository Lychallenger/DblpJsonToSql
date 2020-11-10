package com.neu;

import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author ：lychallengers@gmail.com
 * @date ：2020/11/10 21:41
 */
public class ParseJson {
   public static Connection getConnect() throws ClassNotFoundException, SQLException {
       Connection con;
       String driver="com.mysql.jdbc.Driver";
       //这里我的数据库是cgjr
       String url="jdbc:mysql://219.216.65.36:3306/dblpdata?useUnicode=true&characterEncoding=utf-8&useSSL=false";
       String user="root";
       String password="123";
       Class.forName(driver);
       con=DriverManager.getConnection(url,user,password);
       if(!con.isClosed()) {
           System.out.println("connection success");
           return con;
       }
       else{
           return null;
       }
   }
   public String getObjectToString(JSONObject jsonObject,String key){
       if(jsonObject.has(key)){
           return jsonObject.getString(key);
       }
       else{
           return null;
       }
   }
   public void parseJson(Connection con,String fileName) throws IOException {
       String insertDblpSql = "insert into dblp_tbl  values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
       String insertAuthorSql = "insert into author_tbl values(?,?,?)";//数据库操作语句（插入）
       String queryAuthorSql = "select * from author_tbl where id = ?";
       String insertVenueSql = "insert into venue_tbl  values(?,?,?)";//数据库操作语句（插入）
       String queryVenueSql = "select * from venue_tbl where id = ?";
       FileReader fileReader=new FileReader(fileName);
       BufferedReader bufferedReader=new BufferedReader(fileReader);
       String readStr;
       String author_name;
       String author_org;
       String author_id;
       String title;
       String year;
       String n_citation;
       String page_start;
       String page_end;
       String doc_type;
       String publisher;
       String volume;
       String issue;
       String doi;
       String references_0;
       String venue_raw;
       String venue_id;
       String venue_type;
       while((readStr=bufferedReader.readLine())!=null){
           String str=readStr.substring(1);
           JSONObject jb = JSONObject.fromObject(str);
           String  id = jb.getString("id");
           JSONArray  authors = jb.getJSONArray("authors");
           //获取第一个author
           JSONObject author=authors.getJSONObject(0);
           author_name=getObjectToString(author,"name");
           author_org=getObjectToString(author,"org");
           author_id=getObjectToString(author,"id");
           title=getObjectToString(jb,"title");
           year=getObjectToString(jb,"year");
           System.out.println(title);
           n_citation=getObjectToString(jb,"n_citation");
           page_start=getObjectToString(jb,"page_start");
           page_end=getObjectToString(jb,"page_end");
           doc_type=getObjectToString(jb,"doc_type");
           publisher=getObjectToString(jb,"publisher");
           volume=getObjectToString(jb,"volume");
           issue=getObjectToString(jb,"issue");
           doi=getObjectToString(jb,"doi");
           if(jb.has("references")){
               JSONArray references=jb.getJSONArray("references");
               references_0=references.getString(0);
           }
           JSONObject venue=jb.getJSONObject("venue");
           venue_raw=getObjectToString(venue,"raw");
           venue_id=getObjectToString(venue,"id");
           venue_type=getObjectToString(venue,"type");
           System.out.println(id);
       }

   }
}
