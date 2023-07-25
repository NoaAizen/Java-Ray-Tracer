package primitives;

/**
 * Material class represents the properties of a material used for shading.
 * It includes coefficients for transparency, reflection, diffuse reflection,
 * and specular reflection.
 *
 * Authors: Noga Jacobs and Noa
 */
public class Material {

    /**
     * The transparency coefficient
     */
    public Double3 kT =Double3.ZERO;
    /**
     * The reflection coefficient that changes the intensity of reflection.
     */
    public Double3 kR =Double3.ZERO;
    /**
     * The coefficient of a few percent of the energy that goes to direct reflection.
     */
    public Double3 kD = Double3.ZERO;
    /**
     * The diffusion coefficient that is a few percent of the light scattering.
     */
    public Double3 kS = Double3.ZERO;
    /**
     * The shininess coefficient that represents the level of specular reflection.
     */
    public int nShininess = 0;

    // ***************** Getters ********************** //

    /**
     * Gets the coefficient of a few percent of the energy that goes to direct reflection.
     *
     * @return Double3 The coefficient kS
     */
    public Double3 getkS() {
        return kS;
    }

    /**
     * Gets the diffusion coefficient that is a few percent of the light scattering.
     *
     * @return Double3 The coefficient kD
     */
    public Double3 getkD() {
        return kD;
    }

    /**
     * Gets the transparency coefficient.
     *
     * @return Double3 The coefficient kT
     */
    public Double3 getkT() {
        return kT;
    }

    /**
     * Gets the reflection coefficient that changes the intensity of reflection.
     *
     * @return Double3 The coefficient kR
     */
    public Double3 getkR() {
        return kR;
    }

    // ***************** Setters ********************** //

    /**
     * Sets the diffusion coefficient that is a few percent of the light scattering.
     * This is a setter with a type builder pattern.
     *
     * @param  num The new value for the diffusion coefficient
     * @return Material The Material object with the updated kD coefficient
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
     * Sets the transparency coefficient.
     * This is a setter with a type builder pattern.
     *
     * @param  num The new value for the transparency coefficient
     * @return Material The Material object with the updated kT coefficient
     */
    public Material setkT(double num) {
        this.kT = new Double3(num);
        return this;
    }

    /**
     * Sets the reflection coefficient that changes the intensity of reflection.
     * This is a setter with a type builder pattern.
     *
     * @param  num The new value for the reflection coefficient
     * @return Material The Material object with the updated kR coefficient
     */
    public Material setkR(double num) {
        this.kR = new Double3(num);
        return this;
    }
    /**
     * Sets the coefficient of a few percent of the energy that goes to direct reflection.
     * This is a setter with a type builder pattern.
     *
     * @param  num The new value for the coefficient kS
     * @return Material The Material object with the updated kS coefficient
     */
    public Material setkS(double num) {
        this.kS = new Double3(num);
        return this;
    }

    /**
     * Sets the coefficient of a few percent of the energy that goes to direct reflection.
     * This is a setter with a type builder pattern.
     *
     * @param kS The new Double3 value for the coefficient kS
     * @return Material The Material object with the updated kS coefficient
     */
    public Material setkS(Double3 kS) {
        this.kS = kS;
        return this;
    }

    /**
     * Sets the transparency coefficient.
     * This is a setter with a type builder pattern.
     *
     * @param kT The new Double3 value for the transparency coefficient
     * @return Material The Material object with the updated kT coefficient
     */
    public Material setkT(Double3 kT) {
        this.kT = kT;
        return this;
    }

    /**
     * Sets the reflection coefficient that changes the intensity of reflection.
     * This is a setter with a type builder pattern.
     *
     * @param kR The new Double3 value for the reflection coefficient
     * @return Material The Material object with the updated kR coefficient
     */
    public Material setkR(Double3 kR) {
        this.kR = kR;
        return this;
    }
    /**
     * Sets the shininess coefficient that represents the level of specular reflection.
     * This is a setter with a type builder pattern.
     *
     * @param nShininess The new value for the shininess coefficient
     * @return Material The Material object with the updated shininess coefficient
     */
    public Material setnShininess(int nShininess) {
        this.nShininess = nShininess;
        return this;
    }

}
