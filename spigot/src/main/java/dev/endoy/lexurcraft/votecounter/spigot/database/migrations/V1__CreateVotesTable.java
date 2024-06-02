package dev.endoy.lexurcraft.votecounter.spigot.database.migrations;

import dev.endoy.minecraft.helpers.database.helpers.SqlHelpers;
import dev.endoy.minecraft.helpers.database.migration.Migration;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class V1__CreateVotesTable implements Migration
{

    @Override
    public void migrate( Connection connection ) throws SQLException
    {
        if ( !SqlHelpers.doesTableExist( connection, "votes" ) )
        {
            try ( Statement statement = connection.createStatement() )
            {
                statement.executeUpdate(
                    """
                        CREATE TABLE votes (
                            id SERIAL PRIMARY KEY,
                            username VARCHAR(64) NOT NULL,
                            vote_site VARCHAR(512) NOT NULL,
                            created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
                        );"""
                );
            }
        }
    }
}
