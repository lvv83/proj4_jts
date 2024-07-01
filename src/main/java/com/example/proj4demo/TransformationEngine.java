package com.example.proj4demo;

import org.locationtech.jts.geom.Geometry;

import java.util.Map;

public interface TransformationEngine<T> {
	Geometry transform(KnownTransformation transformation, Geometry geometryToTransform);

	void setTransformations(Map<KnownTransformation, T> transformations);
}
