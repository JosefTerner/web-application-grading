package com.github.grading.dto;

import com.github.grading.entity.LevelOfTournament;
import com.github.grading.entity.ScenarioForTournament;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateTournamentDto {

    private String name;
    private String description;
    private String place;
    private LevelOfTournament levelOfTournament;
    private int numberOfParticipants;
    private ScenarioForTournament scenarioForTournament;
    private String startDate;
    private String lastRegistrationDate;
}
