using System;
using System.Collections.Generic;
using System.Linq;
using System.Runtime.Serialization;
using System.ServiceModel;
using System.ServiceModel.Web;
using System.Text;

[ServiceContract]
public interface ILocalService
{
    [WebInvoke(Method = "GET", UriTemplate = "cong?a={a}&b={b}")]
    [OperationContract]
    int PhepCong(int a, int b);
    [WebInvoke(Method = "GET", UriTemplate = "test")]
    [OperationContract]
    string Test();
}