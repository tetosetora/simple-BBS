<%@ page contentType="text/html; charset=Windows-31J"%>
<%@ page import="modelPack.BbsEntity,java.util.ArrayList"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="Windows-31J">
<title>���X�ꗗ�y�[�W</title>
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
       <p>�^�C�g���F<%= fast.getContent() %></p>
      </div>
      <div class="resBottom">
       <p>���e�ҁF<%= fast.getSpeaker() %></p>
       <p class="resRight">���e�����F<%= fast.getYmdhms() %></p>
      </div>
     </div>
    </td>
   </tr>
  </table>
  </div>

  <div class="subtitle1">
   <h3>���̃X���b�h�ɓ��e����</h3>
  </div>
  <form action="insert?new20=true" method="post">
   <input type="hidden" name="thread_id" value="<%= request.getAttribute("thread_id") %>">
   <input type="hidden" name="page" value="<%= request.getAttribute("page") %>">
   <div class="insert">
    <table>
     <tr>
      <th class="label"><label>���e�Җ�</label></th>
      <td><input type="text" size="20" name="inputSpeaker" value=""></td>
     </tr>
    </table>
    <table>
     <tr>
      <th class="label"><label>���X���e</label></th>
      <td class="contentsInput"><input type="text" name="inputContent" value=""></td>
     </tr>
    </table>
    <table>
     <tr>
      <td class="button"><input class="submit" type="submit" value="���M"></td>
     </tr>
    </table>
   </div>
  </form>

  <%if(request.getAttribute("message") != null){ %>
   <p class="message"><%=(String)request.getAttribute("message")%></p>
  <%} %>

  <%if(request.getAttribute("inputResult") != null){ %>
   <p class="inputResult"><%=request.getAttribute("inputResult")%>�����e���܂����B</p>
  <%} %>

  <div class="subtitle2">
   <h3>���e�ꗗ</h3>
    <% if(Boolean.TRUE.equals(request.getAttribute("new20"))) {%>
    <form action="/bbs_system_4/res?new20=true" method="post">
   <% }else{ %>
    <form action="/bbs_system_4/res" method="post">
     <input type="hidden" NAME="page" VALUE="<%=request.getAttribute("page")%>">
   <% } %>
     <input type="hidden" NAME="thread_id" VALUE="<%=request.getAttribute("thread_id")%>">
     <table>
      <tr>
       <td class="button"><input class="submit" type="submit" VALUE="�X�V"></td>
      </tr>
     </table>
    </form>
  </div>

  <div class="mainDisplay">
   <div class="sort">
    <a class="new20" href="res?thread_id=<%=request.getAttribute("thread_id")%>
      &new20=true">�ŐV�Q�O���̕\�� �i�ŐV���X���j</a>
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
         <p>���e�ҁF<%=i.getSpeaker() %></p>
         <p class="resRight">���e�����F<%=i.getYmdhms() %></p>
        </div>
       </div>
      </td>
     </tr>
    <%}%>
   </table>

    <a href="thread?new20=true">�X���b�h�ꗗ�ɖ߂�</a>

  </div>

 <%} %>

</body>
</html>