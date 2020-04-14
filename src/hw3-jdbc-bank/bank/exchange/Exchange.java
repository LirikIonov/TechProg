package bank.exchange;

import exception.Trouble;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class Exchange {
	public static final BigDecimal USD_TO_RUB = BigDecimal.valueOf(73.15);
	public static final BigDecimal EUR_TO_RUB = BigDecimal.valueOf(79.99);
	public static final BigDecimal EUR_TO_USD = BigDecimal.valueOf(1.09);
	public static final MathContext mc = new MathContext(2, RoundingMode.CEILING);

	public static BigDecimal getRate(String from, String to) throws Trouble {
		if (from.equals("EUR") && to.equals("RUB")) return EUR_TO_RUB;
		if (from.equals("EUR") && to.equals("USD")) return EUR_TO_USD;
		if (from.equals("RUB") && to.equals("EUR")) return BigDecimal.ONE.divide(EUR_TO_RUB, mc);
		if (from.equals("RUB") && to.equals("USD")) return BigDecimal.ONE.divide(USD_TO_RUB, mc);
		if (from.equals("USD") && to.equals("EUR")) return BigDecimal.ONE.divide(EUR_TO_USD, mc);
		if (from.equals("USD") && to.equals("RUB")) return USD_TO_RUB;
		throw new Trouble("Unsupported finance type!");
	}
}
