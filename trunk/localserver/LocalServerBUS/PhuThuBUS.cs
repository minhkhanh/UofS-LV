using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using LocalServerDTO;
using LocalServerDAO;

namespace LocalServerBUS
{
    public class PhuThuBUS
    {
        public static List<PhuThu> LayDanhSachPhuThu()
        {
            return PhuThuDAO.LayDanhSachPhuThu();
        }

        public static PhuThu LayPhuThu(int maPhuThu)
        {
            return PhuThuDAO.LayPhuThu(maPhuThu);
        }

        public static bool Xoa(int maPhuThu)
        {
            return PhuThuDAO.Xoa(maPhuThu);
        }

        public static bool Them(PhuThu phuThu)
        {
            return PhuThuDAO.Them(phuThu);
        }

        public static bool CapNhat(PhuThu phuThu)
        {
            return PhuThuDAO.CapNhat(phuThu);
        }

        public static List<PhuThu> LayDanhSachPhuThuApDungJson(int maOrder)
        {
            return PhuThuDAO.LayDanhSachPhuThuApDungJson(maOrder);
        }
    }
}
