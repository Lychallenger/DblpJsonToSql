package com.neu;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONReader;

import java.io.*;
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
       if(jsonObject.containsKey(key)){
           return jsonObject.getString(key);
       }
       else{
           return "NONE";
       }
   }

    public Reader getFileReader(String pathFile) throws FileNotFoundException {
        Reader reader = new FileReader(pathFile);//转化为流
        return reader;
    }

   public void parseJson(Connection con,String fileName) throws IOException {
       String insertDblpSql = "insert into dblp_tbl  values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
       String insertAuthorSql = "insert into author_tbl values(?,?,?)";//数据库操作语句（插入）
       String queryAuthorSql = "select * from author_tbl where id = ?";
       String insertVenueSql = "insert into venue_tbl  values(?,?,?)";//数据库操作语句（插入）
       String queryVenueSql = "select * from venue_tbl where id = ?";
       Reader readerArray = getFileReader(fileName);
       JSONReader jsonArray = new JSONReader(readerArray);//传入流

       String readStr="NONE";
       String author_name="NONE";
       String author_org="NONE";
       String author_id="NONE";
       String title="NONE";
       String year;
       String n_citation="NONE";
       String page_start="NONE";
       String page_end="NONE";
       String doc_type="NONE";
       String publisher="NONE";
       String volume="NONE";
       String issue="NONE";
       String doi="NONE";
       String references_0="NONE";
       String venue_raw="NONE";
       String venue_id="NONE";
       String venue_type="NONE";
       jsonArray.startArray();//相当于开始读整个json的Object对象。
       while(jsonArray.hasNext()){
           JSONObject jb= (JSONObject) jsonArray.readObject();
           //处理单个JSON对象内容 {}.这里可以再一次使用流式解析analysisObject,解析单个对象
           String  id = jb.getString("id");
           JSONArray authors = jb.getJSONArray("authors");
           //获取第一个author
           JSONObject author=authors.getJSONObject(0);
           author_name=getObjectToString(author,"name");
           author_org=getObjectToString(author,"org");
           author_id=getObjectToString(author,"id");
           title=getObjectToString(jb,"title");
           year=getObjectToString(jb,"year");
           n_citation=getObjectToString(jb,"n_citation");
           page_start=getObjectToString(jb,"page_start");
           page_end=getObjectToString(jb,"page_end");
           doc_type=getObjectToString(jb,"doc_type");
           publisher=getObjectToString(jb,"publisher");
           volume=getObjectToString(jb,"volume");
           issue=getObjectToString(jb,"issue");
           doi=getObjectToString(jb,"doi");
           if(jb.containsKey("references")){
               JSONArray references=jb.getJSONArray("references");
               references_0=references.getString(0);
           }
           JSONObject venue=jb.getJSONObject("venue");
           venue_raw=getObjectToString(venue,"raw");
           venue_id=getObjectToString(venue,"id");
           venue_type=getObjectToString(venue,"type");
           System.out.println(id);
       }
       jsonArray.endArray();//结束读取
       jsonArray.close();//关闭reader流

   }
}
