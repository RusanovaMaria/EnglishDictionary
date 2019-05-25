package com.rusanova.englishdictionary;

import java.util.UUID;

public interface Element {

    String getName();
    String getDescription();
    void setName(String name);
    void setDescription(String description);
    UUID getId();
    void setId(UUID id);
}
