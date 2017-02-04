package com.ayursinfotech.agent.dao.impl;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ayursinfotech.agent.beans.dto.LoginDTO;
import com.ayursinfotech.agent.beans.entity.Agent;
import com.ayursinfotech.agent.dao.AgentDAO;
import com.ayursinfotech.agent.exception.DuplicateRecordFoundException;
import com.ayursinfotech.agent.exception.InvalidStatusException;
import com.ayursinfotech.agent.exception.NoRecordFoundException;
import com.ayursinfotech.agent.util.AgentConstants;

@Repository
public class AgentDAOImpl implements AgentDAO {

	private static final Logger LOGGER = Logger.getLogger(AgentDAOImpl.class);

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public boolean isRegisteredAgent(Agent agent)
			throws DuplicateRecordFoundException, Exception {
		LOGGER.info("start executing isRegisteredAgent");
		boolean isRegisteredAgent = true;
		Session session = sessionFactory.openSession();
		try {
			// check for duplicate mobile number
			Criteria cr = session.createCriteria(Agent.class);
			cr.add(Restrictions.eq("mobileNo", agent.getMobileNo()));
			Agent agentGotFromDB = (Agent) cr.uniqueResult();
			if (agentGotFromDB != null) {
				throw new DuplicateRecordFoundException(
						"duplicate mobile number");
			}
			LOGGER.info("end executing isRegisteredAgent");
		} finally {
			session.close();
		}
		return true;
	}

	@Override
	public Agent login(LoginDTO login) throws InvalidStatusException,
	NoRecordFoundException, Exception {
		LOGGER.info("start executing login");
		Agent agent = null;
		Session session = sessionFactory.openSession();
		try {
			Criteria cr = session.createCriteria(Agent.class);
			cr.add(Restrictions.eq("mobileNo", login.getMobileNo()));
			cr.add(Restrictions.eq("password", login.getPassword()));
			agent = (Agent) cr.uniqueResult();
			if (agent != null) {
				if (AgentConstants.INACTIVE.equalsIgnoreCase(agent.getStatus())) {
					throw new InvalidStatusException("Agent is Inactive");
				} else if (AgentConstants.BLOCKED.equalsIgnoreCase(agent
						.getStatus())) {
					throw new InvalidStatusException("Agent is Blocked");
				} else if (AgentConstants.UNVERIFIED.equalsIgnoreCase(agent
						.getStatus())) {
					throw new InvalidStatusException("Agent is Unverified");
				} else if (AgentConstants.ACTIVE.equalsIgnoreCase(agent
						.getStatus())) {
					// TODO create a login log
					// TODO maintain session
				}
			} else {
				throw new NoRecordFoundException("No Agent Found");
			}
		} finally {
			session.close();
		}
		LOGGER.info("end executing login");
		return agent;
	}

	@Override
	public Boolean ping() throws Exception {
		LOGGER.info("start executing ping");
		Boolean result = true;
		LOGGER.info("end executing ping");
		return result;
	}

	@Override
	public Agent registerAgent(Agent agent) {
		LOGGER.info("start executing registerAgent");
		Session session = sessionFactory.openSession();
		try {
			Transaction tx = session.beginTransaction();
			session.saveOrUpdate(agent);
			tx.commit();
			LOGGER.info("end executing registerAgent");
			return agent;
		} finally {
			session.close();
		}
	}

}
