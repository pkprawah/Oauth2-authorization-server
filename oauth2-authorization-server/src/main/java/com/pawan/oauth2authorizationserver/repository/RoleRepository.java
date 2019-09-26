package com.pawan.oauth2authorizationserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.pawan.oauth2authorizationserver.model.Role;


@Repository
public interface RoleRepository extends CustomRoleRepository,JpaRepository<Role, Long> {

}
