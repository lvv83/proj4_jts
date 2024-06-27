package com.example.proj4demo;

import org.junit.jupiter.api.Test;
import org.locationtech.proj4j.ProjCoordinate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.withPrecision;

public class TransformFacadeTest {

    // Контрольные точки СТО Роскартография 3.5-2020 (Приложение Ж)

    double wgs84_x = 44.03420944;  // 44° 02' 03,154"
    double wgs84_y = 56.29180389;  // 56° 17' 30,494"

    double p42_x = 44.03599139;  // 44° 02' 09,569"
    double p42_y = 56.29164361;  // 56° 17' 29,917"

    // =======================================================================

    // Контрольная точка из 5-ой зоны МСК11

    // МСК-11 Зона 5
    double msk11_q5_x = 5289418.39;
    double msk11_q5_y = 848024.02;

    // Координаты пересчитаны с помощью Proj4Net (https://github.com/shanhuashuiqing/proj4net)
    // Рядом закомментированы координаты от А. С. с разбежкой в 4,5 знаках
    double wgs84_msk11_q5_x = 53.8016706475; // 53.8017766
    double wgs84_msk11_q5_y = 63.6162921557;  // 63.6162694

    // =======================================================================

    // Контрольная точка из 4-ой зоны МСК11 от А. С.

    // МСК-11 Зона 4
    double sa_msk11_q4_x = 4442499.0447;
    double sa_msk11_q4_y = 629037.2421;

    double sa_wgs84_x = 50.83369907;
    double sa_wgs84_y = 61.66640579;

    TransformFacade transformFacade = new TransformFacade();

    @Test
    public void test_wgs84_p42()
    {
        ProjCoordinate wgs84_point = new ProjCoordinate(wgs84_x, wgs84_y);
        ProjCoordinate p42_point = transformFacade.wgs84_to_p42(wgs84_point);

        assertThat(p42_point.x).isEqualTo(p42_x, withPrecision(6d));
        assertThat(p42_point.y).isEqualTo(p42_y, withPrecision(6d));
    }

    @Test
    public void test_p42_wgs84()
    {
        ProjCoordinate p42_point = new ProjCoordinate(p42_x, p42_y);
        ProjCoordinate wgs84_point = transformFacade.p42_to_wgs84(p42_point);

        assertThat(wgs84_point.x).isEqualTo(wgs84_x, withPrecision(6d));
        assertThat(wgs84_point.y).isEqualTo(wgs84_y, withPrecision(6d));
    }

    @Test
    public void test_msk11q5_wgs84()
    {
        ProjCoordinate msk11q5_point = new ProjCoordinate(msk11_q5_x, msk11_q5_y);
        ProjCoordinate wgs84_point = transformFacade.msk11q5_to_wgs84(msk11q5_point);

        assertThat(wgs84_point.x).isEqualTo(wgs84_msk11_q5_x, withPrecision(6d));
        assertThat(wgs84_point.y).isEqualTo(wgs84_msk11_q5_y, withPrecision(6d));
    }

    @Test
    public void test_msk11q4_wgs84()
    {
        ProjCoordinate msk11q4_point = new ProjCoordinate(sa_msk11_q4_x, sa_msk11_q4_y);
        ProjCoordinate wgs84_point = transformFacade.msk11q4_to_wgs84(msk11q4_point);

        assertThat(wgs84_point.x).isEqualTo(sa_wgs84_x, withPrecision(6d));
        assertThat(wgs84_point.y).isEqualTo(sa_wgs84_y, withPrecision(6d));
    }

}
