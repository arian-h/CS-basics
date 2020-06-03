package design.specification;

public class NotSpecification <T> implements CompositeSpecification<T> {

    private final CompositeSpecification<T> spec1;

    public NotSpecification(CompositeSpecification<T> spec) {
        this.spec1 = spec;
    }

    @Override
    public boolean conforms(T subject) {
        return !this.spec1.conforms(subject);
    }

}
