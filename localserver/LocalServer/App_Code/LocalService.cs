using System;
using System.Collections.Generic;
using System.Linq;
using System.Runtime.Serialization;
using System.ServiceModel;
using System.Text;

public class LocalService : ILocalService
{
    public int PhepCong(int a, int b)
    {
        return a + b;
    }


    public string Test()
    {
        return "Hello eMenu";
    }


    public List<BanResult> LayDanhSachBan()
    {
        return new List<BanResult>();
    }
}
