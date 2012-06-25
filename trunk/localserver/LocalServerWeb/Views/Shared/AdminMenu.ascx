<%@ Control Language="C#" Inherits="System.Web.Mvc.ViewUserControl" %>
<%@ Import Namespace="LocalServerWeb.Resources.Views.Menu" %>
<div id="nav-menu">
    <!-- Start css3menu.com BODY section -->
    <ul id="css3menu1" class="topmenu">
        <!-- Home ------------------------------------------------------------------------------------------------------------->
        <li class="topmenu"><a href="<%= Url.Action("Index", "AdminHome") %>" style="height: 32px;
            line-height: 32px;">
            <img src="../../Content/css3menu/home.png" alt="Home" /><%: MenuString.Home %>
        </a></li>
        <!-- User ------------------------------------------------------------------------------------------------------------->
        <li class="topmenu"><a href="" style="height: 32px; line-height: 32px;"><span>
            <img src="../../Content/css3menu/user.png" alt="Area" /><%: MenuString.User %></span></a>
            <ul>
                <li class="subfirst"><a href="<%: Url.Action("Index", "AdminUser") %>"><span>
                    <img src="../../Content/css3menu/1.png" alt="Area manager" /><%: MenuString.ManageUser %></span></a>
                    <ul>
                        <li class="sublast"><a href="<%: Url.Action("Add", "AdminUser") %>">
                            <img src="../../Content/css3menu/adduser.png" alt="Add area" /><%: MenuString.AddUser %></a></li>
                    </ul>
                </li>
            </ul>
        </li>
        <!-- Config ------------------------------------------------------------------------------------------------------------->
        <li class="topmenu"><a href="<%= Url.Action("Index", "AdminConfig") %>" style="height: 32px;
            line-height: 32px;">
            <img src="../../Content/css3menu/config.png" alt="Config" /><%:MenuString.Config %></a></li>
        <!-- User ------------------------------------------------------------------------------------------------------------->
        <li class="topmenu"><a href="" style="height: 32px; line-height: 32px;"><span>
            <img src="../../Content/css3menu/user.png" alt="Area" /><%: MenuString.User %></span></a>
            <ul>
                <li class="subfirst"><a href="<%: Url.Action("Index", "AdminUser") %>"><span>
                    <img src="../../Content/css3menu/1.png" alt="Area manager" /><%: MenuString.ManageUser %></span></a>
                    <ul>
                        <li class="sublast"><a href="<%: Url.Action("Add", "AdminUser") %>">
                            <img src="../../Content/css3menu/adduser.png" alt="Add area" /><%: MenuString.AddUser %></a></li>
                    </ul>
                </li>
            </ul>
        </li>
    </ul>
    <!-- End css3menu.com BODY section -->
</div>
