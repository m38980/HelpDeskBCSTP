package com.bcstp.helpdesk.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bcstp.helpdesk.models.Role;


public interface RoleRepository extends JpaRepository<Role, Integer>{

}
