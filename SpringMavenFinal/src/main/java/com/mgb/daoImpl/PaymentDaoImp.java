package com.mgb.daoImpl;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Component;

import com.mgb.bo.PaymentDefinition;
import com.mgb.bo.PaymentDetails;
import com.mgb.daos.IPaymentDao;
import com.mgb.forms.User;
@Component
public class PaymentDaoImp implements IPaymentDao{
	@Autowired
	private HibernateTemplate template;
	public PaymentDaoImp() {
		// TODO Auto-generated constructor stub
	}
	
	public Map<Integer, PaymentDefinition> getPaymentDefinitionMap(String userId) throws Exception {
		Session session=null;
		Map<Integer,PaymentDefinition> paymentMap=new LinkedHashMap<Integer,PaymentDefinition>();
		try {
			session=template.getSessionFactory().openSession();
			Query query=session.createQuery("select bo from PaymentDefinition bo where bo.isActive=1 order by bo.year desc,bo.month desc");
			List<PaymentDefinition> list=query.list();
			if(list!=null){
				for (PaymentDefinition paymentDefinition : list) {
					if(paymentDefinition!=null){
						paymentMap.put(paymentDefinition.getId(), paymentDefinition);
					}
				}
			}
			session.close();
		} catch (Exception e) {
			session.close();
			throw e;
		}
		return paymentMap;
	}
	public Map<Integer, Integer> getUserPaymentCompletedMap(String userId) throws Exception {
		Session session=null;
		Map<Integer,Integer> paymentMap=new HashMap<Integer,Integer>();
		try {
			session=template.getSessionFactory().openSession();
			Query query=session.createQuery("select bo from PaymentDetails bo where bo.isActive=1 and bo.userId.id=:userId");
			query.setInteger("userId", Integer.parseInt(userId));
			List<PaymentDetails> list=query.list();
			if(list!=null){
				for (PaymentDetails paymentDetails : list) {
					if(paymentDetails!=null){
						paymentMap.put(paymentDetails.getPaymentDefinition().getId(), paymentDetails.getAmountPaid());
					}
				}
			}
			session.close();
		} catch (Exception e) {
			session.close();
			throw e;
		}
		return paymentMap;
	}
	public User loadUser(String userId) throws Exception {
		Session session=null;
		User user=null;
		try {
			session=template.getSessionFactory().openSession();
			user=session.get(User.class, Integer.parseInt(userId));
		} catch (Exception e) {
		}
		finally {
			session.close();
		}
		return user;
	}
	public boolean savePaymentDetailsList(List<PaymentDetails> boSaveList, String userId) throws Exception {
	   Session session=null;
	   boolean isSaved=false;
	   
		 try {
			session=template.getSessionFactory().openSession();
			for (PaymentDetails paymentDetails : boSaveList) {
				session.save(paymentDetails);
			}
			Transaction tx=session.beginTransaction();
			tx.commit();
			isSaved=true;
		} catch (Exception e) {
			throw e;
		}
		finally {
			session.close();
		}
		 return isSaved;
	}
	public List<PaymentDetails> getaPaymentDetailsBoList(String userId) throws Exception {
		List<PaymentDetails> boList=(List<PaymentDetails>) template.findByNamedParam("select bo from PaymentDetails bo where bo.isActive=1 and bo.userId.id=:userId ","userId",Integer.parseInt(userId));
		return boList;
	}
	public boolean cancelMonthlyPaymennt(String boId) throws Exception {
		Session session=null;
		   boolean isCancelled=false;
		   
			 try {
				session=template.getSessionFactory().openSession();
				PaymentDetails paymentDetails=session.get(PaymentDetails.class, Integer.parseInt(boId));
				paymentDetails.setIsActive(false);
				session.update(paymentDetails);
				Transaction tx=session.beginTransaction();
				tx.commit();
				isCancelled=true;
			} catch (Exception e) {
				throw e;
			}
			finally {
				session.close();
			}
			 return isCancelled;
		
	}
	

}
