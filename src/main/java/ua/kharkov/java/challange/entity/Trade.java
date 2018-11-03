package ua.kharkov.java.challange.entity;

import ua.kharkov.java.challange.service.Command;

public class Trade extends Entity {

  public Trade(int price, int amount) {
    super(Command.TRADE, price, amount);
  }

}
