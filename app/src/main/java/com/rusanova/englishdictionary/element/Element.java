package com.rusanova.englishdictionary.element;

import java.util.UUID;

public interface Element {


    UUID getId();

    void setId(UUID id);

    String getName();

    void setName(String name);

    String getDescription();

    void setDescription(String description);
}
