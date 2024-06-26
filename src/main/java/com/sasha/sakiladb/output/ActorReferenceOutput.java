package com.sasha.sakiladb.output;

import com.sasha.sakiladb.entities.Actor;
import lombok.Getter;

@Getter
public class ActorReferenceOutput {
    private String fullName;

    public ActorReferenceOutput(Actor actor) {
        this.fullName = actor.getFirstName() + " " + actor.getLastName();
    }
}
