package controllerPack;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelPack.ThreadSelect;

public class ThreadControl extends HttpServlet {

 @Override
 public void doGet(HttpServletRequest request, HttpServletResponse response)
   throws IOException, ServletException{
  doPost(request, response);
 }

 @Override
 public void doPost(HttpServletRequest request, HttpServletResponse response)
   throws IOException, ServletException{
  try{
   ThreadSelect ids = new ThreadSelect();
   ids.execute(request);
  }catch(Exception e) {
   request.setAttribute("message", "å¥àˆïsñæÇÃÉGÉâÅ[Ç≈Ç∑");
   e.printStackTrace();
  }
  ServletContext context = getServletContext();
  RequestDispatcher rd = context.getRequestDispatcher("/threadList.jsp");
  rd.forward(request, response);
 }
}