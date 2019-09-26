package com.pawan.oauth2authorizationserver.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pawan.oauth2authorizationserver.model.Privilege;


@Repository
public interface PrivilegeRepository extends PrivilegeRepositoryCustom, JpaRepository<Privilege, Long> {

}
