<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.exam.mail.MailSender"%>
<%
	//받을 이메일 주소
	String toEmail = "wsbg@naver.com";
	// 받을 사람 이름
	String toName = "테스터";
	// 보낼 제목
	String subject = "테스트 제목";
	// 보낼 내용
	// html 형식으로 => 디자인
	// String content = "테스트 내용";
	StringBuilder sb = new StringBuilder();
	sb.append("<html>");
	sb.append("<head>");
	sb.append("<meta charset='utf-8'>");
	sb.append("</head>");
	sb.append("<body>");
	sb.append("<font color='blue'>테스트 내용</font>");
	sb.append("<img src='https://t1.daumcdn.net/daumtop_chanel/op/20200723055344399.png'>");
	sb.append("</body>");
	sb.append("</html>");
	
	String content = sb.toString();
	new MailSender("wsbg6006@gmail.com","ilmj giif ywem xoua").sendMail(toEmail, toName, subject, content);
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JAVA MAIL SMTP by GOOGLE</title>
</head>
<body>

</body>
</html>