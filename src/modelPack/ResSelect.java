package modelPack;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

public class ResSelect {

 public void execute(HttpServletRequest request) throws Exception {
  BbsDao dao = null;
  try {
   dao = new BbsDao();
   String page = "1";
   if (request.getParameter("page") != null) {
    page = request.getParameter("page");
   }
   String thread_id = request.getParameter("thread_id");
   boolean new20 = false;
   if(request.getParameter("new20")!=null && request.getParameter("new20").equals("true")){
    new20 = true;
   }
   request.setAttribute("page", page);
   request.setAttribute("thread_id", thread_id);
   request.setAttribute("new20", new20);
   int count = dao.getRecordCount(thread_id);
   String pages = Utilities.editPages("res", thread_id, count);
   BbsEntity fast = dao.getFirstResData(Integer.parseInt(thread_id));
   ArrayList<BbsEntity> list = dao.getResData(Integer.parseInt(page), Integer.parseInt(thread_id), new20);
   if (pages != null && fast != null) {
    request.setAttribute("fast", fast);
    request.setAttribute("list", list);
    request.setAttribute("pages", pages);
   } else {
    request.setAttribute("message", "�f�[�^������܂���");
   }
  }catch(NumberFormatException e) {
   e.printStackTrace();
   request.setAttribute("message", "���l����͂��Ă�������");
  } catch (SQLException e) {
   e.printStackTrace();
   request.setAttribute("message", "JDBC�̃G���[�ł�");
  } finally {
   if (dao != null) {
    dao.close();
   }
  }
 }
}