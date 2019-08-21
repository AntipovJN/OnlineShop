package com.example.demo.Service;

import com.example.demo.Model.Code;
import com.example.demo.Model.User;

import java.util.Optional;

public interface CodeService {

    void addCode(Code code);

    Optional<Code> getLastCodeForUser(User user);
}
