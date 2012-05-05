<%@ Control Language="C#" Inherits="System.Web.Mvc.ViewUserControl<FoodGalleryItemViewModel>" %>
<%@ Import Namespace="LocalServerWeb.ViewModels" %>
<%@ Import Namespace="LocalServerWeb.Resources.Views.FoodCategory" %>

<div id="food_gallery_item" item_num="<%:Model.STT.ToString() %>">
    <a href="/FoodCategory/Food/<%: Model.MaMonAn %>">
        <img src="<%: Model.HinhAnh %>" alt="<%:Model.TenMonAn %>" />
    </a>
    <p>
        <%: Model.TenMonAn %></p>
    <p>
        <%: Model.DonGia %>
        &nbsp; / &nbsp;
        <%: Model.TenDonViTinhMacDinh %>
    </p>
</div>
