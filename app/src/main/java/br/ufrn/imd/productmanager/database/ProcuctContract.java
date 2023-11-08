package br.ufrn.imd.productmanager.database;

import android.provider.BaseColumns;

public class ProcuctContract {
    private ProcuctContract() {}

    public static class ProductData implements BaseColumns {
        public static final String TABLE_NAME = "procuct";
        public static final String COLUMN_NAME_CODE = "code";
        public static final String COLUMN_NAME_NAME = "name";
        public static final String COLUMN_NAME_DESCRIPTION = "description";
        public static final String COLUMN_NAME_QUANTITY = "quantity";
    }
}
