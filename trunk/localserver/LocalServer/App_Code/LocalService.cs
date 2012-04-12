using System;
using System.Collections.Generic;
using System.Configuration;
using System.Linq;
using System.Runtime.Serialization;
using System.ServiceModel;
using System.ServiceModel.Activation;
using System.Text;
using LocalServerBUS;
using LocalServerDTO;

[AspNetCompatibilityRequirements(RequirementsMode = AspNetCompatibilityRequirementsMode.Allowed)]
public class LocalService : ILocalService
{
    public LocalService()
    {
        string strConn = ConfigurationManager.ConnectionStrings["ApplicationServices"].ConnectionString;
        ThucDonDienTuBUS.KhoiTao(strConn);
    }

    public int PhepCong(int a, int b)
    {
        return a + b;
    }


    public string Test()
    {
        return "Hello eMenu";
    }

    public List<KhuVuc> LayDanhKhuVuc()
    {
        return KhuVucBUS.LayDanhSachKhuVuc();
    }


    public List<Ban> LayDanhSachBan()
    {
        var listBan = new List<Ban>();
        try
        {
            listBan = BanBUS.LayDanhSachBan();
        }
        catch (Exception e)
        {
            Console.Error.WriteLine(e.Message);
        }
        return listBan;
    }

    public List<Ban> LayDanhSachBanChinh()
    {
        try
        {
            return BanBUS.LayDanhSachBanChinh();
        }
        catch (Exception e)
        {
            Console.Error.WriteLine(e.Message);
        }
        return null;
    }

    public List<Ban> LayDanhSachBanThuocBanChinh(int maBanChinh)
    {
        try
        {
            return BanBUS.LayDanhSachBanThuocBanChinh(maBanChinh);
        }
        catch (Exception e)
        {
            Console.Error.WriteLine(e.Message);
        }
        return null;
    }

    public List<Ban> LayDanhSachBanTheoKhuVuc(int maKhuVuc)
    {
        try
        {
            return BanBUS.LayDanhSachBan(maKhuVuc);
        }
        catch (Exception e)
        {
            Console.Error.WriteLine(e.Message);
        }
        return null;        
    }

    public bool TachBan(int maBan)
    {
        try
        {
            return BanBUS.TachBan(maBan);
        }
        catch (Exception e)
        {
            Console.Error.WriteLine(e.Message);
        }
        return false;
    }

    public bool GhepBan(RequestGhepBan request)
    {
        try
        {
            return BanBUS.GhepBan(request);
        }
        catch (Exception e)
        {
            Console.Error.WriteLine(e.Message);
        }
        return false;        
    }

    public List<MonAn> LayDanhSachMonAn()
    {
        var listMonAn = new List<MonAn>();
        try
        {
            listMonAn = MonAnBUS.LayDanhSachMonAn();
        }
        catch (Exception e)
        {
            Console.Error.WriteLine(e.Message);
        }
        return listMonAn;
    }

    public List<MonAn> LayDanhSachMonAnTheoDanhMuc(int maDanhMuc)
    {
        var listMonAn = new List<MonAn>();
        try
        {
            listMonAn = MonAnBUS.LayDanhSachMonAnTheoDanhMuc(maDanhMuc);
        }
        catch (Exception e)
        {
            Console.Error.WriteLine(e.Message);
        }
        return listMonAn;

    }

    public MonAn LayMonAn(int maMonAn)
    {
        var monAn = new MonAn();
        try
        {
            monAn = MonAnBUS.LayMonAn(maMonAn);
        }
        catch (Exception e)
        {
            Console.Error.WriteLine(e.Message);
        }
        return monAn;
    }

    public List<Foo> LayDanhSachFoo()
    {
        var listMonAn = new List<Foo>();
        try
        {
            listMonAn = FooBUS.LayDanhSachFoo();
        }
        catch (Exception e)
        {
            Console.Error.WriteLine(e.Message);
        }
        return listMonAn;
    }

    public List<DanhMuc> LayDanhSachDanhMuc()
    {
        var listDanhMuc = new List<DanhMuc>();
        try
        {
            listDanhMuc = DanhMucBUS.LayDanhSachDanhMuc();
        }
        catch (Exception e)
        {
            Console.Error.WriteLine(e.Message);
        }
        return listDanhMuc;
    }

    public ChiTietDanhMucDaNgonNgu LayChiTietDanhMucDaNgonNgu(int maDanhMuc, int maNgonNgu)
    {
        var ct = new ChiTietDanhMucDaNgonNgu();
        try
        {
            ct = ChiTietDanhMucDaNgonNguBUS.LayChiTietDanhMucDaNgonNgu(maDanhMuc, maNgonNgu);
        }
        catch (Exception e)
        {
            Console.Error.WriteLine(e.Message);
        }
        return ct;
    }

    public ChiTietDonViTinhDaNgonNgu LayChiTietDonViTinhDaNgonNgu(int maDonViTinh, int maNgonNgu)
    {
        var ct = new ChiTietDonViTinhDaNgonNgu();
        try
        {
            ct = ChiTietDonViTinhDaNgonNguBUS.LayChiTietDonViTinhDaNgonNgu(maDonViTinh, maNgonNgu);
        }
        catch (Exception e)
        {
            Console.Error.WriteLine(e.Message);
        }
        return ct;
    }

    public ChiTietMonAnDaNgonNgu LayChiTietMonAnDaNgonNgu(int maMonAn, int maNgonNgu)
    {
        var ct = new ChiTietMonAnDaNgonNgu();
        try
        {
            ct = ChiTietMonAnDaNgonNguBUS.LayChiTietMonAnDaNgonNgu(maMonAn, maNgonNgu);
        }
        catch (Exception e)
        {
            Console.Error.WriteLine(e.Message);
        }
        return ct;
    }

    public float LayChiTietMonAnDonViTinhDonGia(int maMonAn, int maDonViTinh)
    {
        float donGia = -1;
        try
        {
            donGia = ChiTietMonAnDonViTinhBUS.LayDonGia(maMonAn, maDonViTinh);
        }
        catch (Exception e)
        {
            Console.Error.WriteLine(e.Message);
        }
        return donGia;
    }
}
