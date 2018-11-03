package ua.kharkov.java.challange.service;

import ua.kharkov.java.challange.entity.Order;
import ua.kharkov.java.challange.entity.Trade;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class OrderMatchingService {

  private static List<Order> orders = new ArrayList<>();
  private static List<Trade> trades = new ArrayList<>();

  public void run() {
    System.out.println("Please enter you order. EXAMPLE:" + System.lineSeparator() +
      " - BUY 100 50 (where 100 - price and 50 - quantity)" + System.lineSeparator() +
      " - SELL 100 10 (where 100 - price and 10 - quantity)" + System.lineSeparator() +
      " - LIST (to view all order list, or CLEAR for cleaning trade history" + System.lineSeparator() +
      " - CLEAR (to clear all trades)" + System.lineSeparator() +
      " - END (to finish input)" + System.lineSeparator()
    );

    BufferedReader inputReader = null;

    try {
      inputReader = new BufferedReader(new InputStreamReader(System.in));
      Command command = Command.START;
      while (!Command.END.equals(command)) {
        String input = inputReader.readLine();
        String[] parseInput = input.split(" ");

        try {
          ValidationResult validationResult = validateInput(parseInput);
          validationResult.assertValid();

          command = Command.valueOf(parseInput[0].toUpperCase());

          if (parseInput.length == 3) {
            Order order = new Order(command, Integer.valueOf(parseInput[1]), Integer.valueOf(parseInput[2]));
            orders.add(order);

            if (command.equals(Command.SELL)) {
              order.tradeSell(orders, trades);
            } else {
              order.tradeBuy(orders, trades);
            }
            System.out.println("OK");
          } else if (parseInput.length == 1) {
            if (command.equals(Command.LIST)) {
              System.out.println("Orders:");
              orders.forEach(System.out::println);
              System.out.println("Trades:");
              trades.forEach(System.out::println);
              System.out.println();
            }

            if (command.equals(Command.CLEAR)) {
              trades.clear();
              System.out.println("Trade list has been cleared.");
            }
          }
        } catch (Exception ignored) {
        }
      }
    } catch (IOException ex) {
      ex.printStackTrace();
    } finally {
      if (inputReader != null) {
        try {
          inputReader.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
  }

  private ValidationResult validateInput(String[] input) {
    ValidationResult validationResult = ValidationResult.getValidInstance();
    Command command;
    try {
      command = Command.valueOf(input[0].toUpperCase());
      if (command.equals(Command.TRADE)) {
        throw new IllegalArgumentException();
      }
    } catch (IllegalArgumentException e) {
      System.out.println("ERR: Command not found.");
      validationResult.addError("Command not found.");
      return validationResult;
    }

    if (input.length == 3) {
      try {
        if (!command.equals(Command.SELL) && !command.equals(Command.BUY)) {
          throw new IllegalArgumentException();
        }
        Integer.valueOf(input[1]);
        Integer.valueOf(input[2]);
      } catch (Exception e) {
        System.out.println("ERR: Invalid input.");
        validationResult.addError("Invalid input.");
      }
    }

    if (input.length != 1 && input.length != 3) {
      System.out.println("ERR: Invalid input.");
      validationResult.addError("Invalid input.");
    }
    return validationResult;
  }

}
