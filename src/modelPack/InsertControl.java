package controllerPack;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelPack.DataInsert;

public class InsertControl extends HttpServlet {

 @Override
 public void doGet(HttpServletRequest request, HttpServletResponse response)
   throws IOException, ServletException{
  doPost(request, response);
 }

 @Override
 public void doPost(HttpServletRequest request, HttpServletResponse response)
   throws IOException, ServletException{
  request.setCharacterEncoding("Windows-31J");
  try{
   DataInsert ids = new DataInsert();
   if (!request.getParameter("inputSpeaker").equals("") && !request.getParameter("inputContent").equals("")) {
    ids.execute(request);
   } else {
    request.setAttribute("message", "入力情報が不十分です");
   }
  }catch(Exception e) {
   e.printStackTrace();
   request.setAttribute("message", "原因不明の入力エラーです");
  }
  ServletContext context = getServletContext();
  RequestDispatcher rd;
  if (request.getParameter("thread_id") != null) {
   rd = context.getRequestDispatcher("/res");
  } else {
   rd = context.getRequestDispatcher("/thread");
  }
  rd.forward(request, response);
 }
}