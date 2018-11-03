package ua.kharkov.java.challange.entity;

import ua.kharkov.java.challange.service.Command;

import java.util.List;
import java.util.stream.Collectors;

public class Order extends Entity {

  public Order(Command command, int price, int amount) {
    super(command, price, amount);
  }

  public void tradeSell(List<Order> orders, List<Trade> trades) {
    int currentAmount = this.getAmount();
    int currentPrice = this.getPrice();

    List<Order> buyOrders = orders
      .stream()
      .filter(order -> order.getCommand().equals(Command.BUY))
      .collect(Collectors.toList());

    for (Order buyOrder: buyOrders) {
      if (buyOrder.getPrice() >= currentPrice) {
        while (buyOrder.getAmount() > 0 && currentAmount > 0) {
          buyOrder.setAmount(buyOrder.getAmount() - 1);
          currentAmount -= 1;
        }

        if (buyOrder.getAmount() == 0) {
          orders.remove(buyOrder);
        }

        if (currentAmount == 0) {
          orders.remove(this);
        }
      }
    }

    if (this.getAmount() != currentAmount) {
      Trade trade = new Trade(currentPrice, this.getAmount() - currentAmount);
      trades.add(trade);
    }

    this.setAmount(currentAmount);
  }

  public void tradeBuy(List<Order> orders, List<Trade> trades) {
    int currentAmount = this.getAmount();
    int currentPrice = this.getPrice();

    List<Order> sellOrders = orders
      .stream()
      .filter(order -> order.getCommand().equals(Command.SELL))
      .collect(Collectors.toList());

    for (Order sellOrder: sellOrders) {
      if (sellOrder.getPrice() <= currentPrice) {
        while (sellOrder.getAmount() > 0 && currentAmount > 0) {
          sellOrder.setAmount(sellOrder.getAmount() - 1);
          currentAmount -= 1;
        }
        if (sellOrder.getAmount() == 0) {
          orders.remove(sellOrder);
        }

        if (currentAmount == 0) {
          orders.remove(this);
        }
      }
    }

    if (this.getAmount() != currentAmount) {
      Trade trade = new Trade(currentPrice, this.getAmount() - currentAmount);
      trades.add(trade);
    }

    this.setAmount(currentAmount);
  }

}
