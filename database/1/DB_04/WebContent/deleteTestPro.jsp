<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ page import="java.sql.*"%>
<% request.setCharacterEncoding("euc-kr"); %>

<%
   String id = request.getParameter("id");
   String passwd = request.getParameter("passwd");
   
  
   Connection conn = null;
   PreparedStatement pstmt = null;
   ResultSet rs = null;
   
   try{
      String jdbcUrl = "jdbc:mysql://localhost:3306/member?serverTimezone=UTC&useSSL=false";
      String dbId = "root";
      String dbPass = "thdud0917@";
      
      Class.forName("com.mysql.cj.jdbc.Driver");
      conn = DriverManager.getConnection(jdbcUrl,dbId,dbPass);
      String sql = "select id, passwd from member where id=?";
      pstmt = conn.prepareStatement(sql);
      pstmt.setString(1,id);
      rs = pstmt.executeQuery();
      
      if(rs.next()){
    	  String rId = rs.getString("id");
    	  String rPasswd = rs.getString("passwd");
      
      if(id.equals(rId) && passwd.equals(rPasswd)){
    	  sql = "delete from member where id = ?";
    	  pstmt = conn.prepareStatement(sql);
    	  pstmt.setString(1, id);
    	  pstmt.executeUpdate();
    	  
    	  %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>���ڵ� ����</title>
</head>
<body>
member ���̺��� ���ڵ带 �����߽��ϴ�.
</body>
</html>
<%
    	  }else{
    	  out.println("�н����尡 Ʋ�Ⱦ��ϴ�");
    	  }
    	  }else{
    	  out.println("���̵� Ʋ�Ƚ��ϴ�");
    	  }
    	  }catch(Exception e){
    	  e.printStackTrace();
    	  }finally{
    	  if(rs!= null) try{rs.close();}catch(SQLException sqle){}
    	  if(pstmt != null)try{pstmt.close();}catch(SQLException sql){}
    	  if(conn != null)try{conn.close();}catch(SQLException sqle){}
    	  }
    	  %>