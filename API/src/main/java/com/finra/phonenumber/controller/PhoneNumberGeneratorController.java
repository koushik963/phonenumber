package com.finra.phonenumber.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.finra.phonenumber.model.PhoneNumber;
import com.finra.phonenumber.repository.PhoneNumberRepository;
import com.finra.phonenumber.service.NumberGeneratorService;

@RestController
@RequestMapping("/phonenumber")
public class PhoneNumberGeneratorController {

	@Autowired
	NumberGeneratorService numberGeneratorService;

	@Autowired
	PhoneNumberRepository phoneNumberRepository;

	@GetMapping("/ping")
	public List<PhoneNumber> ping() {
		return phoneNumberRepository.findAll();
	}

	@PostMapping("/generate/{number}")
	public Integer generateNumbers(@PathVariable("number") String phoneNumber) {
		return numberGeneratorService.generatedNumbers(phoneNumber);
	}

	@GetMapping("/{number}/{pageNumber}")
	public List<PhoneNumber> getNumbers(@PathVariable("number") String parentPhoneNumber,
			@PathVariable("pageNumber") Integer pageNumber) {
		return numberGeneratorService.getNumbers(parentPhoneNumber, pageNumber);
	}
}
