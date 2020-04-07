package com.job4j.cars.controller;

import com.job4j.cars.entity.CarsAd;
import com.job4j.cars.entity.CarsBrand;
import com.job4j.cars.service.CarsAdsService;
import com.job4j.cars.service.CarsBrandService;
import com.job4j.cars.service.CarsModelService;
import jdk.nashorn.internal.runtime.NumberToString;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/carsad")
public class CarsAdsController {

	private CarsAdsService carsAdsService;
	private CarsBrandService carsBrandService;
	private CarsModelService carsModelService;

	public CarsAdsController(CarsAdsService pCarsAdsService, CarsBrandService pCarsBrandService, CarsModelService pCarsModelService) {
		carsAdsService = pCarsAdsService;
		carsBrandService = pCarsBrandService;
		carsModelService = pCarsModelService;
	}
	
	// add mapping for "/list"

	@GetMapping("/list")
	public String listCustomers(Model pModel) {
		
		// get customers from db
		// add to the spring model
		pModel.addAttribute("carsads", carsAdsService.findAll());
		
		return "carsads/list-ads";
	}
	
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model pModel) {
		
		// create model attribute to bind form data
		//CarsAd pCarsAd = new CarsAd();
		pModel.addAttribute("carsad", new CarsAd());

        pModel.addAttribute("carsbrands", carsBrandService.findAll());

		//pModel.addAttribute("carsmodels", carsModelService.findAll());
		
		return "carsads/ads-form";
	}

	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("carsAdId") int pId, Model pModel) {
		
		// get the customer from the service
		// set customer as a model attribute to pre-populate the form
		CarsAd carsAd = carsAdsService.findById(pId);
		pModel.addAttribute("carsad", carsAd);

		pModel.addAttribute("carsbrands", carsBrandService.findAll());

		pModel.addAttribute("carsmodels", carsModelService.findByBrandId(carsAd.getCarsBrandId()));
		//pModel.addAttribute("carsmodels", carsModelService.findAll());

		
		// send over to our form
		return "carsads/ads-form";
	}
	
	
	@PostMapping("/save")
	public String saveCustomer(@ModelAttribute("carsad") CarsAd pCarsAd) {
		
		// save p customer
		carsAdsService.save(pCarsAd);
		
		// use a redirect to prevent duplicate submissions
		return "redirect:/carsad/list";
	}
	
	
	@GetMapping("/delete")
	public String delete(@RequestParam("carsAdId") int pId) {
		
		// delete the customer
		carsAdsService.deleteById(pId);
		
		// redirect to /carsjpa/list
		return "redirect:/carsad/list";
	}
}


