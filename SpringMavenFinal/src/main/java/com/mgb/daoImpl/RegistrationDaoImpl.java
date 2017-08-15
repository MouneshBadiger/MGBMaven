package com.mgb.daoImpl;

import org.hibernate.NonUniqueResultException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Component;

import com.mgb.bo.PaymentDetails;
import com.mgb.daos.IRegistrationDao;
import com.mgb.forms.Subscriber;
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
		session.saveOrUpdate(register);
		tx.commit();
		session.close();
		return true;  
	}
	public boolean checkDuplicate(String mobileNo,int userId) throws Exception {
		Session session=null;
		try{
			SessionFactory sf=template.getSessionFactory();
			session=sf.openSession();
			Query query=session.createQuery("from User bo where bo.isActive=1 and bo.mobileNo=:mobileNo and bo.id!=:subsId");
			query.setString("mobileNo", mobileNo);
			query.setInteger("subsId", userId);
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
		finally {
			if(session!=null)
			session.close();
		}
	
	}
	public boolean saveSubscriber(Subscriber subscriber) {
		SessionFactory sf=template.getSessionFactory();
		Session session=sf.openSession();
		Transaction tx=session.beginTransaction();
		session.saveOrUpdate(subscriber);
		tx.commit();
		session.close();
		return true;  
	}
	public Subscriber loadSubscriber(String userId) throws Exception {
		return template.get(Subscriber.class, Integer.parseInt(userId));
	}
	public boolean deleteSubscriber(String userId) throws Exception {
		Session session=null;
		   boolean isDeleted=false;
		   
			 try {
				session=template.getSessionFactory().openSession();
				Subscriber subscriber=session.get(Subscriber.class, Integer.parseInt(userId));
				subscriber.setIsActive(false);
				session.update(subscriber);
				Transaction tx=session.beginTransaction();
				tx.commit();
				isDeleted=true;
			} catch (Exception e) {
				throw e;
			}
			finally {
				if(session!=null)
				session.close();
			}
			 return isDeleted;
	}
	public User loadUser(String userId) throws Exception {
		Session session=null;
		User user=null;
		try {
			session=template.getSessionFactory().openSession();
			user=session.get(User.class, Integer.parseInt(userId));
		} catch (Exception e) {
			throw e;
		}
		finally {
			if(session!=null)
			session.close();
		}
		return user;
	}
	public User loadUserByUsername(String userName) {
		User user=null;
		Session session=null;
		try {
		 	SessionFactory sf=template.getSessionFactory();
			session=sf.openSession();
			Query query=session.createQuery("select bo from User bo where bo.name=:userName");
			query.setString("userName", userName);
			user=(User) query.uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			if(session!=null)
			session.close();
		}
		return user;
	}
	
}
