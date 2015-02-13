package com.levelup;

import static org.junit.Assert.assertEquals;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@SqlGroup({
		@Sql(executionPhase = ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:beforeScripts.sql"),
		@Sql(executionPhase = ExecutionPhase.AFTER_TEST_METHOD, scripts = "classpath:afterScripts.sql") })
public class ApplicationTests {

	private final static Logger LOGGER = Logger
			.getLogger(ApplicationTests.class);

	@Autowired
	JdbcTemplate jdbcTemplate;

	public class StateRowMapper implements RowMapper<State> {

		@Override
		public State mapRow(ResultSet rs, int rowNum) throws SQLException {
			State state = new State();

			state.setStateId(rs.getInt("STATEID"));
			state.setAbbreviation(rs.getString("ABBREVIATION"));
			state.setPostalCode(rs.getString("POSTALCODE"));
			state.setState(rs.getString("STATE"));

			return state;
		}

	}

	@Test
	public void query_for_one_state() {

		String sql = "SELECT * from STATE WHERE STATEID = " + 1;

		State state = jdbcTemplate.queryForObject(sql, new StateRowMapper());

		LOGGER.info(state);

		assertEquals("AL", state.getPostalCode());
	}

	@Test
	public void query_for_list_states() {

		String sql = "SELECT * from STATE";

		List<State> states = jdbcTemplate.query(sql, new StateRowMapper());

		LOGGER.info(states);

		assertEquals(3, states.size());

	}

}
