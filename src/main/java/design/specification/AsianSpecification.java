package design.specification;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;

@Getter
public class AsianSpecification extends NationalitySpecification {

    private final List<Person.Nationality> nationalities = Arrays.asList(Person.Nationality.CHINESE,
            Person.Nationality.INDIAN, Person.Nationality.IRANIAN);

}
