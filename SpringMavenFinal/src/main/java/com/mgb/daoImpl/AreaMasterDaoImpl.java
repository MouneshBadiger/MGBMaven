package com.mgb.daoImpl;

import java.util.List;

import org.hibernate.NonUniqueResultException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Component;

import com.mgb.bo.AreaMaster;
import com.mgb.bo.PaymentDefinition;
import com.mgb.daos.IAreaMasterDao;
import com.mgb.forms.AreaMasterDto;
@Component
public class AreaMasterDaoImpl implements IAreaMasterDao {
	@Autowired
	private HibernateTemplate template;
	public AreaMasterDaoImpl() {
		// TODO Auto-generated constructor stub
	}
	public boolean addOrUpdateArea(AreaMaster bo) throws Exception {
		 Session session=null;
		   boolean isSaved=false;
		   
			 try {
				session=template.getSessionFactory().openSession();
				if(bo.getId()!=null && bo.getId()>0){
					session.update(bo);
				}else{
					session.save(bo);
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
	public AreaMaster getAreaBo(String boId) throws Exception {
		Session session=null;
		AreaMaster bo=null;
		try {
			session=template.getSessionFactory().openSession();
			bo=session.get(AreaMaster.class, Integer.parseInt(boId));
		} catch (Exception e) {
		}
		finally {
			session.close();
		}
		return bo;
	}
	public boolean deleteArea(String boId) throws Exception {
		Session session=null;
		   boolean isCancelled=false;
		   
			 try {
				session=template.getSessionFactory().openSession();
				AreaMaster def=session.get(AreaMaster.class, Integer.parseInt(boId));
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
	public List<AreaMaster> listAllAreas() throws Exception{
		List<AreaMaster> boList=(List<AreaMaster>) template.find("select bo from AreaMaster bo where bo.isActive=1 ");
		return boList;
	}
	public boolean checkForAreaDuplicate(AreaMasterDto areaMasterDto) throws Exception {

		 Session session=null;
		   boolean isDuplicate=false;
		   
			 try {
				session=template.getSessionFactory().openSession();
				Query query=session.createQuery("select bo from AreaMaster bo where bo.isActive=1 and bo.name=:name");
				query.setString("name", areaMasterDto.getName());
				AreaMaster bo=(AreaMaster) query.uniqueResult();
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
	
	

}
