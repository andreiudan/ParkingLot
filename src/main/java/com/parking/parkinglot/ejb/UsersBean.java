package com.parking.parkinglot.ejb;

import com.parking.parkinglot.common.UsersDto;
import com.parking.parkinglot.entities.User;
import jakarta.ejb.EJBException;
import jakarta.ejb.Stateless;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Stateless
public class UsersBean {

    private static final Logger LOG = Logger.getLogger(UsersBean.class.getName());

    @PersistenceContext
    EntityManager entityManager;

    public List<UsersDto> copyToUsersDto(List<User> users){
        List<UsersDto> copyList = new ArrayList<>();
        LOG.info("copyToUsersDto");
        try{
            for(User user : users){
                copyList.add(new UsersDto(user.getId(), user.getEmail(), user.getUsername(), user.getPassword()));
            }
            return copyList;
        }catch (Exception ex){
            throw new EJBException(ex);
        }
    }

    public List<UsersDto> findAllUsers(){
        LOG.info("findAllUsers");
        try{
            TypedQuery<User> typedQuery = entityManager.createQuery("SELECT u FROM User u", User.class);
            List<User> users = typedQuery.getResultList();
            return copyToUsersDto(users);
        }catch (Exception ex){
            throw new EJBException(ex);
        }
    }
}
