package com.ayursinfotech.agent.dao.impl;

import java.math.BigInteger;
import java.util.Date;

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
	public Agent editAgentProfile(Agent agent) {
		LOGGER.info("start executing editAgentProfile");
		Session session = sessionFactory.openSession();
		try {
			Transaction tx = session.beginTransaction();
			session.update(agent);
			tx.commit();
			LOGGER.info("end executing editAgentProfile");
			return agent;
		} finally {
			session.close();
		}
	}

	@Override
	public Agent getAgent(BigInteger agentId) throws NoRecordFoundException,
			Exception {
		LOGGER.info("start executing getAgent");
		Session session = sessionFactory.openSession();
		Agent agent = null;
		try {
			agent = (Agent) session.get(Agent.class, agentId);
			if (agent != null) {
				LOGGER.info("end executing getAgent");
			} else {
				throw new NoRecordFoundException("invalid agent Id");
			}
		} finally {
			session.close();
		}
		return agent;
	}

	@Override
	public boolean isRegisteredAgent(Agent agent)
			throws DuplicateRecordFoundException, Exception {
		LOGGER.info("start executing isRegisteredAgent");
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

	@Override
	public boolean validateCredentials(String mobileNo, String password)
			throws NoRecordFoundException, InvalidStatusException {
		LOGGER.info("start executing validateCredentials");
		Session session = sessionFactory.openSession();

		try {
			Criteria cr = session.createCriteria(Agent.class);
			cr.add(Restrictions.eq("mobileNo", mobileNo));
			cr.add(Restrictions.eq("password", password));
			Agent agent = (Agent) cr.uniqueResult();
			if (agent != null) {

				if (AgentConstants.ACTIVE.equalsIgnoreCase(agent.getStatus()
						.toUpperCase())) {
					LOGGER.info("end executing validateCredentials");
					return true;
				} else {
					throw new InvalidStatusException(
							"customer is not in active state");
				}
			} else {
				throw new NoRecordFoundException(
						"email or password is incorrect");
			}
		} finally {
			session.close();
		}
	}

	@Override
	public void changePassword(LoginDTO login) {
		LOGGER.info("start executing changePassword");
		Session session = sessionFactory.openSession();

		try {
			Transaction tx = session.beginTransaction();
			Criteria cr = session.createCriteria(Agent.class);
			cr.add(Restrictions.eq("mobileNo", login.getMobileNo()));

			Agent agent = (Agent) cr.uniqueResult();

			agent.setPassword(login.getNewpassword());

			agent.setUpdatedDate(new Date());

			session.update(agent);
			tx.commit();

			LOGGER.info("end executing changePassword");
		} finally {
			session.close();
		}
	}

	@Override
	public Agent getAgentByMobileNo(String mobileNo)
			throws NoRecordFoundException, Exception {
		LOGGER.info("start executing getAgentByMobileno");
		Session session = sessionFactory.openSession();
		try {
			Criteria cr = session.createCriteria(Agent.class);
			cr.add(Restrictions.eq("mobileNo", mobileNo));
			Agent agent = (Agent) cr.uniqueResult();
			if (agent == null) {
				throw new NoRecordFoundException(
						"No agent exist with mobile number");
			}
			LOGGER.info("end executing getAgentByMobileno");
			return agent;
		} finally {
			session.close();
		}
	}

	@Override
	public void resetPassword(Agent agent) {
		LOGGER.info("start executing resetPassword");
		Session session = sessionFactory.openSession();
		try {
			Transaction tx = session.beginTransaction();
			session.update(agent);
			tx.commit();
			LOGGER.info("end executing resetPassword");
		} finally {
			session.close();
		}
	}
}
