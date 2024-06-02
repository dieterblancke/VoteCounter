package dev.endoy.lexurcraft.votecounter.spigot.database;

import com.zaxxer.hikari.HikariDataSource;
import dev.endoy.lexurcraft.votecounter.spigot.config.Config;
import dev.endoy.minecraft.helpers.injector.Manager;
import dev.endoy.minecraft.helpers.logger.Logger;
import lombok.Data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Data
@Manager
public class DatabaseManager
{

    private final Logger logger = Logger.forClass( DatabaseManager.class );
    private final HikariDataSource dataSource;

    public DatabaseManager( Config config )
    {
        this.dataSource = new HikariDataSource();
        this.dataSource.setJdbcUrl( config.getDatabase().getUrl() );
        this.dataSource.setUsername( config.getDatabase().getUsername() );
        this.dataSource.setPassword( config.getDatabase().getPassword() );
        this.dataSource.setDriverClassName( "com.mysql.cj.jdbc.Driver" );
        this.dataSource.setMaximumPoolSize( 10 );
    }

    public void saveVote( String username, String voteSite )
    {
        try ( Connection connection = this.dataSource.getConnection() )
        {
            try ( PreparedStatement statement = connection.prepareStatement( "INSERT INTO votes (username, vote_site) VALUES (?, ?)" ) )
            {
                statement.setString( 1, username );
                statement.setString( 2, voteSite );
                statement.executeUpdate();
            }
        }
        catch ( SQLException e )
        {
            this.logger.error( "Failed to save the vote to the database", e );
        }
    }
}
