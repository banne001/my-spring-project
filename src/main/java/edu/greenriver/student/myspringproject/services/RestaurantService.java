package edu.greenriver.student.myspringproject.services;

import edu.greenriver.student.myspringproject.models.Restaurant;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * Restaurant Service class to access the restaurant data
 * and manipulate it for the use that the user needs it for
 *
 * @author blezyl
 * @version 10.12
 */
@Service
public class RestaurantService {

    private List<Restaurant> allRest = new ArrayList<>(
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

    /**
     * gets all the restaurants from the dummy data
     * @return all restaurants
     */
    public List<Restaurant> allRestaurants(){
        return allRest;
    }

    /**
     * gets the restaurant that matches name
     *
     * @param name the name of the restaurant
     * @return the restaurant with the same name as the name param
     */
    public Restaurant findByName(String name){
        return allRest.stream()
                .filter(m -> m.getName().equals(name))
                .findFirst()
                .orElse(null);
    }

    /**
     * Gets the top three rated restaurants
     * @return top three restaurants
     */
    public List<Restaurant> topThree(){
        return allRest.stream()
                .sorted()
                .limit(3)
                .collect(Collectors.toList());
    }

    /**
     * randomly picks a restaurant
     *
     * @return a random restaurant
     */
    public Restaurant random(){
        Random random = new Random();

        return allRest.get(random.nextInt(allRest.size()));
    }
}

