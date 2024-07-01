package com.example.proj4demo.config;

import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;

import org.locationtech.proj4j.CRSFactory;
import org.locationtech.proj4j.CoordinateReferenceSystem;
import org.locationtech.proj4j.CoordinateTransform;
import org.locationtech.proj4j.CoordinateTransformFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.proj4demo.KnownProjStrings;
import com.example.proj4demo.KnownTransformation;
import com.example.proj4demo.Proj4TransformationEngine;

@Configuration
public class ApplicationConfig {

	
	@Bean
	Proj4TransformationEngine proj4TransformationEngine(
			Map<KnownTransformation, CoordinateTransform> knownTransformations) {
		Proj4TransformationEngine engine = new Proj4TransformationEngine();
		engine.setTransformations(knownTransformations);

		return engine;
	}

	@Bean
	Map<KnownTransformation, CoordinateTransform> knownTransformations(
			Map<String, CoordinateReferenceSystem> knownCRS) {

		Map<KnownTransformation, CoordinateTransform> map = new EnumMap<>(KnownTransformation.class);
		CoordinateTransformFactory factory = new CoordinateTransformFactory();

		map.put(KnownTransformation.WGS84_P42, factory.createTransform(knownCRS.get("WGS84"), knownCRS.get("P42")));
		map.put(KnownTransformation.P42_WGS84, factory.createTransform(knownCRS.get("P42"), knownCRS.get("WGS84")));
		map.put(KnownTransformation.MSK11_Q4_WGS84,
				factory.createTransform(knownCRS.get("MSK11_Q4"), knownCRS.get("WGS84")));
		map.put(KnownTransformation.MSK11_Q5_WGS84,
				factory.createTransform(knownCRS.get("MSK11_Q5"), knownCRS.get("WGS84")));
		map.put(KnownTransformation.MSK11_Q6_WGS84,
				factory.createTransform(knownCRS.get("MSK11_Q6"), knownCRS.get("WGS84")));

		return Collections.unmodifiableMap(map);

	}

	@Bean(name = "WGS84")
	CoordinateReferenceSystem wgs84(CRSFactory factory) {
		return factory.createFromParameters("WGS84", KnownProjStrings.WGS84);
	}

	@Bean(name = "P42")
	CoordinateReferenceSystem p42(CRSFactory factory) {
		return factory.createFromParameters("P42", KnownProjStrings.P42);
	}

	@Bean(name = "MSK11_Q4")
	CoordinateReferenceSystem msk11q4(CRSFactory factory) {
		return factory.createFromParameters("MSK11_Q4", KnownProjStrings.P42_MSK11_Q4);
	}

	@Bean(name = "MSK11_Q5")
	CoordinateReferenceSystem msk11q5(CRSFactory factory) {
		return factory.createFromParameters("MSK11_Q5", KnownProjStrings.P42_MSK11_Q5);
	}

	@Bean(name = "MSK11_Q6")
	CoordinateReferenceSystem msk11q6(CRSFactory factory) {
		return factory.createFromParameters("MSK11_Q6", KnownProjStrings.P42_MSK11_Q6);
	}

	@Bean
	CRSFactory crsFactory() {
		return new CRSFactory();
	}
}
