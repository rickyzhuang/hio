package com.alibaba.dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.alibaba.common.PageModel;


/**
 * 公用Dao继承基础接口
 * @author Sugar at 2013.7.21
 *
 */
@SuppressWarnings("rawtypes")
public interface IBaseDao {
	
	public <T>List<T> qryListBySql(String sql,Class obj) throws Exception;
	
	public <T>  boolean add(T obj) throws Exception;
	
	/*
	*//**
	 * 根据sql语句、Class实例查询List
	 * @author sjf
	 * @createTime 2013-7-1
	 * @param sql
	 * @param obj
	 * @return List<VO对象>
	 * 例如： qryListBySql("select * from person", Person.class)
	 *//*
	public <T>List<T> qryListBySql(String sql,Class obj) throws Exception;
	
	*//**
	 * 根据ibatics查询数据
	 * @author sjf
	 * @createTime 2013-9-4
	 * @param statementId
	 * @param parameters
	 * @return
	 * @throws Exception
	 *//*
	public <T> List<T> queryList(String statementId, Map<String, Object> parameters) throws Exception; 
	
	*//**
	 * 根据ibatics查询数据(返回Object)
	 * @author linwang
	 * @createTime 2014-1-6
	 * @param statementId
	 * @param parameters
	 * @return
	 * @throws Exception
	 *//*
	public <T> T query(String statementId, Map<String, Object> parameters) throws Exception;
	
	public <T> List<T> find(String hql)throws Exception;
	
	*//**
	 * 根据sql语句、参数值查询List
	 * @author sjf
	 * @createTime 2013-7-1
	 * @param sql
	 * @param params
	 * @return List<Map对象>
	 * 例如： qryListBySql("select * from person where username=?",new Object[]{"admin"})
	 *//*
	public List<Map<String,Object>> qryListBySql(String sql,Object params[])throws Exception;
	
	*//**
	 * 根据sql语句查询List
	 * @author sjf
	 * @createTime 2013-7-1
	 * @param sql
	 * @return List<Map对象>
	 * 例如： qryListBySql("select * from person")
	 *//*
	public List<Map<String,Object>> qryListBySql(String sql)throws Exception;
	
	*//**
	 * iBatis 查询记录list
	 * 
	 * @param statementId
	 *            【规则名：SqlMap的namespace+"." + 该sqlMap文件某片段的id】【baseServiceSql.getCustomList】
	 * @param object
	 *            查询条件值【参数，一般传HashMap】
	 * 
	 * @return list 找到的记录
	 *//*
	public <T> List<T> queryForList(String statementId, Object object) throws Exception;

	*//**
	 * 根据sql语句update、delete、insert
	 * @author sjf
	 * @createTime 2013-7-1
	 * @param sql
	 * @return
	 *//*
	public int updateSql(String sql) throws Exception;
	
	*//**
	 * 批量操作
	 * @param sql
	 * @return
	 * @throws Exception
	 *//*
	public int[] updateSqlBatch(String[] sql) throws Exception;
	
	
	
	*//**
	 * 根据sql语句查询总记录数
	 * @author sjf
	 * @createTime 2013-7-1
	 * @param sql
	 * @return
	 * 例如：qryCountBySql("select count(*) from person where username='admin'")
	 *//*
	public int qryCountBySql(String sql) throws Exception;
	
	*//**
	 * 分页查询记录总数
	 * @param demo 查询条件bean
	 * @return
	 *//*
	public int findForPageCount(String statementId, Object object)throws Exception;
	
	*//**
	 * 根据sql语句、参数值、Class实例查询Object
	 * @author sjf
	 * @createTime 2013-7-1
	 * @param sql
	 * @param params
	 * @param obj
	 * @return VO对象
	 * 例如： qryObjBySql("select * from person where username=?",new Object[]{"sjf1"},Person.class)
	 *//*
	public <T>T qryObjBySql(String sql,Object params[],Class obj) throws Exception;
	
	*//**
	 * 根据sql语句、参数值、分页查询Object
	 * @author chenXiyuan  
	 * @createTime 2014-8-25 
	 * @param sql
	 * @param pageModel
	 * @param params
	 * @return
	 * @throws Exception
	 *//*
	public PageModel queryPageModelBySql(String sql, PageModel pageModel,Object params[]) throws Exception;
	
	*//**
	 * 根据sql语句、参数值、Class实例分页查询Object
	 * @author chenXiyuan  
	 * @createTime 2014-8-25 
	 * @param sql
	 * @param pageModel
	 * @param params
	 * @return
	 * @throws Exception
	 *//*
	public PageModel queryPageModelBySql(String sql, PageModel pageModel,String entityClass,Object params[]) throws Exception;
	
	*//**
	 * 根据sql语句、参数值、分页查询Object
	 * @author chenXiyuan  
	 * @createTime 2014-8-25 
	 * @param sql
	 * @param pageModel
	 * @param paramters
	 * @return
	 * @throws Exception
	 *//*
	public List<?> queryListByPageModelUseSQL(String sql, PageModel pageModel, Object[] paramters) throws Exception;
	
	*//**
	 * 根据sql语句、参数值、Class实例分页查询Object
	 * @author chenXiyuan  
	 * @createTime 2014-8-25 
	 * @param sql
	 * @param pageModel
	 * @param entityClass
	 * @param paramters
	 * @return
	 * @throws Exception
	 *//*
	public List<?> queryListByPageModelUseSQL(String sql, PageModel pageModel, String entityClass, Object[] paramters) throws Exception;
*/}
