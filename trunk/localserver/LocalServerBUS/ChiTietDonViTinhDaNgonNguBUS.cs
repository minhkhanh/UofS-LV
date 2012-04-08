using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using LocalServerDAO;
using LocalServerDTO;

namespace LocalServerBUS
{
    public class ChiTietDonViTinhDaNgonNguBUS
    {
        public static ChiTietDonViTinhDaNgonNgu LayChiTietDonViTinhDaNgonNgu(int maDonViTinh, int maNgonNgu)
        {
            return ChiTietDonViTinhDaNgonNguDAO.LayChiTietDonViTinhDaNgonNgu(maDonViTinh, maNgonNgu);
        }
    }
}
