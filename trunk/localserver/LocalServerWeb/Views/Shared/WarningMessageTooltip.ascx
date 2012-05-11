<%@ Control Language="C#" Inherits="System.Web.Mvc.ViewUserControl<dynamic>" %>
<%@ Import Namespace="LocalServerWeb.Resources.Views.Shared" %>

<div class="message-yellow">
    <table border="0" width="100%" cellpadding="0" cellspacing="0">
        <tbody>
            <tr>
                <td class="yellow-left">
                    <%:SharedString.Warning %>
                    <a href="">
                        <%: Model %></a>
                </td>
                <td class="yellow-right">
                    <a class="close-yellow">
                        <img src="../../Images/adminimages/table/icon_close_yellow.gif" alt="" /></a>
                </td>
            </tr>
        </tbody>
    </table>
</div>
