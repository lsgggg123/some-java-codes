package com.lsgggg123.reactive.r2dbc;

import io.asyncer.r2dbc.mysql.MySqlConnectionConfiguration;
import io.asyncer.r2dbc.mysql.MySqlConnectionFactory;
import io.r2dbc.spi.ConnectionFactory;
import reactor.core.publisher.Mono;

public class SimpleR2dbcDemo {
    public static void main(String[] args) throws InterruptedException {
        /**
         ConnectionFactoryOptions options = ConnectionFactoryOptions.builder()
         .option(DRIVER, "mysql")
         .option(HOST, "127.0.0.1")
         .option(USER, "root")
         .option(PORT, 3306)  // optional, default 3306
         .option(PASSWORD, "zoMc2-8E") // optional, default null, null means has no password
         .option(DATABASE, "r2dbc") // optional, default null, null means not specifying the database
         .option(CONNECT_TIMEOUT, Duration.ofSeconds(3)) // optional, default null, null means no timeout
         .option(SSL, false) // optional, default sslMode is "preferred", it will be ignored if sslMode is set
         .build();
         ConnectionFactory connectionFactory = ConnectionFactories.get(options);
         **/
        MySqlConnectionConfiguration configuration = MySqlConnectionConfiguration
                .builder()
                .host("127.0.0.1")
                .database("r2dbc")
                .user("root")
                .password("zoMc2-8E")
                .build();
        ConnectionFactory connectionFactory = MySqlConnectionFactory.from(configuration);

        Mono.from(connectionFactory.create())
                .flatMapMany(connection -> Mono.from(connection.createStatement("select * from user_table where id=?")
                        .bind(0, 1)
                        .execute()))
                .flatMap(result -> Mono.from(result.map((r, meta) -> r.get("name", String.class))))
                .subscribe(System.out::println);

        Thread.sleep(200L);
    }
}
