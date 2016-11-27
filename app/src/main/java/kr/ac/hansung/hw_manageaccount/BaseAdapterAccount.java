package kr.ac.hansung.hw_manageaccount;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by user on 2016-11-24.
 */

public class BaseAdapterAccount extends BaseAdapter {
    Context mContext = null;
    ArrayList<Account> mData = null;
    LayoutInflater mLayoutIflater = null;

    public BaseAdapterAccount(Context context, ArrayList<Account> data ) {
        mContext = context;
        mData = data;
        mLayoutIflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return mData.size(); }
    public long getItemId(int position) {
        return position; }
    public Account getItem(int position) {
        return mData.get(position);}

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        // 리스트의 한 항목에 해당하는 레이아웃을 생성한다.
        View itemLayout = convertView;
        if(itemLayout == null) { //어댑터뷰가 재사용할 뷰를 넘겨주지 않는 경우에만 새로운 뷰 생성
            itemLayout = mLayoutIflater.inflate(R.layout.listview_item_layout, null);
        }
        TextView sitenameTV = (TextView) itemLayout.findViewById(R.id.item_sitename);
        TextView urlTV = (TextView) itemLayout.findViewById(R.id.item_url);
        TextView idTV = (TextView) itemLayout.findViewById(R.id.item_id);

        // 사이트이름, URL, ID 데이터를 참조하여 레이아웃을 갱신한다.
        sitenameTV.setText(mData.get(position).siteName);
        urlTV.setText(mData.get(position).url);
        idTV.setText(mData.get(position).id);

        return itemLayout;
    }

}
