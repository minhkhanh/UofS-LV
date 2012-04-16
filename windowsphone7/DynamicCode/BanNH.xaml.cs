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
using DynamicCode.ServerConnect;
using DynamicCode.ViewModel;
using System.Windows.Media.Imaging;

namespace DynamicCode
{
    //Quy định mỗi lần thay đổi trạng thái của bàn chỉ được 1 lần
    //Muốn thay đổi trạng thái lại thì cần Thoát và đăng nhập lại vào danh mục chỉ định
    
    public partial class BanNH : PhoneApplicationPage
    {
        public BanNH()
        {
            InitializeComponent();            
        }
        
        private string maKhuVuc;        
        protected override void OnNavigatedTo(System.Windows.Navigation.NavigationEventArgs e)
        {
            base.OnNavigatedTo(e);
            string msg = "";            
            if (NavigationContext.QueryString.TryGetValue("msg", out msg))
            {
                maKhuVuc = msg;
                KhoiTaoBanTheoKhuVuc(maKhuVuc);                
            }
            else
            {

            }            
        }
        
        //Các biến này nên khai báo new khi nào sử dụng trong hàm sẽ tốt!
        private List<Rectangle> m_lSumTable; 
        private List<TextBlock> m_lSumTextBlock;
        private List<Ban> DanhSachBanTrongKhuVuc;

        private void KhoiTaoBanTheoKhuVuc(string maKhuVuc)
        {                        
            //Chọn các bàn thuộc khu vực nào           
            DanhSachBanTrongKhuVuc = new List<Ban>();            
            m_lSumTable = new List<Rectangle>();
            m_lSumTextBlock = new List<TextBlock>();
            if (LayDuLieuTuServer.DanhSachBanCacKhuVuc != null)
            {
                for (int i = 0; i < LayDuLieuTuServer.DanhSachBanCacKhuVuc.Count(); i++)
                {
                    if (LayDuLieuTuServer.DanhSachBanCacKhuVuc[i]._maKhuVuc.ToString() == maKhuVuc)
                    {
                        DanhSachBanTrongKhuVuc.Add(LayDuLieuTuServer.DanhSachBanCacKhuVuc[i]);
                    }                                 
                }
                               
                StackPanel GeneralStack = new StackPanel();
                GeneralStack.Orientation = System.Windows.Controls.Orientation.Vertical;
                GeneralStack.Tap += new EventHandler<GestureEventArgs>(GeneralStack_Tap);
                LayoutTable.Children.Add(GeneralStack);

                int m_iSoBan = DanhSachBanTrongKhuVuc.Count();
                int m_iSoLuongBan = 0;
                int m_iCol = 3;
                int m_iRow = m_iSoBan / m_iCol + 1;
                int m_iWidthOrHeightOfRect = 155;

                //Add rectangle with Image
                ImageBrush ib = new ImageBrush();
                BitmapImage bImage = new BitmapImage(new Uri("images/Ban.jpg", UriKind.Relative));
                ib.ImageSource = bImage;

                for (int i = 0; i < m_iRow; i++)
                {
                    StackPanel stackElement = new StackPanel();
                    stackElement.Name = i.ToString();
                    stackElement.Orientation = System.Windows.Controls.Orientation.Horizontal;
                    stackElement.HorizontalAlignment = System.Windows.HorizontalAlignment.Left;
                    GeneralStack.Children.Add(stackElement);

                    for (int j = 0; j < m_iCol; j++)
                    {
                        if (m_iSoLuongBan < m_iSoBan)
                        {
                            Rectangle rectBan = new Rectangle();
                            rectBan.Name = DanhSachBanTrongKhuVuc[m_iSoLuongBan].MaBan.ToString() + "," + DanhSachBanTrongKhuVuc[m_iSoLuongBan].TenBan.ToString() + "," + DanhSachBanTrongKhuVuc[m_iSoLuongBan].Active.ToString();
                            rectBan.Height = m_iWidthOrHeightOfRect;
                            rectBan.Width = m_iWidthOrHeightOfRect;
                            rectBan.Fill = ib;
                            rectBan.RadiusX = 20;
                            rectBan.RadiusY = 20;
                            rectBan.StrokeThickness = 2;                            
                            if (DanhSachBanTrongKhuVuc[m_iSoLuongBan].Active == true )
                                rectBan.Stroke = new SolidColorBrush(Colors.Green);
                            //Quy định bàn nào đã ghép thì đều ở chế độ active == false
                            else if (DanhSachBanTrongKhuVuc[m_iSoLuongBan].Active == false || DanhSachBanTrongKhuVuc[m_iSoLuongBan]._maBanChinh != null)
                                rectBan.Stroke = new SolidColorBrush(Colors.Red);
                                                        
                            TextBlock textBlockName = new TextBlock();
                            textBlockName.Text = DanhSachBanTrongKhuVuc[m_iSoLuongBan].TenBan;
                            
                            //Thể hiện các bàn đã ghép vs 
                            if(DanhSachBanTrongKhuVuc[m_iSoLuongBan]._maBanChinh !=  null)
                               textBlockName.Text += "-" + DanhSachBanTrongKhuVuc[m_iSoLuongBan]._maBanChinh.ToString();                                                        
                            
                            //Báo bàn có khách hay không
                            if (DanhSachBanTrongKhuVuc[m_iSoLuongBan].Active == true)
                                textBlockName.Foreground = new SolidColorBrush(Colors.Green);
                            else if (DanhSachBanTrongKhuVuc[m_iSoLuongBan].Active == false)
                                textBlockName.Foreground = new SolidColorBrush(Colors.Red);                            
                            textBlockName.HorizontalAlignment = System.Windows.HorizontalAlignment.Center;
                            textBlockName.FontSize = 30;

                            m_lSumTextBlock.Add(textBlockName);

                            StackPanel stackElementChil = new StackPanel();
                            stackElementChil.Orientation = System.Windows.Controls.Orientation.Vertical;
                            stackElementChil.Children.Add(rectBan);
                            stackElementChil.Children.Add(textBlockName);

                            m_iSoLuongBan++;
                            Rectangle rectSide = new Rectangle();                            
                            rectSide.Height = m_iWidthOrHeightOfRect;
                            rectSide.Width = 5;
                            rectSide.Fill = new SolidColorBrush(Colors.Black);
                            
                            stackElement.Children.Add(stackElementChil);
                            if (m_iSoLuongBan%3 != 0)
                                stackElement.Children.Add(rectSide);
                            
                            m_lSumTable.Add(rectBan);
                            
                        }
                        else if (m_iSoLuongBan > m_iSoBan)
                        {
                            return;
                        }
                    }
                }
            }
        }

        private void GeneralStack_Tap(object sender, GestureEventArgs e)
        {
            for (int i = 0; i < m_lSumTable.Count(); i++)
            {
               if (e.OriginalSource == m_lSumTable[i])
                {
                    string[] thongTinBan = m_lSumTable[i].Name.Split(',');
                    NavigationService.Navigate(new Uri("/Order.xaml?msg=" + thongTinBan[1], UriKind.Relative));
                }
            }
        }

        private void ApplicationBarIconButton_Click(object sender, EventArgs e)
        {
            NavigationService.Navigate(new Uri("/GhepBanNH.xaml?msg=" + maKhuVuc, UriKind.Relative));
        }
        
        private string maBan = "";
        private string tenBan = "";
        private bool trangThai;
        private int thuTuBan = -1;
        private List<int> danhSachThuTuBan;

        private void ScrollViewTable_Hold(object sender, GestureEventArgs e)
        {                                    
            for (int i = 0; i < m_lSumTable.Count(); i++)
            {
                
                //Cực kỳ quan trọng vì phân tích bàn tại đoạn code này
                if (e.OriginalSource == m_lSumTable[i])
                {
                    thuTuBan = i;
                    string[] thongTinBan = m_lSumTable[i].Name.Split(',');
                    maBan = thongTinBan[0];
                    tenBan = thongTinBan[1];
                    trangThai = Convert.ToBoolean(thongTinBan[2]);
                    if (maBan != "")
                    {
                        ScrollViewTable.IsHitTestVisible = false;
                        AddItemForListDanhMuc(maBan, tenBan, trangThai);
                    }
                    return;
                }                
            }                        
        }
          
        private void AddItemForListDanhMuc(string maBan, string tenBan, bool trangThai)
        {
            list_ChooseOptionBan.Items.Clear();
            text_DanhMuc.Text = tenBan;              
            ThongTinDanhMucBan thongTinDanhMucActive = new ThongTinDanhMucBan();
            ThongTinDanhMucBan thongTinDanhMucDeActive = new ThongTinDanhMucBan();
            ThongTinDanhMucBan thongTinDanhMucInfor = new ThongTinDanhMucBan();
            ThongTinDanhMucBan thongTinDanhMucTachBan = new ThongTinDanhMucBan();

            thongTinDanhMucActive.DuongDanHinhAnh = "/DynamicCode;component/images/bt_Check.jpg";
            thongTinDanhMucDeActive.DuongDanHinhAnh = "/DynamicCode;component/images/bt_Uncheck.jpg";
            thongTinDanhMucInfor.DuongDanHinhAnh = "/DynamicCode;component/images/bt_ThongTin.png";
            thongTinDanhMucTachBan.DuongDanHinhAnh = "/DynamicCode;component/images/bt_Tachban.jpg";

            thongTinDanhMucActive.Ten = "Bàn trống";
            thongTinDanhMucDeActive.Ten = "Bàn bận";
            thongTinDanhMucInfor.Ten = "Thông tin bàn";
            thongTinDanhMucTachBan.Ten = "Tách bàn";
            
            list_ChooseOptionBan.Items.Add(thongTinDanhMucInfor);        
            list_ChooseOptionBan.Items.Add(thongTinDanhMucTachBan);        
            
            //Tùy theo loại trạng thái mà add hình vs tên phù hợp
            if (trangThai == true )
                list_ChooseOptionBan.Items.Add(thongTinDanhMucActive);
            else if (trangThai == false )
                list_ChooseOptionBan.Items.Add(thongTinDanhMucDeActive);       
            
        }

        private void list_ChooseOptionBan_SelectionChanged(object sender, SelectionChangedEventArgs e)
        {                      
            if (list_ChooseOptionBan.SelectedIndex == 0)
            {
                MessageBox.Show(tenBan , "Thông tin bàn" , MessageBoxButton.OK);
            }
            
            else if (list_ChooseOptionBan.SelectedIndex == 1)
            {
                if (DanhSachBanTrongKhuVuc[thuTuBan]._maBanChinh != null)
                {
                    MessageBox.Show("Đây không phải bàn chính để tách bàn", "Thông báo", MessageBoxButton.OK);
                }
                
                
                //Nếu các bàn con đã tự chuyển đổi sang trống hết thì bàn chính ko là bàn chính nữa mà tự động sẽ là bàn lẽ 
                else if (DanhSachBanTrongKhuVuc[thuTuBan]._maBanChinh == null)
                {                    
                    for (int i = 0; i < DanhSachBanTrongKhuVuc.Count; i++)
                    {
                        //Trường hợp bàn này là bàn chính
                        if (DanhSachBanTrongKhuVuc[i]._maBanChinh == Convert.ToInt32(maBan))
                        {                            
                            //Chỉ xử lý lưu trữ khi nào xác nhận OK.
                            MessageBoxResult ketQua = MessageBox.Show("Bạn muốn tách bàn? ", "Thông báo", MessageBoxButton.OKCancel);
                            if (ketQua == MessageBoxResult.OK)
                            {
                                                       

                                //Lưu trữ các vị trí bàn trên giao diện
                                LuuTruDanhSachThuTuBanTrongNhom(Convert.ToInt32(maBan));
                                for (int j = 0; j < LayDuLieuTuServer.DanhSachBanCacKhuVuc.Count(); j++)
                                {
                                    //Cải tiến là lấy danh sách bàn thuộc khu vực để thay đổi
                                    //Khi lưu trữ dữ liệu phải xem xét lại vấn đề này
                                    if (LayDuLieuTuServer.DanhSachBanCacKhuVuc[j]._maBanChinh == Convert.ToInt32(maBan))
                                    {
                                        LayDuLieuTuServer.DanhSachBanCacKhuVuc[j].Active = true;
                                        LayDuLieuTuServer.DanhSachBanCacKhuVuc[j]._maBanChinh = null;
                                    }

                                    //Tìm được bàn chính
                                    else if (LayDuLieuTuServer.DanhSachBanCacKhuVuc[j].MaBan == Convert.ToInt32(maBan))
                                    {
                                        LayDuLieuTuServer.DanhSachBanCacKhuVuc[j].Active = true;
                                        LayDuLieuTuServer.DanhSachBanCacKhuVuc[j]._maBanChinh = null;                           
                                    }
                                }
                                                                                                                             
                                //Chuyển sang trạng thái true vì bàn chính lúc này đã free
                                trangThai = true;
                                DanhSachBanTrongKhuVuc[thuTuBan].Active = true;

                                //Thay đổi diện mạo cho thông tin danh mục khi bàn chính được tách
                                ThongTinDanhMucBan thongTinDanhMucActive = new ThongTinDanhMucBan();
                                thongTinDanhMucActive.DuongDanHinhAnh = "/DynamicCode;component/images/bt_Check.jpg";
                                thongTinDanhMucActive.Ten = "Bàn trống";
                                list_ChooseOptionBan.Items.Add(thongTinDanhMucActive);  
                                list_ChooseOptionBan.Items.RemoveAt(2);                                
                            }
                            else if (ketQua == MessageBoxResult.Cancel)
                            {
                                
                            }
                            return;
                        }                                         
                    }
                    //Trường hợp bàn này là bàn lẽ
                    MessageBox.Show("Đây không phải bàn chính để tách bàn", "Thông báo", MessageBoxButton.OK);
                }

            }

            else if (list_ChooseOptionBan.SelectedIndex == 2)
            {
                
                //Kiểm tra xem bàn này có phải bàn chính hay không
                if (KiemTraBanChinh(Convert.ToInt32(maBan)) == false)
                {
                    ThongTinDanhMucBan thongTinDanhMucActive = new ThongTinDanhMucBan();
                    ThongTinDanhMucBan thongTinDanhMucDeActive = new ThongTinDanhMucBan();
                    thongTinDanhMucActive.DuongDanHinhAnh = "/DynamicCode;component/images/bt_Check.jpg";
                    thongTinDanhMucDeActive.DuongDanHinhAnh = "/DynamicCode;component/images/bt_Uncheck.jpg";
                    thongTinDanhMucActive.Ten = "Bàn trống";
                    thongTinDanhMucDeActive.Ten = "Bàn bận";
                    
                    //Bàn này là bàn bận
                    //Chuyển sang trạng thái bàn trống
                    if (trangThai == false)
                    {
                        MessageBoxResult m = MessageBox.Show("Bàn trống", "Chuyển trạng thái bàn", MessageBoxButton.OKCancel);
                        if (m == MessageBoxResult.OK)
                        {
                            list_ChooseOptionBan.Items.Add(thongTinDanhMucActive);                                                        

                            //Do bàn busy có 2 loại là lẻ vs nhóm nên khi chuyển về phải đặt _maBanChinh = null
                            for (int i = 0; i < LayDuLieuTuServer.DanhSachBanCacKhuVuc.Count(); i++)
                            {
                                if (LayDuLieuTuServer.DanhSachBanCacKhuVuc[i].MaBan == Convert.ToInt32(maBan))
                                {
                                    //Thay đổi trong csdl chính
                                    LayDuLieuTuServer.DanhSachBanCacKhuVuc[i].Active = true;
                                    LayDuLieuTuServer.DanhSachBanCacKhuVuc[i]._maBanChinh = null;
                                    
                                    //Thay đổi csdl tạm thời
                                    DanhSachBanTrongKhuVuc[thuTuBan].Active = true;
                                    list_ChooseOptionBan.Items.RemoveAt(2);
                                    return;
                                }
                            }
                        }
                    }

                    //Bàn này là bàn trống
                    //Chuyển sang trạng thái bàn bận
                    else if (trangThai == true)
                    {
                        MessageBoxResult m = MessageBox.Show("Bàn bận", "Chuyển trạng thái bàn", MessageBoxButton.OKCancel);
                        if (m == MessageBoxResult.OK)
                        {                            
                            list_ChooseOptionBan.Items.Add(thongTinDanhMucDeActive);                            
                            //trangThai = false;

                            for (int i = 0; i < LayDuLieuTuServer.DanhSachBanCacKhuVuc.Count(); i++)
                            {
                                if (LayDuLieuTuServer.DanhSachBanCacKhuVuc[i].MaBan == Convert.ToInt32(maBan))
                                {
                                    
                                    //Thay đổi trong csdl chính
                                    LayDuLieuTuServer.DanhSachBanCacKhuVuc[i].Active = false;
                                    LayDuLieuTuServer.DanhSachBanCacKhuVuc[i]._maBanChinh = null;
                                    
                                    //Thay đổi csdl tạm thời
                                    DanhSachBanTrongKhuVuc[thuTuBan].Active = false;
                                    list_ChooseOptionBan.Items.RemoveAt(2);
                                    return;
                                }
                            }
                         
                        }
                    }
                }
            }

            if (KiemTraBanChinh(Convert.ToInt32(maBan)) == true)
            {
                MessageBox.Show("Bàn chính không đổi trạng thái trống được", "Thông báo", MessageBoxButton.OK);
            }
        }

        private void LuuTruDanhSachThuTuBanTrongNhom(int maBanChinh)
        {
            danhSachThuTuBan = new List<int>();
            for (int i = 0; i < DanhSachBanTrongKhuVuc.Count(); i++)
            {
                //Lấy thứ tự bàn chính
                //if (DanhSachBanTrongKhuVuc[i].MaBan == maBanChinh)
                //{
                //    danhSachThuTuBan.Add(i);
                //}
                
                //Lấy thứ tự các bàn con
                if (DanhSachBanTrongKhuVuc[i]._maBanChinh == maBanChinh)
                {
                    danhSachThuTuBan.Add(i);
                }
            }
        }

        private bool KiemTraBanChinh(int maBan)
        {
            bool ketQua = false;
            for (int i = 0; i < DanhSachBanTrongKhuVuc.Count(); i++)
            {
                if (DanhSachBanTrongKhuVuc[i]._maBanChinh == maBan)
                    return ketQua = true;
            }
            return ketQua;
        }


        private void btThoat_Tap(object sender, GestureEventArgs e)
        {
            ScrollViewTable.IsHitTestVisible = true;
            try
            {
                
                //Chuyển đổi màu của các bàn lẻ theo yêu cầu        
                //Đã lưu bàn chính
                if (DanhSachBanTrongKhuVuc[thuTuBan].Active == true)
                {
                    //Đổi sang màu xanh
                    m_lSumTable[thuTuBan].Stroke = new SolidColorBrush(Colors.Green);
                    m_lSumTextBlock[thuTuBan].Foreground = new SolidColorBrush(Colors.Green);
                    m_lSumTextBlock[thuTuBan].Text = "";
                    m_lSumTextBlock[thuTuBan].Text = tenBan;
                    m_lSumTable[thuTuBan].Name = DanhSachBanTrongKhuVuc[thuTuBan].MaBan.ToString() + "," + DanhSachBanTrongKhuVuc[thuTuBan].TenBan.ToString() + "," + DanhSachBanTrongKhuVuc[thuTuBan].Active.ToString();                              
                }
                    
                else if (DanhSachBanTrongKhuVuc[thuTuBan].Active == false)
                {
                    m_lSumTable[thuTuBan].Stroke = new SolidColorBrush(Colors.Red);
                    m_lSumTextBlock[thuTuBan].Foreground = new SolidColorBrush(Colors.Red);
                    m_lSumTable[thuTuBan].Name = DanhSachBanTrongKhuVuc[thuTuBan].MaBan.ToString() + "," + DanhSachBanTrongKhuVuc[thuTuBan].TenBan.ToString() + "," + DanhSachBanTrongKhuVuc[thuTuBan].Active.ToString();
                }
                
                //Chuyển đổi màu của các bàn con
                if (danhSachThuTuBan != null)
                {
                    for (int i = 0; i < danhSachThuTuBan.Count; i++)
                    {
                        m_lSumTable[danhSachThuTuBan[i]].Stroke = new SolidColorBrush(Colors.Green);
                        m_lSumTextBlock[danhSachThuTuBan[i]].Foreground = new SolidColorBrush(Colors.Green);
                        m_lSumTextBlock[danhSachThuTuBan[i]].Text = "";
                        m_lSumTextBlock[danhSachThuTuBan[i]].Text = DanhSachBanTrongKhuVuc[danhSachThuTuBan[i]].TenBan;
                        DanhSachBanTrongKhuVuc[danhSachThuTuBan[i]]._maBanChinh = null;
                        DanhSachBanTrongKhuVuc[danhSachThuTuBan[i]].Active = true;
                        m_lSumTable[danhSachThuTuBan[i]].Name = DanhSachBanTrongKhuVuc[danhSachThuTuBan[i]].MaBan.ToString() + "," + DanhSachBanTrongKhuVuc[danhSachThuTuBan[i]].TenBan.ToString() + "," + DanhSachBanTrongKhuVuc[danhSachThuTuBan[i]].Active.ToString();                        
                    }
                    danhSachThuTuBan = null;
                }                
            }
            catch (Exception ex)
            {

            }
        }        


        private void ApplicationBarIconButton_Click_1(object sender, EventArgs e)
        {
            NavigationService.Navigate(new Uri("/KhuVucNH.xaml",UriKind.Relative));
        }        
    }
}