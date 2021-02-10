package clean.entities;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

public class CalculationOperationsTest {

    @Test
    public void returnsSum_whenAdditionSelected() {
        BigDecimal result = CalculationOperations.ADDITION.calculate(new BigDecimal("10"), new BigDecimal("15"));

        assertEquals(result, new BigDecimal("25"));
    }

    @Test
    public void returnsResult_whenSubtractionSelected() {
        BigDecimal result = CalculationOperations.SUBTRACTION.calculate(new BigDecimal("10"), new BigDecimal("15"));

        assertEquals(result, new BigDecimal("-5"));
    }

    @Test
    public void returnsResult_whenMultiplicationSelected() {
        BigDecimal result = CalculationOperations.MULTIPLICATION.calculate(new BigDecimal("10"), new BigDecimal("15"));

        assertEquals(result, new BigDecimal("150"));
    }

    @Test
    public void returnsResult_whenDivisionSelected() {
        BigDecimal result = CalculationOperations.DIVISION.calculate(new BigDecimal("10"), new BigDecimal("15"));

        assertEquals(result, new BigDecimal("150"));
    }

}
