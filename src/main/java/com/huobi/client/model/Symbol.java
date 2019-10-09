package com.huobi.client.model;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

import lombok.ToString;

import com.huobi.client.impl.RestApiJsonParser;
import com.huobi.client.impl.utils.JsonWrapperArray;

/**
 * The Huobi supported symbols.
 */
@ToString
public class Symbol {

  private String baseCurrency;
  private String quoteCurrency;
  private int pricePrecision;
  private int amountPrecision;
  private String symbolPartition;
  private String symbol;

  private Integer valuePrecision;
  private BigDecimal minOrderAmt;
  private BigDecimal maxOrderAmt;
  private BigDecimal minOrderValue;
  private Integer leverageRatio;


  /**
   * Get the base currency in a trading symbol.
   *
   * @return The currency.
   */
  public String getBaseCurrency() {
    return baseCurrency;
  }

  /**
   * Get the quote currency in a trading symbol.
   *
   * @return The currency.
   */
  public String getQuoteCurrency() {
    return quoteCurrency;
  }

  /**
   * Get the quote currency precision when quote price (decimal places).
   *
   * @return The precision.
   */
  public int getPricePrecision() {
    return pricePrecision;
  }

  /**
   * Get the base currency precision when quote amount (decimal places).
   *
   * @return The precision.
   */
  public int getAmountPrecision() {
    return amountPrecision;
  }

  /**
   * Get the trading section, possible values: [main，innovation，bifurcation].
   *
   * @return The partition.
   */
  public String getSymbolPartition() {
    return symbolPartition;
  }

  /**
   * Get the symbol, like "btcusdt".
   *
   * @return The symbol.
   */
  public String getSymbol() {
    return symbol;
  }

  /**
   * Get the trading amount precision .
   * @return
   */
  public Integer getValuePrecision() {
    return valuePrecision;
  }

  /**
   * Get the min order size .
   * @return
   */
  public BigDecimal getMinOrderAmt() {
    return minOrderAmt;
  }

  /**
   * Get the max order size .
   * @return
   */
  public BigDecimal getMaxOrderAmt() {
    return maxOrderAmt;
  }

  /**
   * Get the min order amount .
   * @return
   */
  public BigDecimal getMinOrderValue() {
    return minOrderValue;
  }

  /**
   * Get the maximum leverage
   * @return
   */
  public Integer getLeverageRatio() {
    return leverageRatio;
  }

  public void setBaseCurrency(String baseCurrency) {
    this.baseCurrency = baseCurrency;
  }

  public void setQuoteCurrency(String quoteCurrency) {
    this.quoteCurrency = quoteCurrency;
  }

  public void setPricePrecision(int pricePrecision) {
    this.pricePrecision = pricePrecision;
  }

  public void setAmountPrecision(int amountPrecision) {
    this.amountPrecision = amountPrecision;
  }

  public void setSymbolPartition(String symbolPartition) {
    this.symbolPartition = symbolPartition;
  }

  public void setSymbol(String symbol) {
    this.symbol = symbol;
  }

  public void setValuePrecision(Integer valuePrecision) {
    this.valuePrecision = valuePrecision;
  }

  public void setMinOrderAmt(BigDecimal minOrderAmt) {
    this.minOrderAmt = minOrderAmt;
  }

  public void setMaxOrderAmt(BigDecimal maxOrderAmt) {
    this.maxOrderAmt = maxOrderAmt;
  }

  public void setMinOrderValue(BigDecimal minOrderValue) {
    this.minOrderValue = minOrderValue;
  }

  public void setLeverageRatio(Integer leverageRatio) {
    this.leverageRatio = leverageRatio;
  }

  public static RestApiJsonParser<List<Symbol>> getListParser(){
    return (jsonWrapper -> {
      List<Symbol> symbolList = new LinkedList<>();
      JsonWrapperArray dataArray = jsonWrapper.getJsonArray("data");
      dataArray.forEach((item) -> {
        Symbol symbol = new Symbol();
        symbol.setBaseCurrency(item.getString("base-currency"));
        symbol.setQuoteCurrency(item.getString("quote-currency"));
        symbol.setPricePrecision(item.getInteger("price-precision"));
        symbol.setAmountPrecision(item.getInteger("amount-precision"));
        symbol.setSymbolPartition(item.getString("symbol-partition"));
        symbol.setSymbol(item.getString("symbol"));
        symbol.setValuePrecision(item.getIntegerOrDefault("value-precision", null));
        symbol.setMinOrderAmt(item.getBigDecimalOrDefault("min-order-amt", null));
        symbol.setMaxOrderAmt(item.getBigDecimalOrDefault("max-order-amt", null));
        symbol.setMinOrderValue(item.getBigDecimalOrDefault("min-order-value", null));
        symbol.setLeverageRatio(item.getIntegerOrDefault("leverage-ratio", null));
        symbolList.add(symbol);

      });
      return symbolList;
    });
  }
}
