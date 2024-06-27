package com.example.proj4demo;

import org.locationtech.proj4j.CRSFactory;
import org.locationtech.proj4j.CoordinateReferenceSystem;
import org.locationtech.proj4j.CoordinateTransform;
import org.locationtech.proj4j.CoordinateTransformFactory;
import org.locationtech.proj4j.ProjCoordinate;

public class TransformFacade {

    private final CRSFactory crsFactory = new CRSFactory();
    private final CoordinateTransformFactory coordinateTransformFactory = new CoordinateTransformFactory();

    private final CoordinateReferenceSystem wgs84_crs = crsFactory.createFromParameters("WGS84", KnownProjStrings.WGS84);
    private final CoordinateReferenceSystem p42_crs = crsFactory.createFromParameters("P42", KnownProjStrings.P42);
    private final CoordinateReferenceSystem msk11q4_crs = crsFactory.createFromParameters("MSK11_Q4", KnownProjStrings.P42_MSK11_Q4);
    private final CoordinateReferenceSystem msk11q5_crs = crsFactory.createFromParameters("MSK11_Q5", KnownProjStrings.P42_MSK11_Q5);
    private final CoordinateReferenceSystem msk11q6_crs = crsFactory.createFromParameters("MSK11_Q6", KnownProjStrings.P42_MSK11_Q6);

    private final CoordinateTransform wgs84_p42_transform = coordinateTransformFactory.createTransform(wgs84_crs, p42_crs);
    private final CoordinateTransform p42_wgs84_transform = coordinateTransformFactory.createTransform(p42_crs, wgs84_crs);
    private final CoordinateTransform msk11q4_wgs84_transform = coordinateTransformFactory.createTransform(msk11q4_crs, wgs84_crs);
    private final CoordinateTransform msk11q5_wgs84_transform = coordinateTransformFactory.createTransform(msk11q5_crs, wgs84_crs);
    private final CoordinateTransform msk11q6_wgs84_transform = coordinateTransformFactory.createTransform(msk11q6_crs, wgs84_crs);

    public ProjCoordinate wgs84_to_p42(ProjCoordinate coordinate)
    {
        ProjCoordinate result = new ProjCoordinate();
        wgs84_p42_transform.transform(coordinate, result);

        return result;
    }

    public ProjCoordinate p42_to_wgs84(ProjCoordinate coordinate)
    {
        ProjCoordinate result = new ProjCoordinate();
        p42_wgs84_transform.transform(coordinate, result);

        return result;
    }

    public ProjCoordinate msk11q4_to_wgs84(ProjCoordinate coordinate)
    {
        ProjCoordinate result = new ProjCoordinate();
        msk11q4_wgs84_transform.transform(coordinate, result);

        return result;
    }

    public ProjCoordinate msk11q5_to_wgs84(ProjCoordinate coordinate)
    {
        ProjCoordinate result = new ProjCoordinate();
        msk11q5_wgs84_transform.transform(coordinate, result);

        return result;
    }

    public ProjCoordinate msk11q6_to_wgs84(ProjCoordinate coordinate)
    {
        ProjCoordinate result = new ProjCoordinate();
        msk11q6_wgs84_transform.transform(coordinate, result);

        return result;
    }
}
