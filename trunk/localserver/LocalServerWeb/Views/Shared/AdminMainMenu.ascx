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
		s
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
		
		<ul <%: ((int)ViewData["menuMainId"] == 2)?"class=current":"class=select" %> ><li><a href="#nogo"><b><%:SharedString.FoodManager %></b></a>
		<div class='select_sub <%: ((int)ViewData["menuMainId"] == 2)?"show":"" %> '>
			<ul class="sub">
				<li <%: ((int)ViewData["menuSubId"] == 0 && (int)ViewData["menuMainId"] == 2)?"class=sub_show":"" %> ><%:Html.ActionLink(SharedString.FoodManagerList, "Index", "AdminFood") %></li>
				<li <%: ((int)ViewData["menuSubId"] == 1 && (int)ViewData["menuMainId"] == 2)?"class=sub_show":"" %> ><%:Html.ActionLink(SharedString.FoodManagerAddFood, "AddFood", "AdminFood") %></li>
				<li <%: ((int)ViewData["menuSubId"] == 2 && (int)ViewData["menuMainId"] == 2)?"class=sub_show":"" %> ><a href="#nogo">Categories Details 3</a></li>
			</ul>
		</div>
		</li>
		</ul>
		
		<div class="nav-divider">&nbsp;</div>
		
		<ul <%: ((int)ViewData["menuMainId"] == 3)?"class=current":"class=select" %> ><li><a href="#nogo"><b><%: SharedString.Order%> / <%:SharedString.Invoice %></b></a>
		<div class='select_sub <%: ((int)ViewData["menuMainId"] == 3)?"show":"" %> '>
			<ul class="sub">
				<li <%: ((int)ViewData["menuSubId"] == 0 && (int)ViewData["menuMainId"] == 3)?"class=sub_show":"" %> ><%:Html.ActionLink(SharedString.OrderList, "Index", "AdminOrder") %></li>
                <li <%: ((int)ViewData["menuSubId"] == 1 && (int)ViewData["menuMainId"] == 3)?"class=sub_show":"" %> ><%:Html.ActionLink(SharedString.InvoiceList, "Index", "AdminInvoice") %></li>
                <li <%: ((int)ViewData["menuSubId"] == 2 && (int)ViewData["menuMainId"] == 3)?"class=sub_show":"" %> ><%:Html.ActionLink(SharedString.LanguageList, "Index", "AdminLanguage") %></li>
                <li <%: ((int)ViewData["menuSubId"] == 3 && (int)ViewData["menuMainId"] == 3)?"class=sub_show":"" %> ><%:Html.ActionLink(SharedString.AddLanguage, "Add", "AdminLanguage") %></li>
                <li <%: ((int)ViewData["menuSubId"] == 4 && (int)ViewData["menuMainId"] == 3)?"class=sub_show":"" %> ><%:Html.ActionLink(SharedString.AreaList, "Index", "AdminArea") %></li>
                <li <%: ((int)ViewData["menuSubId"] == 5 && (int)ViewData["menuMainId"] == 3)?"class=sub_show":"" %> ><%:Html.ActionLink(SharedString.AddArea, "Add", "AdminArea") %></li>
                <li <%: ((int)ViewData["menuSubId"] == 6 && (int)ViewData["menuMainId"] == 3)?"class=sub_show":"" %> ><%:Html.ActionLink(SharedString.TableList, "Index", "AdminTable") %></li>
                <li <%: ((int)ViewData["menuSubId"] == 7 && (int)ViewData["menuMainId"] == 3)?"class=sub_show":"" %> ><%:Html.ActionLink(SharedString.AddTable, "Add", "AdminTable") %></li>
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
