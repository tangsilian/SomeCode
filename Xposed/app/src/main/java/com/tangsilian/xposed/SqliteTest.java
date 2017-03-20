package com.tangsilian.xposed;

/**
 * Created by tangsilian on 2017-3-2.
 */

import android.util.Log;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.sqlite.JDBC;

public class SqliteTest {
    static String TAG;

    static {
        SqliteTest.TAG = "zq-proyx";
    }

    public SqliteTest() {
        super();
    }

    public void copyfile() {
    }

    public static void test() {
        System.out.println("This is a SqliteTest program!");
        try {
            Connection v0 = new JDBC().connect("jdbc:sqlite:/data/data/com.zq.hookhttp/databases/zq.db",
                    null);
            Log.e(SqliteTest.TAG, "Connect sucessfully!");
            Statement v7 = v0.createStatement();
            ResultSet v6 = v7.executeQuery("SELECT * FROM app_selected");
            while(v6.next()) {
                System.out.println("id is " + v6.getString("app_name") + " name is " + v6.getString(
                        "package_name"));
            }

            v7.close();
            v0.close();
            return;
        }
        catch(SQLException v2) {
            Log.e(SqliteTest.TAG, "Fail！");
            System.out.println(v2.getMessage());
            return;
        }
    }
}
