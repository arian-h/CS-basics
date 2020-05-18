package leetCode;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class MostOccurringPatternUT {

    @Test
    public void testFind() {
        MostOccurringPattern patternFinder = new MostOccurringPattern();
        List<String> pattern = patternFinder.mostVisitedPattern(new String[] {"joe","joe","joe","james","james","james",
                "james","mary","mary","mary"}, new int[] {0, 1, 2, 3, 4, 5, 6, 7, 8, 9},
                new String[] {"home","about","career","home","cart","maps", "home","home","about","career"});
        Assert.assertEquals(Arrays.asList("home", "about", "career"), pattern);
    }

    @Test
    public void testFind_2() {
        MostOccurringPattern patternFinder = new MostOccurringPattern();
        List<String> pattern = patternFinder.mostVisitedPattern(new String[] {"h","eiy","cq","h","cq","txldsscx",
                        "cq","txldsscx","h","cq","cq"}, new int[] {527896567,334462937,517687281,134127993,859112386,
                        159548699,51100299,444082139,926837079,317455832,411747930},
                new String[] {"hibympufi","hibympufi","hibympufi","hibympufi","hibympufi","hibympufi","hibympufi",
                        "hibympufi","yljmntrclw","hibympufi","yljmntrclw"});
        Assert.assertEquals(Arrays.asList("hibympufi","hibympufi","yljmntrclw"), pattern);
    }
}