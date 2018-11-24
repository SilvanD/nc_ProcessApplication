package com.company.ProcessApplication.Repository;

import com.company.ProcessApplication.Model.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleDAO extends CrudRepository<Role, Long>{
    //public List<Role> findByUsername(String username);

    //public List<Role> findByRolename(String rolename);
}
