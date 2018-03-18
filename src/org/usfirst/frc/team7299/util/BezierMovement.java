package org.usfirst.frc.team7299.util;

import java.util.function.Function;

public class BezierMovement {
	private double[] b;
	private double[] c;
	private double[] d;
	private final double DSP = 24.5;
	private final double DSPSQ = Math.pow(24.5, 2);
	
	public Function<Double, double[]> func;
	public Function<Double, double[]> der1;
	public Function<Double, double[]> der2;
	
	BezierMovement(double[] b, double[] c, double d[]) {
		this.b = b;
		this.c = c;
		this.d = d;
	}

	public double[] evaluate(double t) {
		double u = (1-t);
		double n1 = 3 * t * Math.pow(u, 2);
		double n2 = 3 * u * Math.pow(t, 2);
		double n3 = Math.pow(t, 3);
		double[] out = {
			n1 * b[0] + n2 * c[0] + n3 * d[0],
			n1 * b[1] + n2 * c[1] + n3 * d[1]
		};
		return out;
	}
	
	public double[] der(double t) {
		double u = (1-t);
		double n0 = 3 * Math.pow(u, 2);
		double n1 = 6 * u * t;
		double n2 = 3 * Math.pow(t, 2);
		double[] out = {
				n0 * b[0] + n1 * (c[0] - b[0]) + n2 * (d[0] - c[0]),
				n0 * b[1] + n1 * (c[1] - b[1]) + n2 * (d[1] - c[1])
		};
		return out;
	}
	
	public double[] der2(double t) {
		double u = (1-t);
		double[] out = {
				6 * u * (c[0] - 2 * b[0]) + 6 * t * (d[0] - 2 * c[0] + b[0]),
				6 * u * (c[1] - 2 * b[1]) + 6 * t * (d[1] - 2 * c[1] + b[1])
		};
		return out;
	}
	
	public double[] drivetrainDelta(double t) {
		double[] v = der(t);
		double[] a = der2(t);
		double m = Math.hypot(v[0], v[1]);
		double f = (Math.pow(v[0],2) + Math.pow(v[1], 2) + DSPSQ) * m;
		double g = 2 * DSP * (a[0] * v[1] + a[1] * v[0]);
		double[] out = {
				Math.sqrt((f - g) / m),
				Math.sqrt((f + g) / m)
		};
		return out;
	}
	
	
}
