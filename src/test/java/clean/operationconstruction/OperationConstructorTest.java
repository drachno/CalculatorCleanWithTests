package clean.operationconstruction;

import clean.entities.CalculationOperations;
import clean.entities.CalculationStatement;
import clean.exceptions.InvalidInformationEnteredException;
import clean.operationconstrution.OperationConstructor;
import clean.userinteraction.UserInteractor;
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

public class OperationConstructorTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Mock
    private UserInteractor userInteractor;

    @InjectMocks
    private OperationConstructor operationConstructor;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void fails_whenInvalidOperationProvided() throws Exception {
        expectedException.expect(InvalidInformationEnteredException.class);
        expectedException.expectMessage("Wrong operation");

        when(userInteractor.writeAndGetResponse("Enter operation")).thenReturn("Not valid operation");

        operationConstructor.fetchCalculationDetails();
    }

    @Test
    public void fails_whenInvalidOperand1Provided() throws Exception {
        expectedException.expect(InvalidInformationEnteredException.class);
        expectedException.expectMessage("Wrong first operand");

        when(userInteractor.writeAndGetResponse("Enter operation")).thenReturn("ADDITION");
        when(userInteractor.writeAndGetResponse("Enter first operand")).thenReturn("");

        operationConstructor.fetchCalculationDetails();
    }

    @Test
    public void fails_whenInvalidOperand2Provided() throws Exception {
        expectedException.expect(InvalidInformationEnteredException.class);
        expectedException.expectMessage("Wrong second operand");

        when(userInteractor.writeAndGetResponse("Enter operation")).thenReturn("ADDITION");
        when(userInteractor.writeAndGetResponse("Enter first operand")).thenReturn("10");
        when(userInteractor.writeAndGetResponse("Enter second operand")).thenReturn("");

        operationConstructor.fetchCalculationDetails();
    }

    @Test
    public void CalculationDetailsVerifier() throws Exception {

        when(userInteractor.writeAndGetResponse("Enter operation")).thenReturn("ADDITION");
        when(userInteractor.writeAndGetResponse("Enter first operand")).thenReturn("11");
        when(userInteractor.writeAndGetResponse("Enter second operand")).thenReturn("12");

        CalculationStatement result = operationConstructor.fetchCalculationDetails();

        assertEquals(result.getFirstOperand(), new BigDecimal("11"));
        assertEquals(result.getSecondOperand(), new BigDecimal("12"));
        assertEquals(result.getOperation(), CalculationOperations.ADDITION);
    }
}
