package com.sasha.sakiladb.input;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class InventoryFormInput {
    @NotNull(groups={ValidationGroup.Create.class})
    private Short storeId;

    @NotNull(groups={ValidationGroup.Create.class})
    private int filmId;


}
