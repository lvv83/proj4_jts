package com.example.proj4demo;

import org.locationtech.jts.geom.Geometry;

public interface TransformationEngine {
	Geometry transform(KnownTransformation transformation, Geometry geometryToTransform);
}
