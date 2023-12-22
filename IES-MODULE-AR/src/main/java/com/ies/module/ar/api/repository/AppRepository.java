package com.ies.module.ar.api.repository;

import com.ies.module.ar.api.entities.AppEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AppRepository extends JpaRepository<AppEntity,String> {

}
