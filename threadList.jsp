<%@ page contentType="text/html; charset=Windows-31J"%>
<%@ page import="modelPack.BbsEntity,java.util.ArrayList"%>

<!DOCTYPE html>
<html>
<head>
<title>スレッド一覧ページ</title>
<link rel="stylesheet" href="css/bbs.css">
</head>
<body>

 <div class="subtitle1">
  <h3>新規スレッドを作成する</h3>
 </div>
 <form action="insert?new20=true" method="post">
  <input type="hidden" name="page" value="<%= request.getAttribute("page") %>">
  <div class="insert">
   <table>
    <tr>
     <th class="label"><label>投稿者名</label></th>
     <td><input type="text" size="20" name="inputSpeaker" value=""></td>
    </tr>
   </table>
   <table>
    <tr>
     <th class="label"><label>スレッド名</label></th>
     <td class="contentsInput"><input type="text" name="inputContent" value=""></td>
    </tr>
   </table>
   <table>
    <tr>
     <td class="button"><input class="submit" type="submit" value="送信"></td>
    </tr>
   </table>
  </div>
 </form>

 <%if(request.getAttribute("message") != null){ %>
  <p class="message"><%=(String)request.getAttribute("message")%></p>
 <%} %>

 <%if(request.getAttribute("inputResult") != null){ %>
  <p class="inputResult"><%=request.getAttribute("inputResult")%>件投稿しました。</p>
 <%} %>

 <%if(request.getAttribute("list") != null && request.getAttribute("pages") != null) {
  @SuppressWarnings("unchecked")
  ArrayList<BbsEntity> list = (ArrayList<BbsEntity>) request.getAttribute("list");
  %>

  <div class="subtitle2">
   <h3>既存スレッドを選択する</h3>
   <% if(Boolean.TRUE.equals(request.getAttribute("new20"))) {%>
    <form action="/bbs_system_4/thread?new20=true" method="post">
   <% }else{ %>
    <form action="/bbs_system_4/thread" method="post">
     <input type="hidden" NAME="page" VALUE="<%=request.getAttribute("page")%>">
   <% } %>
     <table>
      <tr>
       <td class="button"><input class="submit" type="submit" VALUE="更新"></td>
      </tr>
     </table>
    </form>
  </div>

  <div class="mainDisplay">
   <div class="sort">
    <a class="new20" href="thread?new20=true">最新２０件の表示 （最新レス順）</a>
    <%= request.getAttribute("pages") %>
   </div>

   <table class="threadList">
     <tr>
      <th class="no">No</th>
      <th class="title">スレッド別　最新レス</th>
      <th class="count">レス数</th>
     </tr>
     <% for (BbsEntity i : list) {%>
     <tr>
      <td class="noList"><%=i.getThread_id() %></td>
      <td><a href="res?new20=true&thread_id=<%=i.getThread_id() %>"><%=i.getContent() %></a></td>
      <td class="countList"><%=i.getCount()-1 %></td>
     </tr>
    <% } %>
   </table>

  </div>

 <% } %>

</body>
</html>
			