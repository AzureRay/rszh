<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta content="text/html; charset=utf-8" http-equiv="Content-Type"></meta>
<title>人事档案管理系统</title>
<link media="screen" type="text/css" href="/css/reset.css"
	rel="stylesheet"></link>
<link media="screen" type="text/css" href="/css/styleTwo.css"
	rel="stylesheet"></link>
<link media="screen" type="text/css" href="/css/invalid.css"
	rel="stylesheet"></link>
<script src="/js/jquery-1.3.2.min.js" type="text/javascript"></script>
<script src="/js/simpla.jquery.configuration.js" type="text/javascript"></script>
<script src="/js/facebox.js" type="text/javascript"></script>
<script src="/js/jquery.wysiwyg.js" type="text/javascript"></script>
<script type="text/javascript" src="/js/jquery-1.7.2.js"></script>
<script type="text/javascript">
	$(function() {
		$("#login-btn").click(function(){
			var $acco = $(":text[name='account']");
			var account=$acco.val();
			account = $.trim(account);
			$acco.val(account);
		   if (account == "") {
				alert("请输入账号");
				return false;
			}
		   
			var $pass=$(":password");
			var password=$pass.val();
			 // window.location.href="/loginMatch?account="+account+"&password="+password;
			password=$.trim(password);
			$pass.val(password);
			  if(password==""){
			    alert("请输入密码");
			    return false;
			  }
			  $.getJSON("/loginMatch",{"account":account,"password":password},function(json){
				
				  var answer = json.answer;
				
				var leibie = json.leibie;
				
				if(answer){
					//alert("10");
					if(leibie == "1"){
						window.location.href="/index.jsp";
					}else if(leibie == "2"){
						window.location.href="normalIndex.jsp";
					}
				}else{
					$("#error_info").show();
				}
			});
		});
	})
</script>
</head>
<body id="login">
	<div id="login-wrapper" class="png_bg">
		<div id="login-top">
			<h1>人事档案管理系统</h1>
			<img id="logo" alt="人事档案管理系统" src="/images/logo.png"></img>
		</div>
		<div id="login-content">
			
				<div class="notification information png_bg" id="error_info" style="display:none">
					<div>请输入正确的账号和密码</div>
				</div>
				<p>
					<label> 账号 </label> <input class="text-input" type="text"
						name="account" />
				</p>
				<div class="clear"></div>
				<p>
					<label> 密码 </label> <input class="text-input" type="password"
						name="password" />
				</p>
				<div class="clear"></div>
				<p id="remember-password">
					<input type="checkbox" name="remember" value="记住我的密码"/>记住我的密码
				</p>
				<div class="clear">
				<center>
				 <button type="button" id="login-btn">登录</button>
				</center>
				</div>
			</form>
		</div>
	</div>
	<div width="100%" height="90px" style="margin-top:180px;text-align:center;">
	<img id="footPic" alt="党政机关" src="/images/red.png"/><br/>
	<label>
	    版权:山东工商学院   信息管理与信息系统专业
	</label>
	</div>
</body>
</html>

