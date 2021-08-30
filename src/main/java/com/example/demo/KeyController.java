package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KeyController {

    @Autowired
    DemoController demo;

   @GetMapping("/keyvalue/{key}")
   public String getKeyValue(@PathVariable("key") String keyId){
      String keyValue = demo.getPropertyKeyValue(keyId);

       return keyValue;
   }
}
