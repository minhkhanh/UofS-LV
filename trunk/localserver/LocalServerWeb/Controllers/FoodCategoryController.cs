using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;
using LocalServerWeb.Codes;
using LocalServerWeb.ViewModels;
using LocalServerDTO;
using LocalServerBUS;
using LocalServerWeb.Resources.Views.FoodCategory;
using Webdiyer.WebControls.Mvc;

namespace LocalServerWeb.Controllers
{
    public class FoodCategoryController : BaseController
    {
        // **************************************
        // URL: /FoodCategory
        // **************************************

        public ActionResult Index()
        {
            return Redirect("/FoodCategory/Category/0");          
        }

        public ActionResult Category(int? id, string page)
        {
            FoodCategoryLinksViewModel foodCategoryLinksViewModel = GetFoodCategoryLinksViewModel(id??0, true);
            ViewData["foodCategoryLinksViewModel"] = foodCategoryLinksViewModel;

            FoodCategorySidebarViewModel foodCategorySidebarViewModel = GetFoodCategorySidebarViewModel(id??0, true);
            ViewData["foodCategorySidebarViewModel"] = foodCategorySidebarViewModel;

            //List<FoodGalleryItemViewModel> foodGalleryItemViewModels = GetFoodGalleryItemViewModels(id);
            //ViewData["foodGalleryItemViewModels"] = foodGalleryItemViewModels;
            //return View(id);

            int _page = 1;
            int.TryParse(page, out _page);
            PagedList<FoodGalleryItemViewModel> model = GetFoodGalleryItemViewModels(id??0).AsQueryable().ToPagedList(_page, 9);

            // For use in foodgalleryiten.css item_num attribute
            int stt = 1;
            foreach (FoodGalleryItemViewModel item in model)
            {
                item.STT = stt++;
            }
            // Using ajax
            if (Request.IsAjaxRequest())
                return PartialView("AjaxCategory", model);
            return View(model);
        }

        public ActionResult Food(int? id)
        {
            FoodCategoryLinksViewModel foodCategoryLinksViewModel = GetFoodCategoryLinksViewModel(id??1, false);
            ViewData["foodCategoryLinksViewModel"] = foodCategoryLinksViewModel;

            FoodCategorySidebarViewModel foodCategorySidebarViewModel = GetFoodCategorySidebarViewModel(id??1, false);
            ViewData["foodCategorySidebarViewModel"] = foodCategorySidebarViewModel;

            FoodDetailViewModel foodDetailViewModel = GetFoodDetailViewModel(id??1);
            ViewData["foodDetailViewModel"] = foodDetailViewModel;

            return View(id??1);
        }

        private FoodCategoryLinksViewModel GetFoodCategoryLinksViewModel(int id, bool isCategory)
        {
            FoodCategoryLinksViewModel viewModel = new FoodCategoryLinksViewModel();
            viewModel.Names = new List<string>();
            viewModel.Ids = new List<int>();
            viewModel.IsCategories = new List<bool>();

            if (id == 0)
            {
                viewModel.Names.Insert(0, FoodCategoryString.Index);
                viewModel.Ids.Insert(0, 0);
                viewModel.IsCategories.Insert(0, true);

                return viewModel;
            }
            

            int maDanhMuc = id;
            int maNgonNgu = (Session["ngonNgu"] != null) ? ((NgonNgu)Session["ngonNgu"]).MaNgonNgu : 1;

            try
            {
                if (isCategory == false)
                {
                    MonAn monAn = MonAnBUS.LayMonAn(id); 
                    ChiTietMonAnDaNgonNgu ctMonAnDaNgonNgu = ChiTietMonAnDaNgonNguBUS.LayChiTietMonAnDaNgonNgu(monAn.MaMonAn, maNgonNgu);
                    
                    viewModel.Names.Add(ctMonAnDaNgonNgu.TenMonAn);
                    viewModel.Ids.Add(id);
                    viewModel.IsCategories.Add(false);

                    maDanhMuc = monAn.DanhMuc.MaDanhMuc;
                }

                while (DanhMucBUS.LayDanhMuc(maDanhMuc) != null)
                {
                    DanhMuc temp = DanhMucBUS.LayDanhMuc(maDanhMuc);
                    ChiTietDanhMucDaNgonNgu ctDanhMucDaNgonNgu = ChiTietDanhMucDaNgonNguBUS.LayChiTietDanhMucDaNgonNgu(maDanhMuc, maNgonNgu);

                    viewModel.Names.Insert(0, ctDanhMucDaNgonNgu.TenDanhMuc);
                    viewModel.Ids.Insert(0, maDanhMuc);
                    viewModel.IsCategories.Insert(0, true);

                    maDanhMuc = (temp.DanhMucCha != null) ? temp.DanhMucCha.MaDanhMuc : 0;
                }
    
            }
            catch (Exception e)
            {
                viewModel.Names = new List<string>();
                viewModel.Ids = new List<int>();
                viewModel.IsCategories = new List<bool>();
            }

            // Alwasy has Index at the beginning
            viewModel.Names.Insert(0, FoodCategoryString.Index);
            viewModel.Ids.Insert(0, 0);
            viewModel.IsCategories.Insert(0, true);

            return viewModel;
        }

        private FoodCategorySidebarViewModel GetFoodCategorySidebarViewModel(int id, bool isCategory)
        {
            FoodCategorySidebarViewModel viewModel = new FoodCategorySidebarViewModel();
            viewModel.Names = new List<string>();
            viewModel.Ids = new List<int>();

            int maNgonNgu = (Session["ngonNgu"] != null) ? ((NgonNgu)Session["ngonNgu"]).MaNgonNgu : 1;

            try
            {
                if(id == 0)
                {
                    viewModel.ParentName = FoodCategoryString.Index;
                    viewModel.ParentId = 0;

                    List<DanhMuc> listDanhMuc = DanhMucBUS.LayDanhSachDanhMucCha();
                    for (int i = 0; i < listDanhMuc.Count; ++i)
                    {
                        ChiTietDanhMucDaNgonNgu ct = ChiTietDanhMucDaNgonNguBUS.LayChiTietDanhMucDaNgonNgu(listDanhMuc[i].MaDanhMuc, maNgonNgu);
                        viewModel.Names.Add(ct.TenDanhMuc);
                        viewModel.Ids.Add(listDanhMuc[i].MaDanhMuc);
                    }
                }
                else 
                {
                    DanhMuc danhMuc;
                    if (isCategory == true)
                        danhMuc = DanhMucBUS.LayDanhMuc(id);
                    else
                        danhMuc = MonAnBUS.LayMonAn(id).DanhMuc;

                    ChiTietDanhMucDaNgonNgu ctDanhMucDaNgonNgu = ChiTietDanhMucDaNgonNguBUS.LayChiTietDanhMucDaNgonNgu(danhMuc.MaDanhMuc, maNgonNgu);
                    viewModel.ParentName = ctDanhMucDaNgonNgu.TenDanhMuc;
                    viewModel.ParentId = danhMuc.MaDanhMuc;

                    List<DanhMuc> listDanhMuc = DanhMucBUS.LayDanhSachDanhMucTheoDanhMucCha(danhMuc.MaDanhMuc);
                    for (int i = 0; i < listDanhMuc.Count; ++i)
                    {
                        ChiTietDanhMucDaNgonNgu ct = ChiTietDanhMucDaNgonNguBUS.LayChiTietDanhMucDaNgonNgu(listDanhMuc[i].MaDanhMuc, maNgonNgu);
                        viewModel.Names.Add(ct.TenDanhMuc);
                        viewModel.Ids.Add(listDanhMuc[i].MaDanhMuc);
                    }
                }
                
            }
            catch (Exception e)
            {
                viewModel.Names = new List<string>();
                viewModel.Ids = new List<int>();
                viewModel.ParentName = FoodCategoryString.Index;
                viewModel.ParentId = 0;
            }

            return viewModel;
        }

        // Get a list of ViewModel for displaying Food Gallery (many Food items), get by MaDanhMuc
        private List<FoodGalleryItemViewModel> GetFoodGalleryItemViewModels(int id)
        {
            List<FoodGalleryItemViewModel> viewModels = new List<FoodGalleryItemViewModel>();
            List<DanhMuc> dsDanhMuc;
            List<MonAn> dsMonAn = new List<MonAn>();

            int maNgonNgu = (Session["ngonNgu"] != null) ? ((NgonNgu)Session["ngonNgu"]).MaNgonNgu : 1;

            try
            {
                // If this is Index, get all MonAn from database
                // Need to shuffle this
                if (id == 0)
                {
                    dsMonAn = MonAnBUS.LayDanhSachMonAn();
                }
                else
                {
                    // If this is a parent category, get all MonAn which are in descendant DanhMuc of this category
                    dsDanhMuc = DanhMucBUS.LayDanhSachDanhMucConChauDanhMucCha(id);
                    if (dsDanhMuc != null)
                        for (int i = 0; i < dsDanhMuc.Count; ++i)
                            dsMonAn.AddRange(MonAnBUS.LayDanhSachMonAnTheoDanhMuc(dsDanhMuc[i].MaDanhMuc));
                    
                    // Maybe this category contain MonAn, get all MonAn in it
                    dsMonAn.AddRange(MonAnBUS.LayDanhSachMonAnTheoDanhMuc(id));
                }
            }
            catch (Exception e)
            {

            }
            

            if (dsMonAn != null)
            {

                for (int i = 0; i < dsMonAn.Count; ++i)
                {
                    
                    try
                    {
                        MonAn monAn = dsMonAn[i];

                        FoodGalleryItemViewModel viewModel = GetFoodGalleryItemViewModel(monAn, maNgonNgu);

                        viewModels.Add(viewModel);
                        
                    }
                    catch (Exception e)
                    {

                    }
                           
                }
            }

            return viewModels;
        }

        // Get ViewModel for displaying Food Detail, get by Ma Mon An
        private FoodDetailViewModel GetFoodDetailViewModel(int id)
        {
            FoodDetailViewModel viewModel = null;
            int maNgonNgu = (Session["ngonNgu"] != null) ? ((NgonNgu)Session["ngonNgu"]).MaNgonNgu : 1;

            try
            {
                viewModel = new FoodDetailViewModel();
                MonAn monAn = MonAnBUS.LayMonAn(id);
                ChiTietMonAnDaNgonNgu ctMonAnDaNgonNgu = ChiTietMonAnDaNgonNguBUS.LayChiTietMonAnDaNgonNgu(monAn.MaMonAn, maNgonNgu);

                ChiTietMonAnDonViTinh ctMonAnDonViTinhMacDinh = ChiTietMonAnDonViTinhBUS.LayChiTietMonAnDonViTinh(monAn.MaMonAn, monAn.DonViTinhMacDinh.MaDonViTinh);               
                ChiTietDonViTinhDaNgonNgu ctDonViTinhDaNgonNguMacDinh = ChiTietDonViTinhDaNgonNguBUS.LayChiTietDonViTinhDaNgonNgu(monAn.DonViTinhMacDinh.MaDonViTinh, maNgonNgu);

                DanhMuc danhMuc = monAn.DanhMuc;
                ChiTietDanhMucDaNgonNgu ctDanhMucDaNgonNgu = ChiTietDanhMucDaNgonNguBUS.LayChiTietDanhMucDaNgonNgu(danhMuc.MaDanhMuc, maNgonNgu);

                viewModel.HinhAnh = monAn.HinhAnh;
                viewModel.MaMonAn = monAn.MaMonAn;
                viewModel.TenMonAn = ctMonAnDaNgonNgu.TenMonAn;
                viewModel.MoTaMonAn = ctMonAnDaNgonNgu.MoTaMonAn;

                viewModel.SoLuotDanhGia = monAn.SoLuotDanhGia;
                viewModel.DiemDanhGia = monAn.DiemDanhGia;

                viewModel.DonGiaMacDinh = ctMonAnDonViTinhMacDinh.DonGia;
                viewModel.TenDonViTinhMacDinh = ctDonViTinhDaNgonNguMacDinh.TenDonViTinh;

                viewModel.listTenDonViTinh = new List<string>();
                viewModel.listDonGia = new List<float>();

                List<ChiTietMonAnDonViTinh> listCTMonAnDonViTinh = ChiTietMonAnDonViTinhBUS.LayDanhSachChiTietMonAnDonViTinhTheoMonAn(monAn.MaMonAn);
                if (listCTMonAnDonViTinh != null)
                {
                    for (int i = 0; i < listCTMonAnDonViTinh.Count; ++i)
                    {
                        viewModel.listDonGia.Add(listCTMonAnDonViTinh[i].DonGia);
                        ChiTietDonViTinhDaNgonNgu ctDonViTinhDaNgonNgu = ChiTietDonViTinhDaNgonNguBUS.LayChiTietDonViTinhDaNgonNgu(listCTMonAnDonViTinh[i].DonViTinh.MaDonViTinh, maNgonNgu);
                        viewModel.listTenDonViTinh.Add(ctDonViTinhDaNgonNgu.TenDonViTinh);
                    }
                }

                viewModel.listMonLienQuan = new List<FoodGalleryItemViewModel>();

                List<ChiTietMonLienQuan> listCTMonLienQuan = ChiTietMonLienQuanBUS.LayDanhSachChiTietMonLienQuan(monAn.MaMonAn);
                if (listCTMonLienQuan != null)
                {
                    for (int i = 0; i < listCTMonLienQuan.Count; ++i)
                    {
                        try
                        {
                            FoodGalleryItemViewModel monLienQuan = GetFoodGalleryItemViewModel(listCTMonLienQuan[i].MonAnLienQuan, maNgonNgu);

                            viewModel.listMonLienQuan.Add(monLienQuan);
                        }
                        catch (Exception e)
                        {

                        }
                    }
                }

                
            }
            catch (Exception e)
            {
                viewModel = null;
            }


            return viewModel;
        }

        // Get ViewModel for displaying Food Gallery Item, get by MonAn
        private FoodGalleryItemViewModel GetFoodGalleryItemViewModel(MonAn monAn, int maNgonNgu)
        {
            ChiTietMonAnDonViTinh ctMonAnDonViTinh = ChiTietMonAnDonViTinhBUS.LayChiTietMonAnDonViTinh(monAn.MaMonAn, monAn.DonViTinhMacDinh.MaDonViTinh);
            ChiTietMonAnDaNgonNgu ctMonAnDaNgonNgu = ChiTietMonAnDaNgonNguBUS.LayChiTietMonAnDaNgonNgu(monAn.MaMonAn, maNgonNgu);
            ChiTietDonViTinhDaNgonNgu ctDonViTinhDaNgonNgu = ChiTietDonViTinhDaNgonNguBUS.LayChiTietDonViTinhDaNgonNgu(monAn.DonViTinhMacDinh.MaDonViTinh, maNgonNgu);

            FoodGalleryItemViewModel viewModel = new FoodGalleryItemViewModel();
            viewModel.HinhAnh = monAn.HinhAnh;
            viewModel.MaMonAn = monAn.MaMonAn;
            viewModel.TenMonAn = ctMonAnDaNgonNgu.TenMonAn;
            viewModel.DonGia = ctMonAnDonViTinh.DonGia;
            viewModel.TenDonViTinhMacDinh = ctDonViTinhDaNgonNgu.TenDonViTinh;

            return viewModel;
        }

    }
}