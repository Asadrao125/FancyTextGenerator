package com.android.app.fancytextreplacer.font_utils;

import androidx.annotation.NonNull;

import com.android.app.fancytextreplacer.interfaces.Style;

public class RightEffect implements Style {

    String character;

    public RightEffect(String text) {
        this.character = text;
    }

    @NonNull
    @Override
    public String generate(@NonNull String input) {
        try {
            StringBuilder result = new StringBuilder();
            for (int i = 0; i < input.length(); i++) {
                if (input.charAt(i) == ' ') {
                    result.append(" ");
                } else {
                    result.append(input.charAt(i)).append(character);
                }
            }
            return result.toString();
        } catch (OutOfMemoryError e) {
            return "";
        }
    }

    @Override
    public int hashCode() {
        return character.hashCode();
    }
}
