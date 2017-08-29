package com.jsh.dao.materials;

import org.hibernate.Query;

import com.jsh.base.BaseDAO;
import com.jsh.util.JshException;
import com.jsh.model.po.DepotHead;
import com.jsh.model.po.DepotItem;
import com.jsh.util.PageUtil;
import com.jsh.util.SearchConditionUtil;

public class DepotItemDAO extends BaseDAO<DepotItem> implements DepotItemIDAO
{
	/**
     * 设置dao映射基类
     * @return
     */
	@Override
    public Class<DepotItem> getEntityClass()
    {
        return DepotItem.class;
    }

    @SuppressWarnings("unchecked")
    @Override
	public void findByType(PageUtil<DepotItem> pageUtil,String type,Integer ProjectId,Long MId,String MonthTime,Boolean isPrev) throws JshException
    {
    	//多表联查,多表连查，此处用到了createSQLQuery，可以随便写sql语句，很方便
    	Query query;
		StringBuffer queryString = new StringBuffer();
		if(isPrev) {
			queryString.append("select sum(BasicNumber) as BasicNumber from jsh_depotitem,jsh_depothead  where jsh_depotitem.HeaderId = jsh_depothead.id and type='" + type +"'");
			if(ProjectId!=null) {
				queryString.append(" and DepotId='" + ProjectId +"'");
			}
			queryString.append(" and MaterialId ="+ MId + " and jsh_depothead.OperTime <'"+ MonthTime +"-01 00:00:00' ");
			query = this.getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(queryString + SearchConditionUtil.getCondition(pageUtil.getAdvSearch()));
		}
		else {
			queryString.append("select sum(BasicNumber) as BasicNumber from jsh_depotitem,jsh_depothead  where jsh_depotitem.HeaderId = jsh_depothead.id and type='" + type +"'");
			if(ProjectId!=null) {
				queryString.append(" and DepotId='" + ProjectId +"'");
			}
			queryString.append(" and MaterialId ="+ MId + " and jsh_depothead.OperTime >='"+ MonthTime +"-01 00:00:00' and jsh_depothead.OperTime <='"+ MonthTime +"-31 59:59:59' ");
			query = this.getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(queryString + SearchConditionUtil.getCondition(pageUtil.getAdvSearch()));
		}
        pageUtil.setTotalCount(query.list().size());
        pageUtil.setPageList(query.list());
    }

	@SuppressWarnings("unchecked")
	@Override
	public void findPriceByType(PageUtil<DepotItem> pageUtil,String type,Integer ProjectId,Long MId,String MonthTime,Boolean isPrev) throws JshException
	{
		//多表联查,多表连查，此处用到了createSQLQuery，可以随便写sql语句，很方便
		Query query;
		StringBuffer queryString = new StringBuffer();
		if (isPrev) {
			queryString.append("select sum(AllPrice) as AllPrice from jsh_depotitem,jsh_depothead  where jsh_depotitem.HeaderId = jsh_depothead.id and type='" + type + "'");
			if (ProjectId != null) {
				queryString.append(" and DepotId='" + ProjectId + "'");
			}
			queryString.append(" and MaterialId =" + MId + " and jsh_depothead.OperTime <'" + MonthTime + "-01 00:00:00' ");
			query = this.getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(queryString + SearchConditionUtil.getCondition(pageUtil.getAdvSearch()));
		} else {
			queryString.append("select sum(AllPrice) as AllPrice from jsh_depotitem,jsh_depothead  where jsh_depotitem.HeaderId = jsh_depothead.id and type='" + type + "'");
			if (ProjectId != null) {
				queryString.append(" and DepotId='" + ProjectId + "'");
			}
			queryString.append(" and MaterialId =" + MId + " and jsh_depothead.OperTime >='" + MonthTime + "-01 00:00:00' and jsh_depothead.OperTime <='" + MonthTime + "-31 59:59:59' ");
			query = this.getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(queryString + SearchConditionUtil.getCondition(pageUtil.getAdvSearch()));
		}
		pageUtil.setTotalCount(query.list().size());
		pageUtil.setPageList(query.list());
	}
    
    @SuppressWarnings("unchecked")
    @Override
	public void buyOrSale(PageUtil<DepotItem> pageUtil,String type, String subType,Long MId,String MonthTime, String sumType) throws JshException
    {
    	//多表联查,多表连查，此处用到了createSQLQuery，可以随便写sql语句，很方便
    	Query query;
    	if(sumType.equals("Number")) {
    		query = this.getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery("select sum(OperNumber) as OperNumber from jsh_depotitem,jsh_depothead  where jsh_depotitem.HeaderId = jsh_depothead.id and type='" + type +"' and subType='" + subType +"' and MaterialId ="+ MId + " and jsh_depothead.OperTime >='"+ MonthTime +"-01 00:00:00' and jsh_depothead.OperTime <='"+ MonthTime +"-31 59:59:59' " + SearchConditionUtil.getCondition(pageUtil.getAdvSearch()));
    	}
    	else {
    		query = this.getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery("select sum(AllPrice) as AllPrice from jsh_depotitem,jsh_depothead  where jsh_depotitem.HeaderId = jsh_depothead.id and type='" + type +"' and subType='" + subType +"' and MaterialId ="+ MId + " and jsh_depothead.OperTime >='"+ MonthTime +"-01 00:00:00' and jsh_depothead.OperTime <='"+ MonthTime +"-31 59:59:59' " + SearchConditionUtil.getCondition(pageUtil.getAdvSearch()));
        }
    	pageUtil.setTotalCount(query.list().size());
        pageUtil.setPageList(query.list());
    }

	@SuppressWarnings("unchecked")
	@Override
	public void findGiftByType(PageUtil<DepotItem> pageUtil,String subType,Integer ProjectId,Long MId,String type) throws JshException
	{
		//多表联查,多表连查，此处用到了createSQLQuery，可以随便写sql语句，很方便
		Query query;
		StringBuffer queryString = new StringBuffer();
		queryString.append("select sum(OperNumber) as OperNumber from jsh_depotitem,jsh_depothead  where jsh_depotitem.HeaderId = jsh_depothead.id and SubType='" + subType +"'");
		if(ProjectId!=null) {
			if(type.equals("in")){
				queryString.append(" and AllocationProjectId='" + ProjectId +"'"); //礼品充值时
			}
			else if(type.equals("out")){
				queryString.append(" and  DepotId='" + ProjectId +"'");
			}
		}
		queryString.append(" and MaterialId ="+ MId);
		query = this.getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(queryString + SearchConditionUtil.getCondition(pageUtil.getAdvSearch()));
		pageUtil.setTotalCount(query.list().size());
		pageUtil.setPageList(query.list());
	}
}



