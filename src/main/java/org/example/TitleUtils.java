package org.example;

import lombok.experimental.UtilityClass;
import org.example.exception.TitleParseException;

@UtilityClass
public class TitleUtils {

    public String getTitle(String article){
        if (article.isEmpty()){
            throw new TitleParseException(article);
        }
        else
            if(article.length()>=25){
                int dividerPosition = article.substring(0, 24).lastIndexOf(" ");
            if (dividerPosition==-1){
                throw new TitleParseException();
            }
            else article = article.substring(0, dividerPosition)+"…";
        }
        return article;
    }
}
