package com.becheer.donation.configs;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix="file.resource")
public class FileConfig {

    public String imageRoot;

    public String docRoot;

    public String getImageRoot() {
        return imageRoot;
    }

    public void setImageRoot(String imageRoot) {
        this.imageRoot = imageRoot;
    }

    public String getDocRoot() {
        return docRoot;
    }

    public void setDocRoot(String docRoot) {
        this.docRoot = docRoot;
    }
}
