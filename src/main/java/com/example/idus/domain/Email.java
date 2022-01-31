package com.example.idus.domain;

import com.example.idus.config.Code;
import com.example.idus.config.IdusException;

import javax.persistence.Embeddable;

@Embeddable
public class Email {

    private final String email;

    public Email(){
        this.email = "";
    }

    public Email(String email){
        if(!this.isSatisfiedEmailPolicy(email))
            throw new IdusException(Code.BAD_REQUEST);

        this.email = email;
    }

    private boolean isSatisfiedEmailPolicy(String email){
        return email.contains("@");
    }

    @Override
    public String toString() {
        return email;
    }
}
