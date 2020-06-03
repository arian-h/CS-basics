package design.specification;

import java.util.List;

public abstract class NationalitySpecification implements CompositeSpecification<Person> {

    public abstract List<Person.Nationality> getNationalities();

    @Override
    public boolean conforms(Person subject) {
        return getNationalities().contains(subject.getNationality());
    }
}
