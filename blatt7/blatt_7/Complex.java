package blatt_7;

/** Die Komplexen Zahlen a+b i, mit (a+bi)+(c+di) = (a+c)+(b+d)i und (a+bi)(c+di) = (ac-bd)+(ad+bc)i */
class Complex {
	double real;
	double imag;
	

  /** Konstruktor Complex(Realteil, Imagin�rteil)*/
  public Complex(double a, double b) { 
    this.real=a;
    this.imag=b;
  }

  /** Zugriff auf den realen Anteil */
  double real() {return this.real;}
  /* Zugriff auf den imaginaeren Anteil */
  double imag() {return this.imag;}

  /** Multiplikation ohne Veraenderung der beteiligten Objekte */
  Complex mult(Complex o) {
	  double res_real=this.real()*o.real()-this.imag()*o.imag();
	  double res_imag=this.real()*o.imag()+this.imag()*o.real();
	  return new Complex(res_real,res_imag);
  }

  /** Multiplikation mit Veraenderung der beteiligten Objekte. Gibt this zurueck */
  Complex mult_inplace(Complex o) {
	  double real=this.real()*o.real()-this.imag()*o.imag();  	// don't use this.real=blabla because we need the old value of this.real down 
	  this.imag=this.real()*o.imag()+this.imag()*o.real();		//here
	  this.real=real;
	  return this;
  }

  /** Das Quadrat (c^2 = c*c) ohne Veraenderung der beteiligten Objekte */
  Complex sqr() {
	  return mult(this);
  }

  /** Das Quadrat (c^2 = c*c) in place. Veraendert das Objekt und gibt this zurueck. */
  Complex sqr_inplace() {
	  return mult_inplace(this); //takes same amount of time as hardcoded variant
  }

  /** Das Quadrat des Betrags */
  double abs_sqr() {
	  return this.real()*this.real()+this.imag()*this.imag();
  }

  /** Addition zweier komplexer Zahlen ohne Veraenderung der beteiligten Objekte */
  public Complex add(Complex c) {
	  double res_real=this.real()+c.real();
	  double res_imag=this.imag()+c.imag();
	  return new Complex(res_real,res_imag);
  }

  /** Addition zweier komplexer Zahlen mit Veraenderung der beteiligten Objekte */
  public Complex add_inplace(Complex c) {
	  this.real=this.real()+c.real();
	  this.imag=this.imag()+c.imag();
	  return this;
  }
}