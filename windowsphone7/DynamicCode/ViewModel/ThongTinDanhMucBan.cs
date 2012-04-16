using System;
using System.Net;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Documents;
using System.Windows.Ink;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Animation;
using System.Windows.Shapes;

namespace DynamicCode.ViewModel
{
    public class ThongTinDanhMucBan
    {

        private string m_sDuongDanHinhAnh;

        public string DuongDanHinhAnh
        {
            get { return m_sDuongDanHinhAnh; }
            set { m_sDuongDanHinhAnh = value; }
        }

        private string m_sTen;

        public string Ten
        {
            get { return m_sTen; }
            set { m_sTen = value; }
        }

        private bool m_bChechBox;

        public bool ChechBox
        {
            get { return m_bChechBox; }
            set { m_bChechBox = value; }
        }

    }
}
