<%@ Control Language="C#" Inherits="System.Web.Mvc.ViewUserControl" %>

<div id="nav-menu">
<!-- Start css3menu.com BODY section -->
<ul id="css3menu1" class="topmenu">
	<li class="topmenu">
        <a href="<%= Url.Action("Index", "AdminHome") %>" style="height:32px;line-height:32px;" >
            <img src="../../Content/css3menu/home.png" alt="Home"/>Home
        </a>
    </li>
	<li class="topmenu"><a href="<%= Url.Action("Index", "AdminUser") %>" style="height:32px;line-height:32px;"><span><img src="../../Content/css3menu/user.png" alt="User"/>User</span></a>
	<ul>
		<li class="subfirst"><a href="<%= Url.Action("Index", "AdminUser") %>">User manager</a></li>
		<li class="sublast"><a href="<%= Url.Action("AddUser", "AdminUser") %>"><img src="../../Content/css3menu/adduser.png" alt="Add user"/>Add user</a></li>
	</ul></li>
	<li class="topmenu"><a href="<%= Url.Action("Index", "AdminConfig") %>" style="height:32px;line-height:32px;"><img src="../../Content/css3menu/config.png" alt="Config"/>Config</a></li>
</ul>
<!-- End css3menu.com BODY section -->
</div>