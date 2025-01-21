package utility;

public class ScaleCalculator {
	  private final double translateX;
	  private final double translateY;
	  private final double scaleX;
	  private final double scaleY;

	  private ScaleCalculator(Builder builder) {
	    this.translateX = builder.translateX;
	    this.translateY = builder.translateY;
	    this.scaleX = builder.scaleX;
	    this.scaleY = builder.scaleY;
	  }

	  public static Builder builder() {
	    return new Builder();
	  }

	  public double getTranslateX() {
	    return translateX;
	  }

	  public double getTranslateY() {
	    return translateY;
	  }

	  public double getScaleX() {
	    return scaleX;
	  }

	  public double getScaleY() {
	    return scaleY;
	  }

	  public static class Builder {

	    private double translateX;
	    private double translateY;
	    private double scaleX;
	    private double scaleY;

	    public Builder translateX(double translateX) {
	      this.translateX = translateX;
	      return this;
	    }

	    public Builder translateY(double translateY) {
	      this.translateY = translateY;
	      return this;
	    }

	    public Builder scaleX(double scaleX) {
	      this.scaleX = scaleX;
	      return this;
	    }

	    public Builder scaleY(double scaleY) {
	      this.scaleY = scaleY;
	      return this;
	    }

	    public ScaleCalculator build() {
	      return new ScaleCalculator(this);
	    }
	  }
}
