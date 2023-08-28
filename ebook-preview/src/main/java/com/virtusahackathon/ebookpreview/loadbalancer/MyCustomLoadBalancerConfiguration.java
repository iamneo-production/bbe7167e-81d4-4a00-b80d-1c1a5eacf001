package com.virtusahackathon.ebookpreview.loadbalancer;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.loadbalancer.core.RandomLoadBalancer;
import org.springframework.cloud.loadbalancer.core.ReactorLoadBalancer;
import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
import org.springframework.cloud.loadbalancer.support.LoadBalancerClientFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

public class MyCustomLoadBalancerConfiguration {

	@Bean
	ReactorLoadBalancer<ServiceInstance> randomLoadBalancer(Environment environment,
			LoadBalancerClientFactory loadBalnacerClientFactory){
		String name=environment.getProperty(LoadBalancerClientFactory.PROPERTY_NAME);
		return new RandomLoadBalancer
				(loadBalnacerClientFactory.getLazyProvider
						(name, ServiceInstanceListSupplier.class), name);
	}
}
