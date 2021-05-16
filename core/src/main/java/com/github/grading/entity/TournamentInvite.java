package com.github.grading.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
// TODO: use this @Table... when use heroku database
//@Table(name = "tournament", schema = "public", catalog = "d3dhbnnbhpdelm")
@Table(name = "tournament_invite", schema = "public")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TournamentInvite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "tournament_id", referencedColumnName = "id")
    @ToString.Exclude
    private Tournament tournament;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @ToString.Exclude
    private User user;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private InviteStatus status;
}
