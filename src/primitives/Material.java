package primitives;

public class Material {

    public Double3 kT =Double3.ZERO;//שקיפות
    public Double3 kR =Double3.ZERO;//השתקפות
    public Double3 kD = Double3.ZERO;
    public Double3 kS = Double3.ZERO;
    public int nShininess = 0;

    // ***************** Getters ********************** //
    /**
     * func getter
     * @return Double3 kS
     */
    public Double3 getkS() {
        return kS;
    }
    /**
     * func getter
     * @return Double3 kS
     */
    public Double3 getkD() {
        return kD;
    }

    // ***************** Setters ********************** //
    /**
     * func setter with type builder
     * @param num
     * @return Material
     */
    public Material setkD(double num) {
        this.kD = new Double3(num);
        return this;
    }
    /**
     * func setter with type builder
     * @param kD
     * @return Material
     */
    public Material setkD(Double3 kD) {
        this.kD = kD;
        return this;
    }

    //תיעוד
    public Material setkT(double num) {
        this.kT = new Double3(num);
        return this;
    }
    ///תיעוד
    public Material setkR(double num) {
        this.kR = new Double3(num);
        return this;
    }
    /**
     * func setter with type builder
     * @param num
     * @return Material
     */
    public Material setkS(double num) {
        this.kS = new Double3(num);
        return this;
    }


    /**
     * func setter with type builder
     * @param kS
     * @return Material
     */
    public Material setkS(Double3 kS) {
        this.kS = kS;
        return this;
    }
    //תיעוד
    public Material setkT(Double3 kT) {
        this.kT = kT;
        return this;
    }//תיעוד
    public Material setkR(Double3 kR) {
        this.kR = kR;
        return this;
    }
    /**
     * func setter with type builder
     * @param nShininess
     * @return Material
     */
    public Material setnShininess(int nShininess) {
        this.nShininess = nShininess;
        return this;
    }

}
