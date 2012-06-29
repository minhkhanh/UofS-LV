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
using LocalServerWeb.Resources.Views.Shared;

namespace LocalServerWeb.Controllers
{
    public class FoodCategoryController : BaseController
    {
        // **************************************
        // URL: /FoodCategory
        // **************************************

        public ActionResult Index()
        {
            return RedirectToAction("Category", new { id = 0 });         
        }

        public ActionResult Category(int? id, string page)
        {
            FoodCategoryLinksViewModel foodCategoryLinksViewModel = GetFoodCategoryLinksViewModel(id??0, true);
            ViewData["foodCategoryLinksViewModel"] = foodCategoryLinksViewModel;

            FoodCategorySidebarViewModel foodCategorySidebarViewModel = GetFoodCategorySidebarViewModel(id??0, true);
            ViewData["foodCategorySidebarViewModel"] = foodCategorySidebarViewModel;

            int maNgonNgu = (Session["ngonNgu"] != null) ? ((NgonNgu)Session["ngonNgu"]).MaNgonNgu : 1;
            List<FoodGalleryItemViewModel> foodRandom = SharedCode.GetFoodRandom(20, maNgonNgu);
            ViewData["randomFoods"] = foodRandom;
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

            int maNgonNgu = (Session["ngonNgu"] != null) ? ((NgonNgu)Session["ngonNgu"]).MaNgonNgu : 1;
            List<FoodGalleryItemViewModel> foodRandom = SharedCode.GetFoodRandom(20, maNgonNgu);
            ViewData["randomFoods"] = foodRandom;

            return View(id??1);
        }

        private FoodCategoryLinksViewModel GetFoodCategoryLinksViewModel(int id, bool isCategory)
        {
            FoodCategoryLinksViewModel viewModel = new FoodCategoryLinksViewModel();
            viewModel.Names = new List<string>();
            viewModel.Ids = new List<int>();
            viewModel.IsCategories = new List<bool>();

            // Neu Id = 0
            // Hoac la chon vo danh muc None
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
                // Neu day la Mon An, hien thi no o cuoi links
                if (isCategory == false)
                {
                    MonAn monAn = MonAnBUS.LayMonAn(id); 
                    ChiTietMonAnDaNgonNgu ctMonAnDaNgonNgu = ChiTietMonAnDaNgonNguBUS.LayChiTietMonAnDaNgonNgu(monAn.MaMonAn, maNgonNgu);
                    if (ctMonAnDaNgonNgu != null)
                    {
                        viewModel.Names.Add(ctMonAnDaNgonNgu.TenMonAn);
                    }
                    else
                    {
                        viewModel.Names.Add(SharedString.NoInformation);
                    }

                    viewModel.Ids.Add(id);
                    viewModel.IsCategories.Add(false);
 
                    maDanhMuc = monAn.DanhMuc.MaDanhMuc;
                }

                // De qui tim cac danh muc cap cha
                while (DanhMucBUS.LayDanhMuc(maDanhMuc) != null)
                {
                    DanhMuc temp = DanhMucBUS.LayDanhMuc(maDanhMuc);
                    ChiTietDanhMucDaNgonNgu ctDanhMucDaNgonNgu = ChiTietDanhMucDaNgonNguBUS.LayChiTietDanhMucDaNgonNgu(maDanhMuc, maNgonNgu);

                    if (ctDanhMucDaNgonNgu != null)
                    {
                        viewModel.Names.Insert(0, ctDanhMucDaNgonNgu.TenDanhMuc);
                    }
                    else
                    {
                        viewModel.Names.Insert(0, SharedString.NoInformation);
                    }

                    viewModel.Ids.Insert(0, maDanhMuc);
                    viewModel.IsCategories.Insert(0, true);

                    // Lay danh muc Cha
                    maDanhMuc = (temp.DanhMucCha != null) ? temp.DanhMucCha.MaDanhMuc : 0;
                }
    
            }
            catch (Exception e)
            {
                viewModel.Names = new List<string>();
                viewModel.Ids = new List<int>();
                viewModel.IsCategories = new List<bool>();

                Console.WriteLine(e.Message);
            }

            // Link item dau tien la Index
            if(viewModel.Names.Count > 0 && viewModel.Names[0] != null)
                viewModel.Names[0] = FoodCategoryString.Index;


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
                // Neu ID = 0 hoac la danh Muc None
                if(id == 0 || id == 1)
                {
                    viewModel.ParentName = FoodCategoryString.Index;
                    viewModel.ParentId = 0;

                    List<DanhMuc> listDanhMuc = DanhMucBUS.LayDanhSachDanhMucRoot();
                    for (int i = 0; i < listDanhMuc.Count; ++i)
                    {
                        ChiTietDanhMucDaNgonNgu ct = ChiTietDanhMucDaNgonNguBUS.LayChiTietDanhMucDaNgonNgu(listDanhMuc[i].MaDanhMuc, maNgonNgu);
                        if (ct != null)
                        {
                            viewModel.Names.Add(ct.TenDanhMuc);
                        }
                        else
                        {
                            viewModel.Names.Add(SharedString.NoInformation);
                        }
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
                    if (ctDanhMucDaNgonNgu != null)
                    {
                        viewModel.ParentName = ctDanhMucDaNgonNgu.TenDanhMuc;
                    }
                    else
                    {
                        viewModel.ParentName = SharedString.NoInformation;
                    }
                    viewModel.ParentId = danhMuc.MaDanhMuc;

                    List<DanhMuc> listDanhMuc = DanhMucBUS.LayDanhSachDanhMucTheoDanhMucCha(danhMuc.MaDanhMuc);
                    for (int i = 0; i < listDanhMuc.Count; ++i)
                    {
                        ChiTietDanhMucDaNgonNgu ct = ChiTietDanhMucDaNgonNguBUS.LayChiTietDanhMucDaNgonNgu(listDanhMuc[i].MaDanhMuc, maNgonNgu);
                        if (ct != null)
                        {
                            viewModel.Names.Add(ct.TenDanhMuc);
                        }
                        else
                        {
                            viewModel.Names.Add(SharedString.NoInformation);
                        }
                        
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

                Console.WriteLine(e.Message);
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
                Console.WriteLine(e.Message);
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
                        Console.WriteLine(e.Message);
                    }
                           
                }
            }

            return viewModels;
        }

        // Get ViewModel for displaying Food Gallery Item, get by MonAn
        private FoodGalleryItemViewModel GetFoodGalleryItemViewModel(MonAn monAn, int maNgonNgu)
        {
            FoodGalleryItemViewModel viewModel = new FoodGalleryItemViewModel();
            viewModel.HinhAnh = monAn.HinhAnh;
            viewModel.MaMonAn = monAn.MaMonAn;

            // Hien thi Ten mon an theo ngon ngu
            ChiTietMonAnDaNgonNgu ctMonAnDaNgonNgu = ChiTietMonAnDaNgonNguBUS.LayChiTietMonAnDaNgonNgu(monAn.MaMonAn, maNgonNgu);
            if (ctMonAnDaNgonNgu != null)
            {
                viewModel.TenMonAn = ctMonAnDaNgonNgu.TenMonAn;
            }
            else
            {
                viewModel.TenMonAn = SharedString.NoInformation;
            }

            // Hien thi ten don vi tinh
            List<ChiTietMonAnDonViTinh> listCTMonAnDonViTinh = ChiTietMonAnDonViTinhBUS.LayDanhSachChiTietMonAnDonViTinhTheoMonAn(monAn.MaMonAn);
            if (listCTMonAnDonViTinh != null && listCTMonAnDonViTinh[0] != null)
            {
                viewModel.DonGia = listCTMonAnDonViTinh[0].DonGia;

                ChiTietDonViTinhDaNgonNgu ctDonViTinhDaNgonNgu = ChiTietDonViTinhDaNgonNguBUS.LayChiTietDonViTinhDaNgonNgu(listCTMonAnDonViTinh[0].DonViTinh.MaDonViTinh, maNgonNgu);
                if (ctDonViTinhDaNgonNgu != null)
                {
                    viewModel.TenDonViTinh = ctDonViTinhDaNgonNgu.TenDonViTinh;
                }
                else
                {
                    viewModel.TenDonViTinh = SharedString.NoInformation;
                }
            }

            return viewModel;
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
                viewModel.HinhAnh = monAn.HinhAnh;
                viewModel.MaMonAn = monAn.MaMonAn;
                viewModel.SoLuotDanhGia = monAn.SoLuotDanhGia;
                viewModel.DiemDanhGia = monAn.DiemDanhGia;
                if (monAn.SoLuotDanhGia != 0)
                {
                    viewModel.DiemTrungBinh = monAn.DiemDanhGia / monAn.SoLuotDanhGia;
                }
                

                // Hien thi ten mon an theo Ngon ngu hien tai
                ChiTietMonAnDaNgonNgu ctMonAnDaNgonNgu = ChiTietMonAnDaNgonNguBUS.LayChiTietMonAnDaNgonNgu(monAn.MaMonAn, maNgonNgu);
                if (ctMonAnDaNgonNgu != null)
                {
                    viewModel.TenMonAn = ctMonAnDaNgonNgu.TenMonAn;
                    viewModel.MoTaMonAn = ctMonAnDaNgonNgu.MoTaMonAn;
                }
                else
                {
                    viewModel.TenMonAn = SharedString.NoInformation;
                    viewModel.MoTaMonAn = SharedString.NoInformation;
                }

                
                // Hien thi danh sach don vi tinh
                viewModel.listTenDonViTinh = new List<string>();
                viewModel.listDonGia = new List<float>();

                List<ChiTietMonAnDonViTinh> listCTMonAnDonViTinh = ChiTietMonAnDonViTinhBUS.LayDanhSachChiTietMonAnDonViTinhTheoMonAn(monAn.MaMonAn);
                foreach (ChiTietMonAnDonViTinh ctMonAnDonViTinh in listCTMonAnDonViTinh)
                {
                    viewModel.listDonGia.Add(ctMonAnDonViTinh.DonGia);
                    ChiTietDonViTinhDaNgonNgu ctDonViTinhDaNgonNgu = ChiTietDonViTinhDaNgonNguBUS.LayChiTietDonViTinhDaNgonNgu(ctMonAnDonViTinh.DonViTinh.MaDonViTinh, maNgonNgu);
                    if (ctDonViTinhDaNgonNgu != null)
                    {
                        viewModel.listTenDonViTinh.Add(ctDonViTinhDaNgonNgu.TenDonViTinh);
                    }
                    else
                    {
                        viewModel.listTenDonViTinh.Add(SharedString.NoInformation);
                    }
                }


                // Hien thi danh sach mon lien quan
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
                            Console.WriteLine(e.Message);
                        }
                    }
                }

            }
            catch (Exception e)
            {
                viewModel = null;
                Console.WriteLine(e.Message);
            }


            return viewModel;
        }

        [AcceptVerbs("post")]
        public ActionResult RateFood(FormCollection form)
        {
            

            var rate = Convert.ToInt32(form["score"]);
            var maMonAn = Convert.ToInt32(form["maMonAn"]);
            if (Request.Cookies["rating" + maMonAn] != null)
                return Content("false");
            Response.Cookies["rating" + maMonAn].Value = DateTime.Now.ToString();
            Response.Cookies["rating" + maMonAn].Expires = DateTime.Now.AddYears(1);
            
            // Cap nhat danh gia Mon an
            MonAn monAn = MonAnBUS.LayMonAn(maMonAn);
            if (monAn != null)
            {
                monAn.SoLuotDanhGia++;
                monAn.DiemDanhGia += rate;
                MonAnBUS.CapNhat(monAn);
            }

            FoodDetailViewModel viewModel = GetFoodDetailViewModel(maMonAn);

            string a = Json(viewModel).Data.ToString();
            string b = Content("haha").Content.ToString();

            return Json(viewModel);
        }

        public static string Check(double lower, double upper, double toCheck)
        {
            return toCheck > lower && toCheck <= upper ? " checked=\"checked\"" : null;
        }
    }
}