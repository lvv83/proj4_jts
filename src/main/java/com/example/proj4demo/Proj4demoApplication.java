package com.example.proj4demo;

import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.geom.util.GeometryTransformer;
import org.locationtech.proj4j.CoordinateTransform;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.util.Map;

@SpringBootApplication
public class Proj4demoApplication implements CommandLineRunner {

	@Autowired
	Map<KnownTransformation, CoordinateTransform> knownTransformations;

	public static void main(String[] args) {
		SpringApplication.run(Proj4demoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		CoordinateTransform wgs84_p42 = knownTransformations.get(KnownTransformation.WGS84_P42);
		GeometryTransformer transformer = new Proj4GeometryTransformer(wgs84_p42);
		
		GeometryFactory geometryFactory = new GeometryFactory();
		Point wgsPoint = geometryFactory.createPoint(new Coordinate(44.03420944, 56.29180389));
		
		Geometry outputGeometry = transformer.transform(wgsPoint);

		System.out.println(outputGeometry);
	}
}
