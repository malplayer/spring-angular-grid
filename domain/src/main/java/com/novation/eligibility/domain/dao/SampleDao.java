package com.novation.eligibility.domain.dao;



import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


@Repository
public class SampleDao  {

	private static final Logger log = LoggerFactory.getLogger(SampleDao.class);
	private JdbcTemplate jdbcTemplate;

	
	public SampleDao() {
		log.info("Constructor called.");
	}

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
	
	public Object findCount() {
		return jdbcTemplate.queryForObject("SELECT GETDATE()",Object.class);
	}
}
