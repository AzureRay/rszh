<!DOCTYPE html>
<html>
<head>
<title>修改密码</title>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="/css/bootstrap.css" />
<link rel="stylesheet" type="text/css"
	href="/css/bootstrap-responsive.css" />
<link rel="stylesheet" type="text/css" href="/css/style.css" />
<script type="text/javascript" src="/js/jquery.js"></script>
<script type="text/javascript" src="/js/jquery.sorted.js"></script>
<script type="text/javascript" src="/js/bootstrap.js"></script>
<script type="text/javascript" src="/js/ckform.js"></script>
<script type="text/javascript" src="/js/common.js"></script>
<script type="text/javascript" src="/js/jquery-1.7.2.js"></script>
<script type="text/javascript">
	$(function() {
		$(":submit").click(function() {
			var $oldPass = $(":password[name='oldPass']");
			var oldPa = $oldPass.val();
			oldPa = $.trim(oldPa);
			$oldPass.val(oldPa);
			if (oldPa == "") {
				alert("请输入原来的密码！");
				return false;
			}
			var $newPass = $(":password[name='newPass']");
			var newPa = $newPass.val();
			newPa = $.trim(newPa);
			$newPass.val(newPa);
			if (newPa == "") {
				alert("请输入新密码！");
				return false;
			}
			var $newPassAga = $(":password[name='newPassAgain']");
			var newPaAga = $newPassAga.val();
			newPaAga = $.trim(newPaAga);
			$newPassAga.val(newPaAga);
			if (newPaAga == "") {
				alert("请再次输入新密码！");
				return false;
			}
			if(newPa!=newPaAga){
				alert("两次输入的新密码不一致，请重新输入！");
				$newPass.val("");
				$newPassAga.val("");
				return false;				
			}
			// alert($(":checkbox:checked").val());
			//取消 submit 的默认行为
		});
	})
</script>
<style type="text/css">
body {
	padding-bottom: 40px;
}

.sidebar-nav {
	padding: 9px 0;
}

@media ( max-width : 980px) {
	/* Enable use of floated navbar text */
	.navbar-text.pull-right {
		float: none;
		padding-left: 5px;
		padding-right: 5px;
	}
}
</style>
</head>
<body>
	<form action="/modpass" method="post" class="definewidth m20">
		<table class="table table-bordered table-hover m10">
		   <tr>
				<td width="10%" class="tableleft">编号</td>
				<td><input type="text" name="user" /></td>
			</tr>
			<tr>
				<td class="tableleft">原密码</td>
				<td><input type="password" name="oldPass" /></td>
			</tr>
			<tr>
				<td class="tableleft">新密码</td>
				<td><input type="password" name="newPass" /></td>
			</tr>
			<tr>
				<td class="tableleft">确认密码</td>
				<td><input type="password" name="newPassAgain" /></td>
			</tr>
			<tr>
				<td class="tableleft"></td>
				<td><input type="submit" class="btn btn-primary" value="确认" />
					&nbsp;&nbsp;<input type="reset" class="btn btn-success"
					name="reset" id="reset" value="重置" /></td>
			</tr>
		</table>
	</form>
</body>
</html>