package emenu.client.bus.task;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.client.HttpClient;

import emenu.client.dao.OrderDAO;
import emenu.client.db.dto.ChiTietOrderDTO;

import android.content.ContentValues;

public class PostOrderSplittingTask extends CustomAsyncTask<Void, Void, Boolean> {
    List<Integer> mItemIds;
    HttpClient mClient;

    public PostOrderSplittingTask(HttpClient client, List<ContentValues> listContent) {
        mClient = client;
        mItemIds = new ArrayList<Integer>();
        for (ContentValues c : listContent) {
            mItemIds.add(c.getAsInteger(ChiTietOrderDTO.CL_MA_CHI_TIET));
        }
    }

    @Override
    protected Boolean doInBackground(Void... params) {
        try {
            return OrderDAO.getInstance().postOrderSplitting(mClient, mItemIds);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
