package emenu.client.db.dto;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import emenu.client.util.XmlSerializerWrapper;

import android.content.ContentValues;
import android.provider.BaseColumns;

public class ChiTietOrderDTO {
    public static final String TABLE_NAME = "ChiTietOrder";

    public static final String CL_ID = BaseColumns._ID;
    public static final String CL_MA_CHI_TIET = "MaChiTietOrder";
    public static final String CL_MA_ORDER = "MaOrder";
    public static final String CL_SO_LUONG = "SoLuong";
    public static final String CL_GHI_CHU = "GhiChu";
    public static final String CL_MA_BO_PHAN_CHE_BIEN = "MaBoPhanCheBien";
    public static final String CL_TINH_TRANG = "TinhTrang";
    public static final String CL_MA_MON_AN = "MaMonAn";
    public static final String CL_MA_DON_VI_TINH = "MaDonViTinh";

    public static final String CL_EX_PROCESSED = "SoLuongDaCheBien";
    public static final String CL_EX_PROCESSING = "SoLuongDangCheBien";

    public static final String CL_MA_MON_AN_QN = TABLE_NAME + ".MaMonAn";
    public static final String CL_MA_DON_VI_TINH_QN = TABLE_NAME + ".MaDonViTinh";

    private Integer mId;
    private Integer mMaChiTiet = -1;
    private Integer mMaOrder;
    private Integer mSoLuong;
    private String mGhiChu = "";
    private Integer mMaBoPhanCheBien;
    private Integer mTinhTrang = -1;
    private Integer mMaMonAn;
    private Integer mMaDonViTinh;
    private Integer mSoLuongDaCheBien = 0;
    private Integer mSoLuongDangCheBien = 0;

    public static JSONArray toArrayJson(List<ChiTietOrderDTO> list) throws JSONException {
        JSONArray jsonArray = new JSONArray();
        for (ChiTietOrderDTO c : list) {
            JSONObject jsonObj = new JSONObject();
            jsonObj = c.toJson();

            jsonArray.put(jsonObj);
        }

        return jsonArray;
    }

    public JSONObject toJson() throws JSONException {
        JSONObject jsonObj = new JSONObject();
        jsonObj.put(CL_MA_CHI_TIET, mMaChiTiet);
        jsonObj.put(CL_MA_ORDER, mMaOrder);
        jsonObj.put(CL_SO_LUONG, mSoLuong);
        jsonObj.put(CL_GHI_CHU, mGhiChu);
        jsonObj.put(CL_MA_BO_PHAN_CHE_BIEN, mMaBoPhanCheBien);
        jsonObj.put(CL_TINH_TRANG, mTinhTrang);
        jsonObj.put(CL_MA_MON_AN, mMaMonAn);
        jsonObj.put(CL_MA_DON_VI_TINH, mMaDonViTinh);
        jsonObj.put(CL_EX_PROCESSED, mSoLuongDaCheBien);
        jsonObj.put(CL_EX_PROCESSING, mSoLuongDangCheBien);

        return jsonObj;
    }

    public static final List<ChiTietOrderDTO> fromArrayJson(JSONArray jsonArray)
            throws JSONException {
        List<ChiTietOrderDTO> list = new ArrayList<ChiTietOrderDTO>();

        for (int i = 0; i < jsonArray.length(); ++i) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            ChiTietOrderDTO obj = ChiTietOrderDTO.fromJson(jsonObject);
            list.add(obj);
        }

        return list;
    }

    public static final ChiTietOrderDTO fromJson(JSONObject jsonObject)
            throws JSONException {
        ChiTietOrderDTO obj = new ChiTietOrderDTO();

        if (!jsonObject.isNull(CL_GHI_CHU)) {
            obj.mGhiChu = jsonObject.getString(CL_GHI_CHU);
        }
        if (!jsonObject.isNull(CL_MA_BO_PHAN_CHE_BIEN)) {
            obj.mMaBoPhanCheBien = jsonObject.getInt(CL_MA_BO_PHAN_CHE_BIEN);
        }
        if (!jsonObject.isNull(CL_MA_CHI_TIET)) {
            obj.mMaChiTiet = jsonObject.getInt(CL_MA_CHI_TIET);
        }
        if (!jsonObject.isNull(CL_MA_DON_VI_TINH)) {
            obj.mMaDonViTinh = jsonObject.getInt(CL_MA_DON_VI_TINH);
        }
        if (!jsonObject.isNull(CL_MA_MON_AN)) {
            obj.mMaMonAn = jsonObject.getInt(CL_MA_MON_AN);
        }
        if (!jsonObject.isNull(CL_MA_ORDER)) {
            obj.mMaOrder = jsonObject.getInt(CL_MA_ORDER);
        }
        if (!jsonObject.isNull(CL_SO_LUONG)) {
            obj.mSoLuong = jsonObject.getInt(CL_SO_LUONG);
        }
        if (!jsonObject.isNull(CL_TINH_TRANG)) {
            obj.mTinhTrang = jsonObject.getInt(CL_TINH_TRANG);
        }
        if (!jsonObject.isNull(CL_EX_PROCESSED)) {
            obj.mSoLuongDaCheBien = jsonObject.getInt(CL_EX_PROCESSED);
        }
        if (!jsonObject.isNull(CL_EX_PROCESSING)) {
            obj.mSoLuongDangCheBien = jsonObject.getInt(CL_EX_PROCESSING);
        }

        return obj;
    }

    public static final List<ContentValues> jsonToContentValuesArray(JSONArray jsonArray)
            throws JSONException {
        List<ContentValues> list = new ArrayList<ContentValues>();

        for (int i = 0; i < jsonArray.length(); ++i) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            ContentValues c = jsonToContentValues(jsonObject);

            list.add(c);
        }

        return list;
    }

    public static ContentValues jsonToContentValues(JSONObject jsonObject)
            throws JSONException {
        ChiTietOrderDTO obj = fromJson(jsonObject);

        return obj.toContentValues();
    }

    public ContentValues toContentValues() {
        ContentValues c = new ContentValues();
        c.put(CL_ID, mId);
        c.put(CL_MA_CHI_TIET, mMaChiTiet);
        c.put(CL_MA_ORDER, mMaOrder);
        c.put(CL_SO_LUONG, mSoLuong);
        c.put(CL_GHI_CHU, mGhiChu);
        c.put(CL_MA_BO_PHAN_CHE_BIEN, mMaBoPhanCheBien);
        c.put(CL_TINH_TRANG, mTinhTrang);
        c.put(CL_MA_MON_AN, mMaMonAn);
        c.put(CL_MA_DON_VI_TINH, mMaDonViTinh);
        c.put(CL_EX_PROCESSED, mSoLuongDaCheBien);
        c.put(CL_EX_PROCESSING, mSoLuongDangCheBien);

        return c;
    }

    public static final String toXmlArray(List<ChiTietOrderDTO> list) {
        XmlSerializerWrapper serializer = new XmlSerializerWrapper();

        try {
            serializer.startDocument();
            serializer.startTag("ArrayOf" + TABLE_NAME);

            for (ChiTietOrderDTO c : list) {
                serializer.startTag(TABLE_NAME);
                serializer.writeSimpleElement(CL_GHI_CHU, c.mGhiChu);
                serializer.writeSimpleElement(CL_MA_BO_PHAN_CHE_BIEN, c.mMaBoPhanCheBien);
                serializer.writeSimpleElement(CL_MA_CHI_TIET, c.mMaChiTiet);
                serializer.writeSimpleElement(CL_MA_DON_VI_TINH, c.mMaDonViTinh);
                serializer.writeSimpleElement(CL_MA_MON_AN, c.mMaMonAn);
                serializer.writeSimpleElement(CL_MA_ORDER, c.mMaOrder);
                serializer.writeSimpleElement(CL_SO_LUONG, c.mSoLuong);
                serializer.writeSimpleElement(CL_TINH_TRANG, c.mTinhTrang);
                serializer.endTag(TABLE_NAME);
            }

            serializer.endTag("ArrayOf" + TABLE_NAME);
            serializer.endDocument();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return serializer.toString();
    }

    public static final List<ChiTietOrderDTO> fromXmlArray(String xmlData) {
        List<ChiTietOrderDTO> list = new ArrayList<ChiTietOrderDTO>();

        try {
            XmlPullParserFactory parserFactory = XmlPullParserFactory.newInstance();
            XmlPullParser parser = parserFactory.newPullParser();
            parser.setInput(new StringReader(xmlData));
            int type = parser.getEventType();
            while (type != XmlPullParser.END_DOCUMENT) {
                ChiTietOrderDTO obj = ChiTietOrderDTO.fromXml(parser);
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

    public static final ChiTietOrderDTO fromXml(XmlPullParser parser) {
        ChiTietOrderDTO obj = null;

        try {
            int type = parser.getEventType();
            String tag = "";
            String text;

            while (type != XmlPullParser.END_DOCUMENT) {
                switch (type) {
                    case XmlPullParser.START_TAG:
                        tag = parser.getName();
                        if (tag.compareTo(TABLE_NAME) == 0) {
                            obj = new ChiTietOrderDTO();
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
                            obj.mMaChiTiet = Integer.valueOf(text);
                        } else if (tag.compareTo(CL_MA_ORDER) == 0) {
                            obj.mMaOrder = Integer.valueOf(text);
                        } else if (tag.compareTo(CL_SO_LUONG) == 0) {
                            obj.mSoLuong = Integer.valueOf(text);
                        } else if (tag.compareTo(CL_GHI_CHU) == 0) {
                            obj.mGhiChu = text;
                        } else if (tag.compareTo(CL_MA_BO_PHAN_CHE_BIEN) == 0) {
                            obj.mMaBoPhanCheBien = Integer.valueOf(text);
                        } else if (tag.compareTo(CL_TINH_TRANG) == 0) {
                            obj.mTinhTrang = Integer.valueOf(text);
                        } else if (tag.compareTo(CL_MA_MON_AN) == 0) {
                            obj.mMaMonAn = Integer.valueOf(text);
                        } else if (tag.compareTo(CL_MA_DON_VI_TINH) == 0) {
                            obj.mMaDonViTinh = Integer.valueOf(text);
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

    public void toContentValues(ContentValues values) {
        ContentValues c = toContentValues();
        values.putAll(c);
    }

    public Integer getId() {
        return mId;
    }

    public void setId(Integer id) {
        mId = id;
    }

    public Integer getMaChiTiet() {
        return mMaChiTiet;
    }

    public void setMaChiTiet(Integer maChiTiet) {
        mMaChiTiet = maChiTiet;
    }

    public Integer getMaOrder() {
        return mMaOrder;
    }

    public void setMaOrder(Integer maOrder) {
        mMaOrder = maOrder;
    }

    public Integer getSoLuong() {
        return mSoLuong;
    }

    public void setSoLuong(Integer soLuong) {
        mSoLuong = soLuong;
    }

    public String getGhiChu() {
        return mGhiChu;
    }

    public void setGhiChu(String ghiChu) {
        mGhiChu = ghiChu;
    }

    public Integer getMaBoPhanCheBien() {
        return mMaBoPhanCheBien;
    }

    public void setMaBoPhanCheBien(Integer maBoPhanCheBien) {
        mMaBoPhanCheBien = maBoPhanCheBien;
    }

    public Integer getTinhTrang() {
        return mTinhTrang;
    }

    public void setTinhTrang(Integer tinhTrang) {
        mTinhTrang = tinhTrang;
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

    public Integer getSoLuongDaCheBien() {
        return mSoLuongDaCheBien;
    }

    public void setSoLuongDaCheBien(Integer soLuongDaCheBien) {
        mSoLuongDaCheBien = soLuongDaCheBien;
    }

    public Integer getSoLuongDangCheBien() {
        return mSoLuongDangCheBien;
    }

    public void setSoLuongDangCheBien(Integer soLuongDangCheBien) {
        mSoLuongDangCheBien = soLuongDangCheBien;
    }

}
