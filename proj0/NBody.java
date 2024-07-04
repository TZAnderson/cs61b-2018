public class NBody {
    public static double readRadius(String filename){
        In in = new In(filename);
        int N = in.readInt();
        double Radius = in.readDouble();
        return Radius;
    }
    public static Planet[] readPlanets(String filename){
        In in = new In(filename);
        int N = in.readInt();
        double Radius = in.readDouble();
        Planet[] Planets = new Planet[N];
        for (int i = 0; i < N; i++){
            double xxPos = in.readDouble();
            double yyPos = in.readDouble();
            double xxVel = in.readDouble();
            double yyVel = in.readDouble();
            double mass = in.readDouble();
            String imgFilename = in.readString();
            Planet p = new Planet(xxPos, yyPos, xxVel, yyVel, mass, imgFilename);
            Planets[i] = p;
        }
        return Planets;
    }
    public static void main(String[] args) {
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        double radius = readRadius(filename);
        Planet[] planets = readPlanets(filename);
        StdDraw.setScale(-radius, radius);
        StdDraw.clear();
        StdDraw.picture(0, 0, "images/starfield.jpg");
        for(Planet pl : planets){
            pl.draw();
        }
        StdDraw.pause(2000);
        StdDraw.enableDoubleBuffering();
        double t = 0;
        while (t < T){
            double[] xForces = new double[planets.length];
            double[] yForces = new double[planets.length];
            for (int i = 0; i < planets.length; i++){
                xForces[i] = planets[i].calcNetForceExertedByX(planets);
                yForces[i] = planets[i].calcNetForceExertedByY(planets);
            }
            for (int i = 0; i < planets.length; i++){
                planets[i].update(dt, xForces[i], yForces[i]);
            }
            StdDraw.picture(0, 0, "images/starfield.jpg");
            for (Planet pl : planets){
                pl.draw();
            }
            StdDraw.show();
            StdDraw.pause(10);
            t += dt;
        }
        StdOut.printf("%d\n", planets.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < planets.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                    planets[i].yyVel, planets[i].mass, planets[i].imgFileName);
        }
    }
}
