package com.github.grading.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
// TODO: use this @Table... when use heroku database
//@Table(name = "game", schema = "public", catalog = "d3dhbnnbhpdelm")
@Table(name = "game", schema = "public")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "parent_id", referencedColumnName = "id")
    @ToString.Exclude
    private Game parent;

    @ToString.Exclude
    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, orphanRemoval=true)
    private List<Game> childs;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "tournament_id", referencedColumnName = "id")
    @ToString.Exclude
    private Tournament tournament;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "player_one_id", referencedColumnName = "id")
    private Player playerOne;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "player_two_id", referencedColumnName = "id")
    private Player playerTwo;

    @Column(name = "player_one_score")
    private int playerOneScore;

    @Column(name = "player_two_score")
    private int playerTwoScore;

    @Column(name = "finished_games")
    private int finishedGames;

    @Column(name = "is_finished")
    private boolean isFinished;
}
