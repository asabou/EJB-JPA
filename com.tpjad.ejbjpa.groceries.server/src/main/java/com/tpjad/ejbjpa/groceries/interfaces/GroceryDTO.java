package com.tpjad.ejbjpa.groceries.interfaces;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class GroceryDTO implements Serializable {
    private Long id;
    private String user;
    private String description;
    private String nrItems;
    private String tillDate;

    public GroceryDTO(String user, String description, String nrItems, String tillDate) {
        this.user = user;
        this.description = description;
        this.nrItems = nrItems;
        this.tillDate = tillDate;
    }
}
