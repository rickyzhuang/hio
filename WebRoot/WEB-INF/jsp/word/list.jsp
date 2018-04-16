<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>word生长记录管理</title>
<%@include file="/WEB-INF/jsp/include/easyui.jsp"%>
<!-- 对话框的样式 -->
<link href="<%=basePath%>/css/userList.css" rel="stylesheet" type="text/css" />

<script type="text/javascript">
	var url="";
	var mesTitle;
	function addWord(){
		$('#dlg').dialog('open').dialog('setTitle','新增记录');
		$('#fm').form('clear');
		url = path+"/word/addWord";
		mesTitle = '新增记录成功';
	}
	
	// 更新记录
 	function editWord(){
	 	var row = $('#datagrid').datagrid('getSelected');
	 	if (row){
	 		var id = row.id;
		 	$('#dlg').dialog('open').dialog('setTitle','编辑记录');
		 	$('#fm').form('load',row);//这句话有问题，第一次加载时正确的，第二次就出错了，还保持第一次的数据
		 	
		 	//$("#recordDateStr").val($("#recordDate").val());
		 	url = path+"/word/editWord?id="+id;
		 	mesTitle = '编辑记录成功';
	 	}else{
	 		$.messager.alert('提示', '请选择要编辑的记录！', 'error');
	 	}
	}
 	
 	//保存记录
	function saveWord(){
	 	$('#fm').form('submit',{
		 	url: url,
		 	onSubmit: function(){
		 		return $(this).form('validate');
		 	},
			success: function(result){
				/* console.info(result); */
				var result = eval('('+result+')');
				if (result.success){
				 	$('#dlg').dialog('close'); 
				 	$('#datagrid').datagrid('reload'); 
				} else {
					 mesTitle = '新增记录失败';
				}
				$.messager.show({ 
					 title: mesTitle,
					 msg: result.msg
				});
			}
	 	});
	}	
 	// 删除记录
 	function  deleteWord(){
 		$.messager.confirm("确认框", "确定要删除该记录吗?", function (flag) {  
 	        if (flag) {  
 	        	var row = $('#datagrid').datagrid('getSelected');
		 	   	 	if (row){
		 	   	 		var id = row.id;
		 	   		 	//$("#recordDateStr").val($("#recordDate").val());
		 	   		 	url = path+"/word/deleteById?id="+id;
		 	   		 	mesTitle = '删除记录成功';
		 	   		 	$.ajax({
		 	   		 		 type: "POST",
		 	   		 		 async: false,
		 	   		 		 url: url,
		 	   		 		 data: {"id":id},
		 	   		 		 contentType: "application/json;charset=utf-8",
		 	   		 		 dataType: "json",
		 	   		 		 success: function (res) {
		 	   		 			if(res.success){
		 	   		 			$.messager.alert('提示', '删除成功！', 'success');
		 	   		 			$('#datagrid').datagrid('reload');
		 	   		 			}
		 	   		 		 }
		 	   		 		
		 	   		 		 });
		 	   	 	}else{
		 	   	 		$.messager.alert('提示', '请选择要编辑的记录！', 'error');
		 	   	 	}
 	        }else{
 	        	return false;}
 	    });  
 	}
 	// 图表显示
 	function  openChart(){
 		$('#chartDia').dialog('open').dialog('setTitle','图表显示');
 		showChart();
 	}
 	
 	function showChart(){
 		 var myChart = echarts.init(document.getElementById('wordChart'));
 		 var xdata=[];
 		 var ydata1=[];
 		 var ydata2=[];
 		 var ydata3=[];
 		$.ajax({
		 		 type: "GET",
		 		 async: false,
		 		 url:path+"/word/test",
		 		// data: {"id":id},
		 		 contentType: "application/json;charset=utf-8",
		 		 dataType: "json",
		 		 success: function (data) {
		 			for ( var i = 0; i < data.length; i++) {
						var x=dateUtil.toTimeStamp(data[i].recode_date);
						var y1=data[i].height;
						var y2=data[i].weight;
						var y3=data[i].head;
						xdata.push(x);
						ydata1.push(y1);
						ydata2.push(y2);
						ydata3.push(y3);
					}
		 		 }
		 		
		 		 });

         // 指定图表的配置项和数据
         var option = {
        		    title: {
        		        text: '折线图堆叠'
        		    },
        		    tooltip : {
        		        trigger: 'axis'
        		    },
        		    legend: {
        		        data:['身高','体重','头围']
        		    },
        		    grid: {
        		        left: '3%',
        		        right: '4%',
        		        bottom: '3%',
        		        containLabel: true
        		    },
        		    toolbox: {
        		        feature: {
        		            saveAsImage: {}
        		        }
        		    },
        		    xAxis : [
        		        {
        		            type : 'category',
        		            boundaryGap : false,
        		           // data : ['周一','周二','周三','周四','周五','周六','周日']
        		            data : xdata
        		        }
        		    ],
        		    yAxis : [
        		        {
        		            type : 'value'
        		        }
        		    ],
        		    series : [
        		        {
        		            name:'身高',
        		            type:'line',
        		            stack: '总量',
        		           // data:[120, 132, 101, 134, 90, 230, 210]
        		            data:ydata1
        		        },
        		        {
        		            name:'体重',
        		            type:'line',
        		            stack: '总量',
        		         // 	data:[220, 182, 191, 234, 290, 330, 310]
        		      	  	data:ydata2
        		        },
        		        {
        		            name:'头围',
        		            type:'line',
        		            stack: '总量',
        		        //    data:[150, 232, 201, 154, 190, 330, 410]
        		        	data:ydata3
        		        }
        		       
        		    ]
        		};


         // 使用刚指定的配置项和数据显示图表。
         myChart.setOption(option);
 	}
 	
 	
</script>

</head>
<body class="easyui-layout" fit="true">
	<div region="center" border="false" style="overflow: hidden;">
		<!-- 记录信息列表 -->
		<table id="datagrid" title="记录管理" class="easyui-datagrid" fit="true"
			url="<%=basePath%>/word/getDataGridWord" toolbar="#toolbar" pagination="true"
			fitColumns="true" singleSelect="true" rownumbers="true"
			border="false" nowrap="false">
			<thead>
				<tr>
					<th field="id" width="100">编号</th>
					<th field="name" width="100">单词</th>
					<th field="sound" width="100">读音</th>
					<th field="mean" width="100">意思</th>
					<th field="recodeDate"  formatter="dateUtil.toTimeStamp" width="100">记录日期</th>
					<th field="operator" width="100">记录人员</th>
					<th field="remark" width="100">备注</th>
				</tr>
			</thead>
		</table>

		<!-- 按钮 -->
		<div id="toolbar">
			<a href="javascript:void(0);" class="easyui-linkbutton"
				iconCls="icon-add" plain="true" onclick="addWord();">新增记录</a> <a
				href="javascript:void(0);" class="easyui-linkbutton"
				iconCls="icon-edit" plain="true" onclick="editWord();">编辑记录</a>
				 <a href="javascript:void(0);" class="easyui-linkbutton"
				iconCls="icon-remove" plain="true" onclick="deleteWord();">删除记录</a>
				 <a href="javascript:void(0);" class="easyui-linkbutton"
				iconCls="icon-large-chart" plain="true" onclick="openChart();">查看图表</a>
		</div>

		<!-- 对话框 -->
		<div id="dlg" class="easyui-dialog"
			style="width:400px;height:320px;padding:10px 20px" closed="true"
			buttons="#dlg-buttons">
			<div class="ftitle">新增记录</div>
			<form id="fm" method="post" novalidate>
				<div class="fitem">
					<label>单词:</label> <input name="name" class="easyui-textbox"
						required="true"  validType="string">
				</div>
				<div class="fitem">
					<label>读音:</label> <input name="sound" class="easyui-textbox"
						required="true"  validType="string">
				</div>
				<div class="fitem">
					<label>mean:</label> <input name="mean" class="easyui-textbox"
						validType="string">
				</div>
				 <div class="fitem">
					<label>记录日期:</label> <input id="recordDateStr" name="recordDateStr"  class="easyui-datetimebox">
				</div>
				<div class="fitem">
					<label>记录人员:</label> <input name="operator" class="easyui-textbox"
						/>
				</div>
				<div class="fitem">
					<label>备注:</label> <input name="remark" class="easyui-textbox"
						/>
				</div>
			</form>
		</div>

		<!-- 对话框按钮 -->
		<div id="dlg-buttons">
			<a href="javascript:void(0)" class="easyui-linkbutton c6"
				iconCls="icon-ok" onclick="saveWord()" style="width:90px">保存</a> <a
				href="javascript:void(0)" class="easyui-linkbutton"
				iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')"
				style="width:90px">取消</a>
		</div>
		
		
		<div id="chartDia" class="easyui-dialog"
			style="width:800px;height:500px;padding:10px 20px;margin-top:20px;" closed="true"
			buttons="#chartDia-buttons">
			<div id="wordChart"  style="width:700px;height:400px;"></div>
		</div>
		<!-- 对话框按钮 -->
		<div id="chartDia-buttons">
			 <a href="javascript:void(0)" class="easyui-linkbutton"
				iconCls="icon-cancel" onclick="javascript:$('#chartDia').dialog('close')"
				style="width:90px">返回</a>
		</div>
		
	</div>
</body>
</html>
