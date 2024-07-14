package com.sasha.sakiladb.input;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class FilmInput {
    @NotNull(groups={ValidationGroup.Create.class})
    @Size(min=1, max=80)
    private String filmName;


    private int releaseYear;

    private Short rentalDuration;

    private Short length;

    private String specialFeatures;



    @NotNull(groups={ValidationGroup.Create.class})
    private Byte languageId;



}
