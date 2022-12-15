package com.tpjad.ejbjpa.groceries.beans.transformers;

import com.tpjad.ejbjpa.groceries.interfaces.GroceryDTO;
import com.tpjad.ejbjpa.groceries.domain.GroceryEntity;
import com.tpjad.ejbjpa.groceries.utils.ServiceUtils;

import java.util.ArrayList;
import java.util.List;

public class GroceryTransformer {
    public static void fillGroceryDTO(final GroceryEntity input, final GroceryDTO target) {
        target.setId(input.getId());
        target.setDescription(input.getDescription());
        target.setNrItems(input.getNrItems());
        target.setTillDate(input.getTillDate());
        target.setUser(input.getUserEntity().getUsername());
    }

    public static void fillGroceryEntity(final GroceryDTO input, final GroceryEntity target) {
        target.setId(input.getId());
        target.setDescription(input.getDescription());
        target.setNrItems(input.getNrItems());
        target.setTillDate(input.getTillDate());
    }

    public static GroceryDTO transformGroceryEntity(final GroceryEntity input) {
        if (ServiceUtils.isObjectNull(input)) {
            return null;
        }
        final GroceryDTO target = new GroceryDTO();
        fillGroceryDTO(input, target);
        return target;
    }

    public static GroceryEntity transformGroceryDTO(final GroceryDTO input) {
        if (ServiceUtils.isObjectNull(input)) {
            return null;
        }
        final GroceryEntity target = new GroceryEntity();
        fillGroceryEntity(input, target);
        return target;
    }

    public static List<GroceryDTO> transformGroceryEntities(final List<GroceryEntity> inputs) {
        final List<GroceryDTO> targets = new ArrayList<>();
        inputs.forEach(input -> targets.add(transformGroceryEntity(input)));
        return targets;
    }
}
