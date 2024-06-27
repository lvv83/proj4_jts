package com.example.proj4demo;

public class KnownProjStrings {

	/*
	 * Внимание!!!
	 * Есть различие в параметрах методов "Coordinate frame rotation" и
	 * "Position vector"
	 * Параметры поворота берутся с противоположным знаком!!!
	 * 
	 * В ArcGIS трансформация координат выполняется методом
	 * "Coordinate frame rotation"
	 * В библиотеке Proj трансформация выполняется методом "Position vector"
	 * 
	 */

	public static final String WGS84 = "+proj=longlat +ellps=WGS84 +datum=WGS84 +no_defs";

	public static final String P42 = "+proj=longlat +ellps=krass +towgs84=23.57,-140.95,-79.8,0,0.35,0.79,-0.22 +no_defs";

	// Местные системы координат Республики Коми (шестиградусные)

	public static final String P42_MSK11_Q4 = """
			+proj=tmerc +lat_0=0 +lon_0=50.03333333333 +k=1 +x_0=4400000.0 +y_0=-6211057.63 +ellps=krass \
			+towgs84=23.57,-140.95,-79.8,0,0.35,0.79,-0.22 +units=m +no_defs\
			""";

	public static final String P42_MSK11_Q5 = """
			+proj=tmerc +lat_0=0 +lon_0=56.03333333333 +k=1 +x_0=5400000.0 +y_0=-6211057.63 +ellps=krass \
			+towgs84=23.57,-140.95,-79.8,0,0.35,0.79,-0.22 +units=m +no_defs\
			""";

	// не протестировано - нет контрольной точки
	public static final String P42_MSK11_Q6 = """
			+proj=tmerc +lat_0=0 +lon_0=62.03333333333 +k=1 +x_0=6400000.0 +y_0=-6211057.63 +ellps=krass \
			+towgs84=23.57,-140.95,-79.8,0,0.35,0.79,-0.22 +units=m +no_defs\
			""";

}
