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
