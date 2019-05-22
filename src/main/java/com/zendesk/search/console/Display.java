package com.zendesk.search.console;

import java.lang.reflect.Field;

/** Created by @author Sankash on 5/18/2019 */
public abstract class Display<T> {
  /**
   * This method displays writes the result to console
   *
   * @param result
   */
  protected abstract void show(T result);

  /**
   * Prints the object with names and values
   *
   * @param obj object to print
   * @throws IllegalAccessException
   */
  protected void displayObjectFields(Object obj) throws IllegalAccessException {
    for (Field field : obj.getClass().getDeclaredFields()) {
      field.setAccessible(true);
      String name = field.getName();
      Object value = field.get(obj);
      System.out.println(String.format("%-25.25s|\t %s ", name, value));
    }
  }
}
