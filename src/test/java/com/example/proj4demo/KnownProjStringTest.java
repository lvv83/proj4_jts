package com.example.proj4demo;

import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.locationtech.proj4j.CRSFactory;

public class KnownProjStringTest {

	CRSFactory crsFactory = new CRSFactory();

	@ParameterizedTest
	@MethodSource("allProjStrings")
	void testProjParameters(String projString) {
		// В случае некорректного параметра projString будет выброшено исключение
		crsFactory.createFromParameters("someValue", projString);
	}

	private static Stream<String> allProjStrings() {
		return Stream.of(
				KnownProjStrings.WGS84,
				KnownProjStrings.P42,
				KnownProjStrings.P42_MSK11_Q4,
				KnownProjStrings.P42_MSK11_Q5,
				KnownProjStrings.P42_MSK11_Q6);
	}

}
