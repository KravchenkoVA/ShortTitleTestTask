package org.example.exception;

public class TitleParseException extends RuntimeException{

    public TitleParseException(String article) {
        super("Article parse error" + article);
    }

    public TitleParseException() {
        super("Word longer than 24 characters");
    }
}
