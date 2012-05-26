package client.menu.db.dto;

import java.io.StringReader;
import java.sql.Date;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import client.menu.util.XmlSerializerWrapper;

public class HoaDonDTO {
    public static final String TABLE_NAME = "HoaDon";

    public static final String CL_MA_BAN_CHINH = "MaBanChinh";
    public static final String CL_MA_HOA_DON = "MaHoaDon";
    public static final String CL_MA_PHU_THU = "MaPhuThu";
    public static final String CL_MA_TAI_KHOAN = "MaTaiKhoan";
    public static final String CL_MO_TA_BAN_GHEP = "MoTaBanGhep";
    public static final String CL_THOI_DIEM_LAP = "ThoiDiemLap";
    public static final String CL_TONG_TIEN = "TongTien";

    private Integer mId;
    private Integer mMaBanChinh;
    private Integer mMaHoaDon = -1;
    private Integer mMaPhuThu;
    private Integer mMaTaiKhoan = 1;
    private String mMoTaBanGhep = "";
    private Date mThoiDiemLap = new Date(System.currentTimeMillis());
    private Integer mTongTien = 0;

    public static final HoaDonDTO fromXml(String xmlData) {
        try {
            XmlPullParserFactory parserFactory = XmlPullParserFactory.newInstance();
            XmlPullParser parser = parserFactory.newPullParser();
            parser.setInput(new StringReader(xmlData));
            return fromXml(parser);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static final HoaDonDTO fromXml(XmlPullParser parser) {
        HoaDonDTO obj = null;

        try {
            int type = parser.getEventType();
            String tag = "";
            String text;

            while (type != XmlPullParser.END_DOCUMENT) {
                switch (type) {
                    case XmlPullParser.START_TAG:
                        tag = parser.getName();
                        if (tag.compareTo(TABLE_NAME) == 0) {
                            obj = new HoaDonDTO();
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
                        if (tag.compareTo(CL_MA_BAN_CHINH) == 0) {
                            obj.mMaBanChinh = Integer.valueOf(text);
                        } else if (tag.compareTo(CL_MA_HOA_DON) == 0) {
                            obj.mMaHoaDon = Integer.valueOf(text);
                        } else if (tag.compareTo(CL_MA_PHU_THU) == 0) {
                            obj.mMaPhuThu = Integer.valueOf(text);
                        } else if (tag.compareTo(CL_MA_TAI_KHOAN) == 0) {
                            obj.mMaTaiKhoan = Integer.valueOf(text);
                        } else if (tag.compareTo(CL_MO_TA_BAN_GHEP) == 0) {
                            obj.mMoTaBanGhep = text;
                        } else if (tag.compareTo(CL_THOI_DIEM_LAP) == 0) {
//                            obj.mThoiDiemLap = Date.valueOf(text);
                        } else if (tag.compareTo(CL_TONG_TIEN) == 0) {
                            obj.mTongTien = Integer.valueOf(text);
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

    public String toXml() {
        XmlSerializerWrapper serializer = new XmlSerializerWrapper();

        try {
            serializer.startDocument();
            serializer.startTag(TABLE_NAME);
            serializer.writeSimpleElement(CL_MA_BAN_CHINH, mMaBanChinh);
            serializer.writeSimpleElement(CL_MA_HOA_DON, mMaHoaDon);
            serializer.writeSimpleElement(CL_MA_PHU_THU, mMaPhuThu);
            serializer.writeSimpleElement(CL_MA_TAI_KHOAN, mMaTaiKhoan);
            serializer.writeSimpleElement(CL_MO_TA_BAN_GHEP, mMoTaBanGhep);
            serializer.writeSimpleElement(CL_THOI_DIEM_LAP, mThoiDiemLap);
            serializer.writeSimpleElement(CL_TONG_TIEN, mTongTien);
            serializer.endTag(TABLE_NAME);
            serializer.endDocument();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return serializer.toString();
    }

    public Integer getId() {
        return mId;
    }

    public void setId(Integer id) {
        mId = id;
    }

    public Integer getMaBanChinh() {
        return mMaBanChinh;
    }

    public void setMaBanChinh(Integer maBanChinh) {
        mMaBanChinh = maBanChinh;
    }

    public Integer getMaHoaDon() {
        return mMaHoaDon;
    }

    public void setMaHoaDon(Integer maHoaDon) {
        mMaHoaDon = maHoaDon;
    }

    public Integer getMaPhuThu() {
        return mMaPhuThu;
    }

    public void setMaPhuThu(Integer maPhuThu) {
        mMaPhuThu = maPhuThu;
    }

    public Integer getMaTaiKhoan() {
        return mMaTaiKhoan;
    }

    public void setMaTaiKhoan(Integer maTaiKhoan) {
        mMaTaiKhoan = maTaiKhoan;
    }

    public String getMoTaBanGhep() {
        return mMoTaBanGhep;
    }

    public void setMoTaBanGhep(String moTaBanGhep) {
        mMoTaBanGhep = moTaBanGhep;
    }

    public Date getThoiDiemLap() {
        return mThoiDiemLap;
    }

    public void setThoiDiemLap(Date thoiDiemLap) {
        mThoiDiemLap = thoiDiemLap;
    }

    public Integer getTongTien() {
        return mTongTien;
    }

    public void setTongTien(Integer tongTien) {
        mTongTien = tongTien;
    }
}
