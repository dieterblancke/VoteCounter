package dev.endoy.lexurcraft.votecounter.spigot.config;

import dev.endoy.minecraft.helpers.injector.Comment;
import dev.endoy.minecraft.helpers.injector.Configuration;
import dev.endoy.minecraft.helpers.injector.ConfigurationSection;
import dev.endoy.minecraft.helpers.injector.Value;
import lombok.Data;

@Data
@Configuration( filePath = "config.yml" )
public class Config
{

    @Value
    private DatabaseConfig database = new DatabaseConfig();

    @Data
    @ConfigurationSection
    public static class DatabaseConfig
    {

        @Value
        @Comment( "The URL to connect to the database in the following format: 'jdbc:mysql://host:port/database'" )
        private String url = "jdbc:mysql://localhost:3306/database";

        @Value
        @Comment( "The username to connect to the database" )
        private String username = "username";

        @Value
        @Comment( "The password to connect to the database" )
        private String password = "password";

    }
}
