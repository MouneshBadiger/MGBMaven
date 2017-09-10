package com.mgb.daoImpl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.NonUniqueResultException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Component;

import com.mgb.bo.AreaMaster;
import com.mgb.bo.PaymentDefinition;
import com.mgb.bo.PaymentDetails;
import com.mgb.daos.IPaymentDao;
import com.mgb.forms.Subscriber;
import com.mgb.forms.User;
@Component
public class PaymentDaoImp implements IPaymentDao{
	@Autowired
	private HibernateTemplate template;
	public PaymentDaoImp() {
		// TODO Auto-generated constructor stub
	}
	
	public Map<Integer, PaymentDefinition> getPaymentDefinitionMap() throws Exception {
		Session session=null;
		Map<Integer,PaymentDefinition> paymentMap=new LinkedHashMap<Integer,PaymentDefinition>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date today=new Date();
		today.setDate(1);
		try {
			session=template.getSessionFactory().openSession();
			Query query=session.createQuery("select bo from PaymentDefinition bo where bo.isActive=1 order by bo.year desc,bo.month desc");
			List<PaymentDefinition> list=query.list();
			if(list!=null){
				for (PaymentDefinition paymentDefinition : list) {
					Date date= sdf.parse(paymentDefinition.getYear()+"-"+(paymentDefinition.getMonth()+1)+"-"+01);
					if(paymentDefinition!=null && today.compareTo(date)>=0){
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
	public Map<Integer, PaymentDetails> getUserPaymentCompletedMap(String userId) throws Exception {
		Session session=null;
		Map<Integer,PaymentDetails> paymentMap=new HashMap<Integer,PaymentDetails>();
		try {
			session=template.getSessionFactory().openSession();
			Query query=session.createQuery("select bo from PaymentDetails bo where bo.isActive=1 and bo.userId.id=:userId");
			query.setInteger("userId", Integer.parseInt(userId));
			List<PaymentDetails> list=query.list();
			if(list!=null){
				for (PaymentDetails paymentDetails : list) {
					if(paymentDetails!=null){
						paymentMap.put(paymentDetails.getPaymentDefinition().getId(), paymentDetails);
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
	public boolean addOrUpdatePaymentDef(PaymentDefinition bo) throws Exception {
		 Session session=null;
		   boolean isSaved=false;
		   
			 try {
				session=template.getSessionFactory().openSession();
				session.saveOrUpdate(bo);
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
	public PaymentDefinition getPaymentDefBo(String boId) throws Exception {
		Session session=null;
		PaymentDefinition bo=null;
		try {
			session=template.getSessionFactory().openSession();
			bo=session.get(PaymentDefinition.class, Integer.parseInt(boId));
		} catch (Exception e) {
		}
		finally {
			session.close();
		}
		return bo;
	}
	public boolean deletePaymentDef(String boId) throws Exception {
		Session session=null;
		   boolean isCancelled=false;
		   
			 try {
				session=template.getSessionFactory().openSession();
				PaymentDefinition def=session.get(PaymentDefinition.class, Integer.parseInt(boId));
				def.setIsActive(false);
				session.update(def);
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
	public List<PaymentDefinition> listAllPaymentDefinition() throws Exception{
		List<PaymentDefinition> boList=(List<PaymentDefinition>) template.find("select bo from PaymentDefinition bo where bo.isActive=1 ");
		return boList;
	}
	public boolean checkForDuplicate(String defMonth, String defYear) throws Exception {
		 Session session=null;
		   boolean isDuplicate=false;
		   
			 try {
				session=template.getSessionFactory().openSession();
				Query query=session.createQuery("select bo from PaymentDefinition bo where bo.isActive=1 and bo.month=:month and bo.year=:year");
				query.setInteger("month", Integer.parseInt(defMonth));
				query.setInteger("year", Integer.parseInt(defYear));
				PaymentDefinition bo=(PaymentDefinition) query.uniqueResult();
				if(bo!=null){
					isDuplicate=true;
				}
			} catch (Exception e) {
				if(e instanceof NonUniqueResultException){
					isDuplicate=true;
				}else{
					throw e;
				}
			}
			finally {
				session.close();
			}
			return  isDuplicate;	
	}
	public List<PaymentDefinition> listAllPaymentDefinitionForYear(int year) {
		List<PaymentDefinition> boList=(List<PaymentDefinition>) template.find("select bo from PaymentDefinition bo where bo.isActive=1 and bo.year="+year);
		return boList;
	}
	public List<Subscriber> getAllSubscriberList(String areaId) throws Exception {
		List<Subscriber> boList=new ArrayList<Subscriber>();
		Session session=null;
		try {
			session=template.getSessionFactory().openSession();
			String queryStr="from Subscriber bo where bo.isActive=1 and bo.role!='ROLE_ADMIN'";
			if(areaId!=null && !areaId.isEmpty() && !areaId.equals("0")){
				queryStr="from Subscriber bo  where bo.isActive=1 and bo.role!='ROLE_ADMIN' and bo.areaId.id=:areaId and bo.areaId.isActive=1";
			}
			Query query=session.createQuery(queryStr);
			if(areaId!=null && !areaId.isEmpty() && !areaId.equals("0")){
				query.setInteger("areaId", Integer.parseInt(areaId));
			}
			boList=query.list();
			
			session.close();
		} catch (Exception e) {
			session.close();
			throw e;
		}
		return boList;
	}
	public Map<Integer, String> getAreaMap() throws Exception {
		Map<Integer,String> areaMap=new LinkedHashMap<Integer,String>();
		Session session=null;
		try {
			session=template.getSessionFactory().openSession();
			Query query=session.createQuery("from AreaMaster bo where bo.isActive=1");
			List<AreaMaster>  boList=query.list();
			if(boList!=null & !boList.isEmpty() ){
				for (AreaMaster bo : boList) {
					areaMap.put(bo.getId(),bo.getName());
				}
			}
			
			session.close();
		} catch (Exception e) {
			session.close();
			throw e;
		}
		return areaMap;
	}

}
