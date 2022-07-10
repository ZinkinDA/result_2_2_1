package hiber;

import hiber.config.AppConfig;
import hiber.model.Cars;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.persistence.NoResultException;
import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

     User u1 = new User("user12345","SurName1","Email1@mail.ru");
     User u2 = new User("user2","SurName2","Email2@mail.ru");
     User u3 = new User("user3","SurName3","Email3@mail.ru");
     User u4 = new User("user4","SurName4","Email4@mail.ru");


     Cars c1 = new Cars("BMW",1999);
     Cars c2 = new Cars("W",2005);
     Cars c3 = new Cars("BMW7",1982);
     Cars c4 = new Cars("W5",2007);
     userService.add(u1.setCars(c1).setUser(u1));
     userService.add(u2.setCars(c2).setUser(u2));
     userService.add(u3.setCars(c3).setUser(u3));
     userService.add(u4.setCars(c4).setUser(u4));

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println();
      }

       /************Машины с паользователями**********/
       for (User el : userService.listUsers()){
           System.out.println( el + " : " + el.getCars());
       }
       System.out.println("*************************");

       /**********Пользователь с автомобилем************/
       try {
           System.out.println(userService.getUserByCar("BMW7", 1982));
       } catch (NoResultException e) {
           System.out.println("Not Found");
       }
       System.out.println("*************************");




      context.close();
   }
}
