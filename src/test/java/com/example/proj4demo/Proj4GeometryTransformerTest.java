package com.example.proj4demo;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.locationtech.proj4j.CRSFactory;
import org.locationtech.proj4j.CoordinateReferenceSystem;
import org.locationtech.proj4j.CoordinateTransform;
import org.locationtech.proj4j.CoordinateTransformFactory;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.withPrecision;

public class Proj4GeometryTransformerTest {

    @Test
    void pointTransformTest()
    {
        // Контрольные точки СТО Роскартография 3.5-2020 (Приложение Ж)
        double wgs84_x = 44.03420944; // 44° 02' 03,154"
        double wgs84_y = 56.29180389; // 56° 17' 30,494"
        double p42_x = 44.03599139; // 44° 02' 09,569"
        double p42_y = 56.29164361; // 56° 17' 29,917"

        // Создаём JTS-геометрию
        GeometryFactory geometryFactory = new GeometryFactory();
        Point wgs84_point = geometryFactory.createPoint(new Coordinate(wgs84_x, wgs84_y));
        
        // Создаём трансформер
        Proj4GeometryTransformer transformer = new Proj4GeometryTransformer(createTransform());
        Point p42_point = (Point)transformer.transform(wgs84_point);

        assertThat(p42_point.getX()).isEqualTo(p42_x, withPrecision(6d));
        assertThat(p42_point.getY()).isEqualTo(p42_y, withPrecision(6d));
    }

    @Test
    void emptyPointTest()
    {
        GeometryFactory geometryFactory = new GeometryFactory();
        Point emptyPoint = geometryFactory.createPoint();

        Proj4GeometryTransformer transformer = new Proj4GeometryTransformer(createTransform());
        Point emptyPointResult = (Point)transformer.transform(emptyPoint);

        assertThat(emptyPointResult.isEmpty()).isTrue();
    }

    @Test
    void throwsExceptionOnNullTransformTest()
    {
        assertThrows(IllegalArgumentException.class, () -> {
            new Proj4GeometryTransformer(null);
        });
        
    }

    private CoordinateTransform createTransform()
    {
        CRSFactory crsFactory = new CRSFactory();
        CoordinateTransformFactory coordinateTransformFactory = new CoordinateTransformFactory();

        CoordinateReferenceSystem wgs84_crs = crsFactory.createFromParameters("WGS84", KnownProjStrings.WGS84);
        CoordinateReferenceSystem p42_crs = crsFactory.createFromParameters("P42", KnownProjStrings.P42);

        return coordinateTransformFactory.createTransform(wgs84_crs, p42_crs);
    }
}
