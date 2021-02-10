package clean.userinteraction.resulthandling;

import clean.entities.CalculationOperations;
import clean.entities.CalculationStatement;
import clean.exceptions.ResultNotPresentException;
import clean.userinteraction.UserTextPrinter;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class CalculationResultsPrinterTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Mock
    UserTextPrinter userTextPrinter;

    @InjectMocks
    CalculationResultsPrinter calculationResultsPrinter;

    @Before
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void existingResultsTest() throws Exception {
        expectedException.expect(ResultNotPresentException.class);

        CalculationStatement calculationStatement = new CalculationStatement(new BigDecimal(10), new BigDecimal(5), CalculationOperations.ADDITION);
        calculationResultsPrinter.presentResults(calculationStatement);
        assertFalse(calculationStatement.getResult().isPresent());
    }

    @Test
    public void testResultPrint() {
        CalculationStatement calculationStatement = new CalculationStatement(new BigDecimal(10), new BigDecimal(5), CalculationOperations.ADDITION);
        calculationStatement.setResult(Optional.of(new BigDecimal(15)));
        String message = String.format("%s %s %s is %s",
                calculationStatement.getFirstOperand(),
                calculationStatement.getOperation().getOperationSymbol(),
                calculationStatement.getSecondOperand(),
                calculationStatement.getResult().get());
        assertEquals(message, "10 + 5 is 15");
    }


}
