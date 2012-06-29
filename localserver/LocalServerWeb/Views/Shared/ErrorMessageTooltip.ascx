<%@ Control Language="C#" Inherits="System.Web.Mvc.ViewUserControl<dynamic>" %>
<%@ Import Namespace="LocalServerWeb.Resources.Views.Shared" %>

<div class="message-red">
    <table border="0" width="100%" cellpadding="0" cellspacing="0">
        <tbody>
            <tr>
                <td class="red-left">
                    <%:SharedString.Error %>
                    <a href="">
                        <%: Model %></a>
                </td>
                <td class="red-right">
                    <a class="close-red">
                        <img src="<%:Url.Content("~/Images/adminimages/table/icon_close_red.gif") %>" alt="" /></a>
                </td>
            </tr>
        </tbody>
    </table>
</div>
