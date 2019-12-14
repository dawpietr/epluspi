package com.TAI.FirstServer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;


@EnableDiscoveryClient
@SpringBootApplication
public class FirstServerApplication {

	public static void main(String[] args) {

		SpringApplication.run(FirstServerApplication.class, args);
	}
	@Bean
	public RestTemplate getRestTemplate(){
		return new RestTemplate();
	}


}

@RestController
class get_epluspi_resource {
	@Autowired
	private LoadBalancerClient loadBalancer;
	@Autowired
	private DiscoveryClient discoveryClient;
	@Autowired
	private RestTemplate restTemplate;

	public String getServerLink(String service) {
		List<ServiceInstance> list = discoveryClient.getInstances(service);
		if (list != null && list.size() > 0 ) {
			return ("http://localhost:" + list.get(0).getPort());
		}
		return null;
	}

	@GetMapping("/getepluspi")
	public String general() {

		RestTemplate piTemplate = new RestTemplate();
		RestTemplate eTemplate = new RestTemplate();
		double pi = piTemplate.getForObject(getServerLink("getpi")+ "/getpi", double.class);
		double e = eTemplate.getForObject(getServerLink("gete")+ "/gete", double.class);
		double random1 = eTemplate.getForObject(getServerLink("gete")+ "/getrandom", double.class);
		double random2 = eTemplate.getForObject(getServerLink("getpi")+ "/getrandom", double.class);
		String suma = "Suma PI + E = " + (pi + e) + "\r\n";
		String sumaBonus1 = "PI + E * RGD1 (randomly generated double from service gete) = " + ((pi + e)*random1) + "\r\n";
		String sumaBonus2 = "PI + E * RGD2 (randomly generated double from service getpi) = " + ((pi + e)*random2) + "\r\n";
		String siteView =
				"<html>\n" +
				"<header><title>E + Pi</title></header>\n" +
				"<body>\n" +
				suma + "</br>" +
				sumaBonus1 + "</br>" +
				sumaBonus2 + "</br>" +
				"</body>\n" +
				"</html>";
		return (siteView);
	}
}
