package com.app.transaction;
	import java.io.Serializable;
	import java.math.BigDecimal;
	import java.math.MathContext;
	import java.math.RoundingMode;
	import java.text.NumberFormat;
	import java.util.Currency;
	import static java.math.RoundingMode.HALF_UP;

	/**
	 *
	 * @author farouka
	 */
	public class Money implements Serializable {

	  /**
	   * Why me
	   */
	  private static final int[] cents = new int[]{1, 10, 100, 1000};
	  
	  private BigDecimal amount;
	  private Currency currency;

	  //private MathContext DEFAULT_CONTEXT = new MathContext( 2, HALF_UP );

	  private MathContext DEFAULT_CONTEXT = new MathContext( 10, RoundingMode.HALF_DOWN );

	  public Money(long amount, Currency currency) {
	    this.currency = currency;
	    this.amount = BigDecimal.valueOf(amount, currency.getDefaultFractionDigits()); 
	  }

	  /**
	   * Construct an IMMUTABLE money object from a double. It is assumed that 
	   * the whole part of the double is the Money with the fractional part representing
	   * lowest denominator of the currency. For eg, new Money (50.99, "GBP") is assumed
	   * to be 50 pounds and 99 pence.
	   * PS. 89.788 will be truncated to 89.78 based on the defaultcurrencydigit of the currency
	   * @param amount
	   * @param curr
	   */
	  public Money(double amount, Currency curr) {
	    this.currency = curr;
	    BigDecimal bd = BigDecimal.valueOf( amount );
	    this.amount = bd.setScale(centFactor(), HALF_UP);
	  }

	  /**
	   * Constructs an IMMUTABLE money from a BigDecimal. the BigDecimal provided is only scaled
	   * to used the default digits in currency object represented by the sting parameter
	   * @param multiply
	   * @param currency
	   */
	  public Money(BigDecimal bigDecimal, Currency currency) {
	    this.currency = currency;   
	    this.amount = bigDecimal.setScale( currency.getDefaultFractionDigits(), HALF_UP);
	  }
	  
	  //zwraca ilosc znakow po przecinku dla danej waluty
	  private int centFactor() {
	    return cents[ getCurrency().getDefaultFractionDigits() ];
	  }

	  public BigDecimal amount() {
	    return amount;
	  }

	  public Currency getCurrency() {
	    return currency;
	  }

	  private Money newMoney(BigDecimal amount) {
	    return new Money( amount, this.currency );
	  }
	  
	  public Money(double amount, String currCode) {
		  BigDecimal amountbd = new BigDecimal(amount);
		  this.amount = amountbd;
		  this.currency= Currency.getInstance(currCode);
	  }
	  
	 public String toFormattedString() {
	  NumberFormat nf = NumberFormat.getCurrencyInstance();
	  nf.setCurrency( currency );
	  nf.setGroupingUsed( true );
	  nf.setMaximumFractionDigits( currency.getDefaultFractionDigits() );
	  return nf.format( this.amount.doubleValue() );
	 }
	  
	 /**
	  * Returns the ISO-4217 currency code of the currency
	  * attached to this money.
	  * 
	  * @return The ISO-4217 currency code.
	  */
	 public String getCurrencyCode() {
	  return currency.getCurrencyCode();
	 }
	  
	  @Override
	  public String toString() {
	      return amount.toString();
	  }
	  
	 /**
	  * Returns the precision for this money. The precision is the total number
	  * of digits that the value can represent. This includes the integer part.
	  * So, 18 would be able to represent:
	  * 

	* 1234567890.12345678
	  * 

	* 1234567890123456.78
	  * 

	* 123456789012345678
	  * 

	* 0.123456789012345678
	  * 
	  * @return The precision. 
	  */ 
	 public int precision() {
	  return amount.precision();
	 }

	 /**
	  * Returns the 'scale' for this money. The scale is the number of 
	  * digits that are moved to the fractional part, assuming that all
	  * digits are represented by a single integer value. For example:
	  * 

	* If: 123456789012345678 has scaling 2, it would be :
	  * 

	* 1234567890123456.78
	  * 
	  * @return The scale value. 
	  */
	 public int scale() {
	  return amount.scale();
	 }

	 /**
	  * Returns the sign for the money (negative or positive).
	  * -1 if negative, 0 if 0.00 (zero), 1 if positive.
	  * 
	  * @return The sign of the money. 
	  */ 
	 public int signum() {
	  return amount.signum();
	 }
	}
