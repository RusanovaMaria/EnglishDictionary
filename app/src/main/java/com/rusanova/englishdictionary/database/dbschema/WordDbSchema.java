package com.rusanova.englishdictionary.database.dbschema;

public class WordDbSchema {
    public static final class WordTable {
        public static final String NAME = "words";

        public static final class Cols {
            public static final String UUID = "word_uuid";
            public static final String NAME = "word_name";
            public static final String TRANSLATION = "word_translation";
            public static final String DICTIONARY_UUID = "word_dictionary_uuid";
        }
    }
}
