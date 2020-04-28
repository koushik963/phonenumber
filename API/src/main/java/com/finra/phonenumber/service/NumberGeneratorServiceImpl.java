package com.finra.phonenumber.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.finra.phonenumber.model.PhoneNumber;
import com.finra.phonenumber.repository.PhoneNumberRepository;

@Service
public class NumberGeneratorServiceImpl implements NumberGeneratorService {

	@Autowired
	PhoneNumberRepository phoneNumberRepository;

	private static final int PAGE_SIZE = 10;

	private static final HashMap<Character, String> LETTERS_MAP = new HashMap<Character, String>() {

		private static final long serialVersionUID = 7287698471409612061L;

		{
			put('1', "1");
			put('2', "2abc");
			put('3', "3def");
			put('4', "4ghi");
			put('5', "5jkl");
			put('6', "6mno");
			put('7', "7pqrs");
			put('8', "8tuv");
			put('9', "9wxyz");
			put('0', "0");
		}
	};

	@Override
	public Integer generatedNumbers(String phoneNumber) {
		Integer existingCount = phoneNumberRepository.countByParentPhoneNumber(phoneNumber);
		if (existingCount > 0) {
			return existingCount;
		}
		List<PhoneNumber> finalList = new ArrayList<>();
		List<PhoneNumber> list = new ArrayList<>();
		finalList.add(PhoneNumber.builder().phoneNumber("").parentPhoneNumber(phoneNumber).build());

		for (int i = 0; i < phoneNumber.length(); i++) {
			String letters = LETTERS_MAP.get(phoneNumber.charAt(i));
			if (letters.length() == 0)
				continue;
			for (PhoneNumber str : finalList) {
				for (int j = 0; j < letters.length(); j++)
					list.add(PhoneNumber.builder().phoneNumber(str.getPhoneNumber() + letters.charAt(j))
							.parentPhoneNumber(phoneNumber).build());
			}
			finalList = list;
			list = new ArrayList<>();
		}
		phoneNumberRepository.saveAll(finalList);
		return finalList.size();
	}

	public void batchInsertPhoneNumbers(List<PhoneNumber> phoneNumbers) {
		phoneNumberRepository.saveAll(phoneNumbers);
	}

	@Override
	public List<PhoneNumber> getNumbers(String parentPhoneNumber, Integer pageNumber) {
		return phoneNumberRepository.getNumbers(parentPhoneNumber, pageNumber * PAGE_SIZE);
	}
}
