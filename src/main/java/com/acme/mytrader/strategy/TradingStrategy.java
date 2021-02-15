package com.acme.mytrader.strategy;

import com.acme.mytrader.execution.ExecutionService;
import com.acme.mytrader.price.PriceListener;
import javafx.util.Pair;

/**
 * <pre>
 * User Story: As a trader I want to be able to monitor stock prices such
 * that when they breach a trigger level orders can be executed automatically
 * </pre>
 */
public class TradingStrategy implements PriceListener{

    /** The stock and units are configurable properties **/
    private final Pair<String , Double> ibmStock = new Pair("IBM", 55.0);
    private final Integer UNITS = 100;

    private final ExecutionService executionService;

    public TradingStrategy(ExecutionService executionService){
        this.executionService = executionService;
    }

    @Override
    public void priceUpdate(String security, double price) {
        if(security.equalsIgnoreCase(ibmStock.getKey()) && price<ibmStock.getValue()){
            executionService.sell(security,price,UNITS);
        }
    }
}
