package com.tpjad.ejbjpa.groceries.beans.impl;

import com.tpjad.ejbjpa.groceries.beans.transformers.UserTransformer;
import com.tpjad.ejbjpa.groceries.domain.UserEntity;
import com.tpjad.ejbjpa.groceries.interfaces.ILoginService;
import com.tpjad.ejbjpa.groceries.interfaces.ILoginServiceR;
import com.tpjad.ejbjpa.groceries.interfaces.UserDTO;
import com.tpjad.ejbjpa.groceries.utils.ServiceUtils;
import jakarta.ejb.Local;
import jakarta.ejb.Remote;
import jakarta.ejb.Stateful;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Stateful
@Local(ILoginService.class)
@Remote(ILoginServiceR.class)
public class LoginBean implements ILoginService, ILoginServiceR {
    @PersistenceContext(unitName = "ejbjpa")
    private EntityManager entityManager;

    private UserDTO loggedUser = null;

    @Override
    public void login(String username, String password) {
        log.info("Login user: {}, pass: {}", username, password);
        UserEntity user = findByUsernameAndPassword(username, password);
        loggedUser = UserTransformer.transformUserEntity(user);
    }

    @Override
    public boolean isLoggedIn() {
        return !ServiceUtils.isObjectNull(loggedUser);
    }

    @Override
    public void create(UserDTO userDTO) {
        log.info("Creating a new user: {} {}", userDTO.getUsername(), userDTO.getPassword());
        UserEntity user = UserTransformer.transformUserDTO(userDTO);
        entityManager.persist(user);
    }

    @Override
    public UserDTO findByUsername(String username) {
        log.info("Trying to find by username: {}", username);
        UserEntity user = findUserEntityByUsername(username);
        return UserTransformer.transformUserEntity(user);
    }

    @Override
    public UserDTO getLoggedUser() {
        return loggedUser;
    }

    private UserEntity findByUsernameAndPassword(String username, String password) {
        TypedQuery<UserEntity> query = entityManager.createQuery("select u from UserEntity u where u.username = :username and u.password = " +
                ":password", UserEntity.class).setParameter("username", username).setParameter("password", password);
        ;
        try {
            return query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    private UserEntity findUserEntityByUsername(String username) {
        TypedQuery<UserEntity> query = entityManager.createQuery("select u from UserEntity u where u.username = :username", UserEntity.class)
                .setParameter("username", username);
        ;
        try {
            return query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }
}
