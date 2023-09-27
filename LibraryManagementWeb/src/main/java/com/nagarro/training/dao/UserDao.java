package com.nagarro.training.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.hibernate.service.ServiceRegistry;
import org.springframework.stereotype.Component;

import com.nagarro.training.constants.Constants;
import com.nagarro.training.models.User;

@Component
public class UserDao {
	private SessionFactory sf = null;

	// Constructor
	public UserDao() {
		Configuration con = new Configuration().configure().addAnnotatedClass(User.class);

		ServiceRegistry reg = (ServiceRegistry) new StandardServiceRegistryBuilder().applySettings(con.getProperties())
				.build();

		sf = con.buildSessionFactory(reg);
	}

	// Get User From the Database
	public User getUser(String username) {
		try {
			Session session = sf.openSession();
			// Execute a query for user
			Query<User> q = session.createQuery(Constants.GET_USER_QUERY, User.class);
			// Set User query parameters
			q.setParameter("u", username);
			// Get User
			User user = q.uniqueResult();
			// Close session
			session.close();
			// Return User
			return user;
		} catch (Exception e) {
			System.out.println(Constants.UNKNOWN_ERROR);
		}
		return null;
	}
}
