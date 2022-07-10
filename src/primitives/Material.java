package primitives;

/**
 * @author noa amd noga 
 */
public class Material {

    /**
     * The transparency coefficient
     */
    public Double3 kT =Double3.ZERO;
    /**
     * Reflection coefficient Change the intensity of reflection.
     */
    public Double3 kR =Double3.ZERO;
    /**
     * coefficient of a few percent of the energy goes to direct reflection
     */
    public Double3 kD = Double3.ZERO;
    /**
     * The diffusion coefficient is a few percent of the light scattering.
      */ 
    public Double3 kS = Double3.ZERO;
    /**
     * The coefficient of color is radiant
     */
    public int nShininess = 0;

    // ***************** Getters ********************** //
    /**A coefficient of a few percent of the energy goes to direct reflection
     * func getter
     * @return Double3 kS
     */
    public Double3 getkS() {
        return kS;
    }

    /**The diffusion coefficient is a few percent of the light scattering.
     * func getter
     * @return Double3 kd
     */
    public Double3 getkD() {
        return kD;
    }

    /**
     * The transparency coefficient
     * @return double3
     */
    public Double3 getkT() {
        return kT;
    }

    /**
     * Reflection coefficient Change the intensity of reflection.
     * @return double3
     */
    public Double3 getkR() {
        return kR;
    }
    // ***************** Setters ********************** //

    /**
     * The diffusion coefficient is a few percent of the light scattering.
     * func setter with type builder
     * @param  num double
     * @return Material
     */
    public Material setkD(double num) {
        this.kD = new Double3(num);
        return this;
    }
    /**
     * The diffusion coefficient is a few percent of the light scattering.
     * func setter with type builder
     * @param  kD Double3
     * @return Material
     */
    public Material setkD(Double3 kD) {
        this.kD = kD;
        return this;
    }

    /**
     *  func setter with type builder
     * The transparency coefficient
     * @param  num double
     * @return Material
     */
    public Material setkT(double num) {
        this.kT = new Double3(num);
        return this;
    }

    /**
     * Reflection coefficient Change the intensity of reflection.
     *  func setter with type builder
     * @param  num double
     * @return Material
     */
    public Material setkR(double num) {
        this.kR = new Double3(num);
        return this;
    }
    /**
     * coefficient of a few percent of the energy goes to direct reflection
     * func setter with type builder
     * @param  num double
     * @return Material
     */
    public Material setkS(double num) {
        this.kS = new Double3(num);
        return this;
    }

    /**
     * A coefficient of a few percent of the energy goes to direct reflection
     * func setter with type builder
     * @param kS -double3
     * @return Material
     */
    public Material setkS(Double3 kS) {
        this.kS = kS;
        return this;
    }

    /**
     *  func setter with type builder
     * The transparency coefficient
     * @param  kT Double3
     * @return Material
     */
    public Material setkT(Double3 kT) {
        this.kT = kT;
        return this;
    }

    /**
     * func setter with type builder
     * Reflection coefficient Change the intensity of reflection.
     * @param kR Double3
     * @return Material
     */
    public Material setkR(Double3 kR) {
        this.kR = kR;
        return this;
    }
    /**
     * The coefficient of color is radiant
     * func setter with type builder
     * @param nShininess - int
     * @return Material
     */
    public Material setnShininess(int nShininess) {
        this.nShininess = nShininess;
        return this;
    }

}
