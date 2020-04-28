package com.finra.phonenumber.repository;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.scheduling.annotation.Async;
import org.springframework.transaction.annotation.Transactional;

import com.finra.phonenumber.model.PhoneNumber;

@Transactional
public interface PhoneNumberRepository extends JpaRepository<PhoneNumber, BigInteger> {

	@Query(value = "SELECT * FROM PHONE_NUMBER WHERE PARENT_PHONE_NUMBER = :parentPhoneNumber ORDER BY ID LIMIT 10 OFFSET :pageNumber", nativeQuery = true)
	public List<PhoneNumber> getNumbers(String parentPhoneNumber, Integer pageNumber);
	
	@Query(value = "SELECT COUNT(*) FROM PHONE_NUMBER WHERE PARENT_PHONE_NUMBER = :parentPhoneNumber", nativeQuery = true)
	public Integer countByParentPhoneNumber(String parentPhoneNumber);
}
