package ua.kharkov.java.challange.entity;

import ua.kharkov.java.challange.service.Command;

public class Entity {

  private Command command;
  private int price;
  private int amount;

  public Entity(Command command, int price, int amount) {
    this.command = command;
    this.price = price;
    this.amount = amount;
  }

  public Command getCommand() {
    return command;
  }

  public void setCommand(Command command) {
    this.command = command;
  }

  public int getPrice() {
    return price;
  }

  public void setPrice(int price) {
    this.price = price;
  }

  public int getAmount() {
    return amount;
  }

  public void setAmount(int amount) {
    this.amount = amount;
  }

  @Override
  public String toString() {
    return command + " " + price + " " + amount;
  }
}
