package com.ayursinfotech.agent.dao.impl;

import java.math.BigInteger;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ayursinfotech.agent.beans.entity.PincodeMaster;
import com.ayursinfotech.agent.dao.AgentDAO;

@Service
public class AgentDAOImpl implements AgentDAO {

	private static final Logger LOGGER = Logger.getLogger(AgentDAOImpl.class);

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public Boolean ping() throws Exception {
		LOGGER.info("start executing ping");
		System.out.println("akhskh");
		Session session = sessionFactory.getCurrentSession();
		// Transaction transaction = session.beginTransaction();
		PincodeMaster master = (PincodeMaster) session.get(PincodeMaster.class,
				new BigInteger("1"));
		// transaction.commit();
		Boolean result = true;
		LOGGER.info("end executing ping");
		return result;
	}

}
