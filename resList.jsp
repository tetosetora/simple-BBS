<%@ page contentType="text/html; charset=Windows-31J"%>
<%@ page import="modelPack.BbsEntity,java.util.ArrayList"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="Windows-31J">
<title>レス一覧ページ</title>
<link rel="stylesheet" href="css/bbs.css">
</head>
<body>

 <%if(request.getAttribute("fast") != null) {
   BbsEntity fast = (BbsEntity) request.getAttribute("fast");
 %>

 <div class="title">
  <table class="titleRes">
   <tr>
    <td>
     <div class="res">
      <div class="resCenterTop">
       <p>タイトル：<%= fast.getContent() %></p>
      </div>
      <div class="resBottom">
       <p>投稿者：<%= fast.getSpeaker() %></p>
       <p class="resRight">投稿日時：<%= fast.getYmdhms() %></p>
      </div>
     </div>
    </td>
   </tr>
  </table>
  </div>

  <div class="subtitle1">
   <h3>このスレッドに投稿する</h3>
  </div>
  <form action="insert?new20=true" method="post">
   <input type="hidden" name="thread_id" value="<%= request.getAttribute("thread_id") %>">
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
      <th class="label"><label>レス内容</label></th>
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

  <div class="subtitle2">
   <h3>投稿一覧</h3>
    <% if(Boolean.TRUE.equals(request.getAttribute("new20"))) {%>
    <form action="/bbs_system_4/res?new20=true" method="post">
   <% }else{ %>
    <form action="/bbs_system_4/res" method="post">
     <input type="hidden" NAME="page" VALUE="<%=request.getAttribute("page")%>">
   <% } %>
     <input type="hidden" NAME="thread_id" VALUE="<%=request.getAttribute("thread_id")%>">
     <table>
      <tr>
       <td class="button"><input class="submit" type="submit" VALUE="更新"></td>
      </tr>
     </table>
    </form>
  </div>

  <div class="mainDisplay">
   <div class="sort">
    <a class="new20" href="res?thread_id=<%=request.getAttribute("thread_id")%>
      &new20=true">最新２０件の表示 （最新レス順）</a>
     <%= request.getAttribute("pages") %>
   </div>

   <%
    @SuppressWarnings("unchecked")
    ArrayList<BbsEntity> list = (ArrayList<BbsEntity>) request.getAttribute("list");
   %>
   <table class="contentsRes">
     <% for (BbsEntity i : list) {%>
     <tr>
      <td>
       <div class="res">
        <div class="resTop">
         <p><%=i.getContent() %></p>
        </div>
        <div class="resBottom">
         <p>投稿者：<%=i.getSpeaker() %></p>
         <p class="resRight">投稿日時：<%=i.getYmdhms() %></p>
        </div>
       </div>
      </td>
     </tr>
    <%}%>
   </table>

    <a href="thread?new20=true">スレッド一覧に戻る</a>

  </div>

 <%} %>

</body>
</html>