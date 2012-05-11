<%@ Control Language="C#" Inherits="System.Web.Mvc.ViewUserControl<dynamic>" %>
<%@ Import Namespace="LocalServerWeb.Resources.Views.Shared" %>

<div class="message-green">
    <table border="0" width="100%" cellpadding="0" cellspacing="0">
        <tbody>
            <tr>
                <td class="green-left">
                    <a href="">
                        <%: Model %></a>
                </td>
                <td class="green-right">
                    <a class="close-green">
                        <img src="../../Images/adminimages/table/icon_close_green.gif" alt="" /></a>
                </td>
            </tr>
        </tbody>
    </table>
</div>
