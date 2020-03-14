package com.asahimojica.demo.repository;

import com.asahimojica.demo.entity.Officer;
import com.asahimojica.demo.entity.Rank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public class JdbcOfficerDao {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcOfficerDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public long countOfficers() {
        String query = "select count(*) from officers";
        return jdbcTemplate.queryForObject(query, Long.class);
    }

    public List<Officer> findAllOfficers() {
        return jdbcTemplate.query("select * from officers",
                (rs, rowNum) -> new Officer(rs.getLong("id"),
                        Rank.valueOf(rs.getString("officer_rank")),
                        rs.getString("first_name"),
                        rs.getString("last_name")));
    }

    public boolean exist(long id) {
        String query = "select 1 from officers where id=?";
        return jdbcTemplate.queryForObject(query, Boolean.class, id);

    }


    public Officer findById(long id) {
        try {
            return jdbcTemplate.queryForObject("select * from officers where id = ?",
                    (rs, rowNum) -> new Officer(rs.getLong("id"),
                            Rank.valueOf(rs.getString("officer_rank")),
                            rs.getString("first_name"),
                            rs.getString("last_name")), id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
}
