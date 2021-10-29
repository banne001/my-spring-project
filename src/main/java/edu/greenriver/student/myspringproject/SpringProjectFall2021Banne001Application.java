package edu.greenriver.student.myspringproject;

import edu.greenriver.student.myspringproject.dbs.RestaurantRepository;
import edu.greenriver.student.myspringproject.models.Restaurant;
import edu.greenriver.student.myspringproject.services.RestaurantService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

/**
 Main function to run Spring application
 @author Blezyl Santos
 @version 10.20
 */
@SpringBootApplication
public class SpringProjectFall2021Banne001Application {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(SpringProjectFall2021Banne001Application.class, args);

        RestaurantRepository repo = context.getBean(RestaurantRepository.class);

        List<Restaurant> allRest = new ArrayList<>(
                List.of(
                        Restaurant.builder().name("Pho Dinh").address("2822 Auburn Way N").city("Auburn")
                                .postalCode("98002").stars(4.2).state("WA").type("Vietnamese").build(),
                        Restaurant.builder().name("Ting Tong Thai Cafe").address("20910 108th Ave SE").city("Kent")
                                .postalCode("98031").stars(4.5).state("WA").type("Thai").build(),
                        Restaurant.builder().name("Oishi Yummy").address("10715 SE Carr Rd").city("Kent")
                                .postalCode("98055").stars(4.8).state("WA").type("Asain").build(),
                        Restaurant.builder().name("Szechuan First").address("18124 E Valley Hwy").city("Kent")
                                .postalCode("98032").stars(4.4).state("WA").type("Chinese").build(),
                        Restaurant.builder().name("Duke's Seafood").address("757 Southcenter Mall").city("Tukwilla")
                                .postalCode("98188").stars(4.4).state("WA").type("Seafood").build(),
                        Restaurant.builder().name("Applebee's Grill and Bar").address("1441 D St NE").city("Auburn")
                                .postalCode("98002").stars(4.0).state("WA").type("Burgers").build()
                )
        );

        for(Restaurant rest : allRest){
            repo.save(rest);
        }
        System.out.println("All Restaurant saved in DB");
    }

}
