package com.javadoubts.core.models;

import javax.inject.Inject;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;

@Model(adaptables=Resource.class)
public class TrainingCOnfigModel {
	
	@Inject
	private String fname;

}
