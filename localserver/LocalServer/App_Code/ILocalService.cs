using System;
using System.Collections.Generic;
using System.Linq;
using System.Runtime.Serialization;
using System.ServiceModel;
using System.ServiceModel.Activation;
using System.ServiceModel.Web;
using System.Text;
using LocalServerDTO;

[ServiceContract]
public interface ILocalService
{
    [WebInvoke(Method = "GET", UriTemplate = "cong?a={a}&b={b}")]
    [OperationContract]
    int PhepCong(int a, int b);

    [WebInvoke(Method = "GET", UriTemplate = "test")]
    [OperationContract]
    string Test();

    [WebInvoke(Method = "GET", UriTemplate = "layDanhSachKhuVuc")]
    [OperationContract]
    List<KhuVuc> LayDanhKhuVuc();

    [WebInvoke(Method = "GET", UriTemplate = "layDanhSachBan")]
    [OperationContract]
    List<Ban> LayDanhSachBan();

    [WebInvoke(Method = "GET", UriTemplate = "layDanhSachBanTheoKhuVuc?maKhuVuc={maKhuVuc}")]
    [OperationContract]
    List<Ban> LayDanhSachBanTheoKhuVuc(int maKhuVuc);


    /// <summary>
    /// Tách bàn đã ghép ra thành các bàn rời nhau
    /// </summary>
    /// <param name="maBan">Mã bàn cha đại diện cho nhóm bàn</param>
    /// <returns>Tách thành công hoặc thất bại.</returns>
    [WebInvoke(Method = "GET", UriTemplate = "tachBan?maBan={maBan}")]
    [OperationContract]
    bool TachBan(int maBan);

    [WebInvoke(Method = "GET", UriTemplate = "layDanhSachMonAn")]
    [OperationContract]
    List<MonAn> LayDanhSachMonAn();

    [WebInvoke(Method = "GET", UriTemplate = "layDanhSachFoo")]
    [OperationContract]
    List<Foo> LayDanhSachFoo();
}