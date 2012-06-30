<%@ Control Language="C#" ClassName="LanguageUserControl" Inherits="System.Web.Mvc.ViewUserControl" %>
<%@ Import Namespace="LocalServerWeb.Resources.Views.Shared" %>
<%@ Import Namespace="LocalServerWeb.Codes" %>

<form name="selectLanguage" action="<%: SharedCode.GetHostApplicationAddress(Request) + "Language/ChangeLanguage" %>" method="post">
<%: SharedString.Language %>: <%= Html.DropDownList("kiHieuNgonNgu", ViewData["listNgonNgu"] as SelectList, new { onchange = "submit();" })%>
<input type="hidden" name="returnUrlLanguage" value="<%:Request.RawUrl %>"/>
</form>