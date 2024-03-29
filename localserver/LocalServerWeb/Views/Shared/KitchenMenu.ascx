﻿<%@ Control Language="C#" Inherits="System.Web.Mvc.ViewUserControl" %>
<%@ Import Namespace="LocalServerDTO" %>
<%@ Import Namespace="LocalServerWeb.Resources.Views.Menu" %>

<div id="nav-menu">
    <!-- Start css3menu.com BODY section -->
    <ul id="css3menu1" class="topmenu">
        <!-- Home ------------------------------------------------------------------------------------------------------------->
        <li class="topmenu"><a href="<%= Url.Action("Index", "Home") %>" style="height: 32px;
            line-height: 32px;">
            <img src="<%: Url.Content("~/Content/css3menu/home.png") %>" alt="Home" /><%: MenuString.Home %>
        </a></li>
        <!-- Choose Kitchen ------------------------------------------------------------------------------------------------------------->
        <li class="topmenu"><a href="<%= Url.Action("Choose", "Kitchen") %>" style="height: 32px;
            line-height: 32px;">
            <img src="<%: Url.Content("~/Content/css3menu/kitchen.png") %>" alt="Choose kitchen" /><%: MenuString.ChooseKitchen %>
        </a></li>
        <!-- Account ------------------------------------------------------------------------------------------------------------->
        <li class="topmenu"><a style="height: 32px; line-height: 32px;"><span>
            <img src="<%: Url.Content("~/Content/css3menu/register.png") %>" alt="Account" /><%: MenuString.Account %></span></a>
            <ul>
                <li class="subfirst"><a href="<%: Url.Action("Edit", "Account", new { id = ((TaiKhoan)Session["taiKhoan"]).MaTaiKhoan}) %>"><span>
                    <img src="<%: Url.Content("~/Content/css3menu/1.png") %>" alt="Change Info" /><%: MenuString.ChangeInfo %></span></a>
                </li>
                <li class="subfirst"><a href="<%: Url.Action("LogOff", "Account") %>"><span>
                    <img src="<%: Url.Content("~/Content/css3menu/2.png") %>" alt="Logout" /><%: MenuString.Logout %></span></a>
                </li>
            </ul>
        </li>
    </ul>
    <!-- End css3menu.com BODY section -->
</div>
