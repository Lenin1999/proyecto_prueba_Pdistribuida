package com.distribuida.config;



import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.Jdbi;

@ApplicationScoped
public class DbConfig {

    @Inject
    @ConfigProperty(name="db.user")
    String dbUser;

    @Inject
    @ConfigProperty(name="db.password")
    String dbPassword;
    @Inject
    @ConfigProperty(name="db.url")
    String dbUrl;

    @PostConstruct
    public void init() {
        System.out.println("********post construct");
//opcion 1
      //  Config config = (Config) ConfigProvider.getConfig();
        //String user= config.getValue("db.user", String.class);
        //String password= config.getValue("db.password", String.class);
        //String url = config.getValue("db.url", String.class);

       // System.out.println(user);
        //System.out.println(password);
        //System.out.println(url);

        //opcion2
        System.out.println("op2: user" + dbUser);
        System.out.println("op2: password" + dbPassword);
        System.out.println("op2: url" +dbUrl);
    }
    public HikariConfig hikari(){
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(dbUrl);
        config.setUsername(dbUser);
        config.setPassword(dbPassword);
        HikariDataSource ds = new HikariDataSource(config);



        return ds;
    }
    public void test() {
    }
    public Handle conexion(){
        Jdbi jdbi = Jdbi.create(dbUrl, dbUser, dbPassword);
        return  jdbi.open();

    }
}
