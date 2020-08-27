package com.tvdinh.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tvdinh.entity.RoleEntity;

public interface RoleRepository extends JpaRepository<RoleEntity, Long> {

}
