<%@ Page Language="C#" MasterPageFile="~/Views/Shared/Site.Master" Inherits="System.Web.Mvc.ViewPage<LocalServerWeb.Models.LogOnModel>" %>
<%@ Import Namespace="LocalServerWeb.Resources.Models" %>
<%@ Import Namespace="LocalServerWeb.Resources.Views.Account" %>

<asp:Content ID="loginTitle" ContentPlaceHolderID="TitleContent" runat="server">
    Đăng nhập
</asp:Content>

<asp:Content ID="head" ContentPlaceHolderID="HeadContent" runat="server">
    <script language="javascript" type="text/javascript" src="../../Scripts/MD5.js"></script>
    <script language="javascript" type="text/javascript" src="../../Scripts/Login.js"></script>
    <link href="../../Content/sitecss/Login.css" type="text/css" rel="stylesheet"/>
</asp:Content>

<asp:Content ID="loginContent" ContentPlaceHolderID="MainContent" runat="server">
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
                                <td colspan="2" align="left"><font color="#FF0000">
                                    <%: Html.ValidationSummary(false) %>
                                    <%--<%: Html.ValidationMessageFor(m => m.UserName) %><br/>
                                    <%: Html.ValidationMessageFor(m => m.Password) %>--%>
                                </font></td>
                            </tr>
					        <tr>
						        <td><%: Html.LabelFor(m => m.UserName) %></td>
						        <td><%: Html.TextBoxFor(m => m.UserName, new { style = "width: 100%" })%></td>
					        </tr>
					        <tr>
						        <td><%: Html.LabelFor(m => m.Password) %></td>
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
