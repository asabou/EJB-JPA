package com.tpjad.ejbjpa.groceries.beans.transformers;

import com.tpjad.ejbjpa.groceries.interfaces.UserDTO;
import com.tpjad.ejbjpa.groceries.domain.UserEntity;
import com.tpjad.ejbjpa.groceries.utils.ServiceUtils;

public class UserTransformer {
    public static void fillUserDTO(final UserEntity input, final UserDTO target) {
        target.setId(input.getId());
        target.setUsername(input.getUsername());
        target.setPassword(input.getPassword());
    }

    public static void fillUserEntity(final UserDTO input, final UserEntity target) {
        target.setId(input.getId());
        target.setUsername(input.getUsername());
        target.setPassword(input.getPassword());
    }

    public static UserEntity transformUserDTO(final UserDTO input) {
        if (ServiceUtils.isObjectNull(input)) {
            return null;
        }
        final UserEntity target = new UserEntity();
        fillUserEntity(input, target);
        return target;
    }

    public static UserDTO transformUserEntity(final UserEntity input) {
        if (ServiceUtils.isObjectNull(input)) {
            return null;
        }
        final UserDTO target = new UserDTO();
        fillUserDTO(input, target);
        return target;
    }
}
