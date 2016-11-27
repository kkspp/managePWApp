package kr.ac.hansung.hw_manageaccount;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;


/**
 * Created by user on 2016-11-25.
 */

public class NewAccountActivity extends AppCompatActivity {

    TextView sitename = null;
    TextView url = null;
    TextView id = null;
    TextView pw = null;
    Account newAccount = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);

        sitename = (TextView) findViewById(R.id.ET_SiteName);
        url = (TextView) findViewById(R.id.ET_URL);
        id = (TextView) findViewById(R.id.ET_ID);
        pw = (TextView) findViewById(R.id.ET_PW);
        newAccount = new Account(sitename.toString(),url.toString(),id.toString(),pw.toString());

        Intent intent2 = getIntent();
        if(intent2 != null) {
            sitename.setText(intent2.getStringExtra("sitename"));
            url.setText(intent2.getStringExtra("url"));
            id.setText(intent2.getStringExtra("id"));
            pw.setText(intent2.getStringExtra("pw"));
        }
    }

    public void ClickedatNew(View v) {
        switch(v.getId()) {
            case R.id.cancel:
                setResult(RESULT_CANCELED);
                finish();
                break;
            case R.id.create:
                Intent intent = new Intent();
                intent.putExtra("sitename", sitename.getText().toString());
                intent.putExtra("url", url.getText().toString());
                intent.putExtra("id", id.getText().toString());
                intent.putExtra("pw", pw.getText().toString());

                setResult(RESULT_OK, intent);
                finish();
                break;
        }
    }

}
