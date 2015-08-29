<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta content="text/html; charset=utf-8" http-equiv="Content-Type"></meta>
    <title>人事档案管理系统</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <link href="assets/css/dpl-min.css" rel="stylesheet" type="text/css" />
    <link href="assets/css/bui-min.css" rel="stylesheet" type="text/css" />
    <link href="assets/css/main-min.css" rel="stylesheet" type="text/css" />
    <link rel="stylesheet" type="text/css" href="css/bootstrap-responsive.css" />
    <link rel="stylesheet" type="text/css" href="css/style.css" />
    <script type="text/javascript" src="js/jquery.js"></script>
    <script type="text/javascript" src="js/jquery.sorted.js"></script>
    <script type="text/javascript" src="js/bootstrap.js"></script>
    <script type="text/javascript" src="js/ckform.js"></script>
    <script type="text/javascript" src="js/common.js"></script>
</head>
<body>
<div class="header">
    <div class="dl-title">
          <img src="/images/gh.png"/>
          <span style="font-size:24px;">&nbsp;&nbsp;人事档案管理系统</span>
    </div>

    <div class="dl-log">欢迎您，<span class="dl-log-user"><%= session.getAttribute("uesrName") %></span><a href="/shiyanlogin.jsp" title="重新登录" class="dl-log-quit">[重新登录]</a> 
    	                                                               <a href="" title="退出系统" class="dl-log-quit">[退出]</a>
    </div>  
</div>
<div class="content">
    <div class="dl-main-nav">
        <div class="dl-inform"><div class="dl-inform-title"><s class="dl-inform-icon dl-up"></s></div></div>
        <ul id="J_Nav"  class="nav-list ks-clear">
        	  <li class="nav-item dl-selected"><div class="nav-item-inner nav-home">首页</div></li>
            <li class="nav-item dl-selected"><div class="nav-item-inner nav-order">用户管理</div></li>
            <li class="nav-item dl-selected"><div class="nav-item-inner nav-order">人事设置</div></li>
			      <li class="nav-item dl-selected"><div class="nav-item-inner nav-order">人事管理</div></li>
			      <li class="nav-item dl-selected"><div class="nav-item-inner nav-order">导入导出 </div></li>
			      <li class="nav-item dl-selected"><div class="nav-item-inner nav-order">打印 </div></li>
			      <li class="nav-item dl-selected"><div class="nav-item-inner nav-order">数据维护 </div></li>
			      <li class="nav-item dl-selected"><div class="nav-item-inner nav-order">统计提醒 </div></li>		      	      
        </ul>
    </div>
    <ul id="J_NavContent" class="dl-tab-conten">

    </ul>
</div>
<script type="text/javascript" src="assets/js/jquery-1.8.1.min.js"></script>
<script type="text/javascript" src="assets/js/bui-min.js"></script>
<script type="text/javascript" src="assets/js/common/main-min.js"></script>
<script type="text/javascript" src="assets/js/config-min.js"></script>
<script>
    BUI.use('common/main',function(){
       var config = [{id:'1',homePage:'2',menu:[{text:'首页',items:[{id:'2',text:'今日提醒',href:'/todayNotes.jsp'},
                                                                   {id:'3',text:'显示人员列表',href:'/normalShowPersonal?current=1'},
                                                                   {id:'5',text:'高级查询',href:'/gjcxtj.jsp'}]}
        			]},
                {id:'6',menu:[{text:'用户管理',items:[{id:'7',text:'修改密码',href:'/modpass/modPass.jsp'},
                                                  {id:'7',text:'权限管理',href:'/normal/show.html'}]}
        			]},
        			  {id:'8',menu:[{text:'人事设置',items:[{id:'9',text:'部门设置',href:'normalBmszServlet?parentId=1'},
        			  	                                                     {id:'10',text:'类别设置',href:'normalLbszServlet'},
        			                               						               {id:'11',text:'档案设置',href:'normalDaszServlet?tableName=personal&current=1'},
        			                               						               {id:'12',text:'记录设置',href:'normalJlszServlet?current=1'},
        			                               						            {id:'13',text:'选择列设置',href:'/normalSelectServlet'}]}
        			]},
        			  {id:'14',menu:[{text:'人事管理',items:[{id:'15',text:'人事记录',href:'/normalJlszServletRequest'},
        			                                     {id:'16',text:'部门变动',href:'/normalRsbdServlet?tableName=personal'},
	                                                     {id:'17',text:'类别变动',href:'/normalLbbdServlet?tableName=personal'}]}
        			]},
        			{id:'18',menu:[{text:'导入导出',items:[{id:'19',text:'导入',href:'/normal/show.html'},
	                                                     {id:'20',text:'导出',href:'/normal/show.html'}
	                                                     ]}
                    ]},
                    {id:'21',menu:[{text:'打印',items:[{id:'22',text:'打印人员列表',href:'/normal/show.html'},
                                                     {id:'23',text:'打印人员基本档案',href:'/normal/show.html'},
                                                     {id:'24',text:'打印档案和培训记录',href:'/normal/show.html'},
                                                     {id:'25',text:'打印厂牌',href:'/normal/show.html'}
	                                                 ]}
                    ]},
        			  {id:'26',menu:[{text:'数据维护',items:[{id:'27',text:'数据备份',href:'/normal/show.html'},
        			  	                                                     {id:'28',text:'数据还原',href:'/normal/show.html'},
        			                               						               {id:'29',text:'初始化数据库',href:'/normal/show.html'}]}
        			]},
        			  {id:'30',menu:[{text:'统计提醒',items:[{id:'31',text:'分类统计',href:'/normal/show.html'},        			  	                                                  
        			                               		    {id:'36',text:'自定义提醒',href:'/normal/show.html'}]}
        			]}];
        new PageUtil.MainPage({
            modulesConfig : config
        });
    });
</script>
<div style="text-align:center;margin-top:0px;background:#1B3160">
<img src="/images/red.png"/>
<p style="color:#3366CC;">版权：<a href="http://www.ccec.edu.cn/" target="_blank">山东工商学院 信息管理与信息系统教研室</a></p>
</div>
</body>
</html>