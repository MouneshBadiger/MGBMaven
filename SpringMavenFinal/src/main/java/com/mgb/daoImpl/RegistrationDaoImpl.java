package com.mgb.daoImpl;

import org.hibernate.NonUniqueResultException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.orm.hibernate5.HibernateTemplate;

import com.mgb.daos.IRegistrationDao;
import com.mgb.forms.User;

public class RegistrationDaoImpl implements IRegistrationDao{
	private HibernateTemplate template; 
	public HibernateTemplate getTemplate() {
		return template;
	}
	public void setTemplate(HibernateTemplate template) {
		this.template = template;
	}
	public boolean registerUser(User register) {
		//template.save(register);
		SessionFactory sf=template.getSessionFactory();
		Session session=sf.openSession();
		Transaction tx=session.beginTransaction();
		session.save(register);
		tx.commit();
 		return true;  
	}
	public boolean checkDuplicate(User register) throws Exception {
		try{
			SessionFactory sf=template.getSessionFactory();
			Session session=sf.openSession();
			Query query=session.createQuery("from User bo where bo.isActive=1 and bo.email=:email");
			query.setString("email", register.getEmail());
			User user=(User) query.uniqueResult();
			if(user!=null){
				return true;
			}
			return false;
		}catch(Exception e){
			if(e instanceof NonUniqueResultException){
				return true;
			}
			throw e;
		}
	
	}

}
