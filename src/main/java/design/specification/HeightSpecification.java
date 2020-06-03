package design.specification;

public class HeightSpecification implements CompositeSpecification<Person> {

    @Override
    public boolean conforms(Person subject) {
        return subject.getHeight() > 170;
    }
}
