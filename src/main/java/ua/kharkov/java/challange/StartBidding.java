package ua.kharkov.java.challange;

import ua.kharkov.java.challange.service.OrderMatchingService;

public class StartBidding {

  /**
   * BUY 100 100
   * SELL 100 100
   * =============
   * TRADE 100 100
   *
   * SELL 100 100
   * buy 100 100
   * =============
   * TRADE 100 100
   *
   * (two sides trading)
   *
   * Key insensitive input.
   * Show error while input illegal command.
   * Orders cleared after amount of goods is 0.
   */

  public static void main(String[] args) {
    OrderMatchingService orderMatchingService = new OrderMatchingService();
    orderMatchingService.run();
  }
}

