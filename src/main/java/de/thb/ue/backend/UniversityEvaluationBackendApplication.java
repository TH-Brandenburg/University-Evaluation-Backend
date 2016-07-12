/*
 * Copyright 2016 Max Gregor
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package de.thb.ue.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;

@SpringBootApplication
public class UniversityEvaluationBackendApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        System.setProperty("javax.net.ssl.trustStore", "C:\\Users\\Tobi\\Development\\workspace\\java\\University-Evaluation-Backend\\src\\main\\resources\\cacerts");
        System.setProperty("javax.net.ssl.trustStorePassword", "test123");
        SpringApplication.run(UniversityEvaluationBackendApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(UniversityEvaluationBackendApplication.class);
    }
}
