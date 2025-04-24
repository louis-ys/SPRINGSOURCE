package com.example.relation.team;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.example.relation.entity.team.Team;
import com.example.relation.entity.team.TeamMember;
import com.example.relation.repository.team.TeamMemberRepository;
import com.example.relation.repository.team.TeamRepository;
import com.example.relation.repository.team.TeamMemberRepository;

@SpringBootTest
public class TeamRepositoryTest {

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private TeamMemberRepository teammemberRepository;

    @Test
    public void insertTest() {
        Team team = teamRepository.save(Team.builder().teamName("team2").build());

        // teammemberRepository.save(TeamMember.builder().userName("user1").team(team).build());

    }

    @Test
    public void insertTest2() {
        Team team = teamRepository.findById(1L).get();

        teammemberRepository.save(TeamMember.builder().userName("user2").team(team).build());

    }

    @Test
    public void readTest1() {
        // 팀 조회
        Team team = teamRepository.findById(1L).get();

        TeamMember teamMember = teammemberRepository.findById(1L).get();

        System.out.println(team);
        System.out.println(teamMember);
    }

    @Test
    public void readTest2() {
        // 맴버 조회

        TeamMember teamMember = teammemberRepository.findById(1L).get();

        System.out.println(teamMember);
        System.out.println(teamMember.getTeam());
    }

    @Test
    public void updateTest() {
        TeamMember member = teammemberRepository.findById(1L).get();
        Team team = teamRepository.findById(2L).get();
        member.setTeam(team);

        teammemberRepository.save(member);

    }

    @Test
    public void deleteTest() {
        // teamRepository.deleteById(1L);

        TeamMember member = teammemberRepository.findById(2L).get();
        Team team = teamRepository.findById(2L).get();
        member.setTeam(team);
        teammemberRepository.save(member);

        teamRepository.deleteById(1L);
    }

    @Transactional
    @Test
    public void readBiTest1() {
        // 맴버 조회

        Team team = teamRepository.findById(2L).get();

        System.out.println(team);
        team.getMembers().forEach(member -> System.out.println(member));
    }
}
