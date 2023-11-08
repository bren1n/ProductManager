package br.ufrn.imd.productmanager.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import br.ufrn.imd.productmanager.database.ProcuctContract.ProductData;

public class ProductDbHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Product.db";

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + ProductData.TABLE_NAME + " (" +
                    ProductData._ID + " INTEGER PRIMARY KEY," +
                    ProductData.COLUMN_NAME_CODE + " INTEGER," +
                    ProductData.COLUMN_NAME_NAME + " TEXT," +
                    ProductData.COLUMN_NAME_DESCRIPTION + " TEXT," +
                    ProductData.COLUMN_NAME_QUANTITY + " INTEGER)";

    public ProductDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {}
}
