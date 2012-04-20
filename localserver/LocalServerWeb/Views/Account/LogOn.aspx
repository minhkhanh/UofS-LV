<%@ Page Language="C#" MasterPageFile="~/Views/Shared/Site.Master" Inherits="System.Web.Mvc.ViewPage<LocalServerWeb.Models.LogOnModel>" %>
<%@ Import Namespace="LocalServerWeb.Resources.Models" %>
<%@ Import Namespace="LocalServerWeb.Resources.Views.Account" %>

<asp:Content ID="loginTitle" ContentPlaceHolderID="TitleContent" runat="server">
    Đăng nhập
</asp:Content>

<asp:Content ID="head" ContentPlaceHolderID="HeadContent" runat="server">
    <script language="javascript" type="text/javascript" src="../../Scripts/MD5.js"></script>
    <script language="javascript" type="text/javascript" src="../../Scripts/Login.js"></script>
    <link href="../../Content/Login.css" type="text/css" rel="stylesheet"/>
</asp:Content>

<asp:Content ID="loginContent" ContentPlaceHolderID="MainContent" runat="server">
<%--    <h2>Đăng nhập</h2>
    <p>
        Xin hãy nhập vào tên đăng nhập và mật khẩu. <%: Html.ActionLink("Đăng ký", "Register")%> nếu không có tài khoản.
    </p>

    <% using (Html.BeginForm()) { %>
        <%: Html.ValidationSummary(true, "Đăng nhập thất bại. Xin hãy kiểm tra và thử lại.")%>
        <div>
            <fieldset>
                <legend>Thông tin tài khoản</legend>
                
                <div class="editor-label">
                    <%: Html.LabelFor(m => m.UserName) %>
                </div>
                <div class="editor-field">
                    <%: Html.TextBoxFor(m => m.UserName) %>
                    <%: Html.ValidationMessageFor(m => m.UserName) %>
                </div>
                
                <div class="editor-label">
                    <%: Html.LabelFor(m => m.Password) %>
                </div>
                <div class="editor-field">
                    <%: Html.PasswordFor(m => m.Password) %>
                    <%: Html.ValidationMessageFor(m => m.Password) %>
                </div>
                
                <div class="editor-label">
                    <%: Html.CheckBoxFor(m => m.RememberMe) %>
                    <%: Html.LabelFor(m => m.RememberMe) %>
                </div>
                
                <p>
                    <input type="submit" value="Đăng nhập" />
                </p>
            </fieldset>
        </div>
    <% } %>
--%>
    
    <fieldset>
        <legend><%:AccountString.LoginInfo %></legend>
        <table width="500px" border="1" cellspacing="0" cellpadding="5" align="center">
	        <tr bgcolor="#666666">
		        <td class="bgColorMain"><strong><font color="#FFFFFF"><%:AccountString.Login %></font></strong>
		        </td>
	        </tr>
	        <tr>
		        <td valign="top">
                    <% Html.BeginForm("LogOn", "Account", FormMethod.Post); %>
                        <input type="hidden" name="returnUrl" value="<%: ViewData["returnUrl"] %>"/>
				        <table width="100%">
					        <tr>
						        <td colspan="2"><strong><%:AccountString.LoginInfo %></strong>
						        </td>
					        </tr>
                            <tr>
                                <td colspan="2" align="center"><font color="#FF0000">
                                    <%: Html.ValidationMessageFor(m => m.UserName) %>
                                    <%: Html.ValidationMessageFor(m => m.Password) %>
                                </font></td>
                            </tr>
					        <tr>
						        <td><%: AccountModelString.UserName %></td>
						        <td><%: Html.TextBoxFor(m => m.UserName, new { style = "width: 100%" })%></td>
					        </tr>
					        <tr>
						        <td><%: AccountModelString.Password %></td>
						        <td><%: Html.PasswordFor(m => m.Password, new { style = "width: 100%" })%></td>
					        </tr>
					        <tr>
						        <td></td>
						        <td align="center"><input type="submit" name="dangNhap"
							        value="<%: AccountString.Login %>" /></td>
					        </tr>
				        </table>
			        <% Html.EndForm();%>
                </td>
	        </tr>
        </table>
    </fieldset>
</asp:Content>
