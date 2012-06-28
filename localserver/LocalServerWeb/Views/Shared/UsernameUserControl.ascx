<%@ Control Language="C#" Inherits="System.Web.Mvc.ViewUserControl" %>
<%@ Import Namespace="LocalServerDTO" %>
<%@ Import Namespace="LocalServerWeb.Resources.Views.Shared" %>
<%
    if (Session["taiKhoan"] != null)
    {
%>
<%:SharedString.Welcome %>
<b>
    <%:((TaiKhoan)Session["taiKhoan"]).HoTen %></b>!
<%} %>