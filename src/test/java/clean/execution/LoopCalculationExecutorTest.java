package clean.execution;

import clean.entities.CalculationOperations;
import clean.entities.CalculationStatement;
import clean.operationconstrution.OperationConstructor;
import clean.userinteraction.UserInteractor;
import clean.userinteraction.UserTextPrinter;
import clean.userinteraction.resulthandling.CalculationResultsPrinter;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class LoopCalculationExecutorTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();
    @InjectMocks
    public LoopCalculationExecutor loopCalculationExecutor;
    @Mock
    private OperationConstructor operationConstructor;
    @Mock
    private UserInteractor userInteractor;
    @Mock
    private UserTextPrinter userTextPrinter;
    @Mock
    private CalculationResultsPrinter calculationResultsPrinter;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testSingleApplicationExecution() throws Exception {
        CalculationStatement statement = new CalculationStatement(new BigDecimal(10), new BigDecimal(5), CalculationOperations.ADDITION);
        when(operationConstructor.fetchCalculationDetails()).thenReturn(statement);
        when(userInteractor.writeAndGetResponse("Continue? Type TRUE for yes, FALSE - otherwise.")).thenReturn("False");
        loopCalculationExecutor.execute();

        assertEquals(statement.getFirstOperand(), new BigDecimal(10));
        assertEquals(statement.getSecondOperand(), new BigDecimal(5));
        assertEquals(statement.getOperation(), CalculationOperations.ADDITION);
        verify(calculationResultsPrinter).presentResults(statement);

    }

    @Test
    public void continuousUsageTest() throws Exception {
        CalculationStatement statement = new CalculationStatement(new BigDecimal(10), new BigDecimal(5), CalculationOperations.ADDITION);
        CalculationStatement statement2 = new CalculationStatement(new BigDecimal(11), new BigDecimal(6), CalculationOperations.SUBTRACTION);

        when(operationConstructor.fetchCalculationDetails()).thenReturn(statement);
        when(userInteractor.writeAndGetResponse("Continue? Type TRUE for yes, FALSE - otherwise.")).thenReturn("True");
        when(operationConstructor.fetchCalculationDetails()).thenReturn(statement2);
        when(userInteractor.writeAndGetResponse("Continue? Type TRUE for yes, FALSE - otherwise.")).thenReturn("False");
        loopCalculationExecutor.execute();


        assertEquals(statement.getFirstOperand(), new BigDecimal(10));
        assertEquals(statement.getSecondOperand(), new BigDecimal(5));
        assertEquals(statement.getOperation(), CalculationOperations.ADDITION);
        assertEquals(statement2.getFirstOperand(), new BigDecimal(11));
        assertEquals(statement2.getSecondOperand(), new BigDecimal(6));
        assertEquals(statement2.getOperation(), CalculationOperations.SUBTRACTION);

        verify(calculationResultsPrinter).presentResults(statement2);

    }
}

