package modelPack;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

public class DataInsert {

 public void execute(HttpServletRequest request) throws Exception {
  BbsDao dao = null;
  try {
   dao = new BbsDao();
   String thread_id = request.getParameter("thread_id");
   String inputSpeaker = request.getParameter("inputSpeaker");
   String inputContent = request.getParameter("inputContent");
   int inputResult =dao.setInputData(thread_id, inputSpeaker, inputContent);
   request.setAttribute("inputResult", inputResult);
  }catch(IllegalArgumentException e) {
   request.setAttribute("message", "文字を入力してください");
  } catch (SQLException e) {
   request.setAttribute("message", "JDBCのエラーです");
  } finally {
   if (dao != null) {
    dao.close();
   }
  }
 }
}