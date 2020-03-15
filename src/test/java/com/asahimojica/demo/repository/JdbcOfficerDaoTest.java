package com.asahimojica.demo.repository;

import com.asahimojica.demo.entity.Officer;
import com.asahimojica.demo.entity.Rank;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class JdbcOfficerDaoTest {
    @Autowired
    JdbcOfficerDao jdbcOfficerDao;

    @Test
    void countOfficers() {
        long countOfficers = jdbcOfficerDao.countOfficers();
        assertEquals(5l, countOfficers);
    }

    @Test
    void findAllOfficers() {
        List<Officer> officers = jdbcOfficerDao.findAllOfficers();
        assertEquals(5l, jdbcOfficerDao.findAllOfficers().size());
    }

    @Test
    void officerExistById() {
        boolean officerExists = jdbcOfficerDao.exist(1l);
        assertTrue(officerExists);
    }

    @Test
    void findOfficerByID() {
        Officer expected = new Officer(1l, Rank.CAPTAIN, "James", "Kirk");
        Officer actual = jdbcOfficerDao.findById(1l);
        System.out.println(expected);
        System.out.println(actual);
        assertEquals(expected, actual);

    }
    @Test
    void createOfficerAndAddedToDatabase(){
        Officer testOfficer = new Officer(6l,Rank.ADMIRAL,"Asahi", "Mojica");
       Officer expected = new Officer(jdbcOfficerDao.countOfficers()+1L,Rank.ADMIRAL,"Asahi","Mojica");
       assertEquals(expected,jdbcOfficerDao.save(testOfficer));

    }
    @Test
    void deleteOfficer(){
        Officer testOfficer = new Officer(6l,Rank.COMMANDER,"hello", "World");
        Officer expected = jdbcOfficerDao.findById(6l);
        System.out.println(expected);
        Officer actual = jdbcOfficerDao.removebyId(6l);


    }


}