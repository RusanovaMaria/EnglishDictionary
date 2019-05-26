package com.rusanova.englishdictionary;

public class WordDbSchema {
    public static final class WordTable {
        public static final String NAME = "words";

        public static final class Cols {
            public static final String UUID = "uuid";
            public static final String NAME = "name";
            public static final String TRANSLATION = "translation";
            public static final String DICTIONARY_UUID = "dictionary_uuid";
        }
    }
}
