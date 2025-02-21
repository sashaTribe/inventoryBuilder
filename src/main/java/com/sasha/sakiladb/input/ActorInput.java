package com.sasha.sakiladb.input;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;


@Data
public class ActorInput {
    @NotNull(groups={ValidationGroup.Create.class})
    @Size(min=1, max=45)
    private String firstName;

    @NotNull(groups={ValidationGroup.Create.class})
    @Size(min=1, max=45)
    private String lastName;




}
