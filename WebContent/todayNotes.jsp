<%@page import="shujugeshichuli.UpdateAge"%>
<%@page import="util.DateUtil"%>
<%@page import="notice.SaveNotice"%>
<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>今日提醒</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="css/bootstrap.css" />
    <link rel="stylesheet" type="text/css" href="css/bootstrap-responsive.css" />
    <link rel="stylesheet" type="text/css" href="css/style.css" />
    <script type="text/javascript" src="js/jquery.js"></script>
    <script type="text/javascript" src="js/jquery.sorted.js"></script>
    <script type="text/javascript" src="js/bootstrap.js"></script>
    <script type="text/javascript" src="js/ckform.js"></script>
    <script type="text/javascript" src="js/common.js"></script>
    <style type="text/css">
        body {
            padding-bottom: 40px;
            font-size:18px;
        }
        .sidebar-nav {
            padding: 9px 0;
        }

        @media (max-width: 980px) {
            /* Enable use of floated navbar text */
            .navbar-text.pull-right {
                float: none;
                padding-left: 5px;
                padding-right: 5px;
            }
        }
        marquee{
        width:50%;
        height:420px;
        }
    </style>
</head>
<body>
<%
	SaveNotice saveNotice = new SaveNotice();
	List<Map<String,String>> todayNotice = saveNotice.getTodayNotice();
	//判断当天的日期是不是一月一号，如果是，则更新年龄
	Date today = new Date();
	Calendar calendar = Calendar.getInstance();
	calendar.setTime(today);
	System.out.println("当日时间："+calendar);
	String date = DateUtil.getTime(calendar);
	System.out.println("转换后的String格式的时间："+date);
	date = date.replaceAll("(\\d{4})(\\d{2})(\\d{2})", "$2-$3");
	System.out.println("用正则表达式转换后的String格式的时间(取月日) ："+date);
	if(date.equals("01-01")){
		UpdateAge updateAge = new UpdateAge();
		updateAge.updateAllAge();
	}
%>
<br/>
<table style="width:66%;height:450px;border:1px solid #EAEAEA;float:left;">
    <tr style="background:#F5F5F5;height:30px;">
        <th><span style="font-size:24px;font-weight:bold;">政府简介</span></th>
    </tr>
   <tr style="height:370px;">
      <td>&nbsp;&nbsp;&nbsp;&nbsp;招远市地处山东半岛西北部，位于东经120°08′～120°38′，
      北纬37°05′～37°33′之间。东接栖霞市，西靠莱州市，南与莱阳、莱西两市接壤，北以龙口市为
      邻，西北濒临渤海，海岸线长13.5公里。全市总面积1432.32平方公里。地处胶东低山丘陵地带，
      山区、丘陵分别占总面积的32.9%和38.4%,山丘连绵,沟壑纵横。地势东北部、中部和西部偏高
      ，东北部的罗山山脉高为群首，主峰海拔759米，有“势压登莱百万峰”之说。周围分布有海拔500米
      以上的山头21个，群山环翠，峰峦叠嶂。境内河流11个流域160余条，多为源短流急的时令河，分
      为渤海水系和黄海水系。境内长48公里的大沽河为第一大河，界河流域为第一大流域。干流长度大于
  5公里的河流51条。海岸线略呈凹形，均为海饰作用的沙岸，平缓细腻，为天然的海水浴场。
      礁岩中盛产刺参、梭鱼、对虾及三尤梭子蟹。暖温带季风区大陆性半湿润气候，四季分明，光照充足，
      年平均气温11.5℃，年平均降雨量671.1毫米。冬无严寒,夏无酷暑, 春秋季节,阳光充足而不曝,
       适于北方水果生长；空气湿润而清洁，风力柔和,特别适宜于龙口粉丝生产。 
</td>
   </tr>
</table>
<table style="width:33%;height:450px; border:1px solid #EAEAEA;float:right;">
    <tr style="background:#F5F5F5;">
        <th><span style="font-size:24px;font-weight:bold;">今日提醒</span></th>
    </tr>
	     <tr>
            <td>
            	<marquee direction="up" scrollamount="3" onmouseover="this.stop()" onmouseout="this.start()">
						   <%
						   for(int i = 0;i<todayNotice.size();i++){
							   %><a href=""><%= todayNotice.get(i).get("noticeContent") %></a><br /><%
						   }
						   %>
					    </marquee>
					</td>
        </tr>	
</table>

</body>
</html>