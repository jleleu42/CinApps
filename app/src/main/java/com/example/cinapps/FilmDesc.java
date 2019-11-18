package com.example.cinapps;

import android.provider.BaseColumns;

public class FilmDesc {
    public static final class FilmDescEntry implements BaseColumns {
        public static final String TABLE_NAME = "film";
        public static final String COLUMN_TITLE = "title";
        public static final String COLUMN_DATE = "date";
        public static final String COLUMN_SCENARIO = "scenario";
        public static final String COLUMN_REALISATION = "realisation";
        public static final String COLUMN_MUSIQUE = "musique";
        public static final String COLUMN_DESCRIPTION = "description";
    }
}
