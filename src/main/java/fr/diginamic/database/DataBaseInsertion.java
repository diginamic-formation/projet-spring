package fr.diginamic.database;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.diginamic.value.ParameterExtracter;

@Service
public class DataBaseInsertion {

	@Autowired
	ParameterExtracter parameterExtracter;

	public void insertFromFile() {

	}
}
