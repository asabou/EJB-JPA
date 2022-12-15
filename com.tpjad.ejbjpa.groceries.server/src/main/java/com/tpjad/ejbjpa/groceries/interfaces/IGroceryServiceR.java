package com.tpjad.ejbjpa.groceries.interfaces;

import java.util.List;

public interface IGroceryServiceR {
    void create(GroceryDTO groceryDTO);
    void delete(final Long id);
    List<GroceryDTO> findAll(final String user);
}
