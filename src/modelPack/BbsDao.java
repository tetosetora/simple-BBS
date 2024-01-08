package modelPack;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BbsDao {
 private Connection connection;

 public BbsDao() throws ClassNotFoundException, SQLException {
  Class.forName("com.mysql.jdbc.Driver");
  connection = DriverManager.getConnection(
// 私のデータベースにつなげます。
 }

 public void close(){
  try{
   if (connection != null){
    connection.close();
   }
  } catch (SQLException e){
   e.printStackTrace();
  }
 }

 public int getRecordCount(String thread_id) throws SQLException {
  int count = 0;
  PreparedStatement pstatement = null;
  ResultSet rs = null;
  try{
   if(thread_id != null) {
    pstatement = connection.prepareStatement("SELECT COUNT(*) FROM bbs_tbl WHERE thread_id=?");
    pstatement.setInt(1, Integer.parseInt(thread_id));
   }else{
    pstatement = connection.prepareStatement("SELECT COUNT(DISTINCT thread_id) FROM bbs_tbl");
   }
   rs = pstatement.executeQuery();
   if(rs.next()) {
    count = rs.getInt(1);
   }
   rs.close();
  } finally {
   pstatement.close();
  }
  return count;
 }

 public ArrayList<BbsEntity> getThreadData(int page, boolean new20) throws SQLException {
  BbsEntity bbs = null;
  PreparedStatement pstatement = null;
  ResultSet rs = null;
  ArrayList<BbsEntity> list= new ArrayList<BbsEntity>();
  try{
   if(new20==true){
    pstatement = connection.prepareStatement("SELECT main_tbl.thread_id, main_tbl.speaker, main_tbl.content, MAX(main_tbl.ymdhms) AS newYmdhms, (SELECT COUNT(*) FROM bbs_tbl WHERE bbs_tbl.thread_id = main_tbl.thread_id) AS count FROM (SELECT * FROM bbs_tbl) AS main_tbl GROUP BY main_tbl.thread_id ORDER BY newYmdhms DESC LIMIT 20");
   }else{
    pstatement = connection.prepareStatement("SELECT main_tbl.thread_id, main_tbl.speaker, main_tbl.content, main_tbl.ymdhms AS newYmdhms, (SELECT COUNT(*) FROM bbs_tbl WHERE bbs_tbl.thread_id = main_tbl.thread_id) AS count FROM (SELECT * FROM bbs_tbl group by thread_id ORDER BY thread_id ASC, ymdhms ASC LIMIT 20 OFFSET ?) AS main_tbl");
    pstatement.setInt(1, (page-1)*20);
   }
   rs = pstatement.executeQuery();
   while(rs.next()) {
    bbs = new BbsEntity();
    bbs.setThread_id(rs.getInt("thread_id"));
    bbs.setSpeaker(rs.getString("speaker"));
    bbs.setContent(rs.getString("content"));
    bbs.setYmdhms(rs.getString("newYmdhms"));
    bbs.setCount(rs.getInt("count"));
    list.add(bbs);
   }
   rs.close();
  } finally {
   pstatement.close();
  }
  return list;
 }

 public BbsEntity getFirstResData(int thread_id) throws SQLException {
  BbsEntity bbs = null;
  PreparedStatement pstatement = null;
  ResultSet rs = null;
  bbs = new BbsEntity();
  try{
   pstatement = connection.prepareStatement("SELECT * FROM bbs_tbl WHERE thread_id=? ORDER BY ymdhms LIMIT 1");
   pstatement.setInt(1, thread_id);
   rs = pstatement.executeQuery();
   rs.beforeFirst();
   if(rs.next()) {
    bbs.setSpeaker(rs.getString("speaker"));
    bbs.setContent(rs.getString("content"));
    bbs.setYmdhms(rs.getString("ymdhms"));
   }
   rs.close();
  } finally {
   pstatement.close();
  }
  return bbs;
 }

 public ArrayList<BbsEntity> getResData(int page, int thread_id, boolean new20) throws SQLException {
  BbsEntity bbs = null;
  PreparedStatement pstatement = null;
  ResultSet rs = null;
  ArrayList<BbsEntity> list= new ArrayList<BbsEntity>();
  try{
   if(new20==true){
    pstatement = connection.prepareStatement("SELECT * FROM bbs_tbl WHERE thread_id=? AND ymdhms > (SELECT MIN(ymdhms) FROM bbs_tbl WHERE thread_id=?) ORDER BY ymdhms DESC LIMIT 20");
    }else{
    pstatement = connection.prepareStatement("SELECT * FROM bbs_tbl WHERE thread_id=? AND ymdhms > (SELECT MIN(ymdhms) FROM bbs_tbl WHERE thread_id=?) ORDER BY ymdhms LIMIT 20 OFFSET ?");
    pstatement.setInt(3, ((page-1) * 20));
   }
   pstatement.setInt(1, thread_id);
   pstatement.setInt(2, thread_id);
   rs = pstatement.executeQuery();
   rs.beforeFirst();
   while(rs.next()) {
    bbs = new BbsEntity();
    bbs.setThread_id(rs.getInt("thread_id"));
    bbs.setSpeaker(rs.getString("speaker"));
    bbs.setContent(rs.getString("content"));
    bbs.setYmdhms(rs.getString("ymdhms"));
    list.add(bbs);
   }
   rs.close();
  } finally {
   pstatement.close();
  }
  return list;
 }

 public int setInputData(String thread_id, String inputSpeaker, String inputContent) throws SQLException {
  PreparedStatement pstatement = null;
  int inputResult = 0;
  try{
   connection.setAutoCommit(false);
   if (thread_id != null) {
    pstatement = connection.prepareStatement("INSERT INTO bbs_tbl (thread_id,speaker,content,ymdhms) VALUES (?,?,?,CURRENT_TIMESTAMP)");
    int id = Integer.parseInt(thread_id);
    pstatement.setInt(1, id);
   } else {
    pstatement = connection.prepareStatement("SELECT MAX(thread_id) FROM bbs_tbl FOR UPDATE");
    ResultSet rs = pstatement.executeQuery();
    int Id = 1;
    if (rs.next()) {
     Id = rs.getInt(1) + 1;
    }
    pstatement = connection.prepareStatement("INSERT INTO bbs_tbl (thread_id,speaker,content,ymdhms) VALUES (?,?,?,CURRENT_TIMESTAMP)");
    pstatement.setInt(1, Id);
   }
   pstatement.setString(2, inputSpeaker);
   pstatement.setString(3, inputContent);
  } finally {
   inputResult= pstatement.executeUpdate();
   connection.commit();
   pstatement.close();
  }
  return inputResult;
 }

}