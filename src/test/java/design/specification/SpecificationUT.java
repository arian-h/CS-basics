package design.specification;

import org.junit.Assert;
import org.junit.Test;

public class SpecificationUT {

    @Test
    public void testAnd() {
        Person person = Person.builder()
                .age(19)
                .nationality(Person.Nationality.AMERICAN)
                .height(189)
                .build();
        CompositeSpecification<Person> specification = new AndSpecification<>(new AgeSpecification(),
                new NorthAmericanSpecification());
        Assert.assertFalse(specification.conforms(person));
    }

    @Test
    public void testOr() {
        Person person = Person.builder()
                .age(19)
                .nationality(Person.Nationality.CHINESE)
                .height(170)
                .build();
        CompositeSpecification<Person> specification = new OrSpecification<>(new AgeSpecification(),
                new AsianSpecification());
        Assert.assertTrue(specification.conforms(person));
    }

    @Test
    public void testNot() {
        Person person = Person.builder()
                .age(19)
                .nationality(Person.Nationality.CHINESE)
                .height(170)
                .build();
        CompositeSpecification<Person> specification = new NotSpecification<>(new AgeSpecification());
        Assert.assertTrue(specification.conforms(person));
    }

    @Test
    public void testComposite() {
        Person person = Person.builder()
                .age(19)
                .nationality(Person.Nationality.AMERICAN)
                .height(172)
                .build();
        //either over 21, or not asian and over 170 of height
        CompositeSpecification<Person> specification = new OrSpecification<>(new AgeSpecification(),
                new AndSpecification<>(new NotSpecification<>(new AsianSpecification()), new HeightSpecification()));
        Assert.assertTrue(specification.conforms(person));
    }
}
