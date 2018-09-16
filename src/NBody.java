/**
 * @author Melissa Leal 
 * :) 
 * Simulation program for the NBody assignment
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class NBody {
	
	/**
	 * Read the specified file and return the radius
	 * @param fname is name of file that can be opened
	 * @return the radius stored in the file
	 * @throws FileNotFoundException if fname cannot be open
	 */
	public static double readRadius(String fname) throws FileNotFoundException  {
		Scanner s = new Scanner(new File(fname));
	
		
		double nBodies = s.nextInt();
		double radiusUniv = s.nextDouble();
		s.close();
	
		return radiusUniv;	
	}
	
	/**
	 * Read all data in file, return array of Celestial Bodies
	 * read by creating an array of Body objects from data read.
	 * @param fname is name of file that can be open
	 * @return array of Body objects read
	 * @throws FileNotFoundException if fname cannot be open
	 */
	public static Body[] readBodies(String fname) throws FileNotFoundException {
		
			Scanner s = new Scanner(new File(fname));
			
			// TODO: read # bodies, create array, ignore radius
			int nb = s.nextInt(); // # bodies to be read
			double rU = s.nextDouble();
			Body[] arrayBodies = new Body[nb];
			
			for(int k=0; k < nb; k++) {
				arrayBodies[k] = new Body(s.nextDouble(), s.nextDouble(), s.nextDouble(), s.nextDouble(), s.nextDouble(), s.next());

			}
			
			s.close();
			
			return arrayBodies;
	}
	public static void main(String[] args) throws FileNotFoundException{
		double totalTime = Math.pow(10, 9);
		double dt = 1000000.0;
		
		String fname= "./data/planets.txt";
		if (args.length > 2) {
			totalTime = Double.parseDouble(args[0]);
			dt = Double.parseDouble(args[1]);
			fname = args[2];
		}	
		
		Body[] bodies = readBodies(fname);
		double radius = readRadius(fname);
		
		StdDraw.setScale(-radius, radius);
		StdDraw.picture(0,0,"images/starfield.jpg");
	
		for(double t = 0.0; t < totalTime; t += dt) {
			
			
			double [] xforces = new double[bodies.length];
			double [] yforces = new double[bodies.length];
			for(int i = 0; i < bodies.length; i ++) {
				xforces[i] = bodies[i].calcNetForceExertedByX(bodies);
				yforces[i] = bodies[i].calcNetForceExertedByY(bodies);
			}
			
			for(int k = 0; k < bodies.length; k++) {
				bodies[k].update(dt, xforces[k], yforces[k]);
			}
			
			
			StdDraw.picture(0,0,"images/starfield.jpg");
			
			for(int i = 0; i < bodies.length; i ++) {
				bodies[i].draw();
			}
			
			
			StdDraw.show(10);
		}
		
		
		
		System.out.printf("%d\n", bodies.length);
		System.out.printf("%.2e\n", radius);
		for (int i = 0; i < bodies.length; i++) {
		    System.out.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
		   		              bodies[i].getX(), bodies[i].getY(), 
		                      bodies[i].getXVel(), bodies[i].getYVel(), 
		                      bodies[i].getMass(), bodies[i].getName());	
		}
	}
}



