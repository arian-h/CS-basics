package design.specification;

public class AgeSpecification implements CompositeSpecification<Person> {

    @Override
    public boolean conforms(Person subject) {
        return subject.getAge() >= 21;
    }

}
