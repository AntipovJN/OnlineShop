package com.example.demo.Service.impl;

import com.example.demo.Model.Code;
import com.example.demo.Model.User;
import com.example.demo.Repository.CodeRepository;
import com.example.demo.Service.CodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class CodeServiceImpl implements CodeService {

    private final CodeRepository codeRepository;

    @Autowired
    public CodeServiceImpl(CodeRepository codeRepository) {
        this.codeRepository = codeRepository;
    }

    @Transactional
    public void addCode(Code code) {
        codeRepository.save(code);
    }

    @Transactional
    @Override
    public Optional<Code> getLastCodeForUser(User user) {
        return codeRepository.findFirstByUserOrderByIdDesc(user);
    }
}
