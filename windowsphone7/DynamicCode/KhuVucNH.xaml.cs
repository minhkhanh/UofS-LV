using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Animation;
using System.Windows.Shapes;
using Microsoft.Phone.Controls;

using DynamicCode.ViewModel;
using System.Windows.Media.Imaging;
using System.Runtime.Serialization;
using DynamicCode.ServerConnect;

namespace DynamicCode
{
    public partial class KhuVucNH : PhoneApplicationPage
    {
        public KhuVucNH()
        {
            InitializeComponent();
            LoadKhuVucBan();            
        }

        public List<KhuVuc> DanhSachKhuVuc()
        {
            List<KhuVuc> DanhSachKhuVuc = new List<KhuVuc>();
            try
            {                
                if (LayDuLieuTuServer.DachSachCacKhuVuc.Count() > 0)
                {
                    for (int i = 0; i < LayDuLieuTuServer.DachSachCacKhuVuc.Count(); i++)
                    {
                        DanhSachKhuVuc.Add(LayDuLieuTuServer.DachSachCacKhuVuc[i]);
                    }
                }               
            }
            catch
            {                
                DanhSachKhuVuc = null;
            }
            return DanhSachKhuVuc;
        }

        List<Rectangle> m_lSumKhuVuc = new List<Rectangle>();
        public void LoadKhuVucBan()
        {
            if(DanhSachKhuVuc() != null)
            {
                int m_iSoKhuVuc = DanhSachKhuVuc().Count();
                int m_iCol = 2;
                int m_iRow = m_iSoKhuVuc / m_iCol + 1;
                int m_iWidthOrHeightOfRect = 235;
                int m_iSoLuongKhuVuc = 0;
                //Add rectangle with Image
                ImageBrush ib = new ImageBrush();
                BitmapImage bImage = new BitmapImage(new Uri("images/KhuVuc.jpg", UriKind.Relative));
                ib.ImageSource = bImage;

                StackPanel GeneralStack = new StackPanel();
                GeneralStack.Orientation = System.Windows.Controls.Orientation.Vertical;
                GeneralStack.Tap += new EventHandler<GestureEventArgs>(GeneralStack_Tap);
                LayoutTable.Children.Add(GeneralStack);

                //Add Danh Sach Khu vuc
                for (int i = 0; i < m_iRow; i++)
                {
                    StackPanel stackElement = new StackPanel();
                    stackElement.Name = i.ToString();
                    stackElement.Orientation = System.Windows.Controls.Orientation.Horizontal;
                    stackElement.HorizontalAlignment = System.Windows.HorizontalAlignment.Left;
                    GeneralStack.Children.Add(stackElement);

                    for (int j = 0; j < m_iCol; j++)
                    {
                        if (m_iSoLuongKhuVuc < m_iSoKhuVuc)
                        {
                            //Image cho khu vực
                            Rectangle rectKhuVuc = new Rectangle();
                            rectKhuVuc.Name = DanhSachKhuVuc()[m_iSoLuongKhuVuc].MaKhuVuc.ToString();
                            rectKhuVuc.Height = m_iWidthOrHeightOfRect;
                            rectKhuVuc.Width = m_iWidthOrHeightOfRect;
                            rectKhuVuc.Fill = ib;
                            rectKhuVuc.RadiusX = 20;
                            rectKhuVuc.RadiusY = 20;
                            rectKhuVuc.StrokeThickness = 3;
                            rectKhuVuc.Stroke = new SolidColorBrush(Colors.Green);

                            //Tên khu vực
                            TextBlock textBlockName = new TextBlock();
                            textBlockName.Text = DanhSachKhuVuc()[m_iSoLuongKhuVuc].TenKhuVuc;
                            textBlockName.Foreground = new SolidColorBrush(Colors.Green);
                            textBlockName.HorizontalAlignment = System.Windows.HorizontalAlignment.Center;
                            textBlockName.FontSize = 30;                            

                            //Stack cho 1 khu vực 
                            StackPanel stackElementChil = new StackPanel();
                            stackElementChil.Orientation = System.Windows.Controls.Orientation.Vertical;
                            stackElementChil.Children.Add(rectKhuVuc);
                            stackElementChil.Children.Add(textBlockName);
                            stackElement.Children.Add(stackElementChil);
                            m_lSumKhuVuc.Add(rectKhuVuc);
                            m_iSoLuongKhuVuc++;

                            Rectangle rectSide = new Rectangle();
                            rectSide.Height = m_iWidthOrHeightOfRect;
                            rectSide.Width = 5;
                            rectSide.Fill = new SolidColorBrush(Colors.Black);
                            if (m_iSoLuongKhuVuc %2 != 0)
                                stackElement.Children.Add(rectSide);

                        }
                        else if (m_iSoLuongKhuVuc > m_iSoKhuVuc)
                        {
                            return;
                        }
                    }
                }
            }
        }

        private void GeneralStack_Tap(object sender, GestureEventArgs e)
        {
            for (int i = 0; i < m_lSumKhuVuc.Count(); i++)
            {
                if (e.OriginalSource == m_lSumKhuVuc[i])
                {
                    NavigationService.Navigate(new Uri("/BanNH.xaml?msg=" + m_lSumKhuVuc[i].Name, UriKind.Relative));
                }
            }
        }

        private void ApplicationBarIconButton_Click(object sender, EventArgs e)
        {
            NavigationService.Navigate(new Uri("/ServerConnect/LayDuLieuTuServer.xaml",UriKind.Relative));
        }
    }
}