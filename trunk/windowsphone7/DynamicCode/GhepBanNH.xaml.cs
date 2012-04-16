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
using DynamicCode.ServerConnect;
using System.Text;

namespace DynamicCode
{
    public partial class GhepBanNH : PhoneApplicationPage
    {
        public GhepBanNH()
        {
            InitializeComponent();
        }
                
        private string maKhuVuc = "";
        protected override void OnNavigatedTo(System.Windows.Navigation.NavigationEventArgs e)
        {                        
            base.OnNavigatedTo(e);                                                
            string msg = "";            
            if (NavigationContext.QueryString.TryGetValue("msg", out msg))
            {
                maKhuVuc = msg;
                if (DanhSachBanTrongKhuVuc == null)
                    LoadDanhSachBan(maKhuVuc);
            }
            else
            {

            }                                                            
        }
        //Chọn các bàn thuộc khu vực nào           
        private List<Ban> DanhSachBanTrongKhuVuc;
        private List<Ban> DanhSachBanCanGhep;        
        
        public void LoadDanhSachBan(string maKhuVuc)
        {
            try
            {
                if (LayDuLieuTuServer.DanhSachBanCacKhuVuc != null)
                {
                    DanhSachBanTrongKhuVuc = new List<Ban>();
                    DanhSachBanCanGhep = new List<Ban>();
                    for (int i = 0; i < LayDuLieuTuServer.DanhSachBanCacKhuVuc.Count(); i++)
                    {
                        //Chỉ add vào các bàn chưa có người ngồi để chọn ghép bàn
                        //Nếu bàn Active  == false mà muốn add vào nhóm bàn thì phải trả lại bàn trạng thái Active  == true
                        if (LayDuLieuTuServer.DanhSachBanCacKhuVuc[i]._maKhuVuc.ToString() == maKhuVuc && LayDuLieuTuServer.DanhSachBanCacKhuVuc[i].Active == true)
                        {
                            DanhSachBanTrongKhuVuc.Add(LayDuLieuTuServer.DanhSachBanCacKhuVuc[i]);
                            list_DanhSachBanKhuVuc.Items.Add(LayDuLieuTuServer.DanhSachBanCacKhuVuc[i]);
                        }
                    }
                }                                
                list_DanhSachBanKhuVuc.SelectedIndex = 0;
            }
            catch (Exception ex)
            {
            
            }                    
        }
        
        private void img_btNext_Tap(object sender, GestureEventArgs e)
        {
            if (DanhSachBanTrongKhuVuc != null && DanhSachBanTrongKhuVuc.Count != 0)
            {
              
                //Tìm vị trí bàn muốn ghép
                int maBan = (list_DanhSachBanKhuVuc.SelectedItem as Ban).MaBan;
                for (int i = 0; i < DanhSachBanTrongKhuVuc.Count; i++)
                {
                    if (DanhSachBanTrongKhuVuc[i].MaBan == maBan)
                    {
                        list_DanhSachBanGhep.Items.Add(DanhSachBanTrongKhuVuc[i]);
                        DanhSachBanCanGhep.Add(DanhSachBanTrongKhuVuc[i]);

                        //Đánh dấu đối tượng mới add phía phải
                        list_DanhSachBanGhep.SelectedIndex = DanhSachBanCanGhep.Count() - 1;

                        //Remove danh sách bàn phía trái
                        DanhSachBanTrongKhuVuc.RemoveAt(i);

                        //Remove list_ban phía trái
                        list_DanhSachBanKhuVuc.Items.Remove(list_DanhSachBanKhuVuc.SelectedItem);
                        if (list_DanhSachBanKhuVuc.Items.Count > 0)
                            list_DanhSachBanKhuVuc.SelectedIndex = 0;
                        return;
                    }
                }            	
			}
        }                                    
            
        private void img_btBack_Tap(object sender, GestureEventArgs e)
        {
            if (DanhSachBanCanGhep != null && DanhSachBanCanGhep.Count != 0)
            {
                //Tìm vị trí bàn bỏ
                int maBan = (list_DanhSachBanGhep.SelectedItem as Ban).MaBan;
                for (int i = 0; i < DanhSachBanCanGhep.Count; i++)
                {
                    if (DanhSachBanCanGhep[i].MaBan == maBan)
                    {
                        list_DanhSachBanKhuVuc.Items.Add(DanhSachBanCanGhep[i]);
                        DanhSachBanTrongKhuVuc.Add(DanhSachBanCanGhep[i]);

                        //Đánh dấu đối tượng mới add phía phải
                        list_DanhSachBanKhuVuc.SelectedIndex = DanhSachBanTrongKhuVuc.Count() - 1;

                        //Remove danh sách bàn phía trái
                        DanhSachBanCanGhep.RemoveAt(i);

                        //Remove list_ban phía phải
                        list_DanhSachBanGhep.Items.Remove(list_DanhSachBanGhep.SelectedItem);
                        if (list_DanhSachBanGhep.Items.Count > 0)
                            list_DanhSachBanGhep.SelectedIndex = 0;
                        return;
                    }
                }
            }
        }
        
        private string msg;
        private void img_btXacNhan_Tap(object sender, GestureEventArgs e)
        {
            if (DanhSachBanCanGhep.Count > 1)
            {
                msg = "";
                for (int i = 0; i <= DanhSachBanCanGhep.Count; ++i)
                {
                    if (DanhSachBanCanGhep.Count == i)
                        msg += maKhuVuc;
                    else
                        msg += DanhSachBanCanGhep[i].MaBan.ToString() + ",";
                }
                NavigationService.Navigate(new Uri("/BanChinhNH.xaml?msg=" + msg, UriKind.Relative));
            }
            else if((DanhSachBanCanGhep.Count <= 1))
            {
                MessageBox.Show("Số lượng bàn ghép > 1", "Thông báo", MessageBoxButton.OK);
            }
        }        

        private void img_btReset_Tap(object sender, GestureEventArgs e)
        {
            NavigationService.Navigate(new Uri("/BanNH.xaml?msg=" + maKhuVuc, UriKind.Relative));
        }   
    }
}