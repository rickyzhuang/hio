<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>身高管理</title>
<%@include file="/WEB-INF/jsp/include/easyui.jsp"%>
<!-- 对话框的样式 -->
<link href="<%=basePath%>/css/userList.css" rel="stylesheet" type="text/css" />

<script type="text/javascript">

	$(function(){
		openChart();
	})
	//图表显示
 	function  openChart(){
 		showChart();
 	}
	
 	
 	function showChart(){
 		 var myChart = echarts.init(document.getElementById('heightChart'));

        var	option = {
        	    title : {
        	        text: '某站点用户访问来源',
        	        subtext: '纯属虚构',
        	        x:'center'
        	    },
        	    tooltip : {
        	        trigger: 'item',
        	        formatter: "{a} <br/>{b} : {c} ({d}%)"
        	    },
        	    legend: {
        	        orient: 'vertical',
        	        left: 'left',
        	        data: ['直接访问','邮件营销','联盟广告','视频广告','搜索引擎']
        	    },
        	    series : [
        	        {
        	            name: '访问来源',
        	            type: 'pie',
        	            radius : '55%',
        	            center: ['50%', '60%'],
        	            data:[
        	                {value:335, name:'直接访问'},
        	                {value:310, name:'邮件营销'},
        	                {value:234, name:'联盟广告'},
        	                {value:135, name:'视频广告'},
        	                {value:1548, name:'搜索引擎'}
        	            ],
        	            itemStyle: {
        	                emphasis: {
        	                    shadowBlur: 10,
        	                    shadowOffsetX: 0,
        	                    shadowColor: 'rgba(0, 0, 0, 0.5)'
        	                }
        	            }
        	        }
        	    ]
        	};


         // 使用刚指定的配置项和数据显示图表。
         myChart.setOption(option);
 	}
 	
 	
</script>

</head>
<body class="easyui-layout"  fit="true">
	<div region="center" border="false" style="overflow: hidden;">
		<div  id="heightChart"  style="width:100%;;height:100%;"></div>
	</div>
</body>
</html>
