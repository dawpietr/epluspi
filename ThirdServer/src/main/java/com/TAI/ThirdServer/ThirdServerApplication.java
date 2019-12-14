package com.TAI.ThirdServer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Random;

@EnableDiscoveryClient
@SpringBootApplication
public class ThirdServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ThirdServerApplication.class, args);
	}

	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}
}
	@RestController
	class getpi {
		@Autowired
		private LoadBalancerClient loadBalancer;
		@Autowired
		private DiscoveryClient discoveryClient;

		@RequestMapping("/getpi")
		public double getPI() {
			return Math.PI;
		}

		@RequestMapping("/getrandom")
		public double getRandom() {
			double rand = new Random().nextDouble();
			return (rand);
		}
	}