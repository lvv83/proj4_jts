package com.example.proj4demo.config;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.EnumMap;
import java.util.Map;

import org.geotools.api.referencing.FactoryException;
import org.geotools.api.referencing.crs.CoordinateReferenceSystem;
import org.geotools.api.referencing.operation.MathTransform;
import org.geotools.referencing.CRS;
import org.geotools.referencing.crs.DefaultGeographicCRS;
import org.geotools.util.factory.Hints;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.util.StreamUtils;

import com.example.proj4demo.GeotoolsTransformationEngine;
import com.example.proj4demo.KnownTransformation;


@Configuration
public class GeotoolsConfig {

	static {
		Hints.putSystemDefault(Hints.FORCE_LONGITUDE_FIRST_AXIS_ORDER, Boolean.TRUE);
	}

	@Bean
	GeotoolsTransformationEngine geotoolsTransformationEngine(
			Map<KnownTransformation, MathTransform> knownTransformations) {

		GeotoolsTransformationEngine engine = new GeotoolsTransformationEngine();
		engine.setTransformations(knownTransformations);

		return engine;
	}

	@Bean(name = "geotoolsTransformations")
	Map<KnownTransformation, MathTransform> knownTransformations(Map<String, CoordinateReferenceSystem> knownCRS)
	{
		Map<KnownTransformation, MathTransform> map = new EnumMap<>(KnownTransformation.class);

		try {
			map.put(KnownTransformation.WGS84_P42, CRS.findMathTransform(DefaultGeographicCRS.WGS84, knownCRS.get("GT_P42")));
			map.put(KnownTransformation.P42_WGS84, CRS.findMathTransform(knownCRS.get("GT_P42"), DefaultGeographicCRS.WGS84));

			map.put(KnownTransformation.MSK11_Q4_WGS84, CRS.findMathTransform(knownCRS.get("GT_MSK11Q4"), DefaultGeographicCRS.WGS84));
		} catch (FactoryException e) {
			throw new RuntimeException(e);
		}
		return map;
	}

	@Bean(name = "GT_P42")
	CoordinateReferenceSystem p42()
	{
		return readCRS(p42_resource);
	}

	@Bean(name = "GT_MSK11Q4")
	CoordinateReferenceSystem msk11q4()
	{
		return readCRS(msk11q4_resource);
	}

	@Value("classpath:p42.wkt")
	private Resource p42_resource;

	@Value("classpath:msk11_q4.wkt")
	private Resource msk11q4_resource;

	private CoordinateReferenceSystem readCRS(Resource resource) {
		try (InputStream stream = resource.getInputStream())
		{
			String wkt = StreamUtils.copyToString(stream, Charset.defaultCharset());
			return CRS.parseWKT(wkt);
		} catch (FactoryException | IOException e) {
			throw new RuntimeException(e);
		}
	}
}
