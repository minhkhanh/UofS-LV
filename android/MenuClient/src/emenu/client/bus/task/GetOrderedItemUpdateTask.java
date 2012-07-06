package emenu.client.bus.task;

import emenu.client.dao.OrderDAO;

public class GetOrderedItemUpdateTask extends CustomAsyncTask<Void, Void, Boolean> {
    private Integer mItemId;
    private int mVarQuantity;
    private String mItemNote;

    public GetOrderedItemUpdateTask(Integer itemId, int varQuantity, String itemNote) {
        mItemId = itemId;
        mVarQuantity = varQuantity;
        mItemNote = itemNote;
    }

    @Override
    protected Boolean doInBackground(Void... params) {
        try {
            return OrderDAO.getInstance().getOrderedItemUpdate(mItemId, mVarQuantity,
                    mItemNote);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
