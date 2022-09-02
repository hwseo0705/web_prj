package com.project.web_prj.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

// DB Configuration Class
@Configuration
@ComponentScan(basePackages = "com.project.web_prj")
public class DatabaseConfig {

    // DB 접속 정보 설정 (DataSource 설정)
    @Bean
    public DataSource dataSource() {

        HikariConfig config = new HikariConfig();

        // =========================== Oracle =========================== //

//        config.setUsername("spring4");
//        config.setPassword("1234");
//        config.setJdbcUrl("jdbc:oracle:thin:@localhost:1521:xe");
//        config.setDriverClassName("oracle.jdbc.driver.OracleDriver");

        // =========================== MariaDB =========================== //
        config.setUsername("root");
        config.setPassword("mariadb");
        config.setJdbcUrl("jdbc:mariadb://localhost:3306/spring4");
        config.setDriverClassName("org.mariadb.jdbc.Driver");

        return new HikariDataSource(config);
    }
}
