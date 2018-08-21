package com.mfg.json.jsontestdemo;

import java.util.List;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.ec2.AmazonEC2;
import com.amazonaws.services.ec2.AmazonEC2ClientBuilder;
import com.amazonaws.services.ec2.model.DescribeInstancesRequest;
import com.amazonaws.services.ec2.model.Filter;
import com.amazonaws.services.ec2.model.Reservation;
import com.amazonaws.services.ec2.model.Instance;
import com.amazonaws.services.ec2.model.Tag;

public class RunningEC2 {

	

	public static void main(String[] args) {
		AmazonEC2 ec2 = AmazonEC2ClientBuilder.standard().withRegion(Regions.US_EAST_1).build();
		DescribeInstancesRequest describeRequest = new DescribeInstancesRequest();
		List<Filter> filters = describeRequest.getFilters();
		System.out.println("Setup following filters:");
		for (Filter filter : filters) {
			System.out.println("\t" + filter);
		}
		List<Reservation> reservations = ec2.describeInstances(describeRequest).getReservations();
		System.out.println("Retrieved following reservations:");
		int runningInstanceGroups = 0;
		int runningInstances = 0;
		for (Reservation reservation : reservations) {
			List<Instance> instances = reservation.getInstances();
			runningInstanceGroups++;
			runningInstances += instances.size();
			System.out.println(reservation.getGroupNames() + ":");
			for (Instance instance : instances) {
				System.out.println("\t" + getInstanceName(instance) + " (" + instance.getInstanceId() + " / "
						+ instance.getState().getName() + "): " + instance.getPublicDnsName());
			}
		}
		System.out.println(
				"Found " + runningInstances + " running instances in " + runningInstanceGroups + " instance groups");
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