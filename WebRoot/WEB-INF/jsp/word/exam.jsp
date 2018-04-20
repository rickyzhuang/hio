<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>我要测试</title>
<%@include file="/WEB-INF/jsp/include/easyui.jsp"%>
<!-- 对话框的样式 -->
<link href="<%=basePath%>/css/userList.css" rel="stylesheet" type="text/css" />

<script type="text/javascript">

	$(function(){
		
	})
	//提交查看
 	function  submit(){
		var count=0;
		 $("[name=answer]").each(function(index,ele){
			var o= $(this);
			if(o.attr("answer")==o.val()){
				o.next().html("<font color='green'>√</font>");
				count++
			}else{
				o.next().html("<font color='red' >x</font>");
				var url = path+"/wrong/addSingleWrong";
				$.ajax({
	   		 		 type: "POST",
	   		 		 async: false,
	   		 		 url: url,
	   		 		 data: {
	   		 			 "name":o.attr("answer"),
	   		 			 "sound":o.attr("sound"),
	   		 			 "mean":o.attr("mean"),
	   		 			 "operator":$("#operator").val()
	   		 		 },
	   		 		 contentType: "application/json;charset=utf-8",
	   		 		 dataType: "json",
	   		 		 success: function (res) {
	   		 			if(res.success){
	   		 			//	$.messager.alert('提示', '删除成功！', 'success');
	   		 			}
	   		 		 }
	   		 		
	   		 		 });
			}
		 });
		 $("#score").html("<font color='red' size='8'>"+count+"</font>");
 	}
	
	function seeAnswer(){
		$("[name=show]").show();
	}
	function closeAnswer(){
		$("[name=show]").hide();
	}
	function addWrong(){
		$("[name=show]").hide();
	}
 	
 	
 	
 	
</script>

</head>
<body class="easyui-layout"  fit="true">
<input type="hidden"  id="operator" name="operator"  value="<%=request.getSession().getAttribute("user") %>"/>
<h3>说明，点击提交答案错题目会自动加入错题集</h3>

<table>

<c:forEach items="${list}" var="obj">
<tr>
<td><input type="checkbox" ></input></td>
<td>${obj.mean}</td>
<td> ${obj.sound}</td>
<td><input type="text"  name="answer" value=""   answer="${obj.name}"  sound="${obj.sound}"  mean="${obj.mean}"/><span name="span"></span> </td>
<td><span name="show" style="display:none"><font color="blue">${obj.name}</font></span></td>
</tr>
</c:forEach>
</table>
<br>
<div>
<a href="javascript:void(0)" onclick="submit()">提交答案</a>
  成绩<span id="score"></span>分
  <br>
  <a href="javascript:void(0)" onclick="seeAnswer()">查看答案</a>
    <a href="javascript:void(0)" onclick="closeAnswer()">关闭答案</a>
 <!--    <br>
      <a href="javascript:void(0)" onclick="addWrong();">添加选中的进错题集</a> -->
      <br>
     <a href="javascript:void(0)" onclick="window.location.reload();">再测一次</a>
</div>


</body>
</html>
