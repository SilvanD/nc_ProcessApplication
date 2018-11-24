package com.company.ProcessApplication.Repository;

import com.company.ProcessApplication.Model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDAO extends CrudRepository<User, String> {

}
