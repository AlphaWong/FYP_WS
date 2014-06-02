/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ict;

import bean.Store;
import bean.Tag;
import bean.User;
import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author alphawong
 */
public class GetUserCollection {

    public ArrayList<User> getUserStoreFollower(User _user) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("WSPU");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        User user = entityManager.find(User.class, _user.getUserID());
        Collection<Store> storeCollection = user.getStoreCollection();
        ArrayList<Store> al = new ArrayList(storeCollection);
        ArrayList<User> _u = new ArrayList();
        for (Store _al : al) {

            ArrayList<Tag> tags = new ArrayList(_al.getTagCollection());
            for (Tag _tags : tags) {
                _u.add(_tags.getUserID());
            }
        }
        for (User __u : _u) {
            System.out.println("User?="+__u.getUserID());
        }
        return null;
    }
}
