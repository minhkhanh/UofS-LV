package emenu.client.db.dto;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import android.content.ContentValues;
import android.database.Cursor;
import android.provider.BaseColumns;
import emenu.client.util.U;
import emenu.client.util.XmlSerializerWrapper;

public class BanDTO
// implements Parcelable
{
    public static final String TABLE_NAME = "Ban";

    public static final String CL_ID = BaseColumns._ID;
    public static final String CL_MA_BAN = "MaBan";
    public static final String CL_MA_KHU_VUC = "MaKhuVuc";
    public static final String CL_TEN_BAN = "TenBan";
    public static final String CL_GHI_CHU = "GhiChu";
    public static final String CL_ACTIVE = "Active";
    public static final String CL_TINH_TRANG = "TinhTrang";
    public static final String CL_MA_BAN_CHINH = "MaBanChinh";

    private Integer mId;
    private Integer mMaBan;
    private Integer mMaKhuVuc;
    private Integer mMaBanChinh;
    private String mTenBan;
    private String mGhiChu;
    private Boolean mActive;
    private Boolean mTinhTrang;
    
    public static final List<BanDTO> fromArrayJson(JSONArray jsonArray) throws JSONException {
        List<BanDTO> list = new ArrayList<BanDTO>();
        
        for (int i = 0; i < jsonArray.length(); ++i) {
            JSONObject jsonObj = jsonArray.getJSONObject(i);
            BanDTO ban = fromJson(jsonObj);
            list.add(ban);
        }
        
        return list;
    }

    public JSONObject toJson() throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(CL_ACTIVE, mActive);
        jsonObject.put(CL_GHI_CHU, mGhiChu);
        jsonObject.put(CL_MA_BAN, mMaBan);
        jsonObject.put(CL_MA_BAN_CHINH, mMaBanChinh);
        jsonObject.put(CL_MA_KHU_VUC, mMaKhuVuc);
        jsonObject.put(CL_TEN_BAN, mTenBan);
        jsonObject.put(CL_TINH_TRANG, mTinhTrang);
        
        return null;
    }

    public static final BanDTO fromJson(JSONObject jsonObj) throws JSONException {
        BanDTO ban = new BanDTO();
        if (!jsonObj.isNull(CL_ACTIVE)) {
            ban.mActive = jsonObj.getBoolean(CL_ACTIVE);
        }
        if (!jsonObj.isNull(CL_GHI_CHU)) {
            ban.mGhiChu = jsonObj.getString(CL_GHI_CHU);
        }
        if (!jsonObj.isNull(CL_MA_BAN)) {
            ban.mMaBan = jsonObj.getInt(CL_MA_BAN);
        }
        if (!jsonObj.isNull(CL_MA_BAN_CHINH)) {
            ban.mMaBanChinh = jsonObj.getInt(CL_MA_BAN_CHINH);
        }
        if (!jsonObj.isNull(CL_MA_KHU_VUC)) {
            ban.mMaKhuVuc = jsonObj.getInt(CL_MA_KHU_VUC);
        }
        if (!jsonObj.isNull(CL_TEN_BAN)) {
            ban.mTenBan = jsonObj.getString(CL_TEN_BAN);
        }
        if (!jsonObj.isNull(CL_TINH_TRANG)) {
            ban.mTinhTrang = jsonObj.getBoolean(CL_TINH_TRANG);
        }

        return ban;
    }

    public static final ContentValues toContentValues(JSONObject jsonObj)
            throws JSONException {
        ContentValues values = new ContentValues();
        if (!jsonObj.isNull(CL_TINH_TRANG)) {
            values.put(CL_TINH_TRANG, jsonObj.getBoolean(CL_TINH_TRANG));
        }
        if (!jsonObj.isNull(CL_TEN_BAN)) {
            values.put(CL_TEN_BAN, jsonObj.getString(CL_TEN_BAN));
        }
        if (!jsonObj.isNull(CL_GHI_CHU)) {
            values.put(CL_GHI_CHU, jsonObj.getString(CL_GHI_CHU));
        }
        if (!jsonObj.isNull(CL_ACTIVE)) {
            values.put(CL_ACTIVE, jsonObj.getBoolean(CL_ACTIVE));
        }
        if (!jsonObj.isNull(CL_MA_BAN)) {
            values.put(CL_MA_BAN, jsonObj.getInt(CL_MA_BAN));
        }
        if (!jsonObj.isNull(CL_MA_BAN_CHINH)) {
            values.put(CL_MA_BAN_CHINH, jsonObj.getInt(CL_MA_BAN_CHINH));
        }
        if (!jsonObj.isNull(CL_MA_KHU_VUC)) {
            values.put(CL_MA_KHU_VUC, jsonObj.getInt(CL_MA_KHU_VUC));
        }

        return values;
    }

    public ContentValues toContentValues() {
        ContentValues values = new ContentValues();

        values.put(CL_ID, mId);
        values.put(CL_MA_BAN, mMaBan);
        values.put(CL_MA_KHU_VUC, mMaKhuVuc);
        values.put(CL_MA_BAN_CHINH, mMaBanChinh);
        values.put(CL_TEN_BAN, mTenBan);
        values.put(CL_GHI_CHU, mGhiChu);
        values.put(CL_ACTIVE, mActive);
        values.put(CL_TINH_TRANG, mTinhTrang);

        return values;
    }

    public String toXml() {
        XmlSerializerWrapper serializer = new XmlSerializerWrapper();

        try {
            serializer.startDocument();
            serializer.startTag(TABLE_NAME);
            serializer.writeSimpleElement(CL_ACTIVE, mActive);
            serializer.writeSimpleElement(CL_GHI_CHU, mGhiChu);
            serializer.writeSimpleElement(CL_MA_BAN, mMaBan);
            serializer.writeSimpleElement(CL_MA_BAN_CHINH, mMaBanChinh);
            serializer.writeSimpleElement(CL_MA_KHU_VUC, mMaKhuVuc);
            serializer.writeSimpleElement(CL_TEN_BAN, mTenBan);
            serializer.writeSimpleElement(CL_TINH_TRANG, mTinhTrang);
            serializer.endTag(TABLE_NAME);
            serializer.endDocument();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return serializer.toString();
    }

    public static final List<BanDTO> fromXmlArray(String xmlData) {
        List<BanDTO> list = new ArrayList<BanDTO>();

        try {
            XmlPullParserFactory parserFactory = XmlPullParserFactory.newInstance();
            XmlPullParser parser = parserFactory.newPullParser();
            parser.setInput(new StringReader(xmlData));
            int type = parser.getEventType();
            while (type != XmlPullParser.END_DOCUMENT) {
                BanDTO obj = BanDTO.fromXml(parser);
                if (obj != null) {
                    list.add(obj);
                }

                type = parser.next();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public static final BanDTO fromXml(XmlPullParser parser) {
        BanDTO obj = null;

        try {
            int type = parser.getEventType();
            String tag = "";
            String text;

            while (type != XmlPullParser.END_DOCUMENT) {
                switch (type) {
                    case XmlPullParser.START_TAG:
                        tag = parser.getName();
                        if (tag.compareTo(TABLE_NAME) == 0) {
                            obj = new BanDTO();
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
                        if (tag.compareTo(CL_MA_BAN) == 0) {
                            obj.mMaBan = Integer.valueOf(text);
                        } else if (tag.compareTo(CL_MA_KHU_VUC) == 0) {
                            obj.mMaKhuVuc = Integer.valueOf(text);
                        } else if (tag.compareTo(CL_TEN_BAN) == 0) {
                            obj.mTenBan = text;
                        } else if (tag.compareTo(CL_GHI_CHU) == 0) {
                            obj.mGhiChu = text;
                        } else if (tag.compareTo(CL_ACTIVE) == 0) {
                            obj.mActive = Boolean.valueOf(text);
                        } else if (tag.compareTo(CL_TINH_TRANG) == 0) {
                            obj.mTinhTrang = Boolean.valueOf(text);
                        } else if (tag.compareTo(CL_MA_BAN_CHINH) == 0) {
                            obj.mMaBanChinh = Integer.valueOf(text);
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

    public static final List<BanDTO> fromArrayCursor(Cursor cursor) {
        List<BanDTO> list = new ArrayList<BanDTO>();

        while (cursor.moveToNext()) {
            BanDTO ban = BanDTO.fromCursor(cursor);
            list.add(ban);
        }

        return list;
    }

    public static final BanDTO fromCursor(Cursor cursor) {
        BanDTO obj = new BanDTO();
        int i;
        if ((i = cursor.getColumnIndex(CL_ID)) != -1) {
            obj.mId = cursor.getInt(i);
        }
        if ((i = cursor.getColumnIndex(CL_MA_BAN)) != -1) {
            obj.mMaBan = cursor.getInt(i);
        }
        if ((i = cursor.getColumnIndex(CL_MA_KHU_VUC)) != -1) {
            obj.mMaKhuVuc = cursor.getInt(i);
        }
        if ((i = cursor.getColumnIndex(CL_MA_BAN_CHINH)) != -1) {
            obj.mMaBanChinh = cursor.getInt(i);
        }
        if ((i = cursor.getColumnIndex(CL_TEN_BAN)) != -1) {
            obj.mTenBan = cursor.getString(i);
        }
        if ((i = cursor.getColumnIndex(CL_GHI_CHU)) != -1) {
            obj.mGhiChu = cursor.getString(i);
        }
        if ((i = cursor.getColumnIndex(CL_ACTIVE)) != -1) {
            obj.mActive = U.getCursorBool(cursor, i);
        }
        if ((i = cursor.getColumnIndex(CL_TINH_TRANG)) != -1) {
            obj.mTinhTrang = U.getCursorBool(cursor, i);
        }

        return obj;
    }

    public Integer getId() {
        return mId;
    }

    public void setId(Integer id) {
        mId = id;
    }

    public Integer getMaBan() {
        return mMaBan;
    }

    public void setMaBan(Integer maBan) {
        this.mMaBan = maBan;
    }

    public Integer getMaKhuVuc() {
        return mMaKhuVuc;
    }

    public void setMaKhuVuc(Integer maKhuVuc) {
        this.mMaKhuVuc = maKhuVuc;
    }

    public String getTenBan() {
        return mTenBan;
    }

    public void setTenBan(String tenBan) {
        this.mTenBan = tenBan;
    }

    public String getGhiChu() {
        return mGhiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.mGhiChu = ghiChu;
    }

    public Integer getMaBanChinh() {
        return mMaBanChinh;
    }

    public void setMaBanChinh(Integer maBanChinh) {
        this.mMaBanChinh = maBanChinh;
    }

    public Boolean getActive() {
        return mActive;
    }

    public void setActive(Boolean active) {
        mActive = active;
    }

    public Boolean getTinhTrang() {
        return mTinhTrang;
    }

    public void setTinhTrang(Boolean tinhTrang) {
        mTinhTrang = tinhTrang;
    }

    @Override
    public String toString() {
        return getTenBan();
    }

    // @Override
    // public Integer describeContents() {
    // return 0;
    // }
    //
    // @Override
    // public void writeToParcel(Parcel dest, Integer flags) {
    // dest.writeValue(mActive);
    // dest.writeValue(mGhiChu);
    // dest.writeValue(mId);
    // dest.writeValue(mMaBan);
    // dest.writeValue(mMaBanChinh);
    // dest.writeValue(mMaKhuVuc);
    // dest.writeValue(mTenBan);
    // dest.writeValue(mTinhTrang);
    // }
    //
    // public BanDTO(Parcel src) {
    // mActive = (Boolean) src.readValue(Boolean.class.getClassLoader());
    // mGhiChu = src.readString();
    // mId = src.readInt();
    // mMaBan = src.readInt();
    // mMaBanChinh = (Integer) data[i++];
    // mMaKhuVuc = (Integer) data[i++];
    // mTenBan = (String) data[i++];
    // mTinhTrang = (Boolean) data[i++];
    // }
    //
    // public static final Parcelable.Creator<BanDTO> CREATOR = new
    // Creator<BanDTO>() {
    //
    // @Override
    // public BanDTO createFromParcel(Parcel source) {
    // BanDTO ban = new BanDTO();
    //
    // Object[] data = source.readArray(Object.class.getClassLoader());
    // Integer i = 0;
    // ban.mActive = (Boolean) data[i++];
    // ban.mGhiChu = (String) data[i++];
    // ban.mId = (Integer) data[i++];
    // ban.mMaBan = (Integer) data[i++];
    // ban.mMaBanChinh = (Integer) data[i++];
    // ban.mMaKhuVuc = (Integer) data[i++];
    // ban.mTenBan = (String) data[i++];
    // ban.mTinhTrang = (Boolean) data[i++];
    //
    // return ban;
    // }
    //
    // @Override
    // public BanDTO[] newArray(Integer size) {
    // return new BanDTO[size];
    // }
    // };
}
