package com.example.proj4demo;

import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Proj4DemoApplication implements CommandLineRunner {

	@Autowired
	Proj4TransformationEngine proj4TransformationEngine;

	@Autowired
	GeotoolsTransformationEngine geotoolsTransformationEngine;

	public static void main(String[] args) {
		SpringApplication.run(Proj4DemoApplication.class, args);
	}

	@Override
	public void run(String... args) {

		GeometryFactory geometryFactory = new GeometryFactory();
		Point wgsPoint = geometryFactory.createPoint(new Coordinate(44.03420944, 56.29180389));
		
		System.out.println("Using proj4...");

		Geometry outputGeometry = proj4TransformationEngine.transform(KnownTransformation.WGS84_P42, wgsPoint);

		System.out.println(outputGeometry);

		System.out.println("Using GeoTools...");

		Geometry outputGeometry2 = geotoolsTransformationEngine.transform(KnownTransformation.WGS84_P42, wgsPoint);

		System.out.println(outputGeometry2);
	}
}
