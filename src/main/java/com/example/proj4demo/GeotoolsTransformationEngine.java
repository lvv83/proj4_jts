package com.example.proj4demo;

import org.geotools.api.referencing.operation.MathTransform;
import org.geotools.api.referencing.operation.TransformException;
import org.geotools.geometry.jts.JTS;
import org.locationtech.jts.geom.Geometry;

public class GeotoolsTransformationEngine extends AbstractTransformationEngine<MathTransform> {

	@Override
	public Geometry transform(KnownTransformation transformation, Geometry geometryToTransform) {
		if (transformations == null) {
			throw new RuntimeException("Transformations not found. Call 'setTransformations' method first");
		}

		MathTransform transform = transformations.get(transformation);
		if (transform == null)
		{
			throw new RuntimeException("Transformation not set for " + transformation.name());
		}

		try {
			return JTS.transform(geometryToTransform, transform);
		} catch (TransformException e) {
			throw new RuntimeException(e);
		}
	}
}
