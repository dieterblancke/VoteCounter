package dev.endoy.lexurcraft.votecounter.spigot.database;

import dev.endoy.lexurcraft.votecounter.spigot.database.migrations.V1__CreateVotesTable;
import dev.endoy.minecraft.helpers.database.migration.SqlMigrator;
import dev.endoy.minecraft.helpers.injector.Manager;
import dev.endoy.minecraft.helpers.injector.PostConstruct;
import dev.endoy.minecraft.helpers.logger.Logger;
import lombok.RequiredArgsConstructor;

@Manager
@RequiredArgsConstructor
public class MigrationManager
{

    private final Logger logger = Logger.forClass( MigrationManager.class );
    private final DatabaseManager databaseManager;

    @PostConstruct
    public void migrate()
    {
        logger.debug( "Running the database migrations" );

        SqlMigrator.builder()
            .dataSource( this.databaseManager.getDataSource() )
            .initMigrationClass( V1__CreateVotesTable.class )
            .build()
            .load()
            .migrate();
    }
}
