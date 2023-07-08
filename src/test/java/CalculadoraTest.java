import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CalculadoraTest {

    private final Calculadora calculadora = new Calculadora();

    @Test
    public void testSomar() {

        assertEquals(calculadora.soma(1, 2), 3);
    }

    @Test
    public void testDividir() {
        assertEquals(calculadora.dividir(6, 2), 3);
    }

    @Test
    public void deveLancarExcecaoQuandoDividirPorZeroJUnit4() {
        try {
            float resultado = 10 / 0;
            Assertions.fail("Deveria ter lançado uma exceção");
        } catch (ArithmeticException e) {
            Assertions.assertEquals("/ by zero", e.getMessage());
        }
    }

    @Test
    public void deveLancarExcecaoQuandoDividirPorZeroJUnit5() {
        Exception exception = assertThrows(Exception.class, () -> {
            float resultado = 10 / 0;
        });
            Assertions.assertEquals("/ by zero", exception.getMessage());
    }
}
