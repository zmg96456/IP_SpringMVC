<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>IP搜索</title>
<style>
* {
	margin: 0;
	padding: 0;
	font-family:Arial,Verdana,Sans-serif;
}
body {
	overflow: hidden;
	
}
.div_ip{
	position: absolute; left: 0; top: 0; right: 0; bottom: 0;

    margin: auto; 
	background-color: rgba(255,255,255,0);
	height: 60px;
	line-height: 60px;
	width: 500px;
	color: #facacb;
	font-size: 18px;
	text-align: center;
	font-family:Arial,Verdana,Sans-serif;
	
}
</style>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/layui/css/layui.css">
<script src="<%=request.getContextPath()%>/layui/layui.js"
	charset="utf-8"></script>
<script src="<%=request.getContextPath()%>/layui/jquery.min.js"
	charset="utf-8"></script>
<link href="<%=request.getContextPath()%>/Jquery/css/font.css" rel="stylesheet">
<link rel="stylesheet" href="<%=request.getContextPath()%>/Jquery/css/style.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/Jquery/css/style-search.css" media="screen" type="text/css" />


</head>

<body>
<canvas id="canvas" width="1280" height="1024"> 您的浏览器不支持canvas标签，请您更换浏览器 </canvas>
<script src="<%=request.getContextPath()%>/Jquery/js/word.js"></script>
<p id="offscreen-text" class="offscreen-text"></p>
<p id="text" class="text"></p>
<!--<svg id="svg" > </svg>-->
<form class="layui-form" action="ipsearch">
<div id="d" class="webdesigntuts-workshop"> <span style="margin-top: 100px; margin-bottom: 100px;">

  <input class="input"  type="text" placeholder="请输入您要输入的IP"   name="ip"  lay-verify="ip"/>
  
 <button type="submit" class="layui-btn" lay-submit=""
					lay-filter="demo1">查询</button>
	
  </span> 
  </div> 
</form>  
 <div  id="location" class="div_ip"></div>
<script  src='<%=request.getContextPath()%>/Jquery/js/index.js'></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/gsap/2.0.1/TweenMax.min.js"></script>
<script type="text/javascript">
		layui
				.use(
						[ 'form'],
						function() {
							var form = layui.form, layer = layui.layer;
							

							form.verify({
									ip: [
									/^(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])$/
									,'IP地址不符合规则'
									] 
								}); 
					
							//监听提交
						form.on('submit(demo1)', function(data) {
							console.log(data.field);
								$.post("ipsearch", data.field, function(obj) {
									
									$('#location').html(JSON.stringify(data.field.ip).replace(/\"/g, "") +"   来自 " + obj.msg + "");
									console.log(obj);
									//alert(obj.msg);
									
								});
								
								return false;
							}); 

						});
	</script>
</body>
</html>
