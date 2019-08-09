/**
 * 
 */
package com.example.gederana.dao;

import org.springframework.stereotype.Service;

import com.example.gederana.model.TestModelFX;

/**
 * @author gederanadewadatta
 *
 */
@Service
public interface TestService {

	TestModelFX getFX(String second, String third, String fourth, String fifth);

}
