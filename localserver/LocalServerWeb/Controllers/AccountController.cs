﻿using System;
using System.Collections.Generic;
using System.Diagnostics.CodeAnalysis;
using System.Linq;
using System.Security.Cryptography;
using System.Security.Principal;
using System.Text;
using System.Web;
using System.Web.Mvc;
using System.Web.Routing;
using System.Web.Security;
using LocalServerBUS;
using LocalServerDTO;
using LocalServerWeb.Codes;
using LocalServerWeb.Models;
using LocalServerWeb.Resources.Views.Account;
using LocalServerWeb.Resources.Views.AdminUser;
using LocalServerWeb.Resources.Views.Shared;
using LocalServerWeb.ViewModels;

namespace LocalServerWeb.Controllers
{

    [HandleError]
    public class AccountController : BaseController
    {
        public IFormsAuthenticationService FormsService { get; set; }
        public IMembershipService MembershipService { get; set; }

        protected override void Initialize(RequestContext requestContext)
        {
            if (FormsService == null) { FormsService = new FormsAuthenticationService(); }
            if (MembershipService == null) { MembershipService = new AccountMembershipService(); }

            base.Initialize(requestContext);
        }

        // **************************************
        // URL: /Account/LogOn
        // **************************************

        public ActionResult LogOn()
        {
            if (Request.UrlReferrer != null) ViewData["returnUrl"] = Request.UrlReferrer.ToString();
            if (Session["taiKhoan"] != null)
                if (Request.UrlReferrer != null) return Redirect(Request.UrlReferrer.ToString());
            int maNgonNgu = (Session["ngonNgu"] != null) ? ((NgonNgu)Session["ngonNgu"]).MaNgonNgu : 1;
            List<FoodGalleryItemViewModel> foodRandom = SharedCode.GetFoodRandom(20, maNgonNgu);
            ViewData["randomFoods"] = foodRandom;
            return View();
        }

        [HttpPost]
        public ActionResult LogOn(LogOnModel model, string returnUrl)
        {
            if (ModelState.IsValid)
            {
                TaiKhoan taiKhoan = TaiKhoanBUS.KiemTraTaiKhoan(model.UserName, SharedCode.Hash(model.Password));
                if (taiKhoan!=null)
                {
                    Session["taiKhoan"] = taiKhoan;
                    
                    if (SharedCode.IsAdminLogin(Session) || SharedCode.IsManagerLogin(Session) || SharedCode.IsWaitorLogin(Session))
                    {
                        return RedirectToAction("Index", "AdminHome");
                    }
                    if (SharedCode.IsKitchenLogin(Session))
                    {
                        return RedirectToAction("Index", "Kitchen");
                    }
                    if (!String.IsNullOrEmpty(returnUrl))
                    {
                        return Redirect(returnUrl);
                    }
                    else
                    {
                        return RedirectToAction("Index", "Home");
                    }
                }
                else
                {
                    ModelState.AddModelError("", AccountString.UsernamePasswordIncorrect);
                }
            }

            // If we got this far, something failed, redisplay form
            return View(model);
        }

        // **************************************
        // URL: /Account/LogOff
        // **************************************

        public ActionResult LogOff()
        {
            if (Session["taiKhoan"] != null) Session.Remove("taiKhoan");
            
            //if (Request.UrlReferrer != null) return Redirect(Request.UrlReferrer.ToString());
            return RedirectToAction("Index", "Home");
        }

        // **************************************
        // URL: /Account/Register
        // **************************************

        public ActionResult Register()
        {
            ViewData["PasswordLength"] = MembershipService.MinPasswordLength;
            return View();
        }

        //[HttpPost]
        //public ActionResult Register(RegisterModel model)
        //{
        //    if (ModelState.IsValid)
        //    {
        //        // Attempt to register the user
        //        MembershipCreateStatus createStatus = MembershipService.CreateUser(model.UserName, model.Password, model.Email);

        //        if (createStatus == MembershipCreateStatus.Success)
        //        {
        //            FormsService.SignIn(model.UserName, false /* createPersistentCookie */);
        //            return RedirectToAction("Index", "Home");
        //        }
        //        else
        //        {
        //            ModelState.AddModelError("", AccountValidation.ErrorCodeToString(createStatus));
        //        }
        //    }

        //    // If we got this far, something failed, redisplay form
        //    ViewData["PasswordLength"] = MembershipService.MinPasswordLength;
        //    return View(model);
        //}
        public ActionResult Edit(int? id)
        {
            if (id == null || id <= 0)
            {
                TempData["error"] = SharedString.InputWrong;
                return RedirectToAction("Index", "Error");
            }

            TaiKhoan taiKhoan = TaiKhoanBUS.LayTaiKhoan(id ?? 0);
            if (taiKhoan == null || !SharedCode.IsUserLogin(Session) || ((TaiKhoan)Session["taiKhoan"]).MaTaiKhoan!=taiKhoan.MaTaiKhoan)
            {
                TempData["errorNotFound"] = AdminUserString.Error;
                return RedirectToAction("Index", "Error");
            }
            else
            {
                TempData["tenTaiKhoan"] = taiKhoan.TenTaiKhoan;
                TempData["matKhau"] = taiKhoan.MatKhau;
                TempData["hoTen"] = taiKhoan.HoTen;
                TempData["day"] = taiKhoan.NgaySinh.Day;
                TempData["month"] = taiKhoan.NgaySinh.Month;
                TempData["year"] = taiKhoan.NgaySinh.Year;
                TempData["gioiTinh"] = taiKhoan.GioiTinh;
                TempData["nhomTaiKhoan"] = taiKhoan.NhomTaiKhoan.MaNhomTaiKhoan;
                TempData["cmnd"] = taiKhoan.CMND;

                ViewData["listNhomTaiKhoan"] = NhomTaiKhoanBUS.LayDanhSachNhomTaiKhoan();
                if (TempData["checkDic"] == null)
                {
                    TempData["checkDic"] = new Dictionary<string, string>();
                }

            }

            return View();
        }
        // **************************************
        // URL: /Account/ChangePassword
        // **************************************

        [Authorize]
        public ActionResult ChangePassword()
        {
            ViewData["PasswordLength"] = MembershipService.MinPasswordLength;
            return View();
        }

        [Authorize]
        [HttpPost]
        public ActionResult ChangePassword(ChangePasswordModel model)
        {
            if (ModelState.IsValid)
            {
                if (MembershipService.ChangePassword(User.Identity.Name, model.OldPassword, model.NewPassword))
                {
                    return RedirectToAction("ChangePasswordSuccess");
                }
                else
                {
                    ModelState.AddModelError("", "The current password is incorrect or the new password is invalid.");
                }
            }

            // If we got this far, something failed, redisplay form
            ViewData["PasswordLength"] = MembershipService.MinPasswordLength;
            return View(model);
        }

        // **************************************
        // URL: /Account/ChangePasswordSuccess
        // **************************************

        public ActionResult ChangePasswordSuccess()
        {
            return View();
        }
    }
}
