package com.amazonaws.samples;

import java.util.List;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.ec2.AmazonEC2;
import com.amazonaws.services.ec2.AmazonEC2ClientBuilder;
import com.amazonaws.services.ec2.model.DescribeInstancesRequest;
import com.amazonaws.services.ec2.model.Filter;
import com.amazonaws.services.ec2.model.Reservation;
import com.amazonaws.services.ec2.model.Instance;
import com.amazonaws.services.ec2.model.Tag;

public class MyEC2Instance {

	public static void main(String[] args) {
		AmazonEC2 ec2 = AmazonEC2ClientBuilder.standard().withRegion(Regions.US_EAST_1).build();
		DescribeInstancesRequest describeRequest = new DescribeInstancesRequest();
		List<Filter> filters = describeRequest.getFilters();
		for (Filter filter : filters) {
			System.out.println("\t" + filter);
		}
		List<Reservation> reservations = ec2.describeInstances(describeRequest).getReservations();
		System.out.println("Retrieved following reservations:");
		for (Reservation reservation : reservations) {
			List<Instance> instances = reservation.getInstances();
			for (Instance instance : instances) {
				
				if (getInstanceName(instance).equals("Devops3_krishna")) {
					System.out.println("Instance Name:"+getInstanceName(instance));
					System.out.println("Instance Id:"+instance.getInstanceId());
					System.out.println("Instance Status:"+instance.getState().getName());
					System.out.println("Instance DNS Name: " + instance.getPublicDnsName());
				}
			}
		}
	}

	private static Object getInstanceName(Instance instance) {
		for (Tag tag : instance.getTags()) {
			if (tag.getKey().equalsIgnoreCase("name")) {
				return tag.getValue();
			}
		}
		return null;
	}
}