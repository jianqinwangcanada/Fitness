package com.example.web.wbfitness.JavaBean;

public class Credits {
    private String header;
    private String description;

    public Credits() {
    }

    public Credits(String header, String description) {
        this.header = header;
        this.description = description;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Credits{" +
                "header='" + header + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
