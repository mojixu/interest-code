public class Planet {
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;
    public final double G=6.67e-11;
    public Planet(double xP,double yP,double xV,double yV,double m,String img){
        xxPos=xP;
        yyPos=yP;
        xxVel=xV;
        yyVel=yV;
        mass=m;
        imgFileName=img;
    }

    public Planet(Planet p){
        xxPos=p.xxPos;
        yyPos=p.yyPos;
        xxVel=p.xxVel;
        yyVel=p.yyVel;
        mass=p.mass;
        imgFileName=p.imgFileName;
    }

    public double calcDistance(Planet p){
        double r;
        double dx;
        double dy;
        dx=xxPos-p.xxPos;
        dy=yyPos-p.yyPos;
        r=Math.sqrt(dx*dx+dy*dy);
        return r;
    }

    public double calcForceExertedBy(Planet p){
         double r=calcDistance(p);
         return (G*mass*p.mass)/(r*r);
    }

    public double calcForceExertedByX(Planet p){
        double r=calcDistance(p);
        double f=calcForceExertedBy(p);
        return (f*(xxPos-p.xxPos))/r;
    }

    public double calcForceExertedByY(Planet p){
        double r=calcDistance(p);
        double f=calcForceExertedBy(p);
        return (f*(yyPos-p.yyPos))/r;
    }

    public double calcNetForceExertedByX(Planet[] allP){
        double totalforce=0;
        for(Planet p:allP){
            if(this.equals(p)){
                continue;
            }
            totalforce+=calcForceExertedByX(p);
        }
        return totalforce;
    }

    public double calcNetForceExertedByY(Planet[] allP){
        double totalforce=0;
        for(Planet p:allP){
            if(this.equals(p)){
                continue;
            }
            totalforce+=calcForceExertedByY(p);
        }
        return totalforce;
    }

    public void update(double dt,double fx,double fy){
        double ax=fx/mass;
        double ay=fy/mass;
        xxVel=xxVel+dt*ax;
        yyVel=yyVel+dt*ay;
        xxPos=xxPos+dt*xxVel;
        yyPos=yyPos+dt*yyVel;

    }
    public void draw(){
        StdDraw.picture(xxPos,yyPos,"images/"+imgFileName);
    }

}
