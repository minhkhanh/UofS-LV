﻿<%@ Control Language="C#" Inherits="System.Web.Mvc.ViewUserControl<FoodGalleryItemViewModel>" %>
<%@ Import Namespace="LocalServerWeb.ViewModels" %>
<%@ Import Namespace="LocalServerWeb.Resources.Views.FoodCategory" %>
<%@ Import Namespace="LocalServerWeb.Codes" %>

<div id="food_gallery_item" item_num="<%:Model.STT.ToString() %>">
    <!--<a href="/FoodCategory/Food/<%: Model.MaMonAn %>">-->
    <a href = "<%: SharedCode.GetHostApplicationAddress(Request) + "FoodCategory/Food/" + Model.MaMonAn.ToString() %>" >
        <img src="<%:Url.Content("~/"+Model.HinhAnh+"")%>" alt="<%:Model.TenMonAn %>" />
    </a>
    <p>
        <%: Model.TenMonAn %></p>
    <p>
        <%: Model.DonGia %> VND
        /
        <%: Model.TenDonViTinh %>
    </p>
</div>
