package dev.endoy.lexurcraft.votecounter.web.controller;

import dev.endoy.lexurcraft.votecounter.web.service.VoteService;
import dev.endoy.lexurcraft.votecounter.web.service.VoteService.VoteInfo;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping( "/votes" )
public class VotesController
{

    private final VoteService voteService;

    @GetMapping
    public ModelAndView index()
    {
        List<VoteInfo> topVoters = this.voteService.getTopVotersForThisMonth()
            .stream()
            .sorted( ( a, b ) -> Integer.compare( b.getVotes(), a.getVotes() ) )
            .toList();
        List<VoteInfoVO> topVoterVOs = new ArrayList<>();

        for ( int i = 0; i < topVoters.size(); i++ )
        {
            VoteInfo voteInfo = topVoters.get( i );
            topVoterVOs.add( new VoteInfoVO( i + 1, voteInfo.getUsername(), voteInfo.getVotes() ) );
        }

        return new ModelAndView( "index", Map.of( "topVoters", topVoterVOs ) );
    }

    @Value
    public static class VoteInfoVO {
        int position;
        String username;
        int votes;
    }
}
