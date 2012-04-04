using System;
using System.Collections.Generic;
using System.Linq;
using System.Runtime.Serialization;
using System.ServiceModel;
using System.Text;

namespace eMenu
{
    // NOTE: You can use the "Rename" command on the "Refactor" menu to change the interface name "ILocalService" in both code and config file together.
    [ServiceContract]
    public interface ILocalService
    {
        [OperationContract]
        int PhepCong(int a, int b);
    }
}
