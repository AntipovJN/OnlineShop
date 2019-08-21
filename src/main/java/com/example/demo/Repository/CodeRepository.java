package com.example.demo.Repository;

import com.example.demo.Model.Code;
import com.example.demo.Model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CodeRepository extends CrudRepository<Code, Long> {

    Optional<Code> findFirstByUserOrderByIdDesc(User user);
}
