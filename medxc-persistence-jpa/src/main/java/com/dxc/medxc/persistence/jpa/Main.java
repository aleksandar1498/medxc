package com.dxc.medxc.persistence.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("medxc-persistence-jpa");
        EntityManager manager = factory.createEntityManager();

    }
}
