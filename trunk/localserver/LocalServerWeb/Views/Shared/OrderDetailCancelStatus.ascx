<%@ Control Language="C#" Inherits="System.Web.Mvc.ViewUserControl<OrderDetailViewModel>" %>
<%@ Import Namespace="LocalServerWeb.ViewModels" %>
<%@ Import Namespace="LocalServerWeb.Resources.Views.AdminOrder" %>

<table id='order_detail_cancel_status'>
    <tr>
        <th colspan='2'>
            <%: AdminOrderString.CancelStatusTitle %>
        </th>
    </tr>
    <tr>
        <th>
            <%: AdminOrderString.QuantityRequired %>
        </th>
        <td>
            <%: Model.SoLuongYeuCau %>
        </td>
    </tr>
    <tr>
        <th>
            <%: AdminOrderString.QuantityAllowed %>
        </th>
        <td>
            <%: Model.SoLuongChoPhep %>
        </td>
    </tr>
    <tr>
        <th>
            <%: AdminOrderString.CancelStatus %>
        </th>
        <td>
            <%: Model.TinhTrangHuy %>
        </td>
    </tr>
</table>
