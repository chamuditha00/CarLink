package com.example.carlink.Service;

import com.example.carlink.Entity.Register;
import com.example.carlink.Repository.RegisterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegisterService {

    @Autowired

    private RegisterRepository RegRepo;

    public Register save(Register register){
        RegRepo.save(register);
        return register;
    }

}
