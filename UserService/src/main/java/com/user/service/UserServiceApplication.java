package com.user.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

import com.user.service.service.UserServiceImpl;

@SpringBootApplication
@EnableFeignClients
public class UserServiceApplication {
	private static Logger logger=LoggerFactory.getLogger(UserServiceApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(UserServiceApplication.class, args);
		logger.info("application is started in info mode");
		logger.debug("application is started in info mode");
		checkOutput();
	}
	public static void checkOutput()
{
String s="abc";
String s2="abc";
String s3=new String("abc");
System.out.println(s==s2);
System.out.println(s.equals(s2));
System.out.println(s2==s3);
System.out.println(s2.equals(s3));

Integer num1 = 100;
Integer num2 = 100;
Integer num3 = 500;
Integer num4 = 500;
  
if(num1==num2){
    System.out.println("num1 == num2");
}
else{
    System.out.println("num1 != num2");
}
if(num3 == num4){
    System.out.println("num3 == num4");
}
else{
    System.out.println("num3 != num4");
}
}

}
