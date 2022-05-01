package primitives;

public class Material {
    public Double3 kD = Double3.ZERO;
    public Double3 kS = Double3.ZERO;
    public int nShininess = 0;
    // ***************** Getters ********************** //
    /**
     * func getter with double
     * @return Double3 kS
     */
    public Double3 getkS() {
        return kS;
    }
    /**
     * func getter with double
     * @return Double3 kS
     */
    public Double3 getkD() {
        return kD;
    }

    // ***************** Setters ********************** //
    /**
     * func setter with double
     * @param num
     * @return
     */
    public Material setkD(double num) {
        this.kD = new Double3(num);
        return this;
    }

    /**
     * func setter with double
     * @param num
     * @return
     */
    public Material setkS(double num) {
        this.kS = new Double3(num);
        return this;
    }

    /**
     * func setter with Double3
     * @param kD
     * @return
     */
    public Material setkD(Double3 kD) {
        this.kD = kD;
        return this;
    }

    /**
     * func setter with Double3
     * @param kS
     * @return
     */
    public Material setkS(Double3 kS) {
        this.kS = kS;
        return this;
    }

    /**
     * func setter
     * @param nShininess
     * @return
     */
    public Material setnShininess(int nShininess) {
        this.nShininess = nShininess;
        return this;
    }

}
