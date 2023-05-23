package com.tradepal.TradePalApp.config;

import com.tradepal.TradePalApp.model.*;
import com.tradepal.TradePalApp.repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Configuration
public class DbDefaultConfig {
    private static final Logger log = LoggerFactory.getLogger(DbDefaultConfig.class);

    @Bean
    CommandLineRunner initDatabase(UserRepository userRepository, InventoryRepository inventoryRepository, GameRepository
                                   gameRepository, CategoryRepository categoryRepository, CategoryValueRepository categoryValueRepository, ItemRepository itemRepository){
        return args -> {
            User user1 = new User("bangui", "Bangui1", "bangui@bangui.com", Role.USER);
            User user2 = new User("admin", "admin1", "admin@admin.com", Role.ADMIN);
            User user3 = new User("emi", "Emi1", "emi@emi.com", Role.USER);
            User user4 = new User("chino", "Chino1", "chino@chino.com", Role.USER);

            userRepository.save(user1);
            userRepository.save(user2);
            userRepository.save(user3);
            userRepository.save(user4);


            Inventory inventory1 = new Inventory(user1);
            Inventory inventory2 = new Inventory(user2);
            Inventory inventory3 = new Inventory(user3);
            Inventory inventory4 = new Inventory(user4);

            inventoryRepository.save(inventory1);
            inventoryRepository.save(inventory2);
            inventoryRepository.save(inventory3);
            inventoryRepository.save(inventory4);

            Game game1 = new Game("CS:GO");
            //Game game2 = new Game("Rocket League");
            Game game3 = new Game("Team Fortress 2");

            gameRepository.save(game1);
            //gameRepository.save(game2);
            gameRepository.save(game3);

            Category category1 = new Category("Type", game1);
            Category category2 = new Category("Quality", game1);
            Category category3 = new Category("Exterior", game1);

            //Category category4 = new Category("Type", game2);
            //Category category5 = new Category("Rarity", game2);

            Category category6 = new Category("Type", game3);
            Category category7 = new Category("Class", game3);
            Category category8 = new Category("Quality", game3);


            categoryRepository.save(category1);
            categoryRepository.save(category2);
            categoryRepository.save(category3);
            //categoryRepository.save(category4);
            //categoryRepository.save(category5);
            categoryRepository.save(category6);
            categoryRepository.save(category7);
            categoryRepository.save(category8);


            List<Category> categories = new ArrayList<>();
            categories.add(category1);
            categories.add(category2);
            categories.add(category3);
            //categories.add(category4);
            //categories.add(category5);
            categories.add(category6);
            categories.add(category7);
            categories.add(category8);


            List<List<String>> valueLists = new ArrayList<>();
            valueLists.add(new ArrayList<>(Arrays.asList("Weapon", "Gloves", "Spray")));
            valueLists.add(new ArrayList<>(Arrays.asList("Consumer Grade", "Industrial Grade", "Mil-spec", "Restricted", "Classified", "Covert", "Exceedingly Rare", "Contraband")));
            valueLists.add(new ArrayList<>(Arrays.asList("Battle-Scarred", "Well-Worn", "Field-Tested", "Minimal Wear", "Factory New")));

            //valueLists.add(new ArrayList<>(Arrays.asList("Antenna", "Body", "Boost", "Paint Job", "Goal Explosion", "Engine")));
            //valueLists.add(new ArrayList<>(Arrays.asList("Common", "Uncommon", "Rare", "Very Rare", "Import", "Black Market")));

            valueLists.add(new ArrayList<>(Arrays.asList("Cosmetic", "Tool", "Taunt")));
            valueLists.add(new ArrayList<>(Arrays.asList("Pyro", "Heavy", "Scout", "Sniper", "Engineer", "Medic")));
            valueLists.add(new ArrayList<>(Arrays.asList("Normal", "Strange", "Unique", "Rare", "Decorated")));


            for (int i = 0; i < categories.size(); i++) {
                Category category = categories.get(i);
                List<String> values = valueLists.get(i);
                for (String value : values) {
                    CategoryValue categoryValue = new CategoryValue(value, category);
                    categoryValueRepository.save(categoryValue);
                }
            }



        };
    }
}
