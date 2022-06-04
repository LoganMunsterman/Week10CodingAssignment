package application;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import dao.CatDao;
import entity.Cat;

public class Menu {
  
  private CatDao catDao = new CatDao();
  private Scanner scanner = new Scanner(System.in);
  private List<String> options = Arrays.asList("See all the cats.  >'.'<   >'.'<   >'.'<   >'.'<",
                                               "See a cat.  >'.'<",
                                               "add a cat :D",
                                               "Update a cats attitude.  (Hopefully for the best)",
                                               "remove a cat."
                                               );
  
  public void start() {
    String selection = "";
    
    do {
      printMenu();
      selection = scanner.nextLine();
      
      try {
        if (selection.equals("1")) {
          displayCats();
         } else if (selection.equals("2")) {
          displayCat();
         } else if (selection.equals("3")) {
          addCat();
         } else if (selection.equals("4")) {
          updateAttitude();
         } else if (selection.equals("5")) {
          removeCat();
        }
      } catch(SQLException e) {
          e.printStackTrace();
      }
      
      System.out.println("Press enter to continue....  >'.'<  meow");
      scanner.nextLine();
      
    } while (!selection.equals("-1"));
    
  }
  
  private void printMenu() {
    System.out.println("What would you like to do meow:\n------------------------- >'.'< -------------------------");
    for (int i = 0; i < options.size(); i++) {
      System.out.println(i + 1 + ") " + options.get(i));
    }
  }
  private void displayCats() throws SQLException {
    List<Cat> cats = catDao.getCats();
    for (Cat cat : cats) {
      System.out.println("| ID: " + cat.getCatId() + "| Name: " + cat.getName() + " | Attitude: " + cat.getAttitude()
                          + " | Gender: " + cat.getGender() + " |");
      
    }
  }
  
  private void updateAttitude() throws SQLException {
    System.out.println("Please enter the ID of the cat you would like to update: ");
    String inputCatId = scanner.nextLine();
    int id = 0;
    try { 
      id = Integer.parseInt(inputCatId);
    } catch(NumberFormatException e) {
      System.out.println("The cat ID must be a number.  Returning to main menu...");
      return;
    }    
    System.out.println("Please input the cats updated attitude: ");
    String inputCattitude = scanner.nextLine();    
    try {
      if (catDao.updateCattitude(id, inputCattitude)) {       
        System.out.println("Cattitude update complete.  >'.'< ");
      } else {
        System.out.println("Your update has failed, couldn't find that cat.");
      }
    } catch(SQLException e) {
      e.printStackTrace();
    }     
  }
  private void displayCat() throws SQLException {
    System.out.println("Enter cat id: ");
    int id = Integer.parseInt(scanner.nextLine());
    Cat cat = catDao.getCat(id);
    System.out.println("| ID: " + cat.getCatId() + " | Name: " + cat.getName() + " | Attitude: " + cat.getAttitude()
                        + " | Gender: " + cat.getGender() + " |");
    }
  
  private void addCat() throws SQLException {
    System.out.println("Enter the cats name: ");
    String name = scanner.nextLine();
    System.out.println("Enter the cats attitude: ");
    String attitude = scanner.nextLine();
    System.out.println("Enter the cats gender:");
    String gender = scanner.nextLine();
    catDao.addNewCat(name, attitude, gender);
  }
  
  private void removeCat() throws SQLException {
    System.out.println("Enter id of cat you wish to remove.");
    int id = Integer.parseInt(scanner.nextLine());
    catDao.removeCatById(id);
  }
  
}
