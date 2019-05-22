package com.zendesk.search;

import com.zendesk.search.console.Menu;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/** Created by @author Sankash on 5/15/2019 */
public class App {
  public static void main(String[] args) {
    ExecutorService executorService = Executors.newFixedThreadPool(10);
    Runnable task = Menu::searchLauncher;
    executorService.execute(task);
    executorService.shutdown();
  }
}
