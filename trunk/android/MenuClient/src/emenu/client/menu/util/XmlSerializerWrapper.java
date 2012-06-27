package emenu.client.menu.util;

import java.io.IOException;
import java.io.StringWriter;

import org.xmlpull.v1.XmlSerializer;

import android.util.Xml;

public class XmlSerializerWrapper {
    private XmlSerializer mSerializer = null;
    private StringWriter mWriter = null;
    private String mNamespace = null;

    public String toString() {
        return mWriter.toString();
    }

    public XmlSerializer endTag(String name) throws IOException,
            IllegalArgumentException, IllegalStateException {
        return mSerializer.endTag("", name);
    }

    public XmlSerializer startTag(String name) throws IOException,
            IllegalArgumentException, IllegalStateException {
        return mSerializer.startTag("", name);
    }

    public void endDocument() throws IOException, IllegalArgumentException,
            IllegalStateException {
        mSerializer.endDocument();
    }

    public void startDocument() throws IOException, IllegalArgumentException,
            IllegalStateException {
        mSerializer = Xml.newSerializer();
        mWriter = new StringWriter();
        mNamespace = "http://www.w3.org/2001/XMLSchema-instance";

        mSerializer.setOutput(mWriter);
        mSerializer.startDocument("UTF-8", null);
        mSerializer.setPrefix("i", mNamespace);
    }
    
//    public void sortThenWrite(Map<String, Object> elements)
//            throws IllegalArgumentException, IllegalStateException, IOException {
//        Set<String> keySet = elements.keySet();
////        keySet.
//        keySet.
//        for (int i = 0; i < elements.size() - 1; ++i) {
//            for (int j = i + 1; j < elements.size(); ++j) {
//                Map<String, Object> e1 = elements..get(i);
//                Map<String, Object> ej = elements.get(j);
//                if (e1..getKey().compareTo(ej.getKey()) > 0) {
//                    Entry<String, Object> t = e1;
//                    elements.set(i, ej);
//                    elements.set(j, t);
//                }
//            }
//        }
//        
//        for (Entry<String, Object> e : elements) {
//            writeSimpleElement(e.getKey(), e.getValue());
//        }
//    }

    public void writeSimpleElement(String name, Object value)
            throws IllegalArgumentException, IllegalStateException, IOException {
        startTag(name);
        if (value == null) {
            mSerializer.attribute(mNamespace, "nil", "true");
        } else {
            mSerializer.text(value.toString());
        }
        endTag(name);
    }
}
