package design.specification;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Person {

    public enum Nationality {
        IRANIAN, AMERICAN, CANADIAN, INDIAN, CHINESE;
    }

    private final int age;
    private final int height;
    private final Nationality nationality;

}
