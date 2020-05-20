<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="<%=request.getContextPath()%>/Jquery/css/demo.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/Jquery/css/searchMeme.css" rel="stylesheet" type="text/css" />
<script src="<%=request.getContextPath()%>/Jquery/js/jquery.min.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/Jquery/js/jquery.searchMeme.js" type="text/javascript"></script>

<link rel="stylesheet"
	href="<%=request.getContextPath()%>/layui/css/layui.css">

<script src="<%=request.getContextPath()%>/layui/layui.js"
	charset="utf-8"></script>
<script src="<%=request.getContextPath()%>/layui/jquery.min.js"
	charset="utf-8"></script>
<title>Insert title here</title>
</head>
<body>
	<form class="layui-form" action="ipsearch">
		<div class="layui-form-item">
			<label class="layui-form-label">IP</label>
			<div class="layui-input-block">
				<input type="text" name="ip" autocomplete="off"   placeholder="请输入IP"
					class="layui-input">
			</div>
		</div>
		<div class="layui-form-item">
			<div class="layui-input-block">
				<button type="submit" class="layui-btn" lay-submit=""
					lay-filter="demo1">查询</button>
			</div>
		</div>
	</form>
	<div  id="div0">
	</div>
	<script type="text/javascript">
		layui
				.use(
						[ 'form'],
						function() {
							var form = layui.form, layer = layui.layer;
							
							//监听提交
						form.on('submit(demo1)', function(data) {
							console.log(data.field);
								$.post("ipsearch", data.field, function(obj) {
									
									console.log(obj);
									alert(obj.msg);
									
								});
								
								return false;
							}); 

						});
	</script>
</body>

</html>