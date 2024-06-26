package com.sasha.sakiladb.input;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class InventoryInput {
    @NotNull(groups={ValidationGroup.Create.class})
    private Short storeId;

    @NotNull(groups={ValidationGroup.Create.class})
    @Size(min=1, max=80)
    private String filmName;


}
