package kr.ac.hansung.hw_manageaccount;

/**
 * Created by user on 2016-11-24.
 */

public class Account {
    String siteName;    //사이트이름
    String url;         //URL
    String id;          //ID
    String pw;          //Password

    Account() {}
    Account(String siteName, String url, String id, String pw) {
        this.siteName = siteName;
        this.url = url;
        this.id = id;
        this.pw = pw;
    }
    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }
}
