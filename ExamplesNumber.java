import tester.*;

interface Number {
    int numerator();
    int denominator();
    Number add(Number other);
    Number multiply(Number other);
    String toString();
    double toDouble();
}

class WholeNumber implements Number{
    int n;

    WholeNumber(int n){
        this.n = n;
    }

    public int numerator(){
        return this.n;
    }

    public int denominator(){
        return 1;
    }
    
    public Number add(Number other){
        return new Fraction(this.denominator()*other.numerator() + 
        other.denominator()*this.numerator(), 
        this.denominator()*other.denominator());   
    }

    public Number multiply(Number other){
        return new Fraction(this.numerator() * other.numerator(), this.denominator() * other.denominator());
    }

    public String toString(){
        return String.valueOf(n);
    }

    public double toDouble(){
        return Double.valueOf(n);
    }

}

class Fraction implements Number{
    int n;
    int d;

    Fraction(int n, int d){
        this.n = n;
        this.d = d;
    }

    public int numerator(){
        return n;
    }

    public int denominator(){
        return d;
    }

    public Number add(Number other){
        return new Fraction(this.denominator()*other.numerator() + 
        other.denominator()*this.numerator(), 
        this.denominator()*other.denominator());    
    }

    public Number multiply(Number other){
        return new Fraction(this.numerator() * other.numerator(), this.denominator() * other.denominator());
    }

    public String toString(){
        return String.valueOf(n) + "/" + String.valueOf(d);
    }

    public double toDouble(){
        return Double.valueOf(n) / Double.valueOf(d);
    }
}


//Exploration
class ExamplesNumber{
    double x = 0.1 + 0.2 + 0.3;
    double y = 0.1 + (0.2 + 0.3);

    Fraction num1 = new Fraction(1,10);
    Fraction num2 = new Fraction(2,10);
    Fraction num3 = new Fraction(3,10);

    boolean testFraction(Tester t){
        return t.checkExpect((this.num1.add(num2)).add(num3).toString(), "600/1000") &&
        t.checkExpect((this.num2.add(num3)).add(num1).toString(), "600/1000");
    }
}