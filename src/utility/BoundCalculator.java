package utility;

public class BoundCalculator {
	
	  private final double boundX;
	  private final double boundY;
	  private final double boundWidth;
	  private final double boundHeight;
	  private final double xFactor;
	  private final double yFactor;

	  private BoundCalculator(Builder builder) {
	    this.boundX = builder.boundX;
	    this.boundY = builder.boundY;
	    this.boundWidth = builder.boundWidth;
	    this.boundHeight = builder.boundHeight;
	    this.xFactor = builder.xFactor;
	    this.yFactor = builder.yFactor;
	  }

	  public static Builder builder() {
	    return new Builder();
	  }

	  public double getBoundX() {
	    return boundX;
	  }

	  public double getBoundY() {
	    return boundY;
	  }

	  public double getBoundWidth() {
	    return boundWidth;
	  }

	  public double getBoundHeight() {
	    return boundHeight;
	  }

	  public double getXFactor() {
	    return xFactor;
	  }

	  public double getYFactor() {
	    return yFactor;
	  }

	  public static class Builder {

	    private double boundX;
	    private double boundY;
	    private double boundWidth;
	    private double boundHeight;
	    private double xFactor;
	    private double yFactor;

	    public Builder boundX(double boundX) {
	      this.boundX = boundX;
	      return this;
	    }

	    public Builder boundY(double boundY) {
	      this.boundY = boundY;
	      return this;
	    }

	    public Builder boundWidth(double boundWidth) {
	      this.boundWidth = boundWidth;
	      return this;
	    }

	    public Builder boundHeight(double boundHeight) {
	      this.boundHeight = boundHeight;
	      return this;
	    }

	    public Builder xFactor(double xFactor) {
	      this.xFactor = xFactor;
	      return this;
	    }

	    public Builder yFactor(double yFactor) {
	      this.yFactor = yFactor;
	      return this;
	    }

	    public BoundCalculator build() {
	      return new BoundCalculator(this);
	    }
	  }

}
