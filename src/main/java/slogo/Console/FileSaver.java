package slogo.Console;

import java.util.List;

public class FileSaver {




  private static String listToString(List<Double> list) {
    StringBuilder str = new StringBuilder();
    for (Double element: list) {
      str.append(element).append(" ");
    }
    return str.toString();
  }

}


