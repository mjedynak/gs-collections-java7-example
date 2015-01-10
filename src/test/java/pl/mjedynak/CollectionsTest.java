package pl.mjedynak;

import com.gs.collections.api.block.function.primitive.IntFunction;
import com.gs.collections.api.block.predicate.Predicate;
import com.gs.collections.api.list.MutableList;
import com.gs.collections.impl.list.mutable.FastList;
import com.gs.collections.impl.utility.ListIterate;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static com.gs.collections.impl.utility.ListIterate.select;
import static com.gs.collections.impl.utility.ListIterate.sumOfInt;
import static java.util.Arrays.asList;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class CollectionsTest {

    private List<Person> people;
    private Person robin;
    private Person shinji;
    private Person tom;

    @Before
    public void setUp() {
        robin = new Person("Robin van Persie", 29);
        shinji = new Person("Shinji Kagawa", 23);
        tom = new Person("Tom Cleverley", 23);
        people = asList(robin, shinji, tom);
    }

    @Test
    public void selectsByAge() {
        MutableList<Person> result = select(people, PEOPLE_WITH_AGE_23);

        MutableList<Person> gsPeople = FastList.newList(people);
        MutableList<Person> resultWithGSCollections = gsPeople.select(PEOPLE_WITH_AGE_23);


        List<Person> expectedResult = asList(shinji, tom);
        assertThat(resultWithGSCollections, is(expectedResult));
        assertThat(result, is(expectedResult));
    }

    private static final Predicate<Person> PEOPLE_WITH_AGE_23 = new Predicate<Person>() {
        @Override
        public boolean accept(Person person) {
            return person.getAge() == 23;
        }
    };

    @Test
    public void sumsAge() {
        int result = (int) sumOfInt(people, AGE);

        MutableList<Person> gsPeople = FastList.newList(people);
        int resultWithGSCollections = (int) gsPeople.sumOfInt(AGE);


        int expectedResult = 75;
        assertThat(resultWithGSCollections, is(expectedResult));
        assertThat(result, is(expectedResult));
    }


    private static final IntFunction<Person> AGE = new IntFunction<Person>() {
        @Override
        public int intValueOf(Person person) {
            return person.getAge();
        }
    };
}
