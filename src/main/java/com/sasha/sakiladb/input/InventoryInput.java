package com.sasha.sakiladb.input;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class InventoryInput {


    @NotNull(groups={ValidationGroup.Create.class})
    private Short storeId;



    @NotNull(groups={ValidationGroup.Create.class})
    @Size(min=1, max=80)
    private String filmName;


    public Short getStoreId() {
        return storeId;
    }

    public String getFilmName() {
        return filmName;
    }

}
