﻿using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using LocalServerDAO;
using LocalServerDTO;

namespace LocalServerBUS
{
    public class ChiTietMonAnDonViTinhBUS
    {
        public static float LayDonGia(int maMonAn, int maDonViTinh)
        {
            return ChiTietMonAnDonViTinhDAO.LayDonGia(maMonAn, maDonViTinh);
        }
    }
}