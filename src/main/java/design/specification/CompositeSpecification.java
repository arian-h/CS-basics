package design.specification;

public interface CompositeSpecification<T> {

    boolean conforms(T subject);

}
