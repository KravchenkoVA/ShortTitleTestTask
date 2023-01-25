import org.apache.commons.lang3.RandomStringUtils;
import org.example.TitleUtils;
import org.example.exception.TitleParseException;
import org.junit.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class TitleTest {
    //I could add checks for space, null, other languages. It all depends on requirements and specifications.

    @ParameterizedTest(name = "{index} - {0} is included in its entirety")
    @ValueSource(strings = {"ASDFGHJKL QWERTYUIOP ZXC", "ЙЦУКЕНГШЩЗХ ФЫВАПРОЛДЖ я"})
    public void checkArticleShorterThan25Characters(String article) {
        Assert.assertEquals(TitleUtils.getTitle(article), article);
    }

    @DisplayName("Checking an article longer than 25 characters")
    @Test
    public void checkArticleMore25Characters() {
        String article = "Volvo released a new car with the following spec: V6 236HP. It will cost $22647 " +
                "and going to be sold in New York only";
        Assert.assertEquals(TitleUtils.getTitle(article), "Volvo released a new…");
    }

    @ParameterizedTest(name = "{index} - {0} cut off after 25 characters")
    @ValueSource(strings = {"Volvo released a new car with the following", "Вольво представила новую машину " +
            "со следующими характеристиками"})
    public void checkArticleLongerThan25CharactersWithRegexp(String article) {
        String regExp = ".{1,25}…";
        Assert.assertTrue(TitleUtils.getTitle(article).matches(regExp));
    }

    @DisplayName("Check empty article")
    @Test
    public void checkEmptyArticle() {
        TitleParseException thrown = Assert.assertThrows(TitleParseException.class, ()->{TitleUtils.getTitle("");});
        Assert.assertEquals(thrown.getMessage(), "Article parse error");
    }

    @DisplayName("Checking an article starting with a word longer than 24 characters")
    @Test
    public void checkArticleWithWordMore25Characters() {
        TitleParseException thrown =
        Assert.assertThrows(TitleParseException.class, ()->{
            TitleUtils.getTitle(RandomStringUtils.randomAlphabetic(25));
        });
        Assert.assertEquals(thrown.getMessage(), "Word longer than 24 characters");
    }
}