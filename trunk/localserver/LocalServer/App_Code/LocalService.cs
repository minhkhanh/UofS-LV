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
}
