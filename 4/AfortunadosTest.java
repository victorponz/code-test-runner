
import static org.junit.Assert.assertEquals;


import org.junit.Test;

public class AfortunadosTest {

     @Test
    public void test() {
        assertEquals("2", Afortunados.calcular(3).trim());
        assertEquals("4 6 10", Afortunados.calcular(10).trim());
        assertEquals("10 12 18 22 30", Afortunados.calcular(30).trim());
        assertEquals("30 34 42 48 58 60 78 82", Afortunados.calcular(100).trim());    
    }

}

