package controllerPack;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelPack.ResSelect;

public class ResControl extends HttpServlet {

 @Override
 public void doGet(HttpServletRequest request, HttpServletResponse response)
   throws IOException, ServletException{
  doPost(request, response);
 }

 @Override
 public void doPost(HttpServletRequest request, HttpServletResponse response)
   throws IOException, ServletException{
  try{
   ResSelect ids = new ResSelect();
   ids.execute(request);
  }catch(Exception e) {
   request.setAttribute("message", "å¥àˆïsñæÇÃÉGÉâÅ[Ç≈Ç∑");
  }
  ServletContext context = getServletContext();
  RequestDispatcher rd = context.getRequestDispatcher("/resList.jsp");
  rd.forward(request, response);
 }
}