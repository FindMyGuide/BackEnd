package com.findMyGuide.entity;

import javax.persistence.*;


@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "MEM_SEQ")
    public Long idx;

    @Column
    public String email;
}
