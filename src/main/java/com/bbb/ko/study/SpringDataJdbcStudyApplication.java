package com.bbb.ko.study;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;

// transactionManager을 직접 설정하면 DataSourceTransactionManagerAutoConfiguration를 제거 해줘야하는 것으로 보인다.
// 이유 : spring.main.allow-bean-definition-overriding가 true 변경되어 Exception발생한다.
@SpringBootApplication(exclude = DataSourceTransactionManagerAutoConfiguration.class)
public class SpringDataJdbcStudyApplication {
	public static void main(String[] args) {
		SpringApplication.run(SpringDataJdbcStudyApplication.class, args);
	}

}
