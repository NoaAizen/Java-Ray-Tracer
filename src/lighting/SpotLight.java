package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

import java.util.List;
/**
 * SpotLight is a type of light source that emits a directional light with a narrowing effect.
 * It inherits from the PointLight class and adds the ability to focus the light in a specific direction.
 * The intensity of the light is affected by the angle between the light direction and the light ray hitting the surface.
 * When the light ray is aligned with the direction, the intensity is at its maximum, and it decreases as the angle increases.
 * This creates a spotlight effect.
 *
 * Authors: Noga Jacobs and Noa
 */public class SpotLight  extends PointLight {
    /**
     * The direction of the light beam.
     */
    private Vector direction;

    /**
     * The angle at which the light beam narrows.
     * The narrowBeam value represents the cosine of the half-angle of the beam's aperture.
     * A smaller value makes the beam narrower, and a larger value makes it wider.
     */
    private double narrowBeam = 0d;

    // ***************** Constructor ********************** //

    /**
     * Constructor to create a SpotLight with specified intensity, position, and direction.
     *
     * @param intensity The intensity of the light, represented as a Color.
     * @param _position  The position of the light source, represented as a Point.
     * @param _direction The direction of the light beam, represented as a Vector.
     */
    public SpotLight(Color intensity, Point _position, Vector _direction) {
        super(intensity,_position);
        this.direction = _direction.normalize();
    }
    // ***************** Getter ********************** //

    /**
     * Get the direction of the light beam.
     *
     * @return The direction vector of the light beam.
     */
    public Vector getDirection() {
        return direction;
    }

    /**
     * Get the narrowing effect of the light beam.
     * The narrowBeam value represents the cosine of the half-angle of the beam's aperture.
     *
     * @return The narrowBeam value.
     */
    public double getNarrowBeam() {
        return narrowBeam;
    }


    // ***************** Setter ********************** //

    /**
     * Set the narrowing effect of the light beam.
     * The narrowBeam value represents the cosine of the half-angle of the beam's aperture.
     * A smaller value makes the beam narrower, and a larger value makes it wider.
     *
     * @param narrowBeam The narrowBeam value to set.
     * @return The SpotLight instance with the updated narrowBeam value.
     */
    public SpotLight setNarrowBeam(double narrowBeam) {
        this.narrowBeam = narrowBeam;
        return this;
    }


    // ***************** Override ********************** //
    /**
     * Get the intensity of the SpotLight at a specific point.
     * The intensity is affected by the angle between the light direction and the light ray hitting the surface.
     * The SpotLight has a narrowing effect based on the narrowBeam value.
     *
     * @param point The point for which to calculate the intensity of the SpotLight.
     * @return The intensity of the SpotLight at the specified point, represented as a Color.
     */
    @Override
    public Color getIntensity(Point point) {
        Color Ic = super.getIntensity(point);
        double lv= getL(point).dotProduct(direction);
        double factor = Math.max(0,lv);
        return Ic.scale(factor);
    }

    /**
     * Get a list of vectors representing the directions of the light beams that affect a specific point.
     * The SpotLight has a narrowing effect based on the narrowBeam value.
     *
     * @param point The point for which to calculate the directions of the light beams.
     * @param n     The normal vector at the specified point.
     * @return A list of vectors representing the directions of the light beams that affect the specified point.
     */
    @Override
    public List<Vector> listGetL(Point point,Vector n) {
        return super.listGetL(point,n);
    }

    /**
     * Get the direction of the light beam that affects a specific point.
     * The SpotLight has a narrowing effect based on the narrowBeam value.
     *
     * @param point The point for which to calculate the direction of the light beam.
     * @return The direction vector of the light beam that affects the specified point.
     */
    public Vector getL(Point point) {
        return super.getL(point);
    }
}
