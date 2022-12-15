package com.tpjad.ejbjpa.groceries.beans.impl;

import com.tpjad.ejbjpa.groceries.beans.transformers.GroceryTransformer;
import com.tpjad.ejbjpa.groceries.domain.GroceryEntity;
import com.tpjad.ejbjpa.groceries.domain.UserEntity;
import com.tpjad.ejbjpa.groceries.interfaces.GroceryDTO;
import com.tpjad.ejbjpa.groceries.interfaces.IGroceryService;
import com.tpjad.ejbjpa.groceries.interfaces.IGroceryServiceR;
import com.tpjad.ejbjpa.groceries.utils.ServiceUtils;
import jakarta.ejb.Local;
import jakarta.ejb.Remote;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@Stateless
@Local(IGroceryService.class)
@Remote(IGroceryServiceR.class)
public class GroceryBean implements IGroceryService, IGroceryServiceR {
    @PersistenceContext(unitName = "ejbjpa")
    private EntityManager entityManager;

    @Override
    public void create(GroceryDTO groceryDTO) {
        log.info("Trying to create a new grocery {}", groceryDTO);
        UserEntity user = findUserEntityByUsername(groceryDTO.getUser());
        log.info("Grocery for user: {}", user);
        GroceryEntity grocery = GroceryTransformer.transformGroceryDTO(groceryDTO);
        if (!ServiceUtils.isObjectNull(user) && !ServiceUtils.isObjectNull(grocery)) {
            grocery.setUserEntity(user);
            entityManager.persist(grocery);
        }
    }

    @Override
    public void delete(Long id) {
        log.info("Trying to delete grocery by id {}", id);
        GroceryEntity grocery = findGroceryEntityById(id);
        entityManager.remove(grocery);
    }

    @Override
    public List<GroceryDTO> findAll(String user) {
        log.info("Trying to get all groceries for user: {}", user);
        UserEntity userEntity = findUserEntityByUsername(user);
        List<GroceryEntity> groceries = (List<GroceryEntity>) userEntity.getGroceries();
        log.info("Groceries: {}", groceries.size());
        return GroceryTransformer.transformGroceryEntities(groceries);
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

    public GroceryEntity findGroceryEntityById(Long id) {
        TypedQuery<GroceryEntity> query = entityManager.createQuery("select g from GroceryEntity g where g.id = :id", GroceryEntity.class).setParameter(
                "id", id);
        try {
            return query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }
}
