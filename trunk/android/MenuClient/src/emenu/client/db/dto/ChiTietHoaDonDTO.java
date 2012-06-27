package emenu.client.db.dto;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import emenu.client.menu.util.XmlSerializerWrapper;

public class ChiTietHoaDonDTO {
    public static final String TABLE_NAME = "ChiTietHoaDon";

    public static final String CL_DON_GIA_LUU_TRU = "DonGiaLuuTru";
    public static final String CL_GIA_TRI_KHUYEN_MAI_LUU_TRU = "GiaTriKhuyenMaiLuuTru";
    public static final String CL_MA_DON_VI_TINH = "MaDonViTinh";
    public static final String CL_MA_CHI_TIET = "MaChiTietHoaDon";
    public static final String CL_MA_HOA_DON = "MaHoaDon";
    public static final String CL_MA_KHUYEN_MAI = "MaKhuyenMai";
    public static final String CL_MA_MON_AN = "MaMonAn";
    public static final String CL_SO_LUONG = "SoLuong";
    public static final String CL_THANH_TIEN = "ThanhTien";

    private Integer mMaChiTietHoaDon = -1;
    private Integer mMaHoaDon;
    private Integer mSoLuong = 0;
    private Integer mDonGiaLuuTru = 0;
    private Integer mMaMonAn;
    private Integer mMaDonViTinh;
    private Integer mThanhTien = 0;
    private Integer mGiaTriKhuyenMaiLuuTru = 0;
    private Integer mMaKhuyenMai;

    public static final ChiTietHoaDonDTO fromXml(XmlPullParser parser) {
        ChiTietHoaDonDTO obj = null;

        try {
            int type = parser.getEventType();
            String tag = "";
            String text;

            while (type != XmlPullParser.END_DOCUMENT) {
                switch (type) {
                    case XmlPullParser.START_TAG:
                        tag = parser.getName();
                        if (tag.compareTo(TABLE_NAME) == 0) {
                            obj = new ChiTietHoaDonDTO();
                        }
                        break;

                    case XmlPullParser.END_TAG:
                        tag = parser.getName();
                        if (tag.compareTo(TABLE_NAME) == 0) {
                            return obj;
                        }
                        break;

                    case XmlPullParser.TEXT:
                        text = parser.getText();
                        if (text.trim().length() == 0) {
                            break;
                        }
                        if (tag.compareTo(CL_MA_CHI_TIET) == 0) {
                            obj.mMaChiTietHoaDon = Integer.valueOf(text);
                        } else if (tag.compareTo(CL_DON_GIA_LUU_TRU) == 0) {
                            obj.mDonGiaLuuTru = Integer.valueOf(text);
                        } else if (tag.compareTo(CL_GIA_TRI_KHUYEN_MAI_LUU_TRU) == 0) {
                            obj.mGiaTriKhuyenMaiLuuTru = Integer.valueOf(text);
                        } else if (tag.compareTo(CL_MA_DON_VI_TINH) == 0) {
                            obj.mMaDonViTinh = Integer.valueOf(text);
                        } else if (tag.compareTo(CL_MA_HOA_DON) == 0) {
                            obj.mMaHoaDon = Integer.valueOf(text);
                        } else if (tag.compareTo(CL_MA_KHUYEN_MAI) == 0) {
                            obj.mMaKhuyenMai = Integer.valueOf(text);
                        } else if (tag.compareTo(CL_MA_MON_AN) == 0) {
                            obj.mMaMonAn = Integer.valueOf(text);
                        } else if (tag.compareTo(CL_SO_LUONG) == 0) {
                            obj.mSoLuong = Integer.valueOf(text);
                        } else if (tag.compareTo(CL_THANH_TIEN) == 0) {
                            obj.mThanhTien = Integer.valueOf(text);
                        }
                        break;

                    default:
                        break;
                }

                type = parser.next();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static final List<ChiTietHoaDonDTO> fromXmlArray(String xmlData) {
        List<ChiTietHoaDonDTO> list = new ArrayList<ChiTietHoaDonDTO>();

        try {
            XmlPullParserFactory parserFactory = XmlPullParserFactory.newInstance();
            XmlPullParser parser = parserFactory.newPullParser();
            parser.setInput(new StringReader(xmlData));
            int type = parser.getEventType();
            while (type != XmlPullParser.END_DOCUMENT) {
                ChiTietHoaDonDTO obj = ChiTietHoaDonDTO.fromXml(parser);
                if (obj != null) {
                    list.add(obj);
                }

                type = parser.next();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (list.size() == 0) {
            return null;
        }

        return list;
    }

    public static final String toXmlArray(List<ChiTietHoaDonDTO> list) {
        XmlSerializerWrapper serializer = new XmlSerializerWrapper();

        try {
            serializer.startDocument();
            serializer.startTag("ArrayOf" + TABLE_NAME);

            for (ChiTietHoaDonDTO c : list) {
                serializer.startTag(TABLE_NAME);
                serializer.writeSimpleElement(CL_DON_GIA_LUU_TRU, c.mDonGiaLuuTru);
                serializer.writeSimpleElement(CL_GIA_TRI_KHUYEN_MAI_LUU_TRU,
                        c.mGiaTriKhuyenMaiLuuTru);
                serializer.writeSimpleElement(CL_MA_CHI_TIET, c.mMaChiTietHoaDon);
                serializer.writeSimpleElement(CL_MA_DON_VI_TINH, c.mMaDonViTinh);
                serializer.writeSimpleElement(CL_MA_HOA_DON, c.mMaHoaDon);
                serializer.writeSimpleElement(CL_MA_KHUYEN_MAI, c.mMaKhuyenMai);
                serializer.writeSimpleElement(CL_MA_MON_AN, c.mMaMonAn);
                serializer.writeSimpleElement(CL_SO_LUONG, c.mSoLuong);
                serializer.writeSimpleElement(CL_THANH_TIEN, c.mThanhTien);
                serializer.endTag(TABLE_NAME);
            }

            serializer.endTag("ArrayOf" + TABLE_NAME);
            serializer.endDocument();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return serializer.toString();
    }

    public Integer getMaHoaDon() {
        return mMaHoaDon;
    }

    public void setMaHoaDon(Integer maHoaDon) {
        mMaHoaDon = maHoaDon;
    }

    public Integer getSoLuong() {
        return mSoLuong;
    }

    public void setSoLuong(Integer soLuong) {
        mSoLuong = soLuong;
    }

    public Integer getDonGiaLuuTru() {
        return mDonGiaLuuTru;
    }

    public void setDonGiaLuuTru(Integer donGiaLuuTru) {
        mDonGiaLuuTru = donGiaLuuTru;
    }

    public Integer getMaMonAn() {
        return mMaMonAn;
    }

    public void setMaMonAn(Integer maMonAn) {
        mMaMonAn = maMonAn;
    }

    public Integer getMaDonViTinh() {
        return mMaDonViTinh;
    }

    public void setMaDonViTinh(Integer maDonViTinh) {
        mMaDonViTinh = maDonViTinh;
    }

    public Integer getThanhTien() {
        return mThanhTien;
    }

    public void setThanhTien(Integer thanhTien) {
        mThanhTien = thanhTien;
    }

    public Integer getGiaTriKhuyenMaiLuuTru() {
        return mGiaTriKhuyenMaiLuuTru;
    }

    public void setGiaTriKhuyenMaiLuuTru(Integer giaTriKhuyenMaiLuuTru) {
        mGiaTriKhuyenMaiLuuTru = giaTriKhuyenMaiLuuTru;
    }

    public Integer getMaKhuyenMai() {
        return mMaKhuyenMai;
    }

    public void setMaKhuyenMai(Integer maKhuyenMai) {
        mMaKhuyenMai = maKhuyenMai;
    }
}
