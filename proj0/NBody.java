public class NBody {
    public static double readRadius(String filename){
        In in=new In(filename);
        int num=in.readInt();
        return in.readDouble();
    }

    public static Planet[] readPlanets(String filename){
        In in=new In(filename);
        int num=in.readInt();
        double r=in.readDouble();
        Planet[] p=new Planet[num];
        for(int i=0;i<num;i++){
            double x=in.readDouble();
            double y=in.readDouble();
            double xv=in.readDouble();
            double yv=in.readDouble();
            double m=in.readDouble();
            String img=in.readString();
            p[i]=new Planet(x,y,xv,yv,m,img);
        }
        return p;
    }

    public static void main(String[] args){
        double T=Double.parseDouble(args[0]);
        double dt=Double.parseDouble(args[1]);
        String filename=args[2];
        Planet[] p=readPlanets(filename);
        double r=readRadius(filename);
        StdDraw.setXscale(-r,r);
        StdDraw.setYscale(-r,r);
        StdDraw.enableDoubleBuffering();


        double t=0;
        int num=p.length;
        while(t<T){
            double[] xForces=new double[num];
            double[] yForces=new double[num];
            for(int i=0;i<num;i++){
                xForces[i]=p[i].calcNetForceExertedByX(p);
                yForces[i]=p[i].calcNetForceExertedByY(p);
            }
            for(int i=0;i<num;i++){
                p[i].update(dt,xForces[i],yForces[i]);
            }
            StdDraw.picture(0,0,"images/starfield.jpg");
            for(Planet x : p){
                x.draw();
            }
            StdDraw.show();
            StdDraw.pause(10);
            t+=dt;
        }
        StdOut.printf("%d\n", p.length);
        StdOut.printf("%.2e\n", r);
        for (int i = 0; i < p.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    p[i].xxPos, p[i].yyPos, p[i].xxVel,
                    p[i].yyVel, p[i].mass, p[i].imgFileName);
        }
    }
}
