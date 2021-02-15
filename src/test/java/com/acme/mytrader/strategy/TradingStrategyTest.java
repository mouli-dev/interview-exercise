package com.acme.mytrader.strategy;

import com.acme.mytrader.execution.ExecutionService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TradingStrategyTest {

    @InjectMocks
    private TradingStrategy tradingStrategy;

    @Mock
    private ExecutionService executionService;

    private final Integer UNITS = 100;

    @Test
    public void testSecurity_Execution_Success_SecurityAndThresholdMatch() {
        String security = "IBM";
        Double price = 54.9;
        tradingStrategy.priceUpdate(security, price);
        Mockito.verify(executionService, Mockito.times(1)).sell(security, price, UNITS);
    }

    @Test
    public void testSecurity_NotExecuted_SecurityMatchAndNoThresholdMatch() {
        String security = "IBM";
        Double price = 56.89;
        tradingStrategy.priceUpdate(security, price);
        Mockito.verify(executionService, Mockito.never()).sell(security, price, UNITS);
    }

    @Test
    public void testSecurity_NotExecuted_NoSecurityMatch() {
        String security = "DB";
        Double price = 48.89;
        tradingStrategy.priceUpdate(security, price);
        Mockito.verify(executionService, Mockito.never()).sell(security, price, UNITS);
    }

}
