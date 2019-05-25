package com.rusanova.englishdictionary;

public class DictionaryDbSchema {
    public static final class DictionaryTable {
        public static final String NAME = "dictionaries";

        public static final class Cols {
            public static final String UUID = "uuid";
            public static final String NAME = "name";
            public static final String DESCRIPTION = "description";
        }
    }
}
