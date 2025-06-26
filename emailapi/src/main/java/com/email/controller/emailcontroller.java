package com.email.controller;

import com.email.model.Message;
import com.email.model.email;
import com.email.service.emailservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@CrossOrigin
public class emailcontroller {
        @Autowired
        emailservice eserv;

    @GetMapping("/welcome")
    public String Welcome() {
        return "welcome to email api";
    }

         @PostMapping("/sendemail")
    public ResponseEntity<?> sendmail(@RequestBody email mail)
    {

            System.out.println(mail);

          boolean b= this.eserv.sendemail(mail.getSubject(),mail.getMessage(),mail.getto());

          if(b)
          {
			return ResponseEntity.ok(new Message("Email sended sucessfully"));
			

          }else{

            return  ResponseEntity.ok(new Message("Email not send have some error"));
            }
    }
    

}
