<%@ Control Language="C#" Inherits="System.Web.Mvc.ViewUserControl" %>
<%@ Import Namespace="LocalServerWeb.Resources.Views.Shared" %>

<!--  start nav -->
		<div class="nav">
		<div class="table">
		

		<ul <%: ((int)ViewData["menuMainId"] == 0)?"class=current":"class=select" %> ><li><a href=""><b><%:SharedString.AdminHome %></b></a>
        <div class='select_sub <%: ((int)ViewData["menuMainId"] == 0)?"show":"" %> '>
<%--			<ul class="sub">
				<li <%: ((int)ViewData["menuSubId"] == 0 && (int)ViewData["menuMainId"] == 0)?"class=sub_show":"" %> ><a href="#nogo"><%:SharedString.AccountManagerList %></a></li>
				<li <%: ((int)ViewData["menuSubId"] == 1 && (int)ViewData["menuMainId"] == 0)?"class=sub_show":"" %> ><a href="#nogo">Dashboard Details 2</a></li>
				<li <%: ((int)ViewData["menuSubId"] == 2 && (int)ViewData["menuMainId"] == 0)?"class=sub_show":"" %> ><a href="#nogo">Dashboard Details 3</a></li>
			</ul>--%>
		</div>
		</li>
        </ul>			
		
		<div class="nav-divider">&nbsp;</div>
		                    
		<ul <%: ((int)ViewData["menuMainId"] == 1)?"class=current":"class=select" %> ><li><a href="#nogo"><b><%:SharedString.AccountManager %></b></a>
		<div class='select_sub <%: ((int)ViewData["menuMainId"] == 1)?"show":"" %> '>
			<ul class="sub">
				<li <%: ((int)ViewData["menuSubId"] == 0 && (int)ViewData["menuMainId"] == 1)?"class=sub_show":"" %> ><%:Html.ActionLink(SharedString.AccountManagerList, "Index", "AdminUser") %>"></li>
				<li <%: ((int)ViewData["menuSubId"] == 1 && (int)ViewData["menuMainId"] == 1)?"class=sub_show":"" %> ><%:Html.ActionLink(SharedString.AdminAddUser, "AddUser", "AdminUser") %></li>
				<li <%: ((int)ViewData["menuSubId"] == 2 && (int)ViewData["menuMainId"] == 1)?"class=sub_show":"" %> ><a href="#nogo">Delete products</a></li>
			</ul>
		</div>
		</li>
		</ul>
		
		<div class="nav-divider">&nbsp;</div>
		
		<ul <%: ((int)ViewData["menuMainId"] == 2)?"class=current":"class=select" %> ><li><a href="#nogo"><b>Categories</b></a>
		<div class='select_sub <%: ((int)ViewData["menuMainId"] == 2)?"show":"" %> '>
			<ul class="sub">
				<li <%: ((int)ViewData["menuSubId"] == 0 && (int)ViewData["menuMainId"] == 2)?"class=sub_show":"" %> ><a href="#nogo">Categories Details 1</a></li>
				<li <%: ((int)ViewData["menuSubId"] == 1 && (int)ViewData["menuMainId"] == 2)?"class=sub_show":"" %> ><a href="#nogo">Categories Details 2</a></li>
				<li <%: ((int)ViewData["menuSubId"] == 2 && (int)ViewData["menuMainId"] == 2)?"class=sub_show":"" %> ><a href="#nogo">Categories Details 3</a></li>
			</ul>
		</div>
		</li>
		</ul>
		
		<div class="nav-divider">&nbsp;</div>
		
		<ul <%: ((int)ViewData["menuMainId"] == 3)?"class=current":"class=select" %> ><li><a href="#nogo"><b>Clients</b></a>
		<div class='select_sub <%: ((int)ViewData["menuMainId"] == 3)?"show":"" %> '>
			<ul class="sub">
				<li <%: ((int)ViewData["menuSubId"] == 0 && (int)ViewData["menuMainId"] == 3)?"class=sub_show":"" %> ><a href="#nogo">Clients Details 1</a></li>
				<li <%: ((int)ViewData["menuSubId"] == 1 && (int)ViewData["menuMainId"] == 3)?"class=sub_show":"" %> ><a href="#nogo">Clients Details 2</a></li>
				<li <%: ((int)ViewData["menuSubId"] == 2 && (int)ViewData["menuMainId"] == 3)?"class=sub_show":"" %> ><a href="#nogo">Clients Details 3</a></li>
			 
			</ul>
		</div>
		</li>
		</ul>
		
		<div class="nav-divider">&nbsp;</div>
		
		<ul <%: ((int)ViewData["menuMainId"] == 4)?"class=current":"class=select" %> ><li><a href="#nogo"><b>News</b></a>
		<div class='select_sub <%: ((int)ViewData["menuMainId"] == 4)?"show":"" %> '>
			<ul class="sub">
				<li <%: ((int)ViewData["menuSubId"] == 0 && (int)ViewData["menuMainId"] == 4)?"class=sub_show":"" %> ><a href="#nogo">News details 1</a></li>
				<li <%: ((int)ViewData["menuSubId"] == 1 && (int)ViewData["menuMainId"] == 4)?"class=sub_show":"" %> ><a href="#nogo">News details 2</a></li>
				<li <%: ((int)ViewData["menuSubId"] == 2 && (int)ViewData["menuMainId"] == 4)?"class=sub_show":"" %> ><a href="#nogo">News details 3</a></li>
			</ul>
		</div>
		</li>
		</ul>
		
		<div class="clear"></div>
		</div>

		<div class="clear"></div>
		</div>
		<!--  start nav -->
