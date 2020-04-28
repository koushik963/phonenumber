package com.finra.phonenumber.service;

import java.util.List;

import com.finra.phonenumber.model.PhoneNumber;

public interface NumberGeneratorService {

	public Integer generatedNumbers(String phoneNumber);

	public List<PhoneNumber> getNumbers(String parentPhoneNumber, Integer pageNumber);
}
