import java.awt.*;

public class Planet {
    public double xxPos, yyPos, xxVel, yyVel, mass;
    public String imgFileName;
    private double G = 6.67e-11;
    public Planet(double xP, double yP, double xV,
                  double yV, double m, String img) {
        xxPos = xP; yyPos = yP;
        xxVel = xV; yyVel = yV;
        mass = m; imgFileName = img;
    }
    public Planet(Planet p){
        this.xxPos = p.xxPos; this.yyPos = p.yyPos;
        this.xxVel = p.xxVel; this.yyVel = p.yyVel;
        this.mass = p.mass; this.imgFileName = p.imgFileName;
    }
    public double calcDistance(Planet p){
        double dx = p.xxPos - this.xxPos, dy = p.yyPos - this.yyPos;
        return Math.sqrt(dx*dx + dy*dy);
    }

    public double calcForceExertedBy(Planet p){
        return G * this.mass * p.mass /(calcDistance(p) * calcDistance(p));
    }
    public double calcForceExertedByX(Planet p) {
        return calcForceExertedBy(p) * (p.xxPos - this.xxPos)/calcDistance(p);
    }
    public double calcForceExertedByY(Planet p) {
        return calcForceExertedBy(p) * (p.yyPos - this.yyPos)/calcDistance(p);
    }
    public double calcNetForceExertedByX(Planet[] p){
        double xxNetForce = 0;
        for (Planet pl : p) {
            if (!this.equals(pl)){
                xxNetForce += calcForceExertedByX(pl);
            }
        }
        return xxNetForce;
    }
    public double calcNetForceExertedByY(Planet[] p){
        double yyNetForce = 0;
        for (Planet pl : p) {
            if (!this.equals(pl)){
                yyNetForce += calcForceExertedByY(pl);
            }
        }
        return yyNetForce;
    }
    public void update(double dt, double fX, double fY){
        double ax = fX / mass, ay = fY / mass;
        xxVel += ax * dt;
        yyVel += ay * dt;
        xxPos += dt * xxVel;
        yyPos += dt * yyVel;
    }
    public void draw() {
        StdDraw.picture(xxPos, yyPos, "images/" + imgFileName);
        StdDraw.show();
    }
}
