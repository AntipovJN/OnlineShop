package com.example.demo.Repository;

import com.example.demo.Model.Code;
import com.example.demo.Model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CodeRepository extends CrudRepository<Code, Long> {

//    @Query("SELECT c FROM Code c WHERE c.user=:user")
    Optional<Code> findFirstByUserOrderByIdDesc(User user);
}
