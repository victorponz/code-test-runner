import org.junit.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MyTest {
    My m = new My();

    @Test
    public void testAddition() {
        My m = new My();
    }

    @Test
    public void testSubtraction(){
        assertEquals(5, m.sum(3, 2));
    }
}
