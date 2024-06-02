package dev.endoy.lexurcraft.votecounter.spigot.listeners;

import com.vexsoftware.votifier.model.Vote;
import com.vexsoftware.votifier.model.VotifierEvent;
import dev.endoy.lexurcraft.votecounter.spigot.database.DatabaseManager;
import dev.endoy.minecraft.helpers.injector.Listeners;
import lombok.RequiredArgsConstructor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

@Listeners
@RequiredArgsConstructor
public class VoteListener implements Listener
{

    private final DatabaseManager databaseManager;

    @EventHandler
    public void onVote( VotifierEvent event )
    {
        Vote vote = event.getVote();

        this.databaseManager.saveVote(
            vote.getUsername(),
            vote.getServiceName()
        );
    }
}
