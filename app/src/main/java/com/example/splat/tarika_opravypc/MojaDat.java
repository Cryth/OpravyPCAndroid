package com.example.splat.tarika_opravypc;

/**
 * Created by splat on 31.3.2018.
 */

public final class MojaDat {
    public static class Customers{
        public static final String TABLE_NAME = "customers";
        public static final String COLUMN_ID = "_id";
        public static final String COLUMN_NAME = "Meno";
        public static final String COLUMN_EMAIL = "Email";
    }
    public static class Computers{
        public static final String TABLE_NAME = "computers";
        public static final String COLUMN_ID = "_id";
        public static final String COLUMN_CID = "cID";
        public static final String COLUMN_BRAND = "Znacka";
        public static final String COLUMN_MODEL = "Model";
    }
    public static class Repairs{
        public static final String TABLE_NAME = "repairs";
        public static final String COLUMN_ID = "_id";
        public static final String COLUMN_COID = "coID";
        public static final String COLUMN_DATE = "Datum";
        public static final String COLUMN_OBJECT = "Predmet";
        public static final String COLUMN_ABOUT = "Popis";
    }
}
