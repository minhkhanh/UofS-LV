package emenu.client.bus.task;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.client.HttpClient;

import emenu.client.dao.OrderDAO;
import emenu.client.db.dto.ChiTietOrderDTO;
import emenu.client.db.dto.SplittingOrderItem;

import android.content.ContentValues;

public class PostOrderSplittingTask extends CustomAsyncTask<Void, Void, Boolean> {
    List<SplittingOrderItem> mItems;

    public PostOrderSplittingTask(List<ContentValues> listContent) {
        mItems = new ArrayList<SplittingOrderItem>();
        for (ContentValues c : listContent) {
            SplittingOrderItem item = new SplittingOrderItem();
            item.ItemId = c.getAsInteger(ChiTietOrderDTO.CL_MA_CHI_TIET);
            item.QuantityToSplit = c.getAsInteger(ChiTietOrderDTO.CL_SO_LUONG);
            mItems.add(item);

            // mItemIds.add(c.getAsInteger(ChiTietOrderDTO.CL_MA_CHI_TIET));
        }
    }

    @Override
    protected Boolean doInBackground(Void... params) {
        try {
            return OrderDAO.getInstance().postOrderSplitting(mItems);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
