package design.specification;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;

@Getter
public class NorthAmericanSpecification extends NationalitySpecification {

    private final List<Person.Nationality> nationalities = Arrays.asList(Person.Nationality.AMERICAN,
            Person.Nationality.CANADIAN);

}
