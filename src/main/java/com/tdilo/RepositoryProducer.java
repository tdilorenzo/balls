package com.tdilo;

import javax.ejb.Stateless;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.Disposes;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class RepositoryProducer
{
   // NOTE cannot use producer field because Weld attempts to close EntityManager
   @PersistenceContext EntityManager em;

   public @Produces @Repository
   EntityManager retrieveEntityManager() {
      return em;
   }

   public void disposeEntityManager(@Disposes @Repository EntityManager em) {}
}
