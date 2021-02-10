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
import static org.mockito.Mockito.when;

public class LoopCalculationExecutorTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Mock
    private OperationConstructor operationConstructor;

    @Mock
    private UserInteractor userInteractor;

    @Mock
    private UserTextPrinter userTextPrinter;

    @Mock
    private CalculationResultsPrinter calculationResultsPrinter;

    @InjectMocks
    public LoopCalculationExecutor loopCalculationExecutor;

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
    }

    @Test
    public void continuousUsageTest() throws Exception {
        when(userInteractor.writeAndGetResponse("Continue? Type TRUE for yes, FALSE - otherwise.")).thenReturn("True");
        //loopCalculationExecutor.execute();
    }
}
