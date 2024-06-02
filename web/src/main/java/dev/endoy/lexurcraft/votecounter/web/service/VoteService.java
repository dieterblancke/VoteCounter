package dev.endoy.lexurcraft.votecounter.web.service;

import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VoteService
{

    private final JdbcTemplate jdbcTemplate;

    @Cacheable( "topVoters" )
    public List<VoteInfo> getTopVotersForThisMonth()
    {
        return jdbcTemplate.query(
            """
                SELECT username, COUNT(*) AS votes 
                FROM votes
                WHERE
                 MONTH(created_at) = MONTH(CURRENT_DATE())
                 AND YEAR(created_at) = YEAR(CURRENT_DATE())
                GROUP BY username
                ORDER BY votes DESC LIMIT 10""",
            ( rs, rowNum ) -> new VoteInfo( rs.getString( "username" ), rs.getInt( "votes" ) )
        );
    }

    @Value
    public static class VoteInfo
    {

        String username;
        int votes;

    }
}
