package com.dksys.biz.user.cr;

import org.apache.ibatis.type.MappedTypes;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.dksys.biz.user.cr.cr03.model.User;

@SpringBootApplication
@MapperScan("com.dksys.biz.user.cr.cr03.mapper")
@MappedTypes({User.class})
public class BizCr03Application {

	public static void main(String[] args) {
		SpringApplication.run(BizCr03Application.class, args);
	}

}
