package com.example.jpa.repository.team;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.jpa.entity.team.Team;
import com.example.jpa.entity.team.TeamMember;

public interface TeamRepositoryTest extends JpaRepository<Team, Long> {

    @SpringBootTest
    public class InnerTeamRepository {

        @Autowired
        private TeamRepository teamRepository;

        @Autowired
        private TeammemberRepository teammemberRepository;

        @Test
        public void insertTest() {
            Team team = teamRepository.save(Team.builder().teamName("team1").build());

            teammemberRepository.save(TeamMember.builder().userName("user1").team(team).build());

        }

        // @Test
        // public void insertTest2() {
        // Team team = teamRepository.findAllById(2L).get();

        // teammemberRepository.save(Teammember.builder().userName("user1").team(team).build());

        // }

    }
}
