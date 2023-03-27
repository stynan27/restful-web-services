package com.seamus.rest.webservices.restfulwebservices.filtering;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@RestController
public class FilteringController {
	
	@GetMapping("/filtering")
	public MappingJacksonValue filtering() {
		SomeBean someBean = new SomeBean("Value1", "Value2", "Value3");
		
		// Capability to filter specific filters DYNAMICALLY
		MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(someBean);
		
		// Filtering ALL fields except field3
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field3");
		
		// Filter provider with specific filters applied based on the filter name "SomeBeanFilter" added at class level
		FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanFilter", filter);
		
		mappingJacksonValue.setFilters(filters);
		
		return mappingJacksonValue;
	}
	
	@GetMapping("/filtering-list")
	public MappingJacksonValue filteringList() {
		List<SomeBean> list = Arrays.asList(new SomeBean("Value1", "Value2", "Value3"), new SomeBean("Value4", "Value5", "Value6"));
		
		// Capability to filter specific filters DYNAMICALLY
		MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(list);
		
		// Filtering ALL fields except field2 and field3
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field2", "field3");
		
		// Filter provider with specific filters applied based on the filter name "SomeBeanFilter" added at class level
		FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanFilter", filter);
		
		mappingJacksonValue.setFilters(filters);
		
		return mappingJacksonValue;
	}
}
