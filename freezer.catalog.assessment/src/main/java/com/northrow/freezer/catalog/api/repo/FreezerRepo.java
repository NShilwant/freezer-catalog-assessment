package com.northrow.freezer.catalog.api.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.northrow.freezer.catalog.api.model.Freezer;

@RepositoryRestResource
public interface FreezerRepo extends JpaRepository <Freezer , Integer> ,  JpaSpecificationExecutor<Freezer>  {


}
