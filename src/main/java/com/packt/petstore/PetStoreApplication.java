package com.packt.petstore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class PetStoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(PetStoreApplication.class, args);
    }


//    @Configuration
////    @Slf4j
//    class CORSConfig implements WebFluxConfigurer {
//
//        @Override
//        public void addCorsMappings(CorsRegistry registry) {
//            System.out.println("corsss!");
//            registry.addMapping("/**");
//        }
//    }

}

