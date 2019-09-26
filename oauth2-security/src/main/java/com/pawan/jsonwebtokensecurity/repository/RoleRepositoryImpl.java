package com.pawan.jsonwebtokensecurity.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import org.springframework.stereotype.Repository;

import com.pawan.jsonwebtokensecurity.model.Role;



@Repository
@Transactional
public class RoleRepositoryImpl implements CustomRoleRepository {
	
	private static final String getroleQuery ="select role.* from role role where role.RoleName=?";
	
	private static final String getRoleByUsernameQuery="select u.UserName,r.RoleName from user u inner join user_role ur on u.id=ur.userId inner join role r on r.id=ur.roleId"
			+ " where u.UserName=?";
	@PersistenceContext
    EntityManager entityManager;
	
	
	@Override
	public Role getRoleByRoleName(String roleName) {
		Query query = entityManager.createNativeQuery(getroleQuery,Role.class);
        query.setParameter(1, roleName);
        List<Role> roleList= (List<Role>)query.getResultList();
        if(roleList==null  || roleList.isEmpty()){
        	return null;
        }
        return roleList.get(0);
	}


	@Override
	public Role getRoleByUserName(String username) {
		Query query = entityManager.createNativeQuery(getRoleByUsernameQuery,Role.class);
        query.setParameter(1, username);
        List<Role> roleList= (List<Role>)query.getResultList();
        if(roleList==null  || roleList.isEmpty()){
        	return null;
        }
        return roleList.get(0);
	}
}
