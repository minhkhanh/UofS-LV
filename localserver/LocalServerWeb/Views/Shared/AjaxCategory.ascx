<%@ Control Language="C#" Inherits="System.Web.Mvc.ViewUserControl<PagedList<FoodGalleryItemViewModel>>" %>
<%@ Import Namespace="LocalServerWeb.Resources.Views.FoodCategory" %>
<%@ Import Namespace="LocalServerWeb.ViewModels" %>
<%@ Import Namespace="Webdiyer.WebControls.Mvc" %>

<div id="ajax_category">
    <table width="100%">
        <tr>
            <td colspan="2">
                Category
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <% Html.RenderPartial("FoodCategoryLinks"); %>
            </td>
        </tr>
        <tr valign="top">
            <td>
                <% Html.RenderPartial("FoodCategorySidebar"); %>
            </td>
            <td>
                <% Html.RenderPartial("FoodGallery", Model); %>
            </td>
        </tr>
        <tr>
            <td colspan="2" align="right">
                <%= Html.AjaxPager(Model, new PagerOptions() { PageIndexParameterName = "page", 
                    AlwaysShowFirstLastPageNumber = true,
                    CurrentPagerItemWrapperFormatString = "<span class=\"cpb\">{0}</span>",
                    NumericPagerItemWrapperFormatString = "<span class=\"item\">{0}</span>",
                    CssClass = "pages", SeparatorHtml = "" }, 
                    new AjaxOptions() { UpdateTargetId = "ajax_category"})%>
            </td>
        </tr>
    </table>
</div>
