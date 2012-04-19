<%@ Control Language="C#" ClassName="LanguageUserControl" Inherits="System.Web.Mvc.ViewUserControl" %>
<form name="selectLanguage" action="Account/LogOff" method="POST">
Ngôn ngữ: <%= Html.DropDownList("maNgonNgu", ViewData["listNgonNgu"] as SelectList, new { onchange = "submit();" })%>
</form>