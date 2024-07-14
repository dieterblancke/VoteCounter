package dev.endoy.lexurcraft.votecounter.spigot;

import dev.endoy.minecraft.helpers.logger.Logger;
import dev.endoy.minecraft.helpers.spigot.SpigotEndoyApplication;
import org.bukkit.plugin.java.JavaPlugin;

public class VoteCounter extends JavaPlugin
{

    private final Logger logger = Logger.forClass( VoteCounter.class );
    private SpigotEndoyApplication endoyApplication;

    @Override
    public void onEnable()
    {
        logger.debug( "Initializing endoy-helpers" );
        this.endoyApplication = SpigotEndoyApplication.forPlugin( this );

        logger.info( "VoteCounter has been enabled" );
    }

    @Override
    public void onDisable()
    {
        logger.info( "VoteCounter has been disabled" );
    }
}
