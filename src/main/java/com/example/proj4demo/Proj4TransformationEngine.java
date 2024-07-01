package com.example.proj4demo;

import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.util.GeometryTransformer;
import org.locationtech.proj4j.CoordinateTransform;

import java.util.HashMap;
import java.util.Map;

public class Proj4TransformationEngine implements TransformationEngine<CoordinateTransform> {
	@Override
	public Geometry transform(KnownTransformation transformation, Geometry geometryToTransform) {
		GeometryTransformer geometryTransformer = getGeometryTransformer(transformation);
		return geometryTransformer.transform(geometryToTransform);
	}

	private Map<KnownTransformation, CoordinateTransform> transformations;
	
	@Override
	public void setTransformations(Map<KnownTransformation, CoordinateTransform> transformations) {
		if (transformations == null)
			throw new IllegalArgumentException("transformations parameter can not be a null");
		
		this.transformations = transformations;
	}

	private Map<KnownTransformation, GeometryTransformer> transformers = new HashMap<>();
	
	private GeometryTransformer getGeometryTransformer(KnownTransformation transformation) {
		GeometryTransformer transformer = transformers.get(transformation);
		if (transformer != null) {
			return transformer;
		}
		
		if (transformations == null) {
			throw new RuntimeException("Transformations not found. Call 'setTransformations' method first");
		}
		
		CoordinateTransform coordinateTransform = transformations.get(transformation);
		if (coordinateTransform == null)
		{
			throw  new RuntimeException("Transformation not set for " + transformation.name());
		}

		transformer = new Proj4GeometryTransformer(coordinateTransform);
		transformers.put(transformation, transformer);

		return transformer;
	}

}
