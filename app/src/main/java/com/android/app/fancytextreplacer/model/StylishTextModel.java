package com.android.app.fancytextreplacer.model;

public class StylishTextModel {
    String text;
    int font;

    public StylishTextModel(String text, int font) {
        this.text = text;
        this.font = font;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getFont() {
        return font;
    }

    public void setFont(int font) {
        this.font = font;
    }
}
