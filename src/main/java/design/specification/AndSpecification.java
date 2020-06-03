package design.specification;

public class AndSpecification <T> implements CompositeSpecification<T> {

    private final CompositeSpecification<T> spec1, spec2;

    public AndSpecification(CompositeSpecification<T> spec1, CompositeSpecification<T> spec2) {
        this.spec1 = spec1;
        this.spec2 = spec2;
    }

    @Override
    public boolean conforms(T subject) {
        return spec1.conforms(subject) && spec2.conforms(subject);
    }

}
