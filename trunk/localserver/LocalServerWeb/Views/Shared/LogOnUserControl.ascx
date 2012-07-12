<%@ Control Language="C#" Inherits="System.Web.Mvc.ViewUserControl" %>
<%@ Import Namespace="LocalServerDTO" %>
<%@ Import Namespace="LocalServerWeb.Codes" %>
<%@ Import Namespace="LocalServerWeb.Resources.Views.Shared" %>
<%
    if (Session["taiKhoan"]!=null) {
%>
        <%:SharedString.Welcome %> <b><%:((TaiKhoan)Session["taiKhoan"]).HoTen %></b>!
        [ <%: Html.ActionLink(SharedString.LogOff, "LogOff", "Account") %> ]
<%
        if (SharedCode.IsAdminLogin(new HttpSessionStateWrapper(Session)) || SharedCode.IsManagerLogin(new HttpSessionStateWrapper(Session)) || SharedCode.IsWaitorLogin(new HttpSessionStateWrapper(Session)))
        {
%> 
            [ <%: Html.ActionLink(SharedString.Config, "Index", "AdminHome") %> ]
<%            
        }
        
        if (SharedCode.IsKitchenLogin(new HttpSessionStateWrapper(Session)))
        {
%> 
            [ <%: Html.ActionLink(SharedString.Kitchen, "Index", "Kitchen") %> ]
<%            
        }      
    }
    else {
%> 
        [ <%: Html.ActionLink(SharedString.LogOn, "LogOn", "Account") %> ]
<%      
    }
%>
