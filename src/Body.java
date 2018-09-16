
public class Body {
	private double myXPos;
	private double myYPos; 
	private double myXVel; 
	private double myYVel; 
	private double myMass; 
	private String myFileName; 
	
	/**
	 * Create a Body from parameters 
	 * @param xp initial x position 
	 * @param yp initial y position 
	 * @param xv initial x velocity 
	 * @param yv initial y velocity 
	 * @param mass of object 
	 * @param filename of image for object animation
	 */
public Body(double xp, double yp, double xv, double yv, double mass, String filename) {
	myXPos = xp;
	myYPos = yp;
	myXVel = xv;
	myYVel = yv; 
	myMass = mass;
	myFileName = filename;
	
}
/**
 * Copy constructor: copy instance variables from one
 * body to this body
 * @param b is used to initialize this body
 */
public Body(Body b) {
	myXPos = b.getX();
	myYPos = b.getY();
	myXVel = b.getXVel();
	myYVel = b.getYVel(); 
	myMass = b.getMass();
	myFileName = b.getName();
}
/** 
 * The next 6 methods are getter methods, which are just used to 
 * return the variable from another method 
 * to thus have access to it outside that method 
 * @return myXPos, myYPos, myXVel, myYVel, myMass, myFileName
 */
public double getX() {
	return myXPos;
}
public double getY() {
	return myYPos;
}
public double getXVel() {
	return myXVel; 
}
public double getYVel() {
	return myYVel;
}
public double getMass() {
	return myMass;
}
public String getName() {
	return myFileName;
}

/**
 * Return the distance between this body and another 
 * @param b the other body to which distance is calculated 
 * @return distance between this body and b 			
 */
public double calcDistance(Body b) {
	double dx = myXPos - b.getX();
	double dy = myYPos - b.getY();
	return Math.sqrt(dx*dx + dy*dy);
}
/**
 * Returns the force exerted on this body by body p, 
 * @param p, the body specified in the parameters 
 * Calculates force using masses of the two bodies, 
 * G gravitational constant 
 * and distance between the two bodies
 * @return the force using the force formula 
 */
public double calcForceExertedBy(Body p) {
	double G = 6.67 * Math.pow(10, -11);
	double m1 = myMass;
	double m2 = p.getMass();
	double r = calcDistance(p);
	return G*(m1*m2)/(Math.abs(r*r));
}
/**
 * Returns the force exerted on this body by body p in the X direction, 
 * @param p, the body specified in the parameters 
 * Calculates force using masses of the two bodies, 
 * G gravitational constant 
 * and distance between the two bodies
 * @return the force using the force formula 
 */
public double calcForceExertedByX(Body p) {
	double totalForce = calcForceExertedBy(p);
	double dx = p.getX()- myXPos;
	double r = calcDistance(p);
	return totalForce*(dx/r);
}
public double calcForceExertedByY(Body p) {
	double totalForce = calcForceExertedBy(p);
	double dy = p.getY() - myYPos;
	double r = calcDistance(p);
	return totalForce*(dy/r);
}
/**
 * calculates net force exerted on a certain body
 * by all the bodies in the array- given in the parameter
 * @param bodies
 * @return the net force
 */
public double calcNetForceExertedByX(Body [] bodies) {
	double netsum = 0;
	for(Body b: bodies) {
		if(! b.equals(this)) {
			netsum += calcForceExertedByX(b);
	}
	}
	return netsum;
}
public double calcNetForceExertedByY (Body [] bodies) {
	double netsum =0;
	for(Body b: bodies) {
		if(! b.equals(this)) {
			netsum += calcForceExertedByY(b);
		}
	}
	return netsum;
}

/**
 * update is a modifier method that updates the values of 
 * myXVel, myYVel, myXPos, myYPos after calculating their 
 * correct values using acceleration and and the xforce and yforce
 * @param deltaT
 * @param xforce
 * @param yforce
 */
public void update(double deltaT, double xforce, double yforce) {
	double ax = xforce/ myMass;
	double ay = yforce/myMass;
	double nvx = myXVel + (deltaT*ax);
	double nvy = myYVel + (deltaT*ay);
	double nx = myXPos + (deltaT*nvx);
	double ny = myYPos + (deltaT*nvy);
	myXPos = nx;
	myXVel = nvx;
	myYPos = ny;
	myYVel = nvy; 
}

/**
 * The draw method just shows a non-moving image for 
 * the body in the simulation 
 * We will call this void method in the for loop 
 * so that it creates a moving image of the bodies,
 * using their different positions and velocities
 */
public void draw() {
	StdDraw.picture(myXPos, myYPos, "images/"+myFileName);
}

}





