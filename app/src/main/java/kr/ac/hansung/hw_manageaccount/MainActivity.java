package kr.ac.hansung.hw_manageaccount;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView account_listview = null;
    BaseAdapterAccount mAdapter = null;
    ArrayList<Account> account_data = null;

    final static int ACTIVITY_NEW = 0;
    final static int ACTIVITY_EDIT = 1;

    int longClickedItemPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_view);

        myInit();

        account_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Account clicked_account = mAdapter.getItem(position);
                Toast.makeText(getBaseContext(),clicked_account.pw,Toast.LENGTH_LONG).show();
            }
        });

        account_listview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            Account clicked_account = new Account();
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long l) {
                longClickedItemPosition = position;
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("이 사이트에 대해")
                        .setItems(new String[] {"수정하기", "삭제하기"}, mDlgListener)
                        .setNegativeButton("취소", mDlgListener)
                        .show();
                clicked_account = mAdapter.getItem(position);
                return false;
            }
            DialogInterface.OnClickListener mDlgListener = new DialogInterface.OnClickListener(){
                public void onClick(DialogInterface dlg, int which) {
                    switch(which) {
                        case 0:
                            //목록 첫째값 선택시-수정
                            Intent intent = new Intent(getBaseContext(), NewAccountActivity.class);
                            intent.putExtra("sitename",clicked_account.siteName);
                            intent.putExtra("url",clicked_account.url);
                            intent.putExtra("id",clicked_account.id);
                            intent.putExtra("pw",clicked_account.pw);
                            startActivityForResult(intent, ACTIVITY_EDIT);
                            break;
                        case 1:
                            //목록 둘째값 선택시-삭제
                            account_data.remove(longClickedItemPosition);
                            account_listview.setAdapter(mAdapter);
                            break;
                        case AlertDialog.BUTTON_NEGATIVE:
                            break;
                    }
                }
            };
        });


    }
    private void myInit() {
        account_data = new ArrayList<Account>();

        Account user = new Account("Google","www.google.com","kks","0000");
        account_data.add(user);
        Account user2 = new Account("Naver","www.naver.com","kkk","1111");
        account_data.add(user2);

        //어탭터를 생성하고 데이터 설정
        mAdapter = new BaseAdapterAccount(this, account_data);
        //리스트뷰에 어댑터 설정
        account_listview = (ListView) findViewById(R.id.account_listview);
        account_listview.setAdapter(mAdapter);
    }

    public void mOnClick(View v) {

        switch (v.getId()) {
            case R.id.addAccount :
                Intent intent = new Intent(this, NewAccountActivity.class);
                startActivityForResult(intent, ACTIVITY_NEW);
                break;
            case R.id.quit :
                finish();
                break;
        }
    }

    protected  void onActivityResult(int requestCode, int resultCode, Intent data) {
        Account new_account = new Account();
        switch (requestCode) {
            case ACTIVITY_NEW:  //새로만든 사이트계정 정보
                if(resultCode == RESULT_OK) {
                    new_account.setSiteName(data.getStringExtra("sitename"));
                    new_account.url=data.getStringExtra("url");
                    new_account.id=data.getStringExtra("id");
                    new_account.pw=data.getStringExtra("pw");
                    account_data.add(new_account);

                    account_listview.setAdapter(mAdapter);
                }
                break;
            case ACTIVITY_EDIT: //수정한 사이트계정 정보
                if (resultCode == RESULT_OK) {
                    new_account.setSiteName(data.getStringExtra("sitename"));
                    new_account.url=data.getStringExtra("url");
                    new_account.id=data.getStringExtra("id");
                    new_account.pw=data.getStringExtra("pw");

                    mAdapter.getItem(longClickedItemPosition).siteName = new_account.siteName;
                    mAdapter.getItem(longClickedItemPosition).url = new_account.url;
                    mAdapter.getItem(longClickedItemPosition).id = new_account.id;
                    mAdapter.getItem(longClickedItemPosition).pw = new_account.pw;

                    account_listview.setAdapter(mAdapter);
                }
        }
    }


}
