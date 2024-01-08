package modelPack;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

public class ThreadSelect {

 public void execute(HttpServletRequest request) throws Exception {
  BbsDao dao = null;
  try {
   dao = new BbsDao();
   String page = "1";
   if ( request.getParameter("page") != null) {
    page = request.getParameter("page");
   }
   boolean new20 = false;
   if(request.getParameter("new20")!=null && request.getParameter("new20").equals("true")){
    new20 = true;
   }
   request.setAttribute("page", page);
   request.setAttribute("new20", new20);
   String thread_id = request.getParameter("thread_id");
   int count = 0;
   count = dao.getRecordCount(thread_id);
   String pages = Utilities.editPages("thread", thread_id, count);

   ArrayList<BbsEntity> list = dao.getThreadData(Integer.parseInt(page), new20);
   if (pages != null && list != null) {
    if (!list.isEmpty()) {
     request.setAttribute("list", list);
     request.setAttribute("pages", pages);
    } else {
     request.setAttribute("message", "データがありません");
    }
   }
  } catch (SQLException e) {
   e.printStackTrace();
   request.setAttribute("message", "JDBCのエラーです");
  } finally {
   if (dao != null) {
    dao.close();
   }
  }
 }
}