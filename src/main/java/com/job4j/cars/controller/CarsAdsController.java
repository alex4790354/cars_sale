package com.job4j.cars.controller;

import com.job4j.cars.entity.CarsAd;
import com.job4j.cars.entity.CarsBrand;
import com.job4j.cars.service.CarsAdsService;
import com.job4j.cars.service.CarsBrandService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/carsad")
public class CarsAdsController {

	private CarsAdsService carsAdsService;
	private CarsBrandService carsBrandService;

	public CarsAdsController(CarsAdsService theCarsAdsService, CarsBrandService theCarsBrandService) {
		carsAdsService = theCarsAdsService;
		carsBrandService = theCarsBrandService;
	}
	
	// add mapping for "/list"

	@GetMapping("/list")
	public String listCustomers(Model theModel) {
		
		// get customers from db
		List<CarsAd> theCarsAds = carsAdsService.findAll();
		
		// add to the spring model
		theModel.addAttribute("carsads", theCarsAds);
		
		return "carsads/list-ads";
	}
	
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {
		
		// create model attribute to bind form data
		CarsAd theCarsAd = new CarsAd();
		theModel.addAttribute("carsad", theCarsAd);

        theModel.addAttribute("carsbrands", carsBrandService.findAll());
		
		return "carsads/ads-form";
	}

	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("carsAdId") int theId, Model theModel) {
		
		// get the customer from the service
		// set customer as a model attribute to pre-populate the form
		theModel.addAttribute("carsad", carsAdsService.findById(theId));

		theModel.addAttribute("carsbrands", carsBrandService.findAll());
		
		// send over to our form
		return "carsads/ads-form";
	}
	
	
	@PostMapping("/save")
	public String saveCustomer(@ModelAttribute("carsad") CarsAd theCarsAd) {
		
		// save the customer
		carsAdsService.save(theCarsAd);
		
		// use a redirect to prevent duplicate submissions
		return "redirect:/carsad/list";
	}
	
	
	@GetMapping("/delete")
	public String delete(@RequestParam("carsAdId") int theId) {
		
		// delete the customer
		carsAdsService.deleteById(theId);
		
		// redirect to /carsjpa/list
		return "redirect:/carsad/list";
	}
}


