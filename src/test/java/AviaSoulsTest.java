import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AviaSoulsTest {

    @Test
    public void ticketCompareToShouldSortByPrice() {
        Ticket t1 = new Ticket("VKO", "LED", 5000, 10, 12);
        Ticket t2 = new Ticket("VKO", "LED", 7000, 10, 12);
        assertTrue(t1.compareTo(t2) < 0);
        assertTrue(t2.compareTo(t1) > 0);
        assertEquals(0, t1.compareTo(new Ticket("VKO", "LED", 5000, 10, 12)));
    }

    @Test
    public void searchShouldSortByPrice() {
        AviaSouls souls = new AviaSouls();
        souls.add(new Ticket("VKO", "LED", 7000, 10, 12));
        souls.add(new Ticket("VKO", "LED", 5000, 10, 12));
        souls.add(new Ticket("VKO", "LED", 6000, 10, 12));

        Ticket[] expected = {
                new Ticket("VKO", "LED", 5000, 10, 12),
                new Ticket("VKO", "LED", 6000, 10, 12),
                new Ticket("VKO", "LED", 7000, 10, 12)
        };
        Ticket[] actual = souls.search("VKO", "LED");

        assertArrayEquals(expected, actual);
    }

    @Test
    public void ticketTimeComparatorShouldSortByDuration() {
        Ticket t1 = new Ticket("VKO", "LED", 7000, 10, 12); // 2ч
        Ticket t2 = new Ticket("VKO", "LED", 7000, 10, 15); // 5ч
        Ticket t3 = new Ticket("VKO", "LED", 7000, 10, 13); // 3ч

        TicketTimeComparator comp = new TicketTimeComparator();

        assertTrue(comp.compare(t1, t2) < 0);
        assertTrue(comp.compare(t2, t3) > 0);
        assertEquals(0, comp.compare(t1, new Ticket("VKO", "LED", 7000, 10, 12)));
    }

    @Test
    public void searchAndSortByShouldSortByTime() {
        AviaSouls souls = new AviaSouls();
        souls.add(new Ticket("VKO", "LED", 7000, 10, 13)); // 3ч
        souls.add(new Ticket("VKO", "LED", 7000, 10, 15)); // 5ч
        souls.add(new Ticket("VKO", "LED", 7000, 10, 12)); // 2ч

        TicketTimeComparator comp = new TicketTimeComparator();

        Ticket[] expected = {
                new Ticket("VKO", "LED", 7000, 10, 12), // 2ч
                new Ticket("VKO", "LED", 7000, 10, 13), // 3ч
                new Ticket("VKO", "LED", 7000, 10, 15)  // 5ч
        };

        Ticket[] actual = souls.searchAndSortBy("VKO", "LED", comp);

        assertArrayEquals(expected, actual);
    }
}
